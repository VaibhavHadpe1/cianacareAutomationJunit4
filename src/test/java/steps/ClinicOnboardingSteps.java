package steps;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pomClasses.PersonalInformationPage;
import pomClasses.SignupPage;
import pomClasses.LoginPage;
import utility.Utility;

import java.io.IOException;

public class ClinicOnboardingSteps {
    AndroidDriver driver;
    LoginPage loginPage;
    SignupPage signupPage;
    PersonalInformationPage personalInformationPage;
    public ClinicOnboardingSteps(){
        driver= Utility.getDriver();
        loginPage=new LoginPage(driver);
        signupPage=new SignupPage(driver);
        personalInformationPage=new PersonalInformationPage(driver);
    }
    @Given("The user has launched the application")
    public void the_user_has_launched_the_application() {
        loginPage.opencianacare();
    }

    @When("The application loads successfully and login screen displayed")
    public void the_application_loads_successfully_and_login_screen_displayed() {
        loginPage.verifyLoginScreenLoaded();
    }

    @Then("The login screen should contain the Sign Up option")
    public void the_login_screen_should_contain_the_sign_up_option() {
        loginPage.verifySignUpButtonIsDisplayed();
    }

    @Given("The user is on the login screen")
    public void the_User_Is_On_The_Login_Screen() {
        loginPage.verifyLoginScreenLoaded();
    }

    @When("The user clicks on the Sign Up button")
    public void the_User_Clicks_On_The_Sign_Up_Button()
    {
       loginPage.clickOnSignUpButton();
    }

    @Then("The user should be navigated to the signup screen")
    public void the_User_Should_Be_Navigated_To_The_Signup_Screen() {
        signupPage.verifyUserIsPresentOnSignUpScreen();
    }

    @Given("The user is on the sign-up screen")
    public void theUserIsOnTheSignUpScreen() {
        signupPage.verifyUserIsPresentOnSignUpScreen();
    }

    @When("The user enters a valid mobile number and clinic registration number")
    public void theUserEntersAValidMobileNumberAndClinicRegistrationNumber() throws IOException, InterruptedException {
        signupPage.enterRegistrationMobileNumber();
        signupPage.enterClinicRegistrationNumber();
    }

    @Then("The entered details should be accepted")
    public void theEnteredDetailsShouldBeAccepted() throws InterruptedException {
        signupPage.clickOnContinueButton();
        signupPage.verifyEnteredDetailsAcceptedOnSignUpPage();
    }

    @Given("The user is on the verifyOTP screen")
    public void the_user_is_on_the_verify_otp_screen() {
        signupPage.verifyUserIsPresentOnOTPScreen();
    }
    @When("The user verify the OTP and click on Verify OTP")
    public void the_user_verify_the_otp_and_click_on_verify_otp() throws IOException, InterruptedException {
        loginPage.enterOTP();
        loginPage.clickOnVerifyOTPButton();
    }
    @Then("The user should be navigated to the Personal Information screen")
    public void the_user_should_be_navigated_to_the_personal_information_screen() {
        personalInformationPage.verifyUserIsPresentOnPersonalInformationScreen();
    }

    @Given("The user is on the Personal Information screen")
    public void the_user_is_on_the_personal_information_screen() {
        personalInformationPage.verifyUserIsPresentOnPersonalInformationScreen();
    }

    @When("The user enters all required details")
    public void the_user_enters_all_required_details() throws IOException, InterruptedException {
        personalInformationPage.sendInputToField("First name*","First name",Utility.readDataFromPropertyFile("firstName"));
        personalInformationPage.sendInputToField("Last name*","Last name",Utility.readDataFromPropertyFile("lastName"));
        personalInformationPage.sendInputToField("Email address*","Email address",Utility.readDataFromPropertyFile("emailAddress"));
        personalInformationPage.sendInputToField("Clinic name*","Clinic name",Utility.readDataFromPropertyFile("registrationclinicName"));
    }

    @Then("The details should be saved successfully")
    public void the_details_should_be_saved_successfully() {

    }
}
