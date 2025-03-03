package com.therohitsahu.tests.vwoTestCases.pageFactory;

import com.therohitsahu.base.CommonToAllTest;
import com.therohitsahu.driver.DriverManagerTL;
import com.therohitsahu.pages.pageFactory.LoginPage_PF;
import com.therohitsahu.utils.PropertiesReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.AfterClass;

import java.time.Duration;

public class TestVWOLogin_PF extends CommonToAllTest {

    private static final Logger logger = LogManager.getLogger(TestVWOLogin_PF.class);
    private WebDriver driver;
    private LoginPage_PF loginPage;

    @BeforeMethod
    public void setup(@Optional("chrome") String browser) {
        logger.info("********** Starting Test Setup **********");

        DriverManagerTL.init();
        driver = DriverManagerTL.getDriver();
        logger.info("✅ Browser initialized: {}", browser);

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String url = PropertiesReader.readKey("url");
        driver.get(url);
        driver.navigate().refresh();
        logger.info("✅ Navigated to URL: {}", url);

        // ✅ Fix: Create a fresh instance of LoginPage_PF for each test
        loginPage = new LoginPage_PF(driver);
        logger.info("✅ Fresh PageFactory instance created for LoginPage_PF");

        logger.info("********** Test Setup Completed **********");
    }


    @Test(groups = "negative")
    public void testLoginNegativeVWO_PF() {
        logger.info("Starting Negative Login Test");

        String errorMsg = loginPage.loginTOVWOInvalidCreds();
        Assert.assertEquals(errorMsg, PropertiesReader.readKey("error_message"), "Error message mismatch!");

        driver.navigate().refresh();  // Refresh page after test
        logger.info("Negative test completed successfully!");
    }


    @Test(groups = "positive", dependsOnGroups = "negative")
    public void testLoginPositiveVWO_PF() {
        logger.info("Starting Positive Login Test");

        loginPage.loginTOVWOVValidCreds();
        String actualUsername = loginPage.getLoggedInUsername();
        String expectedUsername = PropertiesReader.readKey("expected_username");

        Assert.assertEquals(actualUsername, expectedUsername, "Username does not match!");
        logger.info("Positive test completed successfully!");
    }



    @AfterClass
    public void tearDown() {
        logger.info("********** Starting Teardown **********");
        if (driver != null) {
            driver.quit();
            logger.info("✅ WebDriver session terminated successfully!");
        } else {
            logger.warn("⚠️ WebDriver was already null, nothing to close.");
        }
        logger.info("********** Teardown Completed **********");
    }


}
