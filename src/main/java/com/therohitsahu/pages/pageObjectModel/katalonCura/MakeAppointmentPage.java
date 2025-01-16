package com.therohitsahu.pages.pageObjectModel.katalonCura;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MakeAppointmentPage {

    WebDriver driver;

    public MakeAppointmentPage(WebDriver driver) {
        this.driver=driver;
    }

    //Page Locator
    private By makeAppointmentButton=By.id("btn-make-appointment");


    //Actions
    public void clickMakeAppointment () {
        driver.get("https://katalon-demo-cura.herokuapp.com");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        driver.findElement(makeAppointmentButton).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
