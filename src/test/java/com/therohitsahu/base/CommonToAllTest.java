package com.therohitsahu.base;

import com.therohitsahu.driver.DriverManagerTL;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.net.MalformedURLException;

public class CommonToAllTest {

    @BeforeMethod
    protected void setUp() throws MalformedURLException {
        DriverManagerTL.init();
        }


    @AfterMethod
    protected void tearDown() {
        DriverManagerTL.down();

    }
}

