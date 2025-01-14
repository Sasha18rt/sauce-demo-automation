package com.sasha.pages;

import com.sasha.utils.DriverWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CheckoutStepTwoPage {

    private final DriverWrapper driverWrapper;

    private final By summaryItemList = By.cssSelector(".cart_item");
    private final By finishButton = By.id("finish");
    private final By cancelButton = By.id("cancel");
    private final By summarySubtotal = By.cssSelector(".summary_subtotal_label");
    private final By summaryTax = By.cssSelector(".summary_tax_label");
    private final By summaryTotal = By.cssSelector(".summary_total_label");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public CheckoutStepTwoPage(DriverWrapper driverWrapper) {
        this.driverWrapper = driverWrapper;
    }

    public int getItemCount() {
        return driverWrapper.findElements(summaryItemList).size();
    }

    public WebElement getErrorMessage() {
        return driverWrapper.visibilityElement(errorMessage);
    }

    public String getErrorMessageText() {
        return getErrorMessage().getText();
    }

    public String getSubtotal() {
        return driverWrapper.visibilityElement(summarySubtotal).getText();
    }

    public String getTax() {
        return driverWrapper.visibilityElement(summaryTax).getText();
    }

    public String getTotal() {
        return driverWrapper.visibilityElement(summaryTotal).getText();
    }

    public CheckoutCompletePage completeCheckout() {
        driverWrapper.clickableElement(finishButton).click();
        return new CheckoutCompletePage(driverWrapper);
    }

    public ProductPage clickCancelButton() {
        driverWrapper.clickableElement(cancelButton).click();
        return new ProductPage(driverWrapper);
    }
}
