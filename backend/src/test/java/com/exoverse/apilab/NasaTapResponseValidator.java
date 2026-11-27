package com.exoverse.apilab;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;

/**
 * Validates a NASA Exoplanet Archive TAP JSON response (format=json) before it
 * is accepted as a fixture. Test-scope only: this is not a production adapter.
 *
 * The body is never modified; this class only inspects it.
 */
public final class NasaTapResponseValidator {

    private static final JsonMapper MAPPER = JsonMapper.builder().build();

    private NasaTapResponseValidator() {
    }

    /** Thrown when a response is empty, malformed, or lacks the expected target. */
    public static class InvalidTapResponseException extends RuntimeException {
        public InvalidTapResponseException(String message) {
            super(message);
        }

        public InvalidTapResponseException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    /**
     * @return the number of records in the response
     * @throws InvalidTapResponseException if the body is blank, not valid JSON,
     *         not a non-empty array of objects, or has no record whose
     *         {@code pl_name} equals {@code expectedPlanetName}
     */
    public static int validate(String body, String expectedPlanetName) {
        if (body == null || body.isBlank()) {
            throw new InvalidTapResponseException("Response body is empty.");
        }

        JsonNode root;
        try {
            root = MAPPER.readTree(body);
        } catch (JacksonException e) {
            throw new InvalidTapResponseException("Response is not valid JSON: " + e.getOriginalMessage(), e);
        }

        if (root == null || !root.isArray()) {
            throw new InvalidTapResponseException("Expected a JSON array of records at the top level.");
        }
        if (root.isEmpty()) {
            throw new InvalidTapResponseException("Response is an empty JSON array (no records returned).");
        }

        boolean targetFound = false;
        for (JsonNode record : root) {
            if (!record.isObject()) {
                throw new InvalidTapResponseException("Array contains a non-object element.");
            }
            JsonNode name = record.get("pl_name");
            if (name != null && name.isString() && expectedPlanetName.equals(name.asString())) {
                targetFound = true;
            }
        }
        if (!targetFound) {
            throw new InvalidTapResponseException(
                    "No record with pl_name = '" + expectedPlanetName + "' found in " + root.size() + " record(s).");
        }
        return root.size();
    }
}
