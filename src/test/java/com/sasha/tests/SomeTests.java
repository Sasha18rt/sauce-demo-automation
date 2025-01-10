package com.sasha.tests;

import com.sasha.data.Products;
import com.sasha.pages.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SomeTests {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeAll
    void setupDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);

    }

    @BeforeEach
    void navigateToSite() {
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    void testValidLogin() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        assertTrue(loginPage.isLoginSuccessful(), "Login was not successful.");
    }

    @Test
    void testInvalidLogin() {
        loginPage.enterUsername("invalid_user");
        loginPage.enterPassword("wrong_password");
        loginPage.clickLogin();

        assertEquals("Username and password do not match any user in this service",
                loginPage.getErrorMessage());
    }
    private static Stream<Arguments> products() {
        return Stream.of(
                Arguments.of(Products.DEFAULT_PRODUCT)
        );
    }

    @ParameterizedTest
    @MethodSource("products")
    void testAddProductToTheCart(Products products) {
        Products product = Products.DEFAULT_PRODUCT;

        String firstItem = loginPage.login("standard_user", "secret_sauce")
                .addToCart(products.getName())
                .goToCart()
                .getFirstItemName();

        assertEquals(firstItem, products.getName());
    }

    @AfterAll
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
