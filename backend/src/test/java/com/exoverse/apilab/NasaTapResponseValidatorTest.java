package com.exoverse.apilab;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

import org.junit.jupiter.api.Test;

import com.exoverse.apilab.NasaTapResponseValidator.InvalidTapResponseException;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;

/** Local resources only; never touches the network. */
class NasaTapResponseValidatorTest {

    private static final String TARGET = "TRAPPIST-1 e";
    private static final String DIR = "/fixtures/nasa/";

    private static byte[] readBytes(String name) throws IOException {
        try (InputStream in = NasaTapResponseValidatorTest.class.getResourceAsStream(name)) {
            assertThat(in).as("test resource %s must exist on the classpath", name).isNotNull();
            return in.readAllBytes();
        }
    }

    private static String read(String name) throws IOException {
        return new String(readBytes(name), StandardCharsets.UTF_8);
    }

    @Test
    void acceptsValidResponseContainingTarget() throws IOException {
        assertThat(NasaTapResponseValidator.validate(read(DIR + "validator/synthetic-valid.json"), TARGET))
                .isEqualTo(2);
    }

    @Test
    void rejectsEmptyJsonArray() throws IOException {
        assertThatThrownBy(() -> NasaTapResponseValidator.validate(
                read(DIR + "validator/synthetic-empty-array.json"), TARGET))
                .isInstanceOf(InvalidTapResponseException.class)
                .hasMessageContaining("empty JSON array");
    }

    @Test
    void rejectsMalformedJson() throws IOException {
        assertThatThrownBy(() -> NasaTapResponseValidator.validate(
                read(DIR + "validator/synthetic-malformed.json"), TARGET))
                .isInstanceOf(InvalidTapResponseException.class)
                .hasMessageContaining("not valid JSON");
    }

    @Test
    void rejectsResponseWithoutExpectedTarget() throws IOException {
        assertThatThrownBy(() -> NasaTapResponseValidator.validate(
                read(DIR + "validator/synthetic-other-planet.json"), TARGET))
                .isInstanceOf(InvalidTapResponseException.class)
                .hasMessageContaining("No record with pl_name");
    }

    @Test
    void rejectsBlankBodyAndNonArrayRoot() {
        assertThatThrownBy(() -> NasaTapResponseValidator.validate("  ", TARGET))
                .isInstanceOf(InvalidTapResponseException.class)
                .hasMessageContaining("empty");
        assertThatThrownBy(() -> NasaTapResponseValidator.validate("{\"pl_name\":\"TRAPPIST-1 e\"}", TARGET))
                .isInstanceOf(InvalidTapResponseException.class)
                .hasMessageContaining("array");
    }

    /**
     * Checks the committed NASA fixture against its own metadata. Both resources are
     * loaded from the classpath, so this works from any launch directory, and the test
     * fails (never skips) if either is missing. See NasaTapFixtureFetcher.
     */
    @Test
    void committedFixtureMatchesItsMetadata() throws IOException, NoSuchAlgorithmException {
        byte[] bytes = readBytes(DIR + "trappist-1e-ps.json");
        JsonNode meta = JsonMapper.builder().build()
                .readTree(new String(readBytes(DIR + "trappist-1e-ps-metadata.json"), StandardCharsets.UTF_8));

        assertThat(HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest(bytes)))
                .isEqualTo(meta.get("sha256").asString());
        assertThat(NasaTapResponseValidator.validate(new String(bytes, StandardCharsets.UTF_8), TARGET))
                .isEqualTo(meta.get("recordCount").asInt());
        assertThat(meta.get("source").asString()).isEqualTo("NASA Exoplanet Archive");
        assertThat(meta.get("httpStatus").asInt()).isEqualTo(200);
    }
}
