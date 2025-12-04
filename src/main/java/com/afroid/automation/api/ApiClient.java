package com.afroid.automation.api;

import com.afroid.automation.config.TestConfig;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.time.Duration;

import static io.restassured.config.HttpClientConfig.httpClientConfig;

public class ApiClient {

    private final RequestSpecification requestSpec;

    /**
     * Default constructor uses TestConfig's API base URL and timeout.
     */
    public ApiClient() {
        this(TestConfig.getApiBaseUrl(), Duration.ofSeconds(TestConfig.getDefaultTimeoutSeconds()));
    }

    /**
     * Override base URI but keep default timeout from TestConfig.
     */
    public ApiClient(String baseUri) {
        this(baseUri, Duration.ofSeconds(TestConfig.getDefaultTimeoutSeconds()));
    }

    /**
     * Fully configurable base URI + timeout.
     */
    public ApiClient(String baseUri, Duration timeout) {
        this.requestSpec = new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setRelaxedHTTPSValidation()
                .setAccept("application/json")
                .setContentType("application/json")
                .build();

        // Configure basic timeouts globally for RestAssured
        RestAssured.config = RestAssured.config()
                .httpClient(
                        httpClientConfig()
                                .setParam("http.connection.timeout", (int) timeout.toMillis())
                                .setParam("http.socket.timeout", (int) timeout.toMillis())
                );
    }

    /**
     * Simple GET helper: logs request + response and returns the raw Response.
     */
    public Response get(String path) {
        return RestAssured
                .given()
                .spec(requestSpec)
                .log().all()
                .get(path)
                .then()
                .log().all()
                .extract()
                .response();
    }

    // Later we will add:
    // public Response post(String path, Object body) { ... }
    // public Response put(String path, Object body) { ... }
    // public Response delete(String path) { ... }
}
