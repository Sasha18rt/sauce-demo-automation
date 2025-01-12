package com.sasha.pages;

import com.sasha.data.Credentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private WebDriver driver;

    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("[data-test='error']");
    private By loginLogo = By.cssSelector(".login_logo");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //Methods for interaction
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public WebElement getLoginLogo(){
        return driver.findElement(loginLogo);
    }
    public String getLoginlogoText(){
        return getLoginLogo().getText();
    }
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }


    public ProductPage login(Credentials credentials) {
        enterUsername(credentials.getUsername());
        enterPassword(credentials.getPassword());
        clickLogin();
        return new ProductPage(driver);
    }

    public ProductPage loginAsStandardUser() {
        return login(Credentials.STANDARD_USER);
    }

}
