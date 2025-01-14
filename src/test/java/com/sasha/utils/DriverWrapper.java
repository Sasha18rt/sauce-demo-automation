package com.sasha.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class DriverWrapper {

    private static final Logger logger = LoggerFactory.getLogger(DriverWrapper.class);

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
        logger.debug("DriverWrapper initialized with {} seconds wait time", sec);
    }

    public void scrollToElement(WebElement element) {
        try {
            threadWait.get().until(ExpectedConditions.visibilityOf(element));
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        } catch (Exception e) {
            logger.error("Failed to scroll to element: {}", e.getMessage(), e);
            throw e;
        }
    }

    public void waitAndType(WebElement element, String text) {
        try {
            threadWait.get().until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            logger.error("Failed to type text into element: {}", e.getMessage(), e);
            throw e;
        }
    }

    public WebElement visibilityElement(By locator) {
        try {
            return threadWait.get().until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            logger.error("Failed to find visible element by locator: {}", e.getMessage(), e);
            throw e;
        }
    }

    public WebElement clickableElement(By locator) {
        try {
            return threadWait.get().until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            logger.error("Failed to find clickable element by locator: {}", e.getMessage(), e);
            throw e;
        }
    }

    public void takeScreenshot(String filePath) {
        try {
            File screenshot = ((TakesScreenshot) threadDriver.get()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(filePath));
        } catch (Exception e) {
            logger.error("Failed to take screenshot: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to take screenshot", e);
        }
    }

    public void hoverOverElement(WebElement element) {
        try {
            actions.moveToElement(element).perform();
        } catch (Exception e) {
            logger.error("Failed to hover over element: {}", e.getMessage(), e);
            throw e;
        }
    }

    public void selectFromDropdown(WebElement dropdown, List<WebElement> options, String value) {
        try {
            openDropdown(dropdown);
            for (WebElement option : options) {
                if (option.getText().equals(value)) {
                    option.click();
                    return;
                }
            }
            throw new RuntimeException("Option with text '" + value + "' not found in dropdown.");
        } catch (Exception e) {
            logger.error("Failed to select value '{}' from dropdown: {}", value, e.getMessage(), e);
            throw e;
        }
    }

    public void openDropdown(WebElement dropdownToggle) {
        try {
            threadWait.get().until(ExpectedConditions.elementToBeClickable(dropdownToggle)).click();
        } catch (Exception e) {
            logger.error("Failed to open dropdown: {}", e.getMessage(), e);
            throw e;
        }
    }

    public void clearBrowserData() {
        try {
            threadDriver.get().manage().deleteAllCookies();
            js.executeScript("localStorage.clear();");
            js.executeScript("sessionStorage.clear();");
        } catch (Exception e) {
            logger.error("Failed to clear browser data: {}", e.getMessage(), e);
            throw e;
        }
    }

    public void quitDriver() {
        try {
            threadDriver.get().quit();
            threadDriver.remove();
        } catch (Exception e) {
            logger.error("Failed to quit WebDriver session: {}", e.getMessage(), e);
            throw e;
        }
    }

    public WebDriver getDriver() {
        return threadDriver.get();
    }

    public void navigateTo(String url) {
        try {
            threadDriver.get().navigate().to(url);
        } catch (Exception e) {
            logger.error("Failed to navigate to URL: {}", e.getMessage(), e);
            throw e;
        }
    }

    public List<WebElement> findElements(By locator) {
        try {
            return threadDriver.get().findElements(locator);
        } catch (Exception e) {
            logger.error("Failed to find elements by locator: {}", e.getMessage(), e);
            throw e;
        }
    }
}
