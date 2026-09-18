package dev.selenium.configs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver initDriver(String browser) {
        if (browser == null || browser.trim().isEmpty()) {
            browser = System.getProperty("browser", "chrome");
        }

        WebDriver driver;
        switch (browser.toLowerCase().trim()) {
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--width=1920", "--height=1080");
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "firefox-headless":
                FirefoxOptions firefoxHeadlessOptions = new FirefoxOptions();
                firefoxHeadlessOptions.addArguments("-headless");
                driver = new FirefoxDriver(firefoxHeadlessOptions);
                break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                driver = new EdgeDriver(edgeOptions);
                break;

            case "safari":
                driver = new SafariDriver();
                driver.manage().window().maximize();
                break;

            case "chrome-headless":
                ChromeOptions headlessOptions = new ChromeOptions();
                headlessOptions.addArguments("--headless=new");
                headlessOptions.addArguments("--window-size=1920,1080");
                headlessOptions.addArguments("--disable-gpu");
                headlessOptions.addArguments("--no-sandbox");
                headlessOptions.addArguments("--disable-dev-shm-usage");
                driver = new ChromeDriver(headlessOptions);
                break;

            case "chrome":
            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(chromeOptions);
                break;
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        driverThreadLocal.set(driver);
        return getDriver();
    }

    public static WebDriver initDriver() {
        return initDriver(System.getProperty("browser", "chrome"));
    }

    public static WebDriver getDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver == null) {
            throw new IllegalStateException(
                    "WebDriver has not been initialized for the current thread. Call initDriver() first.");
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
