package com.sasha.pages;

import com.sasha.utils.DriverWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CheckoutCompletePage {

    private final DriverWrapper driverWrapper;

    // Locators for elements on the page
    private final By thankYouMessage = By.cssSelector(".complete-header");
    private final By orderConfirmationText = By.cssSelector(".complete-text");
    private final By backHomeButton = By.id("back-to-products");

    // Constructor
    public CheckoutCompletePage(DriverWrapper driverWrapper) {
        this.driverWrapper = driverWrapper;
    }

    // Methods to interact with elements

    public String getThankYouMessage() {
        return driverWrapper.visibilityElement(thankYouMessage).getText();
    }

    public String getOrderConfirmationText() {
        return driverWrapper.visibilityElement(orderConfirmationText).getText();
    }

    // Business Logic

    public ProductPage clickBackHomeButton() {
        driverWrapper.clickableElement(backHomeButton).click();
        return new ProductPage(driverWrapper);
    }
}
