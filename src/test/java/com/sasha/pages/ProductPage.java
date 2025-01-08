package com.sasha.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {

    private WebDriver driver;

    //Locator button add to cart
    private By addToCartButton = By.cssSelector(".inventory_item button");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addToCart(String productName) {
        driver.findElement(By.xpath("//div[text()='" + productName + "']/following-sibling::div/button")).click();
    }

    public boolean isProductInCart(String productName) {
        return driver.findElement(By.xpath("//a[text()='" + productName + "']")).isDisplayed();
    }
}
