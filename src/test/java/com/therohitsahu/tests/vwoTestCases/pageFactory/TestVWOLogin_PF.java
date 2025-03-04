package com.therohitsahu.tests.vwoTestCases.pageFactory;

import com.therohitsahu.base.CommonToAllTest;
import com.therohitsahu.driver.DriverManagerTL;
import com.therohitsahu.pages.pageFactory.LoginPage_PF;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class TestVWOLogin_PF extends CommonToAllTest {

    private static final Logger logger = LogManager.getLogger(TestVWOLogin_PF.class);
    private WebDriver driver;
    private LoginPage_PF loginPage;

    @BeforeMethod
    public void setup(@Optional("chrome") String browser) {
        logger.info("********** Starting Test Setup **********");

        // Initialize WebDriver using DriverManagerTL
        DriverManagerTL.init(browser, true); // true for headless mode
        driver = DriverManagerTL.getDriver();

        // Log the browser initialization
        logger.info("Browser initialized: {}", browser);

        // Navigate to the application URL
        String url = "https://app.vwo.com"; // Replace with your actual URL or use PropertiesReader
        driver.get(url);
        logger.info("Navigated to URL: {}", url);

        // Initialize the LoginPage_PF object
        loginPage = new LoginPage_PF(driver);
        logger.info("LoginPage_PF initialized successfully");

        logger.info("********** Test Setup Completed **********");
    }

    @Test(groups = "negative")
    public void testLoginNegativeVWO_PF() {
        logger.info("Starting Negative Login Test");

        // Perform login with invalid credentials
        String errorMsg = loginPage.loginTOVWOInvalidCreds();

        // Assert the error message
        String expectedErrorMessage = "Your email, password, IP address or location did not match"; // Replace with actual expected message
        Assert.assertEquals(errorMsg, expectedErrorMessage, "Error message mismatch!");

        logger.info("Negative test completed successfully!");
    }

    @Test(groups = "positive", dependsOnGroups = "negative")
    public void testLoginPositiveVWO_PF() {
        logger.info("Starting Positive Login Test");

        // Perform login with valid credentials
        loginPage.loginTOVWOVValidCreds();

        // Get the logged-in username and assert it
        String actualUsername = loginPage.getLoggedInUsername();
        String expectedUsername = "Aman"; // Replace with actual expected username
        Assert.assertEquals(actualUsername, expectedUsername, "Username does not match!");

        logger.info("Positive test completed successfully!");
    }
}
