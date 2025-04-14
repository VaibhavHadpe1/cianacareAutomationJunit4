package steps;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pomClasses.*;
import utility.Utility;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PatientScreeningSteps {
    AndroidDriver driver;
    PatientScreeningPage patientScreeningPage;
    ServicesPage servicesPage;
    ClinicInformationPage clinicInformationPage;
    InvoicePage invoicePage;
    TemplatePage templatePage;
    public PatientScreeningSteps(){
        driver= Utility.getDriver();
        patientScreeningPage=new PatientScreeningPage(driver);
        servicesPage=new ServicesPage(driver);
        clinicInformationPage=new ClinicInformationPage(driver);
        invoicePage=new InvoicePage(driver);
        templatePage=new TemplatePage(driver);
    }
    @When("The user clicks on Appointments")
    public void the_user_clicks_on_appointments() {
        patientScreeningPage.clickOnAppointmentsFeature();
    }

    @And("Select the appointments")
    public void Select_the_appointments() throws InterruptedException {
        patientScreeningPage.selectAppointment("09:45 AM");
    }

    @And("The user initiates an appointment")
    public void the_user_initiates_an_appointment() throws InterruptedException {
        patientScreeningPage.checkAppointmentStatus();
        patientScreeningPage.initiateTheAppointment();
    }

    @Then("The user should be navigated to the Patient Screening page")
    public void The_user_should_be_navigated_to_the_patient_screening_page() throws InterruptedException {
        servicesPage.verifyUserIsPresentOnExpectedScreen("Patient Screening");
    }

    @Given("The user is on the Patient Screening page")
    public void TheUserIsOnThePatientScreeningPage() throws InterruptedException {
        servicesPage.verifyUserIsPresentOnExpectedScreen("Patient Screening");
    }

    @Then("All components of patient screening should be visible")
    public void All_Components_Of_PatientScreening_Should_Be_Visible() throws InterruptedException {
        patientScreeningPage.verifyExpectedPatientScreeningFeaturesAreDisplayed1(Arrays.asList("HOPI","Vitals","Diagnosis","Prescribe","Investigations","Patient Instructions","Overview","Assessment","Diet plan","Refer a doctor","Follow Up"));
    }

    @When("The user click on HOPI")
    public void the_user_click_on_hopi() {
       Utility.customizeScrollByCoordinates(driver,500,900,500,1800);
       clinicInformationPage.selectOptions(Collections.singletonList("HOPI"));
    }

    @When("Searches and selects a predefined HOPI")
    public void searches_and_selects_a_predefined_hopi() throws InterruptedException {
        patientScreeningPage.searchAndSelect("Fever");
        patientScreeningPage.searchAndSelect("Cold");
    }

    @Then("The selected HOPI should be added to the list")
    public void the_selected_hopi_should_be_added_to_the_list() throws InterruptedException {
        patientScreeningPage.addedValues(Arrays.asList("Fever","Cold"));
    }

    @Given("The user is on the HOPI screen")
    public void the_user_is_on_the_hopi_screen() throws InterruptedException {
        servicesPage.verifyUserIsPresentOnExpectedScreen("Hopi");
    }

    @When("The user removes a HOPI using the cross icon")
    public void the_user_removes_a_hopi_using_the_cross_icon() {
        patientScreeningPage.removeDataUsingRemoveIcon("fever");
    }

    @Then("The HOPI should be removed from the list")
    public void the_hopi_should_be_removed_from_the_list() throws InterruptedException {
        patientScreeningPage.verifyRemovedValuesAreNotDisplayed(Collections.singletonList("Fever"));
    }

    @When("The user clicks the Clear All button")
    public void the_user_clicks_the_clear_all_button() {
        patientScreeningPage.clickOnClearAll();
    }

    @Then("All HOPIs should be removed")
    public void all_hop_is_should_be_removed() throws InterruptedException {
        patientScreeningPage.verifyRemovedValuesAreNotDisplayed(Arrays.asList("Fever","Cold"));
    }

    @When("The user types a custom HOPI")
    public void the_user_types_a_custom_hopi() throws InterruptedException {
        patientScreeningPage.searchAndSelect("ABCD");
    }

    @Then("The custom HOPI should be added")
    public void the_custom_hopi_should_be_added() throws InterruptedException {
        patientScreeningPage.addedValues(Collections.singletonList("ABCD"));
    }

    @When("The user adds HOPI using quick type")
    public void the_user_adds_hopi_using_quick_type() {
        patientScreeningPage.addValuesFromQuickType("cold");
    }

    @Then("The selected HOPI should be added")
    public void the_selected_hopi_should_be_added() throws InterruptedException {
        patientScreeningPage.addedValues(Arrays.asList("ABCD","cold"));
    }

    @When("The user clicks Next on HOPI screen")
    public void the_user_clicks_next_on_hopi_screen() {
        patientScreeningPage.clickOnNext();
    }

    @Then("The user should navigate to the Vitals screen")
    public void the_user_should_navigate_to_the_vitals_screen() throws InterruptedException {
        servicesPage.verifyUserIsPresentOnExpectedScreen("Vitals");
    }

    @Given("The user is on the Vitals screen")
    public void the_user_is_on_the_vitals_screen() throws InterruptedException {
        servicesPage.verifyUserIsPresentOnExpectedScreen("Vitals");
    }

    @When("The user add vitals")
    public void the_user_add_vitals() throws InterruptedException {
        patientScreeningPage.addVitals("Heart rate");
    }

    @Then("The vitals should be saved successfully")
    public void the_vitals_should_be_saved_successfully() {

    }

    @When("The user clicks Next on Vitals screen")
    public void the_user_clicks_next_on_vitals_screen() {
        patientScreeningPage.clickOnNext();
    }

    @Then("The user should navigate to the Diagnosis screen")
    public void the_user_should_navigate_to_the_diagnosis_screen() throws InterruptedException {
        servicesPage.verifyUserIsPresentOnExpectedScreen("Diagnosis");
    }

    @Given("The user is on the Diagnosis screen")
    public void the_user_is_on_the_diagnosis_screen() throws InterruptedException {
        servicesPage.verifyUserIsPresentOnExpectedScreen("Diagnosis");
    }

    @When("The user searches and adds a diagnosis")
    public void the_user_searches_and_adds_a_diagnosis() throws InterruptedException {
        patientScreeningPage.searchAndSelect("Activity,running");
        patientScreeningPage.searchAndSelect("Activity,rugby");
    }

    @Then("The diagnosis should be added to the list")
    public void the_diagnosis_should_be_added_to_the_list() throws InterruptedException {
        patientScreeningPage.addedValues(Arrays.asList("Activity,running 0", "Activity,rugby 0"));
    }

    @When("The user removes a diagnosis using the cross icon")
    public void the_user_removes_a_diagnosis_using_the_cross_icon() {
        patientScreeningPage.removeDataUsingRemoveIcon("Activity, running");
    }

    @Then("Diagnosis should be removed from the list")
    public void diagnosis_should_be_removed_from_the_list() throws InterruptedException {
        patientScreeningPage.verifyRemovedValuesAreNotDisplayed(Collections.singletonList("Activity,running"));
    }

    @Then("All diagnoses should be removed")
    public void all_diagnoses_should_be_removed() throws InterruptedException {
        patientScreeningPage.verifyRemovedValuesAreNotDisplayed(Arrays.asList("Activity,running 0", "Activity,rugby 0"));
    }

    @When("The user adds a custom diagnosis")
    public void the_user_adds_a_custom_diagnosis() throws InterruptedException {
       patientScreeningPage.searchAndSelect("DiagnosisCustomValue");
    }

    @Then("Custom diagnosis should be added")
    public void custom_diagnosis_should_be_added() throws InterruptedException {
        patientScreeningPage.addedValues(Collections.singletonList("DiagnosisCustomValue 0"));
    }

    @When("The user adds diagnosis using quick type")
    public void the_user_adds_diagnosis_using_quick_type() {
        patientScreeningPage.addValuesFromQuickType("Adverse effects,");
    }

    @Then("It should be added")
    public void it_should_be_added() throws InterruptedException {
        patientScreeningPage.addedValues(Arrays.asList("DiagnosisCustomValue 0","Adverse effects, not elsewhere classified 0"));
    }

    @When("The user clicks on diagnosis history")
    public void the_user_clicks_on_diagnosis_history() {
        patientScreeningPage.clickOnHistory();
    }

    @Then("Relevant diagnosis information should be displayed")
    public void relevant_diagnosis_information_should_be_displayed() {
        patientScreeningPage.verifyHistoryOrDefaultMessage("Diagnosis History Not Available");
        Utility.tapOutsideToCloseBottomSheet(driver,400,150);
    }

    @When("The user clicks Next on Diagnosis screen")
    public void the_user_clicks_next_on_diagnosis_screen() {
        patientScreeningPage.clickOnNext();
    }

    @Then("The user should navigate to the Prescribe screen")
    public void the_user_should_navigate_to_the_prescribe_screen() throws InterruptedException {
        servicesPage.verifyUserIsPresentOnExpectedScreen("Prescribe");
    }

    @Given("The user is on the Prescribe screen")
    public void the_user_is_on_the_prescribe_screen() throws InterruptedException {
        servicesPage.verifyUserIsPresentOnExpectedScreen("Prescribe");
    }

    @When("The user searches and adds medicines")
    public void the_user_searches_and_adds_medicines() throws InterruptedException {
        patientScreeningPage.searchAndSelect("Dolodart");
        templatePage.clickOnSaveButtonOfBottomSheet();
    }

    @Then("The medicines should be added to the list")
    public void the_medicines_should_be_added_to_the_list() throws InterruptedException {
        patientScreeningPage.addedValues(Collections.singletonList("Dolodart"));

    }

    @When("The user removes a medicine using the cross icon and Clear All button")
    public void the_user_removes_a_medicine_using_the_cross_icon_and_clear_all_button() {
        patientScreeningPage.removeDataUsingRemoveIcon("Dolodart");
    }

    @Then("The medicines should be removed")
    public void the_medicines_should_be_removed() throws InterruptedException {
        patientScreeningPage.verifyRemovedValuesAreNotDisplayed(Collections.singletonList("Dolodart"));
    }

    @When("The user adds a custom medicine and from quick type")
    public void the_user_adds_a_custom_medicine_and_from_quick_type() throws InterruptedException {
        patientScreeningPage.searchAndSelect("Azithromycin1");
        templatePage.clickOnSaveButtonOfBottomSheet();
        patientScreeningPage.addValuesFromQuickType("A Bec L");
        Thread.sleep(1000);
        templatePage.clickOnSaveButtonOfBottomSheet();
    }

    @Then("The medicine should be added successfully")
    public void the_medicine_should_be_added_successfully() throws InterruptedException {
        Thread.sleep(2000);
        patientScreeningPage.addedValues(Arrays.asList("Azithromycin1","A Bec L"));
    }

    @When("A template is available and displayed")
    public void a_template_is_available_and_displayed() throws InterruptedException {
        patientScreeningPage.isTemplateAvailable();
    }

    @Then("The user should able to apply and medicines should be added")
    public void The_user_should_able_to_apply_and_medicines_should_be_added() throws InterruptedException {
        patientScreeningPage.applyTemplateAndViewMedicineNames("Acne varioliformis");
    }

    @When("The user opens prescription history and selects a medicine")
    public void the_user_opens_prescription_history_and_selects_a_medicine() {
        patientScreeningPage.addMedicineTroughHistory();
    }

    @Then("The medicine should be added")
    public void the_medicine_should_be_added() throws InterruptedException {
        patientScreeningPage.verifyAddedMedicineThroughHistoryDisplayed();
    }

    @When("The user clicks Next from Prescribe screen")
    public void the_user_clicks_next_from_prescribe_screen() {
        patientScreeningPage.clickOnNext();
    }

    @Then("The user should navigate to the Investigation screen")
    public void the_user_should_navigate_to_the_investigation_screen() throws InterruptedException {
        servicesPage.verifyUserIsPresentOnExpectedScreen("Investigations");
    }

    @Given("The user is on the Investigation screen")
    public void the_user_is_on_the_investigation_screen() throws InterruptedException {
        servicesPage.verifyUserIsPresentOnExpectedScreen("Investigations");
    }

    @When("The user searches and adds investigation tests")
    public void the_user_searches_and_adds_investigation_tests() throws InterruptedException {
        patientScreeningPage.searchAndSelect("Complete blood count");
        patientScreeningPage.searchAndSelect("24 Hr Blood Pressure Monitoring");
    }

    @Then("The tests should be added to the investigation")
    public void the_tests_should_be_added_to_the_investigation() throws InterruptedException {
        patientScreeningPage.addedValues(Arrays.asList("Complete blood count","24 Hr Blood Pressure Monitoring"));
    }

    @When("The user removes a test or clicks Clear All")
    public void the_user_removes_a_test_or_clicks_clear_all() {
        patientScreeningPage.removeDataUsingRemoveIcon("Complete blood count");
        patientScreeningPage.clickOnClearAll();
    }

    @Then("The tests should be removed")
    public void the_tests_should_be_removed() throws InterruptedException {
        patientScreeningPage.verifyRemovedValuesAreNotDisplayed(Arrays.asList("Complete blood count","24 Hr Blood Pressure Monitoring"));
    }

    @When("The user adds a custom test or from quick type")
    public void the_user_adds_a_custom_test_or_from_quick_type() throws InterruptedException {
        patientScreeningPage.searchAndSelect("TestCustomValue");
        patientScreeningPage.addValuesFromQuickType("Lipid profile complete");
    }

    @Then("The test should be added")
    public void the_test_should_be_added() throws InterruptedException {
        patientScreeningPage.addedValues(Arrays.asList("TestCustomValue","LIPID Profile Complete"));
    }

    @When("A test template is available and selected")
    public void a_test_template_is_available_and_selected() {

    }

    @Then("The template tests should be added")
    public void the_template_tests_should_be_added() {

    }

    @When("The user clicks Next from Investigation screen")
    public void the_user_clicks_next_from_investigation_screen() {
        patientScreeningPage.clickOnNext();
    }

    @Then("the user should navigate to Patient Instructions screen")
    public void the_user_should_navigate_to_patient_instructions_screen() throws InterruptedException {
        servicesPage.verifyUserIsPresentOnExpectedScreen("Patient Instructions");
    }

    @Given("The user is on the Patient instructions screen")
    public void the_user_is_on_the_patient_instructions_screen() throws InterruptedException {
        servicesPage.verifyUserIsPresentOnExpectedScreen("Patient Instructions");
    }

    @When("The user adds a patient instruction")
    public void the_user_adds_a_patient_instruction() {
        clinicInformationPage.selectOptions(Arrays.asList("Lose extra pounds","Exercise regularly","eat a healthy diet"));
    }

    @Then("It should appear in the list")
    public void it_should_appear_in_the_list() throws InterruptedException {
        patientScreeningPage.verifyAddedPatientInstructionsDisplayed("lose extra pounds and watch your waistline exercise regularly eat a healthy diet");
    }

    @When("The user removes it")
    public void the_user_removes_it() {
        clinicInformationPage.selectOptions(Collections.singletonList("eat a healthy diet"));
    }

    @Then("Instructions should be removed")
    public void instructions_should_be_removed() {
        patientScreeningPage.verifyAddedPatientInstructionsDisplayed("lose extra pounds and watch your waistline exercise regularly");
    }

    @When("The user clicks Next from Patient Instructions screen")
    public void the_user_clicks_next_from_patient_instructions_screen() {
        patientScreeningPage.clickOnNext();
    }

    @Then("The user should navigate to the Overview screen")
    public void the_user_should_navigate_to_the_overview_screen() throws InterruptedException {
        servicesPage.verifyUserIsPresentOnExpectedScreen("Overview");
    }

    @Given("The user is on the Overview screen")
    public void the_user_is_on_the_overview_screen() {

    }

    @When("The user adds Drug, Food, and Environment allergies")
    public void the_user_adds_drug_food_and_environment_allergies() {

    }

    @Then("Allergies should be visible")
    public void allergies_should_be_visible() {

    }

    @When("The user removes them")
    public void the_user_removes_them() {

    }

    @Then("Allergies should be removed")
    public void allergies_should_be_removed() {

    }

    @When("The user adds a problem list item")
    public void the_user_adds_a_problem_list_item() {

    }

    @Then("Problem list should be visible")
    public void problem_list_should_be_visible() {

    }

    @When("The user removes problem list")
    public void the_user_removes_problem_list() {

    }

    @Then("Problem list item should be removed")
    public void problem_list_item_should_be_removed() {

    }

    @When("The user adds a medication")
    public void the_user_adds_a_medication() {

    }

    @Then("Medication should appear in the list")
    public void medication_should_appear_in_the_list() {

    }

    @When("The user removes medication")
    public void the_user_removes_medication() {

    }

    @Then("Medication should be removed")
    public void medication_should_be_removed() {

    }

    @When("The user adds family history")
    public void the_user_adds_family_history() {

    }

    @Then("Family history should appear")
    public void family_history_should_appear() {

    }

    @When("The user removes family history")
    public void the_user_removes_family_history() {

    }

    @Then("Family history should be removed")
    public void family_history_should_be_removed() {

    }

    @When("The user adds past surgical history")
    public void the_user_adds_past_surgical_history() {

    }

    @Then("Past surgical history items should appear")
    public void past_surgical_history_items_should_appear() {

    }

    @When("The user removes past surgical history items")
    public void the_user_removes_past_surgical_history_items() {

    }

    @Then("Past surgical history items should be removed")
    public void past_surgical_history_items_should_be_removed() {

    }

    @When("The user adds past medical conditions")
    public void the_user_adds_past_medical_conditions() {

    }

    @Then("Past medical conditions item should appear")
    public void past_medical_conditions_item_should_appear() {

    }

    @When("The user removes past medical conditions items")
    public void the_user_removes_past_medical_conditions_items() {

    }

    @Then("Past medical conditions items should be removed")
    public void past_medical_conditions_items_should_be_removed() {

    }

    @When("The user adds or updates social history")
    public void the_user_adds_or_updates_social_history() {

    }

    @Then("The updates should be saved")
    public void the_updates_should_be_saved() {

    }

    @When("The user clicks Next from Overview screen")
    public void the_user_clicks_next_from_overview_screen() {

    }

    @Then("The user should navigate to the Assessment screen")
    public void the_user_should_navigate_to_the_assessment_screen() {

    }

    @Given("The user is on the Assessments screen")
    public void the_user_is_on_the_assessments_screen() {

    }

    @When("The user adds assessments during screening")
    public void the_user_adds_assessments_during_screening() {

    }

    @Then("They should saved properly")
    public void they_should_saved_properly() {

    }

    @When("The user clicks Next from Assessment screen")
    public void the_user_clicks_next_from_assessment_screen() {

    }

    @Then("The user should navigate to Refer a Doctor screen")
    public void the_user_should_navigate_to_refer_a_doctor_screen() {

    }

    @Given("The user is on the Refer a doctor screen")
    public void the_user_is_on_the_refer_a_doctor_screen() {

    }

    @When("The user enters refer doctor details")
    public void the_user_enters_refer_doctor_details() {

    }

    @Then("Details should be saved")
    public void details_should_be_saved() {

    }

    @When("The user clicks Next from Refer a Doctor screen")
    public void the_user_clicks_next_from_refer_a_doctor_screen() {

    }

    @Then("The user should navigate to Follow-up screen")
    public void the_user_should_navigate_to_follow_up_screen() {

    }

    @When("The user clicks on PreviewRx")
    public void the_user_clicks_on_preview_rx() {

    }

    @Then("The PDF should open and display valid information")
    public void the_pdf_should_open_and_display_valid_information() {

    }

    @When("The user click on End encounter")
    public void the_user_click_on_end_encounter() {

    }

    @When("The user selects lab, pharmacy, and language and ends encounter")
    public void the_user_selects_lab_pharmacy_and_language_and_ends_encounter() {

    }

    @Then("The encounter should be marked as completed")
    public void the_encounter_should_be_marked_as_completed() {

    }

    @When("The user provides only HOPI and prescription")
    public void the_user_provides_only_hopi_and_prescription() {

    }

    @When("Ends the encounter completely")
    public void ends_the_encounter_completely() {

    }

    @When("The user fills details but skips prescription")
    public void the_user_fills_details_but_skips_prescription() {

    }
}
