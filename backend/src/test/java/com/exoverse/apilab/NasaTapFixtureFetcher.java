package com.exoverse.apilab;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.time.Duration;
import java.time.Instant;
import java.util.HexFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

/**
 * MANUAL utility (not a JUnit test, never run by Maven): fetches the TRAPPIST-1 e
 * row(s) from the NASA Exoplanet Archive TAP service once and saves them, with
 * provenance metadata, as a test fixture.
 *
 * Run from the backend directory (PowerShell), using the compiled classes and the Maven classpath:
 * <pre>
 * .\mvnw.cmd --batch-mode test-compile dependency:build-classpath "-Dmdep.outputFile=target\cp.txt"
 * java -cp "target\test-classes;target\classes;$(Get-Content target\cp.txt)" com.exoverse.apilab.NasaTapFixtureFetcher [--overwrite]
 * </pre>
 * Existing fixtures are never overwritten unless {@code --overwrite} is passed.
 */
public final class NasaTapFixtureFetcher {

    static final String SOURCE = "NASA Exoplanet Archive";
    static final String TAP_ENDPOINT = "https://exoplanetarchive.ipac.caltech.edu/TAP/sync";
    static final String PLANET_NAME = "TRAPPIST-1 e";
    static final String QUERY = "select * from ps where pl_name = 'TRAPPIST-1 e'";

    static final String FIXTURE_FILE = "trappist-1e-ps.json";
    static final String METADATA_FILE = "trappist-1e-ps-metadata.json";

    private static final Duration CONNECT_TIMEOUT = Duration.ofSeconds(10);
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(60);

    private NasaTapFixtureFetcher() {
    }

    public static void main(String[] args) throws Exception {
        boolean overwrite = false;
        for (String arg : args) {
            if ("--overwrite".equals(arg)) {
                overwrite = true;
            } else {
                throw new IllegalArgumentException("Unknown argument: " + arg + " (only --overwrite is supported)");
            }
        }

        Path outDir = resolveBackendDir().resolve("src/test/resources/fixtures/nasa");
        Path fixture = outDir.resolve(FIXTURE_FILE);
        Path metadata = outDir.resolve(METADATA_FILE);

        if (!overwrite && (Files.exists(fixture) || Files.exists(metadata))) {
            throw new IllegalStateException(
                    "Fixture already exists in " + outDir + ". Re-run with --overwrite to replace it.");
        }

        String requestUrl = TAP_ENDPOINT
                + "?query=" + URLEncoder.encode(QUERY, StandardCharsets.UTF_8)
                + "&format=json";

        System.out.println("Query:       " + QUERY);
        System.out.println("Request URL: " + requestUrl);

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(CONNECT_TIMEOUT)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
        HttpRequest request = HttpRequest.newBuilder(URI.create(requestUrl))
                .timeout(REQUEST_TIMEOUT)
                .header("Accept", "application/json")
                .GET()
                .build();

        Instant retrievedAt = Instant.now();
        HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());

        if (response.statusCode() != 200) {
            throw new IOException("NASA TAP request failed. HTTP status: " + response.statusCode());
        }

        byte[] bodyBytes = response.body();
        String body = decodeStrictUtf8(bodyBytes);
        int recordCount = NasaTapResponseValidator.validate(body, PLANET_NAME);

        String sha256 = HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest(bodyBytes));

        Map<String, Object> meta = new LinkedHashMap<>();
        meta.put("source", SOURCE);
        meta.put("tapEndpoint", TAP_ENDPOINT);
        meta.put("adqlQuery", QUERY);
        meta.put("requestUrl", requestUrl);
        meta.put("retrievedAtUtc", retrievedAt.toString());
        meta.put("httpStatus", response.statusCode());
        meta.put("responseContentType", response.headers().firstValue("Content-Type").orElse(null));
        meta.put("fixtureFile", FIXTURE_FILE);
        meta.put("recordCount", recordCount);
        meta.put("responseSizeBytes", bodyBytes.length);
        meta.put("sha256", sha256);

        String metaJson = JsonMapper.builder().enable(SerializationFeature.INDENT_OUTPUT).build()
                .writeValueAsString(meta) + System.lineSeparator();

        Files.createDirectories(outDir);
        writeBothAtomically(outDir, fixture, bodyBytes, metadata, metaJson.getBytes(StandardCharsets.UTF_8));

        System.out.println("HTTP status:  " + response.statusCode());
        System.out.println("Content-Type: " + meta.get("responseContentType"));
        System.out.println("Records:      " + recordCount);
        System.out.println("Retrieved:    " + retrievedAt);
        System.out.println("SHA-256:      " + sha256);
        System.out.println("Saved:        " + fixture);
        System.out.println("Saved:        " + metadata);
    }

    /** Finds backend/ whether launched from backend/ or from the repository root. */
    static Path resolveBackendDir() {
        Path cwd = Path.of("").toAbsolutePath();
        if (isBackendDir(cwd)) {
            return cwd;
        }
        Path child = cwd.resolve("backend");
        if (isBackendDir(child)) {
            return child;
        }
        throw new IllegalStateException(
                "Run from the backend directory (or repo root). Could not find backend pom.xml from " + cwd);
    }

    private static boolean isBackendDir(Path dir) {
        Path pom = dir.resolve("pom.xml");
        try {
            return Files.isRegularFile(pom) && Files.readString(pom).contains("<artifactId>exoverse-backend</artifactId>");
        } catch (IOException e) {
            return false;
        }
    }

    /** Decodes without replacement characters so corrupt bytes fail instead of being altered. */
    private static String decodeStrictUtf8(byte[] bytes) throws CharacterCodingException {
        return StandardCharsets.UTF_8.newDecoder()
                .onMalformedInput(CodingErrorAction.REPORT)
                .onUnmappableCharacter(CodingErrorAction.REPORT)
                .decode(ByteBuffer.wrap(bytes))
                .toString();
    }

    /** Writes both files to temp files first, then moves them into place. */
    private static void writeBothAtomically(Path dir, Path fixture, byte[] fixtureBytes,
                                            Path metadata, byte[] metadataBytes) throws IOException {
        Path tmpFixture = Files.createTempFile(dir, "fixture-", ".tmp");
        Path tmpMetadata = Files.createTempFile(dir, "metadata-", ".tmp");
        try {
            Files.write(tmpFixture, fixtureBytes);
            Files.write(tmpMetadata, metadataBytes);
            Files.move(tmpFixture, fixture, StandardCopyOption.REPLACE_EXISTING);
            Files.move(tmpMetadata, metadata, StandardCopyOption.REPLACE_EXISTING);
        } finally {
            Files.deleteIfExists(tmpFixture);
            Files.deleteIfExists(tmpMetadata);
        }
    }
}
