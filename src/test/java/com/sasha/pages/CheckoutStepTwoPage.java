package com.sasha.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutStepTwoPage {

    private WebDriver driver;

    private By summaryItemList = By.cssSelector(".cart_item");
    private By finishButton = By.id("finish");
    private By cancelButton = By.id("cancel");
    private By summarySubtotal = By.cssSelector(".summary_subtotal_label");
    private By summaryTax = By.cssSelector(".summary_tax_label");
    private By summaryTotal = By.cssSelector(".summary_total_label");
    private By errorMessage = By.cssSelector("[data-test='error']");

    public CheckoutStepTwoPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getItemCount() {
        return driver.findElements(summaryItemList).size();
    }

    public WebElement getErrorMessage() {
        return driver.findElement(errorMessage);
    }

    public String getErrorMessageText() {
        return getErrorMessage().getText();
    }

    public String getSubtotal() {
        return driver.findElement(summarySubtotal).getText();
    }

    public String getTax() {
        return driver.findElement(summaryTax).getText();
    }

    public String getTotal() {
        return driver.findElement(summaryTotal).getText();
    }

    // Business Logic


    public CheckoutCompletePage CompleteCheckout() {
        driver.findElement(finishButton).click();
        return new CheckoutCompletePage(driver);
    }

    public ProductPage clickCancelButton() {
        driver.findElement(cancelButton).click();
        return new ProductPage(driver);
    }
}
