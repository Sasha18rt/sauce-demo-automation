package com.sasha.pages;

import com.sasha.utils.DriverWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {

    private final DriverWrapper driverWrapper;

    private final By cartItems = By.cssSelector(".cart_item");
    private final By itemName = By.cssSelector(".inventory_item_name");
    private final By removeButton = By.cssSelector(".cart_button");
    private final By checkoutButton = By.id("checkout");
    private final By continueShoppingButton = By.id("continue-shopping");
    private final By itemPrices = By.cssSelector(".inventory_item_price");
    private final By itemDescriptions = By.cssSelector(".inventory_item_desc");

    public CartPage(DriverWrapper driverWrapper) {
        this.driverWrapper = driverWrapper;
    }

    public int getCartItemCount() {
        return driverWrapper.findElements(cartItems).size();
    }

    public WebElement getFirstItem() {
        return driverWrapper.findElements(itemName).get(0);
    }

    public String getFirstItemName() {
        return getFirstItem().getText();
    }

    public List<WebElement> getAllItems() {
        return driverWrapper.findElements(itemName);
    }

    public String getItemPrice(int index) {
        return driverWrapper.findElements(itemPrices).get(index).getText();
    }

    public String getItemDescription(int index) {
        return driverWrapper.findElements(itemDescriptions).get(index).getText();
    }

    public boolean isItemInCart(String productName) {
        List<WebElement> items = driverWrapper.findElements(itemName);
        for (WebElement item : items) {
            if (item.getText().equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }

    public void removeItemFromCartByName(String productName) {
        while (isItemInCart(productName)) {
            List<WebElement> items = driverWrapper.findElements(cartItems);
            for (WebElement item : items) {
                WebElement nameElement = item.findElement(itemName);
                if (nameElement.getText().equalsIgnoreCase(productName)) {
                    item.findElement(removeButton).click();
                    break;
                }
            }
        }
    }

    public WebElement getCheckoutButton() {
        return driverWrapper.visibilityElement(checkoutButton);
    }

    public void clickCheckoutButton() {
        getCheckoutButton().click();
    }

    public WebElement getContinueShoppingButton() {
        return driverWrapper.visibilityElement(continueShoppingButton);
    }

    public void clickContinueShoppingButton() {
        getContinueShoppingButton().click();
    }

    // BUSINESS LOGIC

    public ProductPage continueShopping() {
        clickContinueShoppingButton();
        return new ProductPage(driverWrapper);
    }

    public CheckoutStepOnePage goToCheckoutPage() {
        clickCheckoutButton();
        return new CheckoutStepOnePage(driverWrapper);
    }

    public CartPage removeItemFromCart(String productName) {
        removeItemFromCartByName(productName);
        return this;
    }
}
