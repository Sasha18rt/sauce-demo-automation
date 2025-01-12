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
    private By continueShoppingButton = By.id("continue-shopping");
    private By itemPrices = By.cssSelector(".inventory_item_price");
    private By itemDescriptions = By.cssSelector(".inventory_item_desc");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getCartItemCount() {
        return driver.findElements(cartItems).size();
    }

    public WebElement getFirstItem() {
        return driver.findElements(itemName).get(0);
    }
    public String getFirstItemName() {
        return getFirstItem().getText();
    }

    public List<WebElement> getAllItems() {
        return driver.findElements(itemName);
    }

    public String getItemPrice(int index) {
        return driver.findElements(itemPrices).get(index).getText();
    }

    public String getItemDescription(int index) {
        return driver.findElements(itemDescriptions).get(index).getText();
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

    public void removeItemFromCartByName(String productName) {
        while (isItemInCart(productName)) {
            List<WebElement> items = driver.findElements(cartItems);
            for (WebElement item : items) {
                WebElement nameElement = item.findElement(itemName);
                if (nameElement.getText().equalsIgnoreCase(productName)) {
                    item.findElement(removeButton).click();
                    break;
                }
            }
        }
    }

    public WebElement getCheckoutButton() {return driver.findElement(checkoutButton);}

    public void clickCheckoutButton() {getCheckoutButton().click();}

    public WebElement getContinueShoppingButton() {
        return driver.findElement(continueShoppingButton);
    }
    public void clickContinueShoppingButton() {getContinueShoppingButton().click();}


//BUSINESS LOGIC


    public ProductPage continueShopping() {
        clickContinueShoppingButton();
        return new ProductPage(driver);
    }

    public CheckoutStepOnePage goToCheckoutPage() {
        clickCheckoutButton();
        return new CheckoutStepOnePage(driver);
    }

    public CartPage removeItemFromCart(String productName) {
        removeItemFromCartByName(productName);
        return new CartPage(driver);
    }
}
