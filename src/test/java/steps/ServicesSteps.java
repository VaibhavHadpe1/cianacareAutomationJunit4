package steps;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pomClasses.LoginPage;
import pomClasses.PersonalInformationPage;
import pomClasses.ServicesPage;
import pomClasses.UserListPage;
import utility.Utility;

import java.io.IOException;
import java.util.Arrays;

public class ServicesSteps {
    AndroidDriver driver;
    LoginPage loginPage;
    ServicesPage servicesPage;
    UserListPage userListPage;
    PersonalInformationPage personalInformationPage;
    public ServicesSteps() throws InterruptedException {
        driver= Utility.getDriver();
        loginPage=new LoginPage(driver);
        servicesPage=new ServicesPage(driver);
        userListPage=new UserListPage(driver);
        personalInformationPage=new PersonalInformationPage(driver);
    }
    @Given("User is present on dashboard")
    public void user_is_present_on_dashboard() {
        loginPage.verifyUserIsOnDashboard();
    }

    @When("User clicks on Services from the dashboard")
    public void user_clicks_on_services_from_the_dashboard() throws InterruptedException {
        servicesPage.clickOnServices();
    }

    @Then("User should be navigated to the Services screen")
    public void user_should_be_navigated_to_the_services_screen() throws InterruptedException {
        servicesPage.verifyUserIsPresentOnExpectedScreen("Services");
    }

    @Given("User is on the Services screen")
    public void user_is_on_the_services_screen() throws InterruptedException {
        servicesPage.verifyUserIsPresentOnExpectedScreen("Services");
    }

    @When("User clicks on the Add Service button")
    public void user_clicks_on_the_add_service_button() {
        servicesPage.clickOnAddServiceButton();
    }

    @Then("User should be navigated to the Add Service form")
    public void user_should_be_navigated_to_the_add_service_form() throws InterruptedException {
        servicesPage.verifyUserIsPresentOnExpectedScreen("Add Service");
    }

    @Given("User is on the Add Service form")
    public void user_is_on_the_add_service_form() throws InterruptedException {
        servicesPage.verifyUserIsPresentOnExpectedScreen("Add Service");
    }

    @When("User clicks on the Save button without entering any details")
    public void user_clicks_on_the_save_button_without_entering_any_details() {
        userListPage.clickOnSave();
    }

    @Then("An error message should be displayed for missing mandatory fields")
    public void an_error_message_should_be_displayed_for_missing_mandatory_fields() throws InterruptedException {
        servicesPage.verifyErrorMessages(Arrays.asList("Service is required","Cost is required"));
        servicesPage.closeBottomSheet();
    }

    @When("User enters valid service details")
    public void user_enters_valid_service_details() throws IOException, InterruptedException {
        personalInformationPage.sendInputToField("Service*",Utility.readDataFromPropertyFile("serviceName"));
        personalInformationPage.sendInputToField("Cost*",Utility.readDataFromPropertyFile("serviceCost"));
        personalInformationPage.sendInputToField("Max discount",Utility.readDataFromPropertyFile("serviceDiscount"));
        personalInformationPage.sendInputToField("Respective person",Utility.readDataFromPropertyFile("serviceRespectivePerson"));

    }

    @When("User clicks on the Save button")
    public void user_clicks_on_the_save_button() {
        userListPage.clickOnSave();
    }

    @Then("The new service should be added successfully and displayed in the services list")
    public void the_new_service_should_be_added_successfully_and_displayed_in_the_services_list() throws InterruptedException, IOException {
        servicesPage.closeBottomSheet();
        servicesPage.verifyServiceDetails(Utility.readDataFromPropertyFile("serviceName"),Utility.readDataFromPropertyFile("serviceCost"),Utility.readDataFromPropertyFile("serviceDiscount"),Utility.readDataFromPropertyFile("serviceRespectivePerson"));
        Utility.clickOnBackButton();
    }

    @When("User selects a service from the list and clicks on the Edit icon")
    public void user_selects_a_service_from_the_list_and_clicks_on_the_edit_icon() throws IOException, InterruptedException {
        servicesPage.clickOnEditButtonOfExpectedService(Utility.readDataFromPropertyFile("serviceName"));
    }

    @When("User updates the service details")
    public void user_updates_the_service_details() throws InterruptedException, IOException {
        personalInformationPage.sendInputToField("Max discount",Utility.readDataFromPropertyFile("updateServiceDiscount"));


    }

    @Then("The service details should be updated successfully")
    public void the_service_details_should_be_updated_successfully() throws IOException, InterruptedException {
        servicesPage.verifyServiceDetails(Utility.readDataFromPropertyFile("serviceName"),Utility.readDataFromPropertyFile("serviceCost"),Utility.readDataFromPropertyFile("updateServiceDiscount"),Utility.readDataFromPropertyFile("serviceRespectivePerson"));
        Utility.clickOnBackButton();
    }

    @When("User selects a service from the list and clicks on the Delete icon")
    public void user_selects_a_service_from_the_list_and_clicks_on_the_delete_icon() throws IOException, InterruptedException {
        servicesPage.clickOnDeleteButtonOfExpectedService(Utility.readDataFromPropertyFile("deleteServiceName"));

    }

    @Then("The service should be removed from the list successfully")
    public void the_service_should_be_removed_from_the_list_successfully() throws IOException, InterruptedException {
        Thread.sleep(1000);
        servicesPage.verifyDeletedServiceNotDisplayed(Utility.readDataFromPropertyFile("deleteServiceName"));
    }

    @Then("user navigates to dashboard")
    public void userNavigatesToDashboard() {
        loginPage.verifyUserIsOnDashboard();
    }
}
