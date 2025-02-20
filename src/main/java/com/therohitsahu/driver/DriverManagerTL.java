package com.therohitsahu.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class DriverManagerTL {
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static void init() {
        if (driverThreadLocal.get() == null) {
            WebDriver driver = new ChromeDriver();  // Change based on browser
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60)); // Increase timeout
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driverThreadLocal.set(driver);
        }
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void down() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }
}



