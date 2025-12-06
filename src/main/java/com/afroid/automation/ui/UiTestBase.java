package com.afroid.automation.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public abstract class UiTestBase {

    protected WebDriver driver;

    @BeforeEach
    public void setUpWebDriver() {
        driver = WebDriverFactory.createWebDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDownWebDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
