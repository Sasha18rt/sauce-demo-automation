package com.sasha.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {

    private WebDriver driver;

    //Locator button add to cart
    private By addToCartButton = By.cssSelector(".inventory_item button");
    private By removeButton = By.id("remove-sauce-labs-backpack");
    private By goToCartButton = By.cssSelector(".shopping_cart_link");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }


    public CartPage addToCartByProductName(String productName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']")));

        product.findElement(By.cssSelector(".btn_inventory")).click();
        return new CartPage(driver);
    }

    public boolean isProductInCart(String productName) {
        return driver.findElement(By.xpath("//a[text()='" + productName + "']")).isDisplayed();
    }
    public WebElement getGoToCartButton() {
        return driver.findElement(goToCartButton);
    }
    public void clickGoToCartButton() {
        getGoToCartButton().click();
    }
    public WebElement removeButton() {return driver.findElement(removeButton);}

    public ProductPage addToCart(String productName) {
        addToCartByProductName(productName);
        return new ProductPage(driver);
    }
    public CartPage goToCart() {
        clickGoToCartButton();
        return new CartPage(driver);
    }
}
