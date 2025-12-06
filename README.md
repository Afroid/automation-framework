# automation-framework

A small Java-based testing framework that exposes reusable UI/API testing building blocks for other projects to consume as a Maven dependency.

This is **one example** of how to structure a shared automation library. For most front-end E2E work on the I75 League site, the preferred approach is to use the JS-based stacks in the main app:

- Playwright / WDIO-style E2E: https://github.com/Afroid/i75-recap-site/tree/main/tests/e2e
- Cypress suite: https://github.com/Afroid/i75-recap-site/tree/main/cypress

This repo is more about demonstrating a **Java + Selenium + JUnit** style framework that other teams could wire into their own test projects.

---

## Published artifact

- **Group ID:** `com.afroid`
- **Artifact ID:** `automation-framework`
- **Latest version:** `0.2.0`
- **Registry:** GitHub Packages

To consume from another Maven project:

```xml
<dependency>
  <groupId>com.afroid</groupId>
  <artifactId>automation-framework</artifactId>
  <version>0.2.0</version>
</dependency>
```

Note: You’ll need your Maven settings.xml configured with credentials for GitHub Packages.

## Key defaults (TestConfig)

These are the main values consumers get out of the box, all overrideable via -D system properties:

- ## baseUrl
  - Default: https://www.i75league.com
  - Override: -DbaseUrl=http://localhost:3000

- ## apiBaseUrl
  - Default: https://jsonplaceholder.typicode.com
  - Override: -DapiBaseUrl=https://your-api-url

- ## headless
  - Default: true
  - Override: -Dheadless=false (show browser)

- ## browser
  - Default: chrome
  - Supported: chrome, firefox, edge
  - Override: -Dbrowser=firefox

These are exposed via com.afroid.automation.config.TestConfig.

## What the framework provides

The important pieces consumers typically use:

- TestConfig – central configuration for URLs, timeouts, headless, browser choice
- WebDriverFactory – creates a WebDriver for Chrome / Firefox / Edge (respects headless + browser)
- UiTestBase – JUnit 5 base class that:
  - Creates the driver before each test
  - Maximizes the window
  - Quits the driver after each test

There is also an internal SampleUiTest in this repo that serves as a usage example and sanity check, but it is **not** intended to be run from consumer projects.

## Local sanity check

To verify the framework builds and the sample test runs:
```
mvn clean verify
```

- Runs the framework’s sample UI test using headless Chrome by default
- To see the browser locally:

```
mvn clean verify -Dheadless=false
```

## Tech stack
- Java 17
- Maven
- JUnit 5
- Selenium WebDriver 4.x
- WebDriverManager
- RestAssured (for basic API examples)
