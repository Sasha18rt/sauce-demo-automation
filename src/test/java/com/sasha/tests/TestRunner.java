package com.sasha.tests;

import com.sasha.utils.DriverWrapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class TestRunner {

    protected DriverWrapper driverWrapper;
    protected JavascriptExecutor js;

    @BeforeAll
    void setupDriver() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driverWrapper = new DriverWrapper(driver, 10);
        js = (JavascriptExecutor) driverWrapper.getDriver();
    }

    @BeforeEach
    void navigateToSite() {
        driverWrapper.navigateTo("https://www.saucedemo.com/");
    }

    @AfterEach
    void cleanUp() {
        driverWrapper.clearBrowserData();
    }

    @AfterAll
    void tearDown() {
        driverWrapper.quitDriver();
    }
}
