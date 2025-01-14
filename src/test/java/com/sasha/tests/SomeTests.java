package com.sasha.tests;

import com.sasha.data.*;
import com.sasha.pages.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SomeTests extends TestRunner {

    private LoginPage loginPage;

    @BeforeEach
    void setupPageObjects() {
        loginPage = new LoginPage(driverWrapper);
    }

    private static Stream<Credentials> allUsers() {
        return Stream.of(Credentials.values());
    }

    @ParameterizedTest
    @MethodSource("allUsers")
    void testLogin(Credentials user) {
        boolean isLoginSuccessful = loginPage.login(user).isLoginSuccessful();
        if (user.isLoginExpectedToSucceed()) {
            assertTrue(isLoginSuccessful, "Login failed for user: " + user.getUsername());
        } else {
            assertEquals(user.getExpectedErrorMessage(), loginPage.getErrorMessage());
        }
    }

    private static Stream<Arguments> products() {
        return Stream.of(Products.values()).map(Arguments::of);
    }

    @Test
    void testBurgerMenuOptions() {
        BurgerMenu menu = login().goToBurgerMenu();
        assertTrue(menu.isOptionVisibleById("about_sidebar_link"));
        assertTrue(menu.isOptionVisibleById("inventory_sidebar_link"));
        assertTrue(menu.isOptionVisibleById("logout_sidebar_link"));
        assertTrue(menu.isOptionVisibleById("reset_sidebar_link"));
    }

    @Test
    void testLogout() {
        String loginLogoText = login()
                .goToBurgerMenu()
                .logout()
                .getLoginLogoText();
        assertEquals("Swag Labs", loginLogoText);
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

    @ParameterizedTest
    @CsvSource({
            "'', Doe, 12345, Error: First Name is required",
            "John, '', 12345, Error: Last Name is required",
            "John, Doe, '', Error: Postal Code is required"
    })
    void testCheckoutWithMissingFields(String firstName, String lastName, String postalCode, String expectedErrorMessage) {
        Products product = Products.DEFAULT_PRODUCT;
        CheckoutStepTwoPage checkoutStepTwoPage = login()
                .addToCart(product.getName())
                .goToCart()
                .goToCheckoutPage()
                .proceedToNextStep(firstName, lastName, postalCode);
        assertEquals(expectedErrorMessage, checkoutStepTwoPage.getErrorMessageText());
    }

    @Test
    void testSuccessfulCheckout() {
        Products product = Products.DEFAULT_PRODUCT;
        String orderConfirmationText = login()
                .addToCart(product.getName())
                .goToCart()
                .goToCheckoutPage()
                .proceedToNextStep("name", "Last name", "test")
                .completeCheckout()
                .getOrderConfirmationText();
        assertEquals("Your order has been dispatched, and will arrive just as fast as the pony can get there!", orderConfirmationText);
    }

    @ParameterizedTest
    @MethodSource("sortOptionsAndExpectedProducts")
    void testSortOption(SortOptions option, Products expectedProduct) {
        String firstProduct = login()
                .setSortDropdownOption(option.getValue())
                .getFirstProduct();
        assertEquals(expectedProduct.getName(), firstProduct);
    }

    private static Stream<Arguments> sortOptionsAndExpectedProducts() {
        return Stream.of(
                Arguments.of(SortOptions.A_TO_Z, Products.DEFAULT_PRODUCT),
                Arguments.of(SortOptions.Z_TO_A, Products.LAST_PRODUCT),
                Arguments.of(SortOptions.HIGH_TO_LOW, Products.MOST_EXPENSIVE_PRODUCT),
                Arguments.of(SortOptions.LOW_TO_HIGH, Products.CHEAPEST_PRODUCT)
        );
    }

    private ProductPage login() {
        Credentials user = Credentials.STANDARD_USER;
        return loginPage.login(user);
    }
}
