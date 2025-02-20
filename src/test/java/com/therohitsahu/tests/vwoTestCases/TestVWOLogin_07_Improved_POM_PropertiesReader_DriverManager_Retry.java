package com.therohitsahu.tests.vwoTestCases;

import com.therohitsahu.base.CommonToAllTest;
import com.therohitsahu.driver.DriverManagerTL;
import com.therohitsahu.listeners.RetryAnalyzer;
import com.therohitsahu.pages.pageObjectModel.vwo.normal.DashBoardPage;
import com.therohitsahu.pages.pageObjectModel.vwo.normal.LoginPage;
import com.therohitsahu.utils.PropertiesReader;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
@Test(retryAnalyzer = RetryAnalyzer.class)
public class TestVWOLogin_07_Improved_POM_PropertiesReader_DriverManager_Retry extends CommonToAllTest {

    private static final Logger logger= LogManager.getLogger(TestVWOLogin_07_Improved_POM_PropertiesReader_DriverManager_Retry.class);

    @Owner("ROHIT")
    @Description("Verify that invalid creds gives error message")
    @Test


    public void testLoginNegativeVWO() {

        logger.info("Starting the Testcases Page Object Model|Verify that invalid creds gives error message");

        LoginPage loginPage_VWO = new LoginPage(DriverManagerTL.getDriver());
        String error_message = loginPage_VWO.loginToVWOLoginInvalidCreds(PropertiesReader.readKey("invalid_username"),PropertiesReader.readKey("invalid_password"));

        logger.info("End of The Test Login Negative VWO !!");

        // Assertion Assertj
        assertThat(error_message).isNotBlank().isNotNull().isNotEmpty();

        // Assertion TestNG
        Assert.assertEquals(error_message, PropertiesReader.readKey("error_message"));

    }

    @Owner("ROHIT")
    @Description("Verify that valid creds dashboard page is loaded")
    @Test

    public void testLoginPositiveVWO() {

        logger.info("Starting the Testcases Page Object Model|Verify that valid creds leads us to dashboard page");



        LoginPage loginPage_VWO = new LoginPage(DriverManagerTL.getDriver());
        loginPage_VWO.loginToVWOLoginValidCreds(PropertiesReader.readKey("username"), PropertiesReader.readKey("password"));

        DashBoardPage dashBoardPage = new DashBoardPage(DriverManagerTL.getDriver());
        String usernameLoggedIn =dashBoardPage.loggedInUsername();

        logger.info("End and Asserting the output test Login Positive VWO !!");


        assertThat(usernameLoggedIn).isNotBlank().isNotNull().isNotEmpty();
        Assert.assertEquals(usernameLoggedIn, PropertiesReader.readKey("expected_username"));

    }
}
