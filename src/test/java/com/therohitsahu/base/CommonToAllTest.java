package com.therohitsahu.base;

import com.therohitsahu.driver.DriverManagerTL;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;

import java.net.MalformedURLException;

public class CommonToAllTest {

    @BeforeMethod
    protected void setup(@Optional("chrome") String browser, @Optional("false") boolean headless) throws MalformedURLException {
        DriverManagerTL.init(browser, headless); // Pass browser and headless parameters
    }

    @AfterMethod
    protected void tearDown() {
        DriverManagerTL.tearDown(); // Call tearDown() instead of down()
    }
}

