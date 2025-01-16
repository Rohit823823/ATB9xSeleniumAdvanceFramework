package com.therohitsahu.pages.pageObjectModel.katalonCura;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //Page Locators
    private By usernameField = By.id("txt-username");
    private By passwordField = By.id("txt-password");
    private By loginButton = By.id("btn-login");

    //Actions
    public void loginToCura(String username,String password){

        try {
            // Enter username
            driver.findElement(usernameField).sendKeys(username);

            // Enter password
            driver.findElement(passwordField).sendKeys(password);

            // Click the login button
            driver.findElement(loginButton).click();

            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}