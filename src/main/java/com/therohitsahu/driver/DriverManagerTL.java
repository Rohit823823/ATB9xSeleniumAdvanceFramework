package com.therohitsahu.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager; // Import WebDriverManager
import java.time.Duration;

public class DriverManagerTL {
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static void init() {
        if (driverThreadLocal.get() == null) {
            // ✅ Fix: Use WebDriverManager to auto-download the correct ChromeDriver version
            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();

            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
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



