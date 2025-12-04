package com.afroid.automation.ui;

import com.afroid.automation.config.TestConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public final class WebDriverFactory {

    private WebDriverFactory() {
        // utility class; no instances
    }

    public static WebDriver createChromeDriver() {
        // This sets up the ChromeDriver binary automatically
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        if (TestConfig.isHeadless()) {
            // new headless mode in recent Chrome
            options.addArguments("--headless=new");
        }

        // Common flags that help in CI containers
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        return new ChromeDriver(options);
    }
}
