package com.therohitsahu.tests.KatalonCuraTestcase;

import com.therohitsahu.pages.pageObjectModel.katalonCura.AppointmentPage;
import com.therohitsahu.pages.pageObjectModel.katalonCura.LoginPage;
import com.therohitsahu.pages.pageObjectModel.katalonCura.MakeAppointmentPage;
import com.therohitsahu.pages.pageObjectModel.katalonCura.VerificationPage;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestKatalonCura_POM {

    @Owner("ROHIT")
    @Description("Verify the appointment booking process in the CURA Healthcare")
    @Test
    public void testAppointmentBooking () {

        // Set up WebDriver
        WebDriver driver=new EdgeDriver();


        try {
            //Instantiate Page Objects
            MakeAppointmentPage makeAppointmentPage=new MakeAppointmentPage(driver);
            LoginPage loginPage=new LoginPage(driver);
            AppointmentPage appointmentPage=new AppointmentPage(driver);
            VerificationPage verificationPage=new VerificationPage(driver);

            // Step 1: Navigate to "Make Appointment" page
            makeAppointmentPage.clickMakeAppointment();

            // Step 2: Perform login
            loginPage.loginToCura("John Doe", "ThisIsNotAPassword");

            // Step 3: Fill in appointment details
            appointmentPage.fillAppointmentDetails(
                    "Tokyo CURA Healthcare Center", // Facility
                    true,                           // Readmission
                    "01/02/2025",                   // Visit Date
                    "This is a test appointment."   // Comment
            );

            // Step 4: Verify the appointment booking
            boolean isConfirmed = verificationPage.isAppointmentConfirmed();

            //Assertions
            assertThat(isConfirmed).isTrue();
            Assert.assertTrue(isConfirmed,"The appointment booking process failed.");
        }
        finally {
            driver.quit();
        }

    }
}
