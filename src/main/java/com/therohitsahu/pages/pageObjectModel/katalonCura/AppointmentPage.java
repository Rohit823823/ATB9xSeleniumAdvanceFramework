package com.therohitsahu.pages.pageObjectModel.katalonCura;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AppointmentPage {

    WebDriver driver;

    public AppointmentPage(WebDriver driver) {
        this.driver = driver;
    }

    // Page Locators
    private By facilityDropdown = By.id("combo_facility");
    private By readmissionCheckbox = By.id("chk_hospotal_readmission");
    private By medicareRadioButton = By.id("radio_program_medicare");
    private By visitDateField = By.id("txt_visit_date");
    private By commentField = By.id("txt_comment");
    private By bookAppointmentButton = By.id("btn-book-appointment");

    // Actions
    public void fillAppointmentDetails(String facility, boolean readmission, String visitDate, String comment) {
        try {
            // Select facility
            driver.findElement(facilityDropdown).sendKeys(facility);

            // Check "Readmission" if true
            if (readmission) {
                driver.findElement(readmissionCheckbox).click();
            }

            // Select Medicare radio button
            driver.findElement(medicareRadioButton).click();

            // Enter visit date
            driver.findElement(visitDateField).sendKeys(visitDate);

            // Enter comment
            driver.findElement(commentField).sendKeys(comment);

            // Click the "Book Appointment" button
            driver.findElement(bookAppointmentButton).click();

            // Adding a brief delay to ensure the action is processed
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
