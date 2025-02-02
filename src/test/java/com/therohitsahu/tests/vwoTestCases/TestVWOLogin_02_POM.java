package com.therohitsahu.tests.vwoTestCases;

import com.therohitsahu.base.CommonToAllTest;
import com.therohitsahu.pages.pageObjectModel.vwo.normal.DashBoardPage;
import com.therohitsahu.pages.pageObjectModel.vwo.normal.LoginPage;
import com.therohitsahu.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestVWOLogin_02_POM extends CommonToAllTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Use the factory to get the appropriate DriverManager
        DriverManager manager = DriverManagerFactory.getManager(DriverManagerFactory.DriverType.SELENOID); // or LOCAL, THREAD_LOCAL
        driver = manager.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        // Use the factory to quit the driver
        DriverManager manager = DriverManagerFactory.getManager(DriverManagerFactory.DriverType.SELENOID); // or LOCAL, THREAD_LOCAL
        manager.quitDriver();
    }

    @Test
    public void testLoginNegativeVWO() {
        LoginPage loginPage_VWO = new LoginPage(driver);
        String error_message = loginPage_VWO.loginToVWOLoginInvalidCreds("admin@gmail.com", "123");

        assertThat(error_message).isNotBlank().isNotNull().isNotEmpty();
        Assert.assertEquals(error_message, "Your email, password, IP address or location did not match");
    }

    @Test
    public void testLoginPositiveVWO() {
        LoginPage loginPage_VWO = new LoginPage(driver);
        loginPage_VWO.loginToVWOLoginValidCreds("contact+aug@thetestingacademy.com", "TtxkgQ!s$rJBk85");
        DashBoardPage dashBoardPage = new DashBoardPage(driver);
        String usernameLoggedIn = dashBoardPage.loggedInUsername();

        assertThat(usernameLoggedIn).isNotBlank().isNotNull().isNotEmpty();
        Assert.assertEquals(usernameLoggedIn, "Aman");
    }
}
