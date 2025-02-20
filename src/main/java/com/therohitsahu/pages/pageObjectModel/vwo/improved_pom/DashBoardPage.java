package com.therohitsahu.pages.pageObjectModel.vwo.improved_pom;

import com.therohitsahu.base.CommonToAllPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashBoardPage extends CommonToAllPage {

    public DashBoardPage(WebDriver driver) {
        super(driver);  // ✅ Call parent constructor
    }

    By usernameOnDashBoard = By.cssSelector("[data-qa=\"lufexuloga\"]");

    // Page Actions
    public String loggedInUsername() {
        visibilityOfElement(usernameOnDashBoard);
        return getElement(usernameOnDashBoard).getText();
    }
}
