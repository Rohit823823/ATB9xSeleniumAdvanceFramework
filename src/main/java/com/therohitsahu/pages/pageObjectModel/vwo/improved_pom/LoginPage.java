package com.therohitsahu.pages.pageObjectModel.vwo.improved_pom;

import com.therohitsahu.base.CommonToAllPage;
import com.therohitsahu.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.therohitsahu.driver.DriverManagerTL;

public class LoginPage extends CommonToAllPage {

    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver); // ✅ Fix: Call parent constructor
    }

    // Step 1 - Page Locators
    private By username = By.id("login-username");
    private By password = By.id("login-password");
    private By signButton = By.id("js-login-btn");
    private By error_message = By.id("js-notification-box-msg");

    // Step 2 - Page Actions

    public String loginToVWOLoginInvalidCreds(String user, String pwd) {
        openVWOUrl(PropertiesReader.readKey("url"));
        enterInput(username, user);
        enterInput(password, pwd);
        clickElement(signButton);
        presenceOfElement(error_message);
        return getElement(error_message).getText();
    }

    public void openVWOUrl(String url) {
        DriverManagerTL.getDriver().get(url);
    }

    public void loginToVWOLoginValidCreds(String user, String pwd) {
        openVWOUrl(PropertiesReader.readKey("url"));
        enterInput(username, user);
        enterInput(password, pwd);
        clickElement(signButton);
        implicitWait();
    }
}
