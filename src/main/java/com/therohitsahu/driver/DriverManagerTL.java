package com.therohitsahu.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager; // Import WebDriverManager
import java.time.Duration;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManagerTL {
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static void init() {
        if (driverThreadLocal.get() == null) {
            // ✅ Fix: Use WebDriverManager to auto-download the correct ChromeDriver version
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");  // Run in headless mode (no UI)
            options.addArguments("--no-sandbox");  // Required for Jenkins
            options.addArguments("--disable-dev-shm-usage");  // Fixes memory issues in containers
            options.addArguments("--remote-allow-origins=*"); // Allow remote connections
            options.addArguments("--window-size=1920,1080");  // Set screen size
            options.addArguments("--disable-gpu");  // Helps with rendering timeout

            WebDriver driver = new ChromeDriver();

            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
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



