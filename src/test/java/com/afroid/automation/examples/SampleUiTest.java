package com.afroid.automation.examples;

import com.afroid.automation.ui.UiTestBase;
import com.afroid.automation.config.TestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SampleUiTest extends UiTestBase {

    @Test
    @DisplayName("Open baseUrl and verify the current URL contains it")
    void openBaseUrl_andVerifyUrl() {
        String baseUrl = TestConfig.getBaseUrl();
        driver.get(baseUrl);

        String currentUrl = driver.getCurrentUrl();
        // For a generic framework, just assert we landed on the same base
        assertTrue(
                currentUrl.startsWith(baseUrl),
                "Expected current URL to start with baseUrl. baseUrl=" + baseUrl + ", currentUrl=" + currentUrl
        );
    }
}
