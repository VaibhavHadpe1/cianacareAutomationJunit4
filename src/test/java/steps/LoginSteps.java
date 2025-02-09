package steps;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pomClasses.LoginPage;
import utility.Utility;

import java.io.IOException;

public class LoginSteps {
    AndroidDriver driver;
    LoginPage loginPage;
    public LoginSteps() throws InterruptedException {
        driver=Utility.getDriver();
        loginPage=new LoginPage(driver);
    }

    @Given("the user has launched cianacare application and present on the login page")
    public void the_user_has_launched_cianacare_application_and_present_on_the_login_page() throws InterruptedException {
        loginPage.opencianacare();
    }


    @When("user enter a valid mobile number and click Continue")
    public void userEnterAValidMobileNumberAndClickContinue() throws IOException, InterruptedException {
        loginPage.enterValidMobileNumber();
        loginPage.clickOnContinueButton();
    }

    @And("enter the correct OTP and click Verify OTP")
    public void enterTheCorrectOTPAndClickVerifyOTP() throws IOException, InterruptedException {
        loginPage.enterOTP();
        loginPage.clickOnVerifyOTPButton();
    }

    @Then("user should be redirected to the switch clinic screen and select a clinic")
    public void userShouldBeRedirectedToTheSwitchClinicScreenAndSelectAClinic() throws IOException {
        loginPage.selectClinic();
    }

    @And("be successfully logged in and present on the dashboard")
    public void beSuccessfullyLoggedInAndPresentOnTheDashboard() {
        loginPage.verifyUserIsOnDashboard();
    }

    @Then("user loggedout from the cianacare application")
    public void userLoggedoutFromTheCianacareApplication() throws InterruptedException {
        loginPage.logoutFromDahsboard();
        loginPage.clickOnHome();
    }

}
