package com.therohitsahu.pages.pageFactory;

import com.therohitsahu.base.CommonToAllPage;
import com.therohitsahu.utils.PropertiesReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.FluentWait;
import java.time.Duration;
import java.util.NoSuchElementException;



public class LoginPage_PF extends CommonToAllPage {

    private static final Logger logger = LogManager.getLogger(LoginPage_PF.class);
    private WebDriver driver;
    private WebDriverWait wait;

    // ✅ Locators using @FindBy
    @FindBy(id = "login-username")
    private WebElement username;

    @FindBy(id = "login-password")
    private WebElement password;

    @FindBy(id = "js-login-btn")
    private WebElement signbutton;

    @FindBy(css = "#js-notification-box-msg")
    private WebElement error_message;

    @FindBy(css = "[data-qa='lufexuloga']")
    private WebElement loggedInUsername;

    // ✅ Constructor: Initialize PageFactory elements
    public LoginPage_PF(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        logger.info("✅ LoginPage_PF initialized using PageFactory");
    }

    // ✅ Negative Login: Wait for error message and return it
    public String loginTOVWOInvalidCreds() {
        logger.info("Attempting login with INVALID credentials");

        enterInput(username, PropertiesReader.readKey("invalid_username"));
        enterInput(password, PropertiesReader.readKey("invalid_password"));
        clickElement(signbutton);

        wait.until(ExpectedConditions.visibilityOf(error_message));
        String errorText = error_message.getText();
        logger.info("❌ Login failed. Error message displayed: {}", errorText);
        return errorText;
    }

    // ✅ Positive Login: Wait for logged-in username to appear
    public void loginTOVWOVValidCreds() {
        logger.info("Attempting login with VALID credentials");

        enterInput(username, PropertiesReader.readKey("username"));
        enterInput(password, PropertiesReader.readKey("password"));
        clickElement(signbutton);

        wait.until(ExpectedConditions.visibilityOf(loggedInUsername));
        logger.info("✅ Login successful. User is now logged in.");
    }

    // ✅ Fetch logged-in username after successful login
    public String getLoggedInUsername() {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(40))  // ⏳ Increase wait time
                .pollingEvery(Duration.ofSeconds(2))  // 🔄 Check every 2 seconds
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(loggedInUsername));
        String loggedUser = loggedInUsername.getText();
        logger.info("Retrieved logged-in username: {}", loggedUser);
        return loggedUser;
    }
}




