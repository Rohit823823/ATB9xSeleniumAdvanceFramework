package com.therohitsahu.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class DriverManager {

     static WebDriver driver;

    @BeforeMethod
    static void init(){
        if(driver ==null)
            driver = new ChromeDriver();
    }

    @AfterMethod
    // When we want to close the browser
     static void down(){
        if (driver != null) {
            driver.quit();
            driver = null;
        }

    }


}