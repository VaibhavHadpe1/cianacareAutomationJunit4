package steps;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import pomClasses.*;
import utility.Utility;

import java.io.IOException;
import java.util.Collections;

import static steps.Base.driver;

public class BookAppointmentSteps {
    AndroidDriver driver;
    LoginPage loginPage;
    PersonalInformationPage personalInformationPage;
    ServicesPage servicesPage;
    ClinicInformationPage clinicInformationPage;
    BookAppointmentPage bookAppointmentPage;
    public BookAppointmentSteps(){
        driver= Utility.getDriver();
        servicesPage=new ServicesPage(driver);
        clinicInformationPage=new ClinicInformationPage(driver);
        bookAppointmentPage=new BookAppointmentPage(driver);
        personalInformationPage=new PersonalInformationPage(driver);
        loginPage=new LoginPage(driver);
    }
    @When("User clicks on Book button")
    public void user_clicks_on_book_button() {
        bookAppointmentPage.clickOnBookButton();
    }

    @Then("User should be navigated to Book Appointment screen")
    public void user_should_be_navigated_to_book_appointment_screen() throws InterruptedException {
        servicesPage.verifyUserIsPresentOnExpectedScreen("Book Appointment");
    }

    @Given("User navigated and present on Book Appointment screen")
    public void user_navigates_and_present_on_book_appointment_screen() throws InterruptedException {
        bookAppointmentPage.clickOnBookButton();
        servicesPage.verifyUserIsPresentOnExpectedScreen("Book Appointment");
    }

    @When("User selects Quick appointment")
    public void user_selects_quick_appointment() {
        bookAppointmentPage.selectQuickAppointmentOption();
    }

    @When("Clicks on Bill patient button without entering any data")
    public void clicks_on_bill_patient_button_without_entering_any_data() {
        bookAppointmentPage.clickOnBillPatientButton();
    }

    @Then("Error messages should be displayed for all mandatory fields")
    public void error_messages_should_be_displayed_for_all_mandatory_fields() {
        bookAppointmentPage.verifyErrorMessagesOnBookAppointmentScreen();
        bookAppointmentPage.clickOnCancelButton();
    }

    @And("Enters registered mobile number")
    public void enters_registered_mobile_number() throws IOException, InterruptedException {
        personalInformationPage.sendInputToField("Mobile number*",Utility.readDataFromPropertyFile("registeredPatientMobileNumber1"));
    }

    @When("Selects existing patient")
    public void selects_existing_patient() throws InterruptedException {
        Thread.sleep(1000);
        bookAppointmentPage.selectPatientByNameOrRelation("Self");
    }

    @When("Selects In clinic as type of appointment")
    public void selects_in_clinic_as_type_of_appointment() {
        clinicInformationPage.selectOptions(Collections.singletonList("In clinic"));
    }

    @When("Fills in all required details")
    public void fills_in_all_required_details() {

    }

    @When("Clicks on Bill later")
    public void clicks_on_bill_later() {
        bookAppointmentPage.clickOnBillLaterButton();
    }

    @Then("Appointment should be booked successfully")
    public void appointment_should_be_booked_successfully() {
        bookAppointmentPage.verifySuccessFullAppointmentBooking();
        bookAppointmentPage.clickOnOkButtonForSuccessMessage();
    }

    @Then("User should be navigated to dashboard screen")
    public void user_should_be_navigated_to_dashboard_screen() {
        loginPage.verifyUserIsOnDashboard();
    }

    @When("Selects Online as type of appointment")
    public void selects_online_as_type_of_appointment() {
        clinicInformationPage.selectOptions(Collections.singletonList("Online"));
    }

    @When("Clicks on Bill patient")
    public void clicks_on_bill_patient() throws InterruptedException {
        bookAppointmentPage.clickOnBillPatientButton();
        Thread.sleep(2000);
        bookAppointmentPage.slideToPayAmount();
    }

    @When("Selects a family member from patient list")
    public void selects_a_family_member_from_patient_list() {
        bookAppointmentPage.selectPatientByNameOrRelation("Priyanka");
    }

    @When("Enters new mobile number one")
    public void enters_new_mobile_number_one() throws IOException, InterruptedException {
        personalInformationPage.sendInputToField("Mobile number*",Utility.readDataFromPropertyFile("unregisteredPatientMobileNumber1"));
    }

    @When("Enters patient name, gender, and DOB one")
    public void enters_patient_name_gender_and_dob_one() throws InterruptedException, IOException {
        personalInformationPage.sendInputToField("Patient name*",Utility.readDataFromPropertyFile("unregisteredPatientName1"));
        clinicInformationPage.selectOptions(Collections.singletonList(Utility.readDataFromPropertyFile("unregisteredPatientGender1")));
        personalInformationPage.sendInputToField("Age*",Utility.readDataFromPropertyFile("unregisteredPatientAge1"));
    }
    @When("Enters new mobile number two")
    public void enters_new_mobile_number_two() throws IOException, InterruptedException {
        personalInformationPage.sendInputToField("Mobile number*",Utility.readDataFromPropertyFile("unregisteredPatientMobileNumber2"));
    }

