package com.sasha.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;



import java.io.File;
import java.time.Duration;
import java.util.List;

public class DriverWrapper {

    private final ThreadLocal<WebDriver> threadDriver;
    private final ThreadLocal<Wait<WebDriver>> threadWait;
    private final Actions actions;
    private final JavascriptExecutor js;

    public DriverWrapper(WebDriver driver, int sec) {
        threadDriver = new ThreadLocal<>();
        threadDriver.set(driver);
        threadWait = new ThreadLocal<>();
        threadWait.set(new WebDriverWait(driver, Duration.ofSeconds(sec)));
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
    }

    public void scrollToElement(WebElement element) {
        threadWait.get().until(ExpectedConditions.visibilityOf(element));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }

    public void waitAndType(WebElement element, String text) {
        threadWait.get().until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    public WebElement visibilityElement(By locator) {
        return threadWait.get().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement clickableElement(By locator) {
        return threadWait.get().until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void takeScreenshot(String filePath) {
        try {
            File screenshot = ((TakesScreenshot) threadDriver.get()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(filePath));
        } catch (Exception e) {
            throw new RuntimeException("Failed to take screenshot: " + e.getMessage());
        }
    }

    public void hoverOverElement(WebElement element) {
        actions.moveToElement(element).perform();
    }

    public void selectFromDropdown(WebElement dropdown, List<WebElement> options, String value) {
        openDropdown(dropdown);
        for (WebElement option : options) {
            if (option.getText().equals(value)) {
                option.click();
                return;
            }
        }
        throw new RuntimeException("Option with text '" + value + "' not found in dropdown.");
    }

    public void openDropdown(WebElement dropdownToggle) {
        threadWait.get().until(ExpectedConditions.elementToBeClickable(dropdownToggle)).click();
    }

    public void clearBrowserData() {
        threadDriver.get().manage().deleteAllCookies();
        js.executeScript("localStorage.clear();");
        js.executeScript("sessionStorage.clear();");
    }

    public void quitDriver() {
        threadDriver.get().quit();
        threadDriver.remove();
    }

    public WebDriver getDriver() {
        return threadDriver.get();
    }

    public void navigateTo(String url) {
        threadDriver.get().navigate().to(url);
    }

    public List<WebElement> findElements(By locator) {
        return threadDriver.get().findElements(locator);
    }

}
