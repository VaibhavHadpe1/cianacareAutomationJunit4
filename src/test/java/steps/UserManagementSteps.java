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
        userListPage.verifyAdminIsDisplayed(Utility.readDataFromPropertyFile("fullName"));

    }
    @Then("No other users should be shown if no additional admins, doctors, or staff are available")
    public void no_other_users_should_be_shown_if_no_additional_admins_doctors_or_staff_are_available() {
        userListPage.verifyOnlyOneAdminIsPresentOnUsersScreen();

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
        personalInformationPage.sendInputToField("Mobile number*",Utility.readDataFromPropertyFile("doctorRegistrationMobileNumber"));
        personalInformationPage.sendInputToField("First name*",Utility.readDataFromPropertyFile("doctorFirstName"));
        personalInformationPage.sendInputToField("Last name*",Utility.readDataFromPropertyFile("doctorLastName"));
        clinicInformationPage.selectOptions(Collections.singletonList("Male"));
        userListPage.selectDOBOnPersonalInformationScreen();
        personalInformationPage.sendInputToField("Email*",Utility.readDataFromPropertyFile("doctorEmail"));
        Utility.swipeDown(driver);
        clinicInformationPage.uploadDocument("Upload id proof");
        userListPage.selectLanguagesKnown(Arrays.asList("Telugu","Hindi","English"));
        userListPage.clickOnBackButtonToCloseDropdown();
        userListPage.clickOnNext();

          userListPage.verifyUserIsOnEducationScreenAndClickOnAddQualification();
          userListPage.enterQualificationAndSave();

          userListPage.verifyUserIspresentOnMedicalRegistrationScreen();
          userListPage.enterMedicalRegistrationAndNavigatesToNext();

          userListPage.verifyUserIsPresentOnMedicalSpecialityScreen();
          userListPage.enterMedicalSpecialityAndNavigatesToNext();

          userListPage.verifyUserIsPresentOnAddressScreen();
          userListPage.enterAddressAndNavigatesToNext();

          userListPage.verifyUserIsPresentOnPrivilegesScreen();
          userListPage.verifyDoctorPrivileges();
          userListPage.clickOnNext();

          userListPage.verifyUserIsPresentOnSignatureScreen();
          userListPage.uploadSignature();

          userListPage.verifyUserIsPresentOnClinicScreen();
          userListPage.enterClinicFeesAndSubmit();


    }
    @Then("The doctor should be added successfully")
    public void the_doctor_should_be_added_successfully() {
        userListPage.verifyUserIsPresentOnUsersListScreen();

    }
    @Then("The newly added doctor should be visible in the users list")
    public void the_newly_added_doctor_should_be_visible_in_the_users_list() throws IOException, InterruptedException {
        userListPage.verifyDoctorIsDisplayed(Utility.readDataFromPropertyFile("doctorFullName"));

    }

    @When("The user clicks on Add button and selects Staff")
    public void the_user_clicks_on_add_button_and_selects_staff() throws InterruptedException {
        userListPage.clickOnAddButtonUsersListScreen();
        Thread.sleep(1000);
        userListPage.selectStaffFromUsersListScreen();

    }
    @When("The user enters required staff details and submits the form")
    public void the_user_enters_required_staff_details_and_submits_the_form() throws IOException, InterruptedException {
        personalInformationPage.sendInputToField("Mobile number*",Utility.readDataFromPropertyFile("staffRegistrationMobileNumber"));
        personalInformationPage.sendInputToField("First name*",Utility.readDataFromPropertyFile("staffFirstName"));
        personalInformationPage.sendInputToField("Last name*",Utility.readDataFromPropertyFile("staffLastName"));
        clinicInformationPage.selectOptions(Collections.singletonList("Female"));
        personalInformationPage.sendInputToField("Email*",Utility.readDataFromPropertyFile("staffEmail"));
        userListPage.clickOnNext();
        userListPage.verifyStaffPrivileges();
        userListPage.clickOnSubmit();


    }
    @Then("The staff should be added successfully")
    public void the_staff_should_be_added_successfully() {
        userListPage.verifyUserIsPresentOnUsersListScreen();

    }
    @Then("The newly added staff should be visible in the users list")
    public void the_newly_added_staff_should_be_visible_in_the_users_list() throws IOException, InterruptedException {
        userListPage.verifyStaffIsDisplayed(Utility.readDataFromPropertyFile("staffFullName"));
    }

    @When("The user enters required admin details and submits the form")
    public void the_user_enters_required_admin_details_and_submits_the_form() throws IOException, InterruptedException {
        personalInformationPage.sendInputToField("Mobile number*",Utility.readDataFromPropertyFile("adminRegistrationMobileNumber"));
        personalInformationPage.sendInputToField("First name*",Utility.readDataFromPropertyFile("adminFirstName"));
        personalInformationPage.sendInputToField("Last name*",Utility.readDataFromPropertyFile("adminLastName"));
        clinicInformationPage.selectOptions(Collections.singletonList("Female"));
        personalInformationPage.sendInputToField("Email*",Utility.readDataFromPropertyFile("adminEmail"));
        userListPage.enableAddAdminToggleButton();
        userListPage.clickOnNext();
        userListPage.clickOnSubmit();
    }
    @Then("The admin should be added successfully")
    public void the_admin_should_be_added_successfully() {
        userListPage.verifyUserIsPresentOnUsersListScreen();

    }
    @Then("The newly added admin should be visible in the users list")
    public void the_newly_added_admin_should_be_visible_in_the_users_list() throws IOException, InterruptedException {
        userListPage.verifyAdminIsDisplayed(Utility.readDataFromPropertyFile("adminFullName"));
    }
    @Then("The list of added users should be displayed")
    public void the_list_of_added_users_should_be_displayed() {
        userListPage.allUserListOnUsersListScreen();

    }
    @When("The user enters a name in the search bar")
    public void the_user_enters_a_name_in_the_search_bar() throws IOException {
        userListPage.search(Utility.readDataFromPropertyFile("searchAvailableUser"));

    }
    @Then("The matching users should be displayed in the results")
    public void the_matching_users_should_be_displayed_in_the_results() throws InterruptedException, IOException {
        userListPage.observeUserDisplayed(Utility.readDataFromPropertyFile("searchAvailableUser"));
        userListPage.clearSearchInput();

    }

    @When("The user searches for a non-existing user")
    public void the_user_searches_for_a_non_existing_user() throws IOException {
        userListPage.search(Utility.readDataFromPropertyFile("searchNonAvailableUser"));

    }
    @Then("The message No users found should be displayed")
    public void the_message_no_users_found_should_be_displayed() throws IOException, InterruptedException {
        userListPage.observeUserDisplayed(Utility.readDataFromPropertyFile("searchNonAvailableUser"));
        userListPage.clearSearchInput();


    }
    @Then("The edit option for logged in user should not be available from user list")
    public void the_edit_option_for_logged_in_user_should_not_be_available_from_user_list() throws IOException {
        userListPage.verifyLoggedInUserNotEditSelfProfileFromUserList(Utility.readDataFromPropertyFile("registrationMobileNumber"));
    }
    @When("The user selects a doctor and clicks on Edit Privileges")
    public void the_user_selects_a_doctor_and_clicks_on_edit_privileges() throws IOException {
        userListPage.selectUserAndClickToEdit(Utility.readDataFromPropertyFile("doctorFullName"));
    }
    @And("The user modifies the doctor privileges and saves the changes")
    public void the_user_modifies_the_doctor_privileges_and_saves_the_changes() throws InterruptedException {
        userListPage.listOfUsersPrivileges();
        userListPage.enablePrivilege("Appointment management");
        userListPage.disablePrivilege("Subscriptions");
        userListPage.clickOnSave();
    }
    @Then("The updated doctor privileges should be saved successfully")
    public void the_updated_doctor_privileges_should_be_saved_successfully() throws InterruptedException {
        userListPage.verifyExpectedEnabledPrivilegesAreDisplayed("Appointment management");
        userListPage.listOfUsersPrivileges();
        userListPage.clickOnBackButtonToCloseDropdown();
    }
    @When("The user selects a staff member and clicks on Edit Privileges")
    public void the_user_selects_a_staff_member_and_clicks_on_edit_privileges() throws IOException {
        userListPage.selectUserAndClickToEdit(Utility.readDataFromPropertyFile("staffFullName"));

    }
    @When("The user modifies the staff privileges and saves the changes")
    public void the_user_modifies_the_staff_privileges_and_saves_the_changes() throws InterruptedException {
        userListPage.listOfUsersPrivileges();
        userListPage.enablePrivilege("Subscriptions");
        userListPage.enablePrivilege("Schedules");
        Utility.swipeDown(driver);
        userListPage.enablePrivilege("User management");
        userListPage.clickOnSave();
        userListPage.listOfUsersPrivileges();
    }
    @Then("The updated staff privileges should be saved successfully")
    public void the_updated_staff_privileges_should_be_saved_successfully() throws InterruptedException {
        userListPage.verifyExpectedEnabledPrivilegesAreDisplayed("Subscriptions");
        userListPage.verifyExpectedEnabledPrivilegesAreDisplayed("Schedules");
        userListPage.listOfUsersPrivileges();
        userListPage.clickOnBackButtonToCloseDropdown();

    }

    @When("The user selects an admin and clicks on Edit Privileges")
    public void the_user_selects_an_admin_and_clicks_on_edit_privileges() throws InterruptedException, IOException {
        userListPage.selectUserAndClickToEdit(Utility.readDataFromPropertyFile("adminFullName"));
        userListPage.listOfUsersPrivileges();
        userListPage.clickOnEditPrivilegesButton();
    }
    @When("The user cannot modifies the privileges of admin")
    public void the_user_cannot_modifies_the_privileges_of_admin() throws InterruptedException {
        userListPage.verifyAdminUsersPrivilegesNonEditable();
        userListPage.clickOnSave();
        userListPage.listOfUsersPrivileges();
        userListPage.clickOnBackButtonToCloseDropdown();
    }
    @When("The user selects a Doctor or Staff and clicks Delete")
    public void the_user_selects_a_doctor_or_staff_and_clicks_delete() throws IOException {
        userListPage.selectUserAndClickOnDelete(Utility.readDataFromPropertyFile("toBeDeletedUserName"));

    }
    @When("Confirms the deletion")
    public void confirms_the_deletion() {
        userListPage.confirmDelete();

    }
    @Then("The selected user should be removed from the users list")
    public void the_selected_user_should_be_removed_from_the_users_list() throws InterruptedException, IOException {
        userListPage.observeDeletedUserNotDisplayed(Utility.readDataFromPropertyFile("toBeDeletedUserName"));

    }

    @Given("There are multiple admins in the clinic and displayed on UserList")
    public void there_are_multiple_admins_in_the_clinic_And_Displayed_On_UserList() {
        userListPage.verifyUserIsPresentOnUsersListScreen();
        userListPage.multipleAdminsAreAvailable();

    }
    @When("The user selects an admin and clicks Delete")
    public void the_user_selects_an_admin_and_clicks_delete() throws IOException {
        userListPage.selectUserAndClickOnDelete(Utility.readDataFromPropertyFile("adminToBeDeleted"));

    }
    @Then("The admin should be removed from the users list")
    public void the_admin_should_be_removed_from_the_users_list() throws IOException, InterruptedException {
        userListPage.observeDeletedUserNotDisplayed(Utility.readDataFromPropertyFile("adminToBeDeleted"));
    }

    @Given("There is only one admin in the clinic and displayed on UserList")
    public void there_is_only_one_admin_in_the_clinic_And_Displayed_On_UserList() throws IOException, InterruptedException {
        userListPage.clickOnBackButtonToCloseDropdown();
        loginPage.logoutFromDashboard();
        userListPage.loginToUserAccount(Utility.readDataFromPropertyFile("staffRegistrationMobileNumber"));
        userListPage.clickOnSettings();
        userListPage.clickOnUsersFeatureOnSettings();
        userListPage.verifyOnlyOneAdminIsPresentOnUsersScreen();

    }
    @When("The user attempts to delete the admin")
    public void the_user_attempts_to_delete_the_admin() throws IOException {
        userListPage.selectUserAndClickOnDelete(Utility.readDataFromPropertyFile("fullName"));
        userListPage.confirmDelete();

    }
    @Then("The system should display an error message At least one admin is required")
    public void the_system_should_display_an_error_message_at_least_one_admin_is_required() {
        userListPage.verifyErrorForAtLeastOneAdmin();

    }

    @Given("The doctor logs into the application independently")
    public void the_doctor_logs_into_the_application_independently() throws IOException, InterruptedException {
        userListPage.clickOnBackButtonToCloseDropdown();
        loginPage.logoutFromDashboard();
        userListPage.loginToUserAccount(Utility.readDataFromPropertyFile("doctorRegistrationMobileNumber"));

    }
    @Then("The doctor's dashboard should be displayed correctly")
    public void the_doctor_s_dashboard_should_be_displayed_correctly() {
        loginPage.verifyUserIsOnDashboard();

    }
    @Then("The doctor's details should be visible and accurate")
    public void the_doctor_s_details_should_be_visible_and_accurate() throws IOException {
        userListPage.verifyUserProfileDetailsOnDashboard(Utility.readDataFromPropertyFile("doctorFullName"));
    }

    @Given("The staff logs into the application independently")
    public void the_staff_logs_into_the_application_independently() throws InterruptedException, IOException {
        loginPage.logoutFromDashboard();
        userListPage.loginToUserAccount(Utility.readDataFromPropertyFile("staffRegistrationMobileNumber"));
    }
    @Then("The staff's dashboard should be displayed correctly")
    public void the_staff_s_dashboard_should_be_displayed_correctly() {
        loginPage.verifyUserIsOnDashboard();
    }
    @Then("The staff's details should be visible and accurate")
    public void the_staff_s_details_should_be_visible_and_accurate() throws IOException {
        userListPage.verifyUserProfileDetailsOnDashboard(Utility.readDataFromPropertyFile("staffFullName"));
    }

    @Given("The admin logs into the application independently")
    public void the_admin_logs_into_the_application_independently() throws InterruptedException, IOException {
        loginPage.logoutFromDashboard();
        userListPage.loginToUserAccount(Utility.readDataFromPropertyFile("registrationMobileNumber"));
    }
    @Then("The admin's dashboard should be displayed correctly")
    public void the_admin_s_dashboard_should_be_displayed_correctly() {
        loginPage.verifyUserIsOnDashboard();

    }
    @Then("The admin's details should be visible and accurate")
    public void the_admin_s_details_should_be_visible_and_accurate() throws IOException {
        userListPage.verifyUserProfileDetailsOnDashboard(Utility.readDataFromPropertyFile("fullName"));
    }

    @And("The user enters required existing staff details and submits the form")
    public void theUserEntersRequiredExistingStaffDetailsAndSubmitsTheForm() throws IOException, InterruptedException {
        userListPage.addExistingStaffDetails(Utility.readDataFromPropertyFile("existingStaffMobileNumber"));
        userListPage.enablePrivilege("Appointment management");
        userListPage.enablePrivilege("Subscriptions");
        userListPage.clickOnSubmit();
    }

    @And("The added existing staff should be visible in the users list")
    public void theAddedExistingStaffShouldBeVisibleInTheUsersList() throws IOException, InterruptedException {
        userListPage.verifyStaffIsDisplayed(Utility.readDataFromPropertyFile("existingStaffFullName"));
    }
}
