package com.sasha.pages;

import com.sasha.utils.DriverWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductPage {

    private final DriverWrapper driverWrapper;

    private final By addToCartButton = By.cssSelector(".inventory_item button");
    private final By removeButton = By.id("remove-sauce-labs-backpack");
    private final By goToCartButton = By.cssSelector(".shopping_cart_link");
    private final By appLogo = By.cssSelector(".app_logo");
    private final By burgerMenuButton = By.id("react-burger-menu-btn");
    private final By sortDropdown = By.cssSelector(".product_sort_container");
    private final By productName = By.cssSelector(".inventory_item_name");

    public ProductPage(DriverWrapper driverWrapper) {
        this.driverWrapper = driverWrapper;
    }

    public WebElement getAppLogo() {
        return driverWrapper.visibilityElement(appLogo);
    }

    public void clickAppLogo() {
        driverWrapper.clickableElement(appLogo).click();
    }

    public WebElement getBurgerMenuButton() {
        return driverWrapper.visibilityElement(burgerMenuButton);
    }

    public void clickBurgerMenuButton() {
        driverWrapper.clickableElement(burgerMenuButton).click();
    }

    public WebElement getGoToCartButton() {
        return driverWrapper.clickableElement(goToCartButton);
    }

    public void clickGoToCartButton() {
        getGoToCartButton().click();
    }

    public String getFirstProduct() {
        List<WebElement> products = driverWrapper.findElements(productName);
        return products.isEmpty() ? null : products.get(0).getText();
    }

    public WebElement getSortDropdown() {
        return driverWrapper.visibilityElement(sortDropdown);
    }

    public void clickSortDropdown() {
        getSortDropdown().click();
    }

    public void addToCartByProductName(String productName) {
        WebElement product = driverWrapper.visibilityElement(
                By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']")
        );
        product.findElement(addToCartButton).click();
    }

    public boolean isProductInCart(String productName) {
        try {
            return driverWrapper.visibilityElement(By.xpath("//a[text()='" + productName + "']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isLoginSuccessful() {
        try {
            driverWrapper.visibilityElement(productName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // BUSINESS logic
    public ProductPage addToCart(String productName) {
        addToCartByProductName(productName);
        return this;
    }

    public CartPage goToCart() {
        clickGoToCartButton();
        return new CartPage(driverWrapper);
    }

    public ProductPage setSortDropdownOption(String option) {
        Select dropdown = new Select(getSortDropdown());
        dropdown.selectByValue(option);
        return this;
    }

    public BurgerMenu goToBurgerMenu() {
        clickBurgerMenuButton();
        driverWrapper.visibilityElement(By.id("inventory_sidebar_link"));
        return new BurgerMenu(driverWrapper);
    }


}
