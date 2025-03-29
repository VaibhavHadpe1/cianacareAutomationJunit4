package steps;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pomClasses.LoginPage;
import pomClasses.ServicesPage;
import pomClasses.TemplatePage;
import pomClasses.UserListPage;
import utility.Utility;

import java.util.Arrays;

public class TemplatesSteps {
    AndroidDriver driver;
    LoginPage loginPage;
    TemplatePage templatePage;
    ServicesPage servicesPage;
    UserListPage userListPage;
    public TemplatesSteps(){
        driver= Utility.getDriver();
        loginPage=new LoginPage(driver);
        templatePage=new TemplatePage(driver);
        servicesPage=new ServicesPage(driver);
        userListPage=new UserListPage(driver);
    }

    @Given("The user is logged in as doctor and present on dashboard")
    public void the_user_is_logged_in_as_doctor_and_present_on_dashboard() {
        loginPage.verifyUserIsOnDashboard();
    }

    @When("The user clicks on the Templates feature")
    public void the_user_clicks_on_the_templates_feature() {
        templatePage.clickOnTemplatesFeature();
    }

    @Then("The user should be navigated to the Template screen")
    public void the_user_should_be_navigated_to_the_template_screen() throws InterruptedException {
        servicesPage.verifyUserIsPresentOnExpectedScreen("Templates");
    }

    @Given("The user is on the Template screen")
    public void the_user_is_on_the_template_screen() throws InterruptedException {
        servicesPage.verifyUserIsPresentOnExpectedScreen("Templates");
    }

    @When("The doctor has no templates")
    public void the_doctor_has_no_templates() {
        templatePage.isTemplatesNotAvailableForDoctor();

    }

    @Then("A default message No templates available should be displayed")
    public void a_default_message_no_templates_available_should_be_displayed() {
        templatePage.verifyDefaultMessageForNoTemplate();
    }

    @When("The user clicks on the Add icon")
    public void the_user_clicks_on_the_add_icon() {
        templatePage.clickOnAddIcon();
    }

    @Then("The user should be navigated to the Create Template screen")
    public void the_user_should_be_navigated_to_the_create_template_screen() throws InterruptedException {
        servicesPage.verifyUserIsPresentOnExpectedScreen("Create templates");
    }

    @Given("The user is on the Create Template screen")
    public void the_user_is_on_the_create_template_screen() throws InterruptedException {
        servicesPage.verifyUserIsPresentOnExpectedScreen("Create templates");
    }

    @When("The user clicks on the Save button without filling required fields")
    public void the_user_clicks_on_the_save_button_without_filling_required_fields() {
        templatePage.clickOnSaveButton();
    }

    @Then("Appropriate error messages should be displayed for mandatory fields")
    public void appropriate_error_messages_should_be_displayed_for_mandatory_fields() throws InterruptedException {
        templatePage.verifyErrorMessagesForMandatoryFields(Arrays.asList("Disease name is required","Gender is required","Age group is required"));
        Thread.sleep(1000);
        Utility.clickOnBackButton();
    }

    @When("The user enters valid template details and only medication details")
    public void the_user_enters_valid_template_details_and_only_medication_details() throws InterruptedException {
        templatePage.enterTemplateDetails("Fever","Treatment-1","Male","Young Adults");
        templatePage.addMedication("Dolo 1000mg","Oral","1-1-1","After meal","3","3 Days");
    }

    @When("Clicks on the Save button")
    public void clicks_on_the_save_button() {
        templatePage.clickOnSaveButton();
    }

    @Then("The template should be created successfully")
    public void the_template_should_be_created_successfully() {
        templatePage.verifyTemplateDetails("Fever");
    }

    @When("The user enters valid template and only investigation details")
    public void the_user_enters_valid_template_and_only_investigation_details() throws InterruptedException {
        templatePage.enterTemplateDetails("Joint pain","Treatment-2","Male","Middle-aged Adults");
        templatePage.addInvestigation("X-ray");
    }

    @Then("The template with only investigation should be created successfully")
    public void the_template_with_only_investigation_should_be_created_successfully() {
        templatePage.verifyTemplateDetails("Joint pain");
    }

    @Then("The template with medicine and investigation should be created successfully")
    public void the_template_with_medicine_and_investigation_should_be_created_successfully() {
        templatePage.verifyTemplateDetails("Cold therapy");
    }

    @When("The user enters valid medication details and investigation details")
    public void the_user_enters_valid_medication_details_and_investigation_details() throws InterruptedException {
        templatePage.enterTemplateDetails("Cold therapy","Treatment level-1","Female","Any");
        templatePage.addMedication("3A 250mg","Buccal","STAT","After lunch","5","5 Days");
        templatePage.addInvestigation("blood gas");
    }

    @When("The user selects any combination of Gender and Age group")
    public void the_user_selects_any_combination_of_gender_and_age_group() throws InterruptedException {
        templatePage.enterTemplateDetails("Migraine","Treatment level-1","Female","Old-aged Adults");
    }

    @When("Enters required details")
    public void enters_required_details() throws InterruptedException {
        templatePage.addMedication("A Bec","Oral","STAT","After lunch","5","5 Days");
    }

    @Then("The template should be created successfully for different combination")
    public void the_template_should_be_created_successfully_for_different_combination() {
        templatePage.verifyTemplateDetails("Migraine");
    }

    @When("The user selects Any for Gender and Any for age group")
    public void the_user_selects_any_for_gender_and_any_for_age_group() throws InterruptedException {
        templatePage.enterTemplateDetails("Eyeball Examination","Basic Treatment","Any","Any");
    }

    @Then("The template should be created successfully for any combination")
    public void the_template_should_be_created_successfully_for_any_combination() {
        templatePage.verifyTemplateDetails("Eyeball Examination");
    }

    @When("User selects an existing template to edit")
    public void user_selects_an_existing_template_to_edit() {
        templatePage.clickOnTemplateToEdit("Eyeball Examination");
    }

    @When("Updates the details")
    public void updates_the_details() {
        templatePage.selectGender("Male");
        templatePage.selectAgeGroup("Child");
    }

    @Then("The template should be updated successfully")
    public void the_template_should_be_updated_successfully() {
        templatePage.verifyTemplateDetails("Eyeball Examination");
    }

    @When("User selects an existing template and click on delete")
    public void user_selects_an_existing_template_and_click_on_delete() {
        templatePage.clickOnDeleteForExpectedTemplate("Eyeball Examination");
    }

    @Then("The template should be removed successfully")
    public void the_template_should_be_removed_successfully() {
        templatePage.verifyTemplateIsDeleted("Eyeball Examination");
    }

    @When("The user enters a template name in the search bar")
    public void the_user_enters_a_template_name_in_the_search_bar() {
        userListPage.search("Migraine");
    }

    @Then("The relevant templates should be displayed in the results")
    public void the_relevant_templates_should_be_displayed_in_the_results() throws InterruptedException {
        templatePage.verifySearchResults("Migraine");
        userListPage.clearSearchInput();
        Utility.clickOnBackButton();
    }

}
