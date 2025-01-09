package com.sasha.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutCompletePage {

    private WebDriver driver;

    // Locators for elements on the page
    private By thankYouMessage = By.cssSelector(".complete-header");
    private By orderConfirmationText = By.cssSelector(".complete-text");
    private By backHomeButton = By.id("back-to-products");

    // Constructor
    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
    }


    public String getThankYouMessage() {
        return driver.findElement(thankYouMessage).getText();
    }


    public String getOrderConfirmationText() {
        return driver.findElement(orderConfirmationText).getText();
    }


    public ProductPage clickBackHomeButton() {
        driver.findElement(backHomeButton).click();
        return new ProductPage(driver);
    }
}
