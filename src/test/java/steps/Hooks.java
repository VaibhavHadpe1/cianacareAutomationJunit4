package steps;

import basePackage.AppiumServerClass;
import basePackage.EmulatorLauncher;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import pomClasses.LoginPage;
import utility.Utility;

import java.io.IOException;


public class Hooks extends Base{

    @BeforeAll
    public static void setUp() throws InterruptedException, IOException {
        EmulatorLauncher.startEmulator();
        AppiumServerClass.startAppium();
        Utility.setDriver();
        LoginPage loginPage=new LoginPage(driver);
//        loginPage.opencianacare();
//        loginPage.enterValidMobileNumber();
//        loginPage.clickOnContinueButton();
//        loginPage.enterOTP();
//        loginPage.clickOnVerifyOTPButton();
    }
    @AfterAll
    public static void tearDown() throws InterruptedException {
        LoginPage loginPage=new LoginPage(driver);
//        loginPage.logoutFromDahsboard();
//        loginPage.clickOnHome();
//        Thread.sleep(1000);

        // This will stop the Appium server after all scenario
        if (driver != null) {
            System.out.println("CleanedUp Appium session");
            driver.quit(); // This will stop the Appium server and close the app
            AppiumServerClass.stopAppium();
        }
        else {
            System.out.println("Server is not stopped");
        }

    }
}

