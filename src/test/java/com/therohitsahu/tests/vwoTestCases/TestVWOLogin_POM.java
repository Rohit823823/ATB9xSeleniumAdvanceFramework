package com.therohitsahu.tests.vwoTestCases;

import com.therohitsahu.pages.pageObjectModel.vwo.DashBoardPage;
import com.therohitsahu.pages.pageObjectModel.vwo.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class TestVWOLogin_POM {

    @Owner("ROHIT")
    @Description("Verify that invalid creds gives error message")
    @Test

    public void testLoginNegativeVWO() {
        WebDriver driver = new EdgeDriver();
        LoginPage loginPage_VWO = new LoginPage(driver);
        String error_message = loginPage_VWO.loginToVWOLoginInvalidCreds("admin@gmail.com", "123");

        assertThat(error_message).isNotBlank().isNotNull().isNotEmpty();
        Assert.assertEquals(error_message, "Your email, password, IP address or location did not match");

    }

    @Owner("ROHIT")
    @Description("Verify that valid creds dashboard page is loaded")
    @Test

    public void testLoginPositiveVWO() {

        WebDriver driver = new EdgeDriver();

        LoginPage loginPage_VWO = new LoginPage(driver);
        loginPage_VWO.loginToVWOLoginValidCreds("contact+aug@thetestingacademy.com", "TtxkgQ!s$rJBk85");
        DashBoardPage dashBoardPage = new DashBoardPage(driver);
        String usernameLoggedIn =dashBoardPage.loggedInUsername();

        assertThat(usernameLoggedIn).isNotBlank().isNotNull().isNotEmpty();
        Assert.assertEquals(usernameLoggedIn, "Aman");

    }
}
