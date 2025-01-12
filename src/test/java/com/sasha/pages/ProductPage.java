package com.sasha.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {

    private WebDriver driver;

    private By addToCartButton = By.cssSelector(".inventory_item button");
    private By removeButton = By.id("remove-sauce-labs-backpack");
    private By goToCartButton = By.cssSelector(".shopping_cart_link");
    private By appLogo = By.cssSelector(".app_logo");
    private By burgerMenuButton = By.id("react-burger-menu-btn");
    private By sortDropdown = By.cssSelector(".product_sort_container");
    private By productName = By.cssSelector(".inventory_item_name ");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getAppLogo(){ return driver.findElement(appLogo);}

    public void clickAppLogo(){getAppLogo().click();}

    public WebElement getBurgerMenuButton(){ return driver.findElement(burgerMenuButton);}

    public void clickBurgerMenuButton(){getBurgerMenuButton().click();}

    public WebElement getGoToCartButton() {
        return driver.findElement(goToCartButton);
    }

    public WebElement getAddToCartButton() {   return driver.findElement(addToCartButton);}

    public WebElement getRemoveButton() {return driver.findElement(removeButton);}

    public void clickGoToCartButton() {getGoToCartButton().click();}

    public WebElement getRremoveButton() {return driver.findElement(removeButton);}

    public String getFirstProduct() {
        return driver.findElements(productName).get(0).getText();
    }


    public boolean isLoginSuccessful() {
        return driver.getPageSource().contains("Products");
    }
    public WebElement getSortDropdown() {
        return driver.findElement(sortDropdown);
    }

    public void clickSortDropdown() {
        getSortDropdown().click();
    }

    //Functions

    public void addToCartByProductName(String productName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']")));

        product.findElement(By.cssSelector(".btn_inventory")).click();
    }


    public boolean isProductInCart(String productName) {
        return driver.findElement(By.xpath("//a[text()='" + productName + "']")).isDisplayed();
    }
    //Business logic

    public ProductPage addToCart(String productName) {
        addToCartByProductName(productName);
        return new ProductPage(driver);
    }
    public CartPage goToCart() {
        clickGoToCartButton();
        return new CartPage(driver);
    }

    public ProductPage setSortDropdownOption(String option) {
        clickAppLogo();
        clickSortDropdown();
        Select dropdown = new Select(getSortDropdown());
        dropdown.selectByValue(option);
        return this;
    }

    public BurgerMenu goToBurgerMenu() {
        clickBurgerMenuButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inventory_sidebar_link")));
        return new BurgerMenu(driver);
    }

}
