package com.therohitsahu.pages.pageObjectModel.katalonCura;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VerificationPage {

    WebDriver driver;

    public VerificationPage(WebDriver driver) {
        this.driver=driver;
    }

    //Page Locators
    private By confirmationMessage=By.xpath("//h2[text()='Appointment Confirmation']");


    //Actions
    public Boolean isAppointmentConfirmed(){
        try {
            return driver.findElement(confirmationMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }


    }
}
