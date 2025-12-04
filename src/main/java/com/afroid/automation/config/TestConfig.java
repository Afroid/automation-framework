package com.afroid.automation.config;

public class TestConfig {

    public static String getBaseUrl() {
        // For UI tests (i75league)
        return System.getProperty("baseUrl", "https://www.i75league.com");
    }

    public static String getApiBaseUrl() {
        // For API tests (jsonplaceholder)
        return System.getProperty("apiBaseUrl", "https://jsonplaceholder.typicode.com");
    }

    public static boolean isHeadless() {
        // Run browser in headless mode by default (good for CI)
        return Boolean.parseBoolean(System.getProperty("headless", "true"));
    }

    public static int getDefaultTimeoutSeconds() {
        return Integer.parseInt(System.getProperty("timeoutSeconds", "10"));
    }

    private TestConfig() {
        // prevent instantiation
    }
}
