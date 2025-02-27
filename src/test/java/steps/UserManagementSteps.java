package steps;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pomClasses.*;
import utility.Utility;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class UserManagementSteps {

    AndroidDriver driver;
    LoginPage loginPage;
    SignupPage signupPage;
    PersonalInformationPage personalInformationPage;
    ClinicInformationPage clinicInformationPage;
    UserListPage userListPage;
    public UserManagementSteps(){
        driver= Utility.getDriver();
        loginPage=new LoginPage(driver);
        signupPage=new SignupPage(driver);
        personalInformationPage=new PersonalInformationPage(driver);
        clinicInformationPage=new ClinicInformationPage(driver);
        userListPage=new UserListPage(driver);
    }

    @Given("The user navigates to the users list screen")
    public void the_user_navigates_to_the_users_list_screen() {
        userListPage.clickOnSettings();
        userListPage.clickOnUsersFeatureOnSettings();
        userListPage.verifyUserIsPresentOnUsersListScreen();
    }
    @Then("The logged-in admin should be displayed in the users list by default")
    public void the_logged_in_admin_should_be_displayed_in_the_users_list_by_default() throws IOException, InterruptedException {
        userListPage.verifyLoggedInAdminIsDisplayed(Utility.readDataFromPropertyFile("loggedInAdminName"));

    }
    @Then("No other users should be shown if no additional admins, doctors, or staff are available")
    public void no_other_users_should_be_shown_if_no_additional_admins_doctors_or_staff_are_available() {
        userListPage.verifyOnlyOneAdminIsPresentOnusersScreen();

    }

    @Given("The user is on the users list screen")
    public void the_user_is_on_the_users_list_screen() {
        userListPage.verifyUserIsPresentOnUsersListScreen();
    }
    @When("The user clicks on Add button and selects Doctor")
    public void the_user_clicks_on_add_button_and_selects_doctor() {
        userListPage.clickOnAddButtonUsersListScreen();
        userListPage.selectDoctorFromUsersListScreen();

    }
    @When("The user enters required doctor details and submits the form")
    public void the_user_enters_required_doctor_details_and_submits_the_form() throws IOException, InterruptedException {
//        personalInformationPage.sendInputToField("Mobile number*",Utility.readDataFromPropertyFile("doctorRegistrationMobileNumber"));
//        personalInformationPage.sendInputToField("First name*",Utility.readDataFromPropertyFile("doctorFirstName"));
//        personalInformationPage.sendInputToField("Last name*",Utility.readDataFromPropertyFile("doctorLastName"));
//        clinicInformationPage.selectOptions(Collections.singletonList("Male"));
//        userListPage.selectDOBOnPersonalInformationScreen();
//        personalInformationPage.sendInputToField("Email*",Utility.readDataFromPropertyFile("doctorEmail"));
//        Utility.swipeDown(driver);
//        clinicInformationPage.uploadDocument("Upload id proof");
//        userListPage.selectLanguagesKnown(Arrays.asList("Telugu","Hindi","English"));
//        userListPage.clickOnBackButtonToCloseDropdown();
//        userListPage.clickOnNext();
//
//          userListPage.verifyUserIsOnEducationScreenAndClickOnAddQualification();
//          userListPage.enterQualificationAndSave();
//
//          userListPage.verifyUserIspresentOnMedicalRegistrationScreen();
//          userListPage.enterMedicalRegistrationAndNavigatesToNext();

//          userListPage.verifyUserIsPresentOnMedicalSpecialityScreen();
//          userListPage.enterMedicalSpecialityAndNavigatesToNext();

//          userListPage.verifyUserIsPresentOnAddressScreen();
//          userListPage.enterAddressAndNavigatesToNext();

          userListPage.verifyUserIsPresentOnPrivilegesScreen();
          userListPage.verifyDoctorPrivileges();


    }
    @Then("The doctor should be added successfully")
    public void the_doctor_should_be_added_successfully() {

    }
    @Then("The newly added doctor should be visible in the users list")
    public void the_newly_added_doctor_should_be_visible_in_the_users_list() {

    }

    @When("The user clicks on Add button and selects Staff")
    public void the_user_clicks_on_add_button_and_selects_staff() {

    }
    @When("The user enters required staff details and submits the form")
    public void the_user_enters_required_staff_details_and_submits_the_form() {

    }
    @Then("The staff should be added successfully")
    public void the_staff_should_be_added_successfully() {

    }
    @Then("The newly added staff should be visible in the users list")
    public void the_newly_added_staff_should_be_visible_in_the_users_list() {

    }

    @When("The user enters required admin details and submits the form")
    public void the_user_enters_required_admin_details_and_submits_the_form() {

    }
    @Then("The admin should be added successfully")
    public void the_admin_should_be_added_successfully() {

    }
    @Then("The newly added admin should be visible in the users list")
    public void the_newly_added_admin_should_be_visible_in_the_users_list() {

    }
    @Then("The list of added users should be displayed")
    public void the_list_of_added_users_should_be_displayed() {

    }
    @When("The user enters a name in the search bar")
    public void the_user_enters_a_name_in_the_search_bar() {

    }
    @Then("The matching users should be displayed in the results")
    public void the_matching_users_should_be_displayed_in_the_results() {

    }

    @When("The user searches for a non-existing user")
    public void the_user_searches_for_a_non_existing_user() {

    }
    @Then("The message No users found should be displayed")
    public void the_message_no_users_found_should_be_displayed() {

    }
    @When("The user views their own profile")
    public void the_user_views_their_own_profile() {

    }
    @Then("The edit option should not be available")
    public void the_edit_option_should_not_be_available() {

    }
    @When("The user selects a doctor and clicks on Edit Privileges")
    public void the_user_selects_a_doctor_and_clicks_on_edit_privileges() {

    }
    @And("The user modifies the doctor privileges and saves the changes")
    public void the_user_modifies_the_doctor_privileges_and_saves_the_changes() {

    }
    @Then("The updated doctor privileges should be saved successfully")
    public void the_updated_doctor_privileges_should_be_saved_successfully() {

    }
    @When("The user selects a staff member and clicks on Edit Privileges")
    public void the_user_selects_a_staff_member_and_clicks_on_edit_privileges() {

    }
    @When("The user modifies the staff privileges and saves the changes")
    public void the_user_modifies_the_staff_privileges_and_saves_the_changes() {

    }
    @Then("The updated staff privileges should be saved successfully")
    public void the_updated_staff_privileges_should_be_saved_successfully() {

    }

    @When("The user selects an admin and clicks on Edit Privileges")
    public void the_user_selects_an_admin_and_clicks_on_edit_privileges() {

    }
    @When("The user cannot modifies the privileges of admin")
    public void the_user_cannot_modifies_the_privileges_of_admin() {

    }
    @When("The user selects a Doctor or Staff and clicks Delete")
    public void the_user_selects_a_doctor_or_staff_and_clicks_delete() {

    }
    @When("Confirms the deletion")
    public void confirms_the_deletion() {

    }
    @Then("The selected user should be removed from the users list")
    public void the_selected_user_should_be_removed_from_the_users_list() {

    }

    @Given("There are multiple admins in the clinic")
    public void there_are_multiple_admins_in_the_clinic() {

    }
    @When("The user selects an admin and clicks Delete")
    public void the_user_selects_an_admin_and_clicks_delete() {

    }
    @Then("The admin should be removed from the users list")
    public void the_admin_should_be_removed_from_the_users_list() {

    }

    @Given("There is only one admin in the clinic")
    public void there_is_only_one_admin_in_the_clinic() {

    }
    @When("The user attempts to delete the admin")
    public void the_user_attempts_to_delete_the_admin() {

    }
    @Then("The system should display an error message At least one admin is required")
    public void the_system_should_display_an_error_message_at_least_one_admin_is_required() {

    }

    @Given("The doctor logs into the application independently")
    public void the_doctor_logs_into_the_application_independently() {

    }
    @Then("The doctor's dashboard should be displayed correctly")
    public void the_doctor_s_dashboard_should_be_displayed_correctly() {

    }
    @Then("The doctor's details should be visible and accurate")
    public void the_doctor_s_details_should_be_visible_and_accurate() {

    }

    @Given("The staff logs into the application independently")
    public void the_staff_logs_into_the_application_independently() {

    }
    @Then("The staff's dashboard should be displayed correctly")
    public void the_staff_s_dashboard_should_be_displayed_correctly() {

    }
    @Then("The staff's details should be visible and accurate")
    public void the_staff_s_details_should_be_visible_and_accurate() {

    }

    @Given("The admin logs into the application independently")
    public void the_admin_logs_into_the_application_independently() {

    }
    @Then("The admin's dashboard should be displayed correctly")
    public void the_admin_s_dashboard_should_be_displayed_correctly() {

    }
    @Then("The admin's details should be visible and accurate")
    public void the_admin_s_details_should_be_visible_and_accurate() {

    }
}
