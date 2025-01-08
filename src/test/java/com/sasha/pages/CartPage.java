package com.sasha.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {

    private WebDriver driver;

    private By cartItems = By.cssSelector(".cart_item");
    private By itemName = By.cssSelector(".inventory_item_name");
    private By removeButton = By.cssSelector(".cart_button");
    private By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getCartItemCount() {
        return driver.findElements(cartItems).size();
    }

    public boolean isItemInCart(String productName) {
        List<WebElement> items = driver.findElements(itemName);
        for (WebElement item : items) {
            if (item.getText().equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }

    public void removeItemFromCart(String productName) {
        List<WebElement> items = driver.findElements(cartItems);
        for (WebElement item : items) {
            WebElement nameElement = item.findElement(itemName);
            if (nameElement.getText().equalsIgnoreCase(productName)) {
                item.findElement(removeButton).click();
                break;
            }
        }
    }

    public void proceedToCheckout() {
        driver.findElement(checkoutButton).click();
    }
}
