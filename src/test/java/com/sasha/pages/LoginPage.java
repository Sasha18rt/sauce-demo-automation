package com.sasha.pages;

import com.sasha.data.Credentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.sasha.utils.DriverWrapper;

public class LoginPage {

    private DriverWrapper driverWrapper;

    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("[data-test='error']");
    private By loginLogo = By.cssSelector(".login_logo");

    public LoginPage(DriverWrapper driverWrapper) {
        this.driverWrapper = driverWrapper;
    }

    public void enterUsername(String username) {
        WebElement usernameInput = driverWrapper.visibilityElement(usernameField);
        driverWrapper.waitAndType(usernameInput, username);
    }

    public void enterPassword(String password) {
        WebElement passwordInput = driverWrapper.visibilityElement(passwordField);
        driverWrapper.waitAndType(passwordInput, password);
    }

    public void clickLogin() {
        driverWrapper.clickableElement(loginButton).click();
    }

    public String getErrorMessage() {
        return driverWrapper.visibilityElement(errorMessage).getText();
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return driverWrapper.visibilityElement(errorMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public WebElement getLoginLogo() {
        return driverWrapper.visibilityElement(loginLogo);
    }

    public String getLoginLogoText() {
        return getLoginLogo().getText();
    }

    public ProductPage login(Credentials credentials) {
        enterUsername(credentials.getUsername());
        enterPassword(credentials.getPassword());
        clickLogin();
        return new ProductPage(driverWrapper);
    }

    public ProductPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        return new ProductPage(driverWrapper);
    }

    public ProductPage loginAsStandardUser() {
        return login(Credentials.STANDARD_USER);
    }


}
