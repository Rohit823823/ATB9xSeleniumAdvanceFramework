package com.therohitsahu.tests.vwoTestCases;

import com.therohitsahu.pages.pageObjectModel.vwo.normal.DashBoardPage;
import com.therohitsahu.pages.pageObjectModel.vwo.normal.LoginPage;
import com.therohitsahu.utils.PropertiesReader;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestVWOLogin_04_POM_PropertiesReader {

    @Owner("ROHIT")
    @Description("Verify that invalid creds gives error message")
    @Test

    public void testLoginNegativeVWO() {
        WebDriver driver = new EdgeDriver();
        LoginPage loginPage_VWO = new LoginPage(driver);
        String error_message = loginPage_VWO.loginToVWOLoginInvalidCreds(PropertiesReader.readKey("invalid_username"),PropertiesReader.readKey("invalid_password"));

        assertThat(error_message).isNotBlank().isNotNull().isNotEmpty();
        Assert.assertEquals(error_message, PropertiesReader.readKey("error_message"));

    }

    @Owner("ROHIT")
    @Description("Verify that valid creds dashboard page is loaded")
    @Test

    public void testLoginPositiveVWO() {

        WebDriver driver = new EdgeDriver();

        LoginPage loginPage_VWO = new LoginPage(driver);
        loginPage_VWO.loginToVWOLoginValidCreds(PropertiesReader.readKey("username"), PropertiesReader.readKey("password"));
        DashBoardPage dashBoardPage = new DashBoardPage(driver);
        String usernameLoggedIn =dashBoardPage.loggedInUsername();

        assertThat(usernameLoggedIn).isNotBlank().isNotNull().isNotEmpty();
        Assert.assertEquals(usernameLoggedIn, PropertiesReader.readKey("expected_username"));

    }
}
