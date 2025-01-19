package com.therohitsahu.tests.vwoTestCases;

import com.therohitsahu.base.CommonToAllTest;
import com.therohitsahu.pages.pageObjectModel.vwo.normal.DashBoardPage;
import com.therohitsahu.pages.pageObjectModel.vwo.normal.LoginPage;
import com.therohitsahu.utils.PropertiesReader;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.therohitsahu.driver.DriverManager;
import static org.assertj.core.api.Assertions.assertThat;

public class TestVWOLogin_05_POM_PropertiesReader_DriverManager extends CommonToAllTest {

    @Owner("ROHIT")
    @Description("Verify that invalid creds gives error message")
    @Test

    public void testLoginNegativeVWO() {

        LoginPage loginPage_VWO = new LoginPage(DriverManager.getDriver());
        String error_message = loginPage_VWO.loginToVWOLoginInvalidCreds(PropertiesReader.readKey("invalid_username"),PropertiesReader.readKey("invalid_password"));

        // Assertion Assertj
        assertThat(error_message).isNotBlank().isNotNull().isNotEmpty();

        // Assertion TestNG
        Assert.assertEquals(error_message, PropertiesReader.readKey("error_message"));

    }

    @Owner("ROHIT")
    @Description("Verify that valid creds dashboard page is loaded")
    @Test

    public void testLoginPositiveVWO() {



        LoginPage loginPage_VWO = new LoginPage(DriverManager.getDriver());
        loginPage_VWO.loginToVWOLoginValidCreds(PropertiesReader.readKey("username"), PropertiesReader.readKey("password"));
        DashBoardPage dashBoardPage = new DashBoardPage(DriverManager.getDriver());
        String usernameLoggedIn =dashBoardPage.loggedInUsername();

        assertThat(usernameLoggedIn).isNotBlank().isNotNull().isNotEmpty();
        Assert.assertEquals(usernameLoggedIn, PropertiesReader.readKey("expected_username"));

    }
}
