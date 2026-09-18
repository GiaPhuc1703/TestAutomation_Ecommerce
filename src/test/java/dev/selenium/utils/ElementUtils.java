package dev.selenium.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ElementUtils {
    private WebDriverWait wait;
    private WebDriver driver;

    public ElementUtils(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.driver = driver;
    }

    public void click(By locator) {
        WebElement element = waitForElementVisible(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public WebElement waitForElementVisible(By locator) {
        return this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void enterText(By locator, String text) {
        WebElement element = waitForElementVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    public boolean isElementDisplayed(By locator) {
        return waitForElementVisible(locator).isDisplayed();
    }

    public double parsePrice(String priceText) {
        String preClean = priceText.replace("Rs.", "");
        String cleanPrice = preClean.replaceAll("[^0-9.]", "");
        return Double.parseDouble(cleanPrice);
    }

    public List<WebElement> getListOfElements(By locator) {
        return driver.findElements(locator);
    }

    public String getElementText(WebElement element, By locator) {
        return element.findElement(locator).getText();
    }

}