    @When("Enters patient name, gender, and DOB two")
    public void enters_patient_name_gender_and_dob_two() throws InterruptedException, IOException {
        personalInformationPage.sendInputToField("Patient name*",Utility.readDataFromPropertyFile("unregisteredPatientName2"));
        clinicInformationPage.selectOptions(Collections.singletonList(Utility.readDataFromPropertyFile("unregisteredPatientGender2")));
        personalInformationPage.sendInputToField("Age*",Utility.readDataFromPropertyFile("unregisteredPatientAge2"));
    }
    @When("Enters new mobile number three")
    public void enters_new_mobile_number_three() throws IOException, InterruptedException {
        personalInformationPage.sendInputToField("Mobile number*",Utility.readDataFromPropertyFile("unregisteredPatientMobileNumber3"));
    }

    @When("Enters patient name, gender, and DOB three")
    public void enters_patient_name_gender_and_dob_three() throws InterruptedException, IOException {
        personalInformationPage.sendInputToField("Patient name*",Utility.readDataFromPropertyFile("unregisteredPatientName3"));
        clinicInformationPage.selectOptions(Collections.singletonList(Utility.readDataFromPropertyFile("unregisteredPatientGender3")));
        personalInformationPage.sendInputToField("Age*",Utility.readDataFromPropertyFile("unregisteredPatientAge3"));
    }
    @When("Enters new mobile number four")
    public void enters_new_mobile_number_four() throws IOException, InterruptedException {
        personalInformationPage.sendInputToField("Mobile number*",Utility.readDataFromPropertyFile("unregisteredPatientMobileNumber4"));
    }

    @When("Enters patient name, gender, and DOB four")
    public void enters_patient_name_gender_and_dob_four() throws InterruptedException, IOException {
        personalInformationPage.sendInputToField("Patient name*",Utility.readDataFromPropertyFile("unregisteredPatientName4"));
        clinicInformationPage.selectOptions(Collections.singletonList(Utility.readDataFromPropertyFile("unregisteredPatientGender4")));
        personalInformationPage.sendInputToField("Age*",Utility.readDataFromPropertyFile("unregisteredPatientAge4"));
    }

    @When("Enters patient name, gender, and DOB five")
    public void enters_patient_name_gender_and_dob_five() throws InterruptedException, IOException {
        personalInformationPage.sendInputToField("Patient name*",Utility.readDataFromPropertyFile("unregisteredPatientName5"));
        clinicInformationPage.selectOptions(Collections.singletonList(Utility.readDataFromPropertyFile("unregisteredPatientGender5")));
        personalInformationPage.sendInputToField("Age*",Utility.readDataFromPropertyFile("unregisteredPatientAge5"));
    }
    @When("Enters new mobile number six")
    public void enters_new_mobile_number_six() throws IOException, InterruptedException {
        personalInformationPage.sendInputToField("Mobile number*",Utility.readDataFromPropertyFile("unregisteredPatientMobileNumber6"));
    }

    @When("Enters patient name, gender, and DOB six")
    public void enters_patient_name_gender_and_dob_six() throws InterruptedException, IOException {
        personalInformationPage.sendInputToField("Patient name*",Utility.readDataFromPropertyFile("unregisteredPatientName6"));
        clinicInformationPage.selectOptions(Collections.singletonList(Utility.readDataFromPropertyFile("unregisteredPatientGender6")));
        personalInformationPage.sendInputToField("Age*",Utility.readDataFromPropertyFile("unregisteredPatientAge6"));
    }

    @When("User selects Schedule appointment")
    public void user_selects_schedule_appointment() {
        bookAppointmentPage.selectScheduleAppointmentOption();
    }

    @When("Selects a time slot")
    public void selects_a_time_slot() throws InterruptedException {
        Utility.customizeScrollByCoordinates(driver,500,1800,500,1400);
        bookAppointmentPage.clickOnAppointmentTimeField();
        bookAppointmentPage.selectTimeSlot();
    }

    @Then("Appointment should be scheduled successfully")
    public void appointment_should_be_scheduled_successfully() {
        bookAppointmentPage.verifySuccessFullAppointmentBooking();
        bookAppointmentPage.clickOnOkButtonForSuccessMessage();
    }

    @When("Selects a family member")
    public void selects_a_family_member() {
        bookAppointmentPage.selectPatientByNameOrRelation("Nisha");
    }

    @When("Selects Add new member from patient dropdown")
    public void selects_add_new_member_from_patient_dropdown() throws IOException, InterruptedException {
        personalInformationPage.sendInputToField("Mobile number*",Utility.readDataFromPropertyFile("registeredPatientMobileNumber1"));
        bookAppointmentPage.selectAddNewMemberOptionFromPatientList();

    }

    @When("Enters all required details")
    public void enters_all_required_details() {

    }

    @When("Adds multiple services")
    public void adds_multiple_services() {

    }

    @When("Edits the services")
    public void edits_the_services() {

    }

    @Then("Appointment should be booked with updated services")
    public void appointment_should_be_booked_with_updated_services() {

    }

    @When("Selects a doctor from the doctor dropdown")
    public void selects_a_doctor_from_the_doctor_dropdown() {

    }

    @When("Enters all required patient details")
    public void enters_all_required_patient_details() {

    }

    @Then("Appointment should be booked with selected doctor")
    public void appointment_should_be_booked_with_selected_doctor() {

    }

    @When("Selects symptoms from dropdown")
    public void selects_symptoms_from_dropdown() {

    }

    @When("Enters custom symptoms")
    public void enters_custom_symptoms() {

    }

    @When("Fills in all required patient details")
    public void fills_in_all_required_patient_details() {

    }

    @Then("Appointment should be booked with symptoms")
    public void appointment_should_be_booked_with_symptoms() {

    }

}
