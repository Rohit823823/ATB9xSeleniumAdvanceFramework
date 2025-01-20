package com.therohitsahu.pages.pageFactory;

import com.therohitsahu.base.CommonToAllPage;
import com.therohitsahu.utils.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage_PF extends CommonToAllPage {

    //Page Locators

    public LoginPage_PF(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "login-username")
    private WebElement username;

    @FindBy(id = "login-password")
    private WebElement password;

    @FindBy(id = "js-login-btn")
    private WebElement signbutton;

    @FindBy(css = "#js-notification-box-msg")
    private WebElement error_message;

    //Page Actions
    public String loginTOVWOInvalidCreds () {
        enterInput(username, PropertiesReader.readKey("invalid_username"));
        enterInput(password, PropertiesReader.readKey("invalid_password"));
        clickElement(signbutton);
        custom_wait();

        return getText(error_message);

    }












}
