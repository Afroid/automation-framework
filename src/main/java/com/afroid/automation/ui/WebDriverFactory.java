package com.afroid.automation.ui;

import com.afroid.automation.config.TestConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class WebDriverFactory {

    private WebDriverFactory() {
        // utility class; no instances
    }

    public static WebDriver createWebDriver() {
        String browser = TestConfig.getBrowser();
        boolean headless = TestConfig.isHeadless();

        switch (browser) {
            case "firefox":
                return createFirefoxDriver(headless);
            case "edge":
                return createEdgeDriver(headless);
            case "chrome":
            default:
                return createChromeDriver(headless);
        }
    }

    // Chrome
    private static WebDriver createChromeDriver(boolean headless) {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        if (headless) {
            options.addArguments("--headless=new");
        }

        // Common flags that help in CI containers
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        return new ChromeDriver(options);
    }

    // Firefox
    private static WebDriver createFirefoxDriver(boolean headless) {
        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions options = new FirefoxOptions();
        if (headless) {
            options.addArguments("-headless");
        }

        return new FirefoxDriver(options);
    }

    // Edge
    private static WebDriver createEdgeDriver(boolean headless) {
        WebDriverManager.edgedriver().setup();

        EdgeOptions options = new EdgeOptions();

        if (headless) {
            // Edge is Chromium-based, so same headless flag
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        }

        return new EdgeDriver(options);
    }
}
