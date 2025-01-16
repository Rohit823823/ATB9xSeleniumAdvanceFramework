package com.therohitsahu.tests.orangrHRTestcase;

import com.therohitsahu.pages.pageObjectModel.orangeHR.EmployeeListHomePage;
import com.therohitsahu.pages.pageObjectModel.orangeHR.LoginHRPage;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestOrangeHR_POM {

    @Owner("ROHIT")
    @Description("Verify the login to the orangeHR")
    @Test
    public void testLoginPositive(){
        WebDriver driver=new EdgeDriver();

        LoginHRPage loginHRPage=new LoginHRPage(driver);
        loginHRPage.loginToHRCreds("admin","Hacker@4321");

        EmployeeListHomePage dashboardPagePom=new EmployeeListHomePage(driver);
        String loggedInUserName=dashboardPagePom.loggedInUserName();

        assertThat(loggedInUserName).isNotBlank().isNotNull().isNotEmpty();
        Assert.assertEquals(loggedInUserName,"PIM");



    }
}
