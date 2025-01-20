package com.therohitsahu.tests.vwoTestCases.pageFactory;

import com.therohitsahu.base.CommonToAllTest;

import com.therohitsahu.driver.DriverManager;
import com.therohitsahu.pages.pageFactory.LoginPage_PF;
import com.therohitsahu.utils.PropertiesReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestVWOLogin_PF extends CommonToAllTest {

    private static final Logger logger=LogManager.getLogger(TestVWOLogin_PF.class);

    @Test
    public void testLoginNegativeVWO_PF(){
        logger.info("Starting the testcases PageFactory");
        WebDriver driver= DriverManager.getDriver();
        driver.get(PropertiesReader.readKey("url"));
        LoginPage_PF loginPage_PF=new LoginPage_PF(driver);
        String error_msg= loginPage_PF.loginTOVWOInvalidCreds();
        Assert.assertEquals(error_msg,PropertiesReader.readKey("error_message"));
    }
}
