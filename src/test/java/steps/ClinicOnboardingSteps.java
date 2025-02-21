package steps;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pomClasses.*;
import utility.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ClinicOnboardingSteps {
    AndroidDriver driver;
    LoginPage loginPage;
    SignupPage signupPage;
    PersonalInformationPage personalInformationPage;
    ClinicInformationPage clinicInformationPage;
    LocationInformation locationInformation;
    UserDetailsScreen userDetailsScreen;
    public ClinicOnboardingSteps() throws IOException {
        driver= Utility.getDriver();
        loginPage=new LoginPage(driver);
        signupPage=new SignupPage(driver);
        personalInformationPage=new PersonalInformationPage(driver);
        clinicInformationPage=new ClinicInformationPage(driver);
        locationInformation=new LocationInformation(driver);
        userDetailsScreen=new UserDetailsScreen(driver);
    }
    @Given("The user has launched the application")
    public void the_user_has_launched_the_application() throws InterruptedException {
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

    @Then("The details should be accepted successfully")
    public void the_details_should_be_accepted_successfully() {
        personalInformationPage.verifyAllPersonalInformationIsAccepted();
    }

    @Given("The user has entered valid personal details")
    public void the_user_has_entered_valid_personal_details() {
        personalInformationPage.verifyAllPersonalInformationIsAccepted();
    }

    @When("The user clicks on the continue button")
    public void the_user_clicks_on_the_continue_button() {
        personalInformationPage.clickOnContinue();
    }

    @Then("The user should be navigated to the Clinic information screen")
    public void the_user_should_be_navigated_to_the_clinic_information_screen() {
        clinicInformationPage.verifyUserIsPresentOnClinicInformationScreen();
    }

    @Given("The user is on the Clinic information screen")
    public void the_user_is_on_the_clinic_information_screen() {
        clinicInformationPage.verifyUserIsPresentOnClinicInformationScreen();
    }
    @When("The user enters all required clinic details")
    public void the_user_enters_all_required_clinic_details() throws InterruptedException, IOException {
        clinicInformationPage.uploadDocument("Upload clinic logo");
        clinicInformationPage.selectOptions(Collections.singletonList("Private Sector"));
        personalInformationPage.sendInputToField("Name of central/state council*","Name of central/state council",Utility.readDataFromPropertyFile("name_of_central/state_council"));
        clinicInformationPage.selectOptions(Collections.singletonList("Multi speciality"));
        clinicInformationPage.selectOptions(Arrays.asList("In patient","Out patient","Laboratory"));
        Utility.swipeDown(driver);
        clinicInformationPage.selectOptions(Arrays.asList("Siddha","Yoga","Allopathy"));

    }
    @Then("The clinic details should be accepted successfully")
    public void the_clinic_details_should_be_accepted_successfully() {
        clinicInformationPage.verifyAllClinicInfoIsAccepted();
    }

    @Given("The user has entered required valid clinic details")
    public void the_user_has_entered_required_valid_clinic_details() {
        clinicInformationPage.verifyAllClinicInfoIsAccepted();
    }
    @Then("The user should be navigated to the Address screen")
    public void the_user_should_be_navigated_to_the_address_screen() {
        locationInformation.verifyUserIsPresentOnLocationInformationScreen();
    }

    @Given("The user is on the Address screen")
    public void the_user_is_on_the_address_screen() {
        locationInformation.verifyUserIsPresentOnLocationInformationScreen();
    }
    @When("The user enters all required address details")
    public void the_user_enters_all_required_address_details() throws IOException {
        locationInformation.enterAddress();
    }
    @When("Clicks on the submit button")
    public void clicks_on_the_submit_button() {
        locationInformation.clickOnSubmit();
    }
    @Then("The onboarding process should be completed successfully")
    public void the_onboarding_process_should_be_completed_successfully() throws InterruptedException {
        locationInformation.verifyUserCompletedTheClinicOnboarding();
        userDetailsScreen.verifyUserInformation();
    }
    @Given("account status is pending and user able to logout")
    public void account_status_is_pending_and_user_able_to_logout() {
        userDetailsScreen.clinicVerificationIsPending();
        userDetailsScreen.logoutFromUserDetailsScreen();
        loginPage.verifyLoginScreenLoaded();
    }
}
