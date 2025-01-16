package com.therohitsahu.pages.pageObjectModel.vwo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashBoardPage {

    WebDriver driver;

    public DashBoardPage(WebDriver driver) {
        this.driver=driver;

    }

    By usernameOnDashBoard=By.cssSelector("[data-qa=\"lufexuloga\"]");


    //Page Actions
    public String loggedInUsername() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return driver.findElement(usernameOnDashBoard).getText();
    }
}
