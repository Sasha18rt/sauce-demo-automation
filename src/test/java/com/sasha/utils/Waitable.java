package com.sasha.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class Waitable<T> {
    public abstract T apply(WebDriver driver, By locator);
}
