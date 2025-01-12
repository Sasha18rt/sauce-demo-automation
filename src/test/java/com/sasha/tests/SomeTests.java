package com.sasha.tests;

import com.sasha.data.Credentials;
import com.sasha.data.Products;
import com.sasha.data.SortOptions;
import com.sasha.pages.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SomeTests {

    private WebDriver driver;
    private LoginPage loginPage;
    private JavascriptExecutor js;
    @BeforeAll
    void setupDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver; // Ініціалізація тут

        loginPage = new LoginPage(driver);

    }

    @BeforeEach
    void navigateToSite() {
        driver.get("https://www.saucedemo.com/");
    }
    private static Stream<Credentials> allUsers() {
        return Stream.of(Credentials.values());}

    @ParameterizedTest
    @MethodSource("allUsers")
    void testLogin(Credentials user) {

        boolean isLoginSuccessful = loginPage.login(user)
                .isLoginSuccessful();

        if (user.isLoginExpectedToSucceed()) {
            assertTrue(isLoginSuccessful, "Login failed for user: " + user.getUsername());
        } else {
            assertEquals(user.getExpectedErrorMessage(), loginPage.getErrorMessage());
        }
    }

    private static Stream<Arguments> products() {
        return Stream.of(Products.values()).map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource("products")
    void testAddProductToTheCart(Products product) {


        String firstItem = login()
                .addToCart(product.getName())
                .goToCart()
                .getFirstItemName();

        assertEquals(firstItem, product.getName());
    }

    @ParameterizedTest
    @MethodSource("products")
    void testRemoveProductFromTheCart(Products product) {

        Integer itemsCount = login()
                .addToCart(product.getName())
                .goToCart()
                .removeItemFromCart(product.getName())
                .getCartItemCount();

        assertEquals(0, itemsCount);
    }

    @Test
    void testSuccessfulCheckout(){

        Products product = Products.DEFAULT_PRODUCT;
        String orderConfirmationText = login()
                .addToCart(product.getName())
                .goToCart()
                .goToCheckoutPage()
                .proceedToNextStep("name", "Last name","test")
                .CompleteCheckout()
                .getOrderConfirmationText();
        assertEquals("Your order has been dispatched, and will arrive just as fast as the pony can get there!", orderConfirmationText);
    }

    @Test
    void testSortOptionAtoZ(){
        Products product = Products.DEFAULT_PRODUCT;
        SortOptions option = SortOptions.A_TO_Z;

        String firstProduct = login()
                .setSortDropdownOption(option.getValue())
                .getFirstProduct();

        assertEquals(firstProduct, product.getName());
    }
    @Test
    void testSortOptionZtoA(){
        Products product = Products.LAST_PRODUCT;
        SortOptions option = SortOptions.Z_TO_A;

        String firstProduct = login()
                .setSortDropdownOption(option.getValue())
                .getFirstProduct();

        assertEquals(firstProduct, product.getName());
    }
    @Test
    void testSortOptionHighToLow(){
        Products product = Products.MOST_EXPENSIVE_PRODUCT;
        SortOptions option = SortOptions.HIGH_TO_LOW;

        String firstProduct = login()
                .setSortDropdownOption(option.getValue())
                .getFirstProduct();

        assertEquals(firstProduct, product.getName());
    }
    @Test
    void testSortOptionLowToHigh(){
        Products product = Products.CHEAPEST_PRODUCT;
        SortOptions option = SortOptions.LOW_TO_HIGH;

        String firstProduct = login()
                .setSortDropdownOption(option.getValue())
                .getFirstProduct();

        assertEquals(firstProduct, product.getName());
    }

    private ProductPage login(){
        Credentials user = Credentials.STANDARD_USER;
        return loginPage.login(user);

    }
    @AfterEach
    void cleanUp() {
        driver.manage().deleteAllCookies();
        js.executeScript("localStorage.clear();");
        js.executeScript("sessionStorage.clear();");

    }
    @AfterAll
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
