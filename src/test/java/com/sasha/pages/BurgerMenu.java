package com.sasha.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BurgerMenu {
    private WebDriver driver;

    private By allItems = By.id("inventory_sidebar_link");
    private By About = By.id("about_sidebar_link");
    private By Logout = By.id("logout_sidebar_link");
    private By closeBurgerMenuButton = By.id("react-burger-cross-btn");

    public BurgerMenu(WebDriver driver) {this.driver = driver;}

    public WebElement getAllItems() {return driver.findElement(allItems);}

    public void clickAllItems() {getAllItems().click();}

    public WebElement getAbout() {return driver.findElement(About);}

    public void clickAbout() {getAbout().click();}

    public WebElement getLogout() {return driver.findElement(Logout);}

    public void clickLogout() {getLogout().click();}

    public WebElement getcloseBurgerMenuButton() {return driver.findElement(closeBurgerMenuButton);}

    public void clickCloseBurgerMenuButton() {getcloseBurgerMenuButton().click();}


    //Business logic

    public LoginPage logout(){
        getLogout().click();
        return new LoginPage(driver);
    }

    public ProductPage goToProductPage() {
        getAllItems().click();
        return new ProductPage(driver);
    }
}
