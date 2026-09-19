package dev.selenium.configs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver initDriver() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");

        String seleniumUrl = System.getenv("SELENIUM_URL");

        try {
            WebDriver driver;

            if (seleniumUrl != null && !seleniumUrl.isEmpty()) {
                // Jenkins + Selenium Docker
                driver = new RemoteWebDriver(URI.create(seleniumUrl).toURL(), options);
            } else {
                // Local machine
                driver = new ChromeDriver(options);
            }

            driver.manage().timeouts()
                    .implicitlyWait(Duration.ofSeconds(10));

            driver.manage().timeouts()
                    .pageLoadTimeout(Duration.ofSeconds(30));

            driverThreadLocal.set(driver);

            return driver;

        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Selenium URL: " + seleniumUrl, e);
        }
    }

    public static WebDriver getDriver() {
        WebDriver driver = driverThreadLocal.get();

        if (driver == null) {
            throw new IllegalStateException(
                    "WebDriver has not been initialized.");
        }

        return driver;
    }

    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();

        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }
}