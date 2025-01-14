package com.sasha.pages;

import com.sasha.utils.DriverWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.NoSuchElementException;

public class BurgerMenu {
    private final DriverWrapper driverWrapper;

    private final By allItems = By.id("inventory_sidebar_link");
    private final By about = By.id("about_sidebar_link");
    private final By logout = By.id("logout_sidebar_link");
    private final By closeBurgerMenuButton = By.id("react-burger-cross-btn");

    public BurgerMenu(DriverWrapper driverWrapper) {
        this.driverWrapper = driverWrapper;
    }

    public WebElement getAllItems() {
        return driverWrapper.visibilityElement(allItems);
    }

    public void clickAllItems() {
        getAllItems().click();
    }

    public WebElement getAbout() {
        return driverWrapper.visibilityElement(about);
    }

    public void clickAbout() {
        getAbout().click();
    }

    public WebElement getLogout() {
        return driverWrapper.visibilityElement(logout);
    }

    public void clickLogout() {
        getLogout().click();
    }

    public WebElement getCloseBurgerMenuButton() {
        return driverWrapper.visibilityElement(closeBurgerMenuButton);
    }

    public void clickCloseBurgerMenuButton() {
        getCloseBurgerMenuButton().click();
    }

    public Boolean isOptionVisibleById(String id) {
        try {
            return driverWrapper.getDriver().findElement(By.id(id)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Business logic

    public LoginPage logout() {
        clickLogout();
        return new LoginPage(driverWrapper);
    }

    public ProductPage goToProductPage() {
        clickAllItems();
        return new ProductPage(driverWrapper);
    }
}
