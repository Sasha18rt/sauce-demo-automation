package com.sasha.pages;

import com.sasha.utils.DriverWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CheckoutStepOnePage {

    private final DriverWrapper driverWrapper;

    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By postalCodeField = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By cancelButton = By.id("cancel");
    private final By errorMessage = By.cssSelector(".error-message-container");

    public CheckoutStepOnePage(DriverWrapper driverWrapper) {
        this.driverWrapper = driverWrapper;
    }

    public void enterFirstName(String firstName) {
        WebElement firstNameElement = driverWrapper.clickableElement(firstNameField);
        firstNameElement.clear();
        firstNameElement.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        WebElement lastNameElement = driverWrapper.clickableElement(lastNameField);
        lastNameElement.clear();
        lastNameElement.sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode) {
        WebElement postalCodeElement = driverWrapper.clickableElement(postalCodeField);
        postalCodeElement.clear();
        postalCodeElement.sendKeys(postalCode);
    }

    public void clickContinueButton() {
        driverWrapper.clickableElement(continueButton).click();
    }

    public WebElement getCancelButton() {
        return driverWrapper.clickableElement(cancelButton);
    }

    public void clickCancelButton() {
        getCancelButton().click();
    }

    public String getErrorMessage() {
        return driverWrapper.visibilityElement(errorMessage).getText();
    }

    public void fillCheckoutForm(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
    }

    // Business Logic

    public CartPage cancelCheckout() {
        clickCancelButton();
        return new CartPage(driverWrapper);
    }

    public CheckoutStepTwoPage proceedToNextStep(String firstName, String lastName, String postalCode) {
        fillCheckoutForm(firstName, lastName, postalCode);
        clickContinueButton();
        return new CheckoutStepTwoPage(driverWrapper);
    }
}
