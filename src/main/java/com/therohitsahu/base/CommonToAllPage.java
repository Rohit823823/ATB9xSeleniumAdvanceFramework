package com.therohitsahu.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;


public class CommonToAllPage {

    protected WebDriver driver;  // Declare WebDriver instance

    // Constructor that accepts WebDriver
    public CommonToAllPage(WebDriver driver) {
        this.driver = driver;
    }

    // Waits
    public void implicitWait() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    // Common Functions
    public void clickElement(By by) {
        driver.findElement(by).click();
    }

    public void clickElement(WebElement element) { // Overloaded method for WebElement
        element.click();
    }

    public WebElement presenceOfElement(By elementLocation) {
        return new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.presenceOfElementLocated(elementLocation));
    }

    public WebElement visibilityOfElement(By elementLocation) {
        return new WebDriverWait(driver, Duration.ofSeconds(70))
                .until(ExpectedConditions.visibilityOfElementLocated(elementLocation));
    }

    public WebElement visibilityOfElement(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public void enterInput(By by, String key) {
        WebElement element = driver.findElement(by);
        element.clear(); // ✅ Clears any existing text before entering new text
        element.sendKeys(key);
        System.out.println("Entered text into element: " + key);
    }

    public void enterInput(WebElement element, String key) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='';", element); // ✅ Force clear using JS
        element.sendKeys(key);
        System.out.println("Entered text into element: " + key);
    }


    public WebElement getElement(By key) {
        return driver.findElement(key);
    }

    public void iWaitForElementToBeVisible(WebElement loc, String url) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOf(loc));
            wait.until(ExpectedConditions.urlContains(url));
        } catch (Exception e) {
            System.out.println("Failed to Wait!: " + e.toString());
        }
    }
}
