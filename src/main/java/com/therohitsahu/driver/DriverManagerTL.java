package com.therohitsahu.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.Optional;

import java.time.Duration;

public class DriverManagerTL {
    private static final Logger logger = LogManager.getLogger(DriverManagerTL.class);
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static void init(@Optional("chrome") String browser, @Optional("false") boolean headless) {
        if (driverThreadLocal.get() == null) {
            logger.info("Initializing WebDriver for browser: {}", browser);
            WebDriver driver;
            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    if (headless) {
                        options.addArguments("--headless=new");
                        options.addArguments("--disable-gpu");
                        options.addArguments("--no-sandbox");
                    }
                    driver = new ChromeDriver(options);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (headless) {
                        firefoxOptions.addArguments("--headless");
                    }
                    driver = new FirefoxDriver(firefoxOptions);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            driverThreadLocal.set(driver);
            logger.info("WebDriver initialized successfully");
        }
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void tearDown() {
        if (driverThreadLocal.get() != null) {
            logger.info("Shutting down WebDriver");
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
            logger.info("WebDriver shut down successfully");
        }
    }
}



