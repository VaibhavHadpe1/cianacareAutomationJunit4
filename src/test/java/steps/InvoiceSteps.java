package steps;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pomClasses.*;
import utility.Utility;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class InvoiceSteps {
    AndroidDriver driver;
    LoginPage loginPage;
    ServicesPage servicesPage;
    UserListPage userListPage;
    PersonalInformationPage personalInformationPage;
    InvoicePage invoicePage;
    ClinicInformationPage clinicInformationPage;
    public InvoiceSteps() throws InterruptedException {
        driver= Utility.getDriver();
        loginPage=new LoginPage(driver);
        servicesPage=new ServicesPage(driver);
        userListPage=new UserListPage(driver);
        personalInformationPage=new PersonalInformationPage(driver);
        invoicePage=new InvoicePage(driver);
        clinicInformationPage=new ClinicInformationPage(driver);
    }
    @When("User clicks on Invoice from dashboard")
    public void user_clicks_on_invoice_from_dashboard() {
        invoicePage.clickOnInvoiceFromDashboard();
    }

    @Then("User should be navigated to Invoice screen")
    public void user_should_be_navigated_to_invoice_screen() throws InterruptedException {
        servicesPage.verifyUserIsPresentOnExpectedScreen("Invoice ");
    }

    @Given("User is on Invoice screen")
    public void user_is_on_invoice_screen() throws InterruptedException {
        servicesPage.verifyUserIsPresentOnExpectedScreen("Invoice ");
    }

    @When("User clicks on Add Invoice and selects Service")
    public void user_clicks_on_add_invoice_and_selects_service() throws InterruptedException {
        invoicePage.clickOnAddInvoiceIcon();
        Thread.sleep(1000);
        invoicePage.selectServiceInvoiceOption();
    }

    @Then("User should be navigated to Add Service Invoice form")
    public void user_should_be_navigated_to_add_service_invoice_form() throws InterruptedException {
        servicesPage.verifyUserIsPresentOnExpectedScreen("Add Service invoice ");
    }

    @Given("User is on Add Service Invoice form")
    public void user_is_on_add_service_invoice_form() throws InterruptedException {
        servicesPage.verifyUserIsPresentOnExpectedScreen("Add Service invoice ");
    }

    @When("User clicks on submit button without entering any details")
    public void user_clicks_on_submit_button_without_entering_any_details() {
        userListPage.clickOnSubmit();
    }

    @Then("An error message should be displayed for empty mandatory fields")
    public void an_error_message_should_be_displayed_for_empty_mandatory_fields() throws InterruptedException {
        Thread.sleep(1000);
        servicesPage.verifyErrorMessages(Arrays.asList("Mobile number is required","Name is required","Age is required"));
        Utility.clickOnBackButton();
    }

    @When("User enters valid  service invoice details of existing user")
    public void user_enters_valid_service_invoice_details_of_existing_user() throws IOException, InterruptedException {
        personalInformationPage.sendInputToField("Mobile number*",Utility.readDataFromPropertyFile("serviceInvoiceExistingUserNumber"));
        invoicePage.selectUserFromDropdown(Collections.singletonList(Utility.readDataFromPropertyFile("invoiceUserName")));
        invoicePage.clickOnBillingInformation();
        invoicePage.addExistingServiceDetails(Utility.readDataFromPropertyFile("serviceName"));
        userListPage.clickOnSave();
    }

    @When("User clicks on Submit button")
    public void user_clicks_on_Submit_button() {
        userListPage.clickOnSubmit();
    }

    @Then("The service invoice should be created successfully and displayed in the invoice list")
    public void the_service_invoice_should_be_created_successfully_and_displayed_in_the_invoice_list() throws IOException, InterruptedException {
        invoicePage.clickOnOk();
        Utility.clickOnBackButton();
        Thread.sleep(1000);
        invoicePage.verifyInvoiceDetails(Utility.readDataFromPropertyFile("invoiceUserName"),"Service","2000");
    }

    @When("User enters valid  service invoice details of new user")
    public void user_enters_valid_service_invoice_details_of_new_user() throws InterruptedException, IOException {
        personalInformationPage.sendInputToField("Mobile number*",Utility.readDataFromPropertyFile("serviceInvoiceNewUserNumber"));
        personalInformationPage.sendInputToField("Name*",Utility.readDataFromPropertyFile("invoiceNewUserName"));
        clinicInformationPage.selectOptions(Collections.singletonList("Male"));
        personalInformationPage.sendInputToField("Age*","22");
        invoicePage.clickOnBillingInformation();
        invoicePage.addExistingServiceDetails(Utility.readDataFromPropertyFile("serviceName"));
        userListPage.clickOnSave();
    }

    @Then("The service invoice for new user should be created successfully and displayed in the invoice list")
    public void the_service_invoice_for_new_user_should_be_created_successfully_and_displayed_in_the_invoice_list() throws IOException, InterruptedException {
        invoicePage.clickOnOk();
        Utility.clickOnBackButton();
        Thread.sleep(1000);
        invoicePage.verifyInvoiceDetails(Utility.readDataFromPropertyFile("invoiceNewUserName"),"Service","2000");
    }

    @When("User clicks on Add Invoice and selects Lab")
    public void user_clicks_on_add_invoice_and_selects_lab() throws InterruptedException {
        invoicePage.clickOnAddInvoiceIcon();
        Thread.sleep(1000);
        invoicePage.selectLabInvoiceOption();
    }

    @Then("User should be navigated to Add Lab Invoice form")
    public void user_should_be_navigated_to_add_lab_invoice_form() throws InterruptedException {
        servicesPage.verifyUserIsPresentOnExpectedScreen("Add Lab invoice ");

    }

    @Given("User is on Add Lab Invoice form")
    public void user_is_on_add_lab_invoice_form() throws InterruptedException {
        servicesPage.verifyUserIsPresentOnExpectedScreen("Add Lab invoice ");
    }

    @Then("An error message should be displayed for invalid mandatory fields")
    public void an_error_message_should_be_displayed_for_invalid_mandatory_fields() throws InterruptedException {
        Thread.sleep(1000);
        servicesPage.verifyErrorMessages(Arrays.asList("Mobile number is required","Name is required","Age is required"));
        Utility.clickOnBackButton();
    }

    @When("User enters valid lab invoice details of existing user")
    public void user_enters_valid_lab_invoice_details_of_existing_user() throws InterruptedException, IOException {
        personalInformationPage.sendInputToField("Mobile number*",Utility.readDataFromPropertyFile("labInvoiceExistingUserNumber"));
        invoicePage.selectUserFromDropdown(Collections.singletonList(Utility.readDataFromPropertyFile("labInvoiceExistingUserName")));
        invoicePage.clickOnBillingInformation();
        invoicePage.addExistingLabTestDetails(Utility.readDataFromPropertyFile("labName"),Utility.readDataFromPropertyFile("pathologyTestName"));
        userListPage.clickOnSave();
    }

    @Then("The lab invoice for existing user should be created successfully and displayed in the invoice list")
    public void the_lab_invoice_for_existing_user_should_be_created_successfully_and_displayed_in_the_invoice_list() throws IOException, InterruptedException {
        invoicePage.clickOnOk();
        Utility.clickOnBackButton();
        Thread.sleep(1000);
        invoicePage.clickOnFilterIconOnInvoiceScreen();
        invoicePage.selectFilterValue("Type","Lab order");
        invoicePage.clickOnApplyFilter();
        Thread.sleep(1000);
        invoicePage.verifyInvoiceDetails(Utility.readDataFromPropertyFile("labInvoiceExistingUserName"),"Lab orders","200");
    }

    @When("User enters valid lab invoice details of new user")
    public void user_enters_valid_lab_invoice_details_of_new_user() throws InterruptedException, IOException {
        personalInformationPage.sendInputToField("Mobile number*",Utility.readDataFromPropertyFile("labInvoiceNewUserNumber"));
        personalInformationPage.sendInputToField("Name*",Utility.readDataFromPropertyFile("labInvoiceNewUserName"));
        clinicInformationPage.selectOptions(Collections.singletonList("Male"));
        personalInformationPage.sendInputToField("Age*","22");
        //invoicePage.selectBilledOnDate();
        invoicePage.clickOnBillingInformation();
        invoicePage.addExistingLabTestDetails(Utility.readDataFromPropertyFile("labName"),Utility.readDataFromPropertyFile("pathologyTestName"));
        userListPage.clickOnSave();
        invoicePage.clickOnBillingInformation();
        invoicePage.addExistingLabTestDetails(Utility.readDataFromPropertyFile("labName"),Utility.readDataFromPropertyFile("radiologyTestName"));
        userListPage.clickOnSave();
    }
    @Then("The lab invoice for new user should be created successfully and displayed in the invoice list")
    public void the_lab_invoice_for_new_user_should_be_created_successfully_and_displayed_in_the_invoice_list() throws IOException, InterruptedException {
        invoicePage.clickOnOk();
        Utility.clickOnBackButton();
        Thread.sleep(1000);
        invoicePage.clickOnFilterIconOnInvoiceScreen();
        invoicePage.selectFilterValue("Type","Lab order");
        invoicePage.clickOnApplyFilter();
        Thread.sleep(1000);
        invoicePage.verifyInvoiceDetails(Utility.readDataFromPropertyFile("labInvoiceNewUserName"),"Lab orders","3200");
    }

    @When("User selects a lab invoice and clicks on Upload Report")
    public void user_selects_a_lab_invoice_and_clicks_on_upload_report() throws IOException, InterruptedException {
        Thread.sleep(1000);
        invoicePage.clickOnUploadReportButtonOfLabOrder(Utility.readDataFromPropertyFile("labInvoiceNewUserName"));
    }

    @When("User uploads a valid lab report file")
    public void user_uploads_a_valid_lab_report_file() throws InterruptedException, IOException {
        Thread.sleep(1000);
        invoicePage.selectTestTypeAndTestName("Pathology",Utility.readDataFromPropertyFile("pathologyTestName"));
        invoicePage.uploadImageFromGallery();
        Thread.sleep(1000);
        invoicePage.selectTestTypeAndTestName("Radiology",Utility.readDataFromPropertyFile("radiologyTestName"));
        invoicePage.uploadImageFromGallery();
        userListPage.clickOnSave();
        Thread.sleep(2000);
    }

    @Then("The report should be uploaded successfully and associated with the invoice")
    public void the_report_should_be_uploaded_successfully_and_associated_with_the_invoice() throws IOException, InterruptedException {
        Thread.sleep(1000);
        Utility.clickOnBackButton();
        invoicePage.clickOnInvoiceFromDashboard();
        Thread.sleep(1000);
        invoicePage.clickOnFilterIconOnInvoiceScreen();
        Thread.sleep(1000);
        invoicePage.selectFilterValue("Type","Lab order");
        invoicePage.clickOnApplyFilter();
        Thread.sleep(1000);
        invoicePage.verifyUploadReportButtonDisabled(Utility.readDataFromPropertyFile("labInvoiceNewUserName"));
    }

    @When("User selects a lab invoice clicks on View Report")
    public void user_selects_a_lab_invoice_clicks_on_view_report() throws IOException, InterruptedException {
        Thread.sleep(2000);
        invoicePage.clickOnDownloadReportButtonOfLabOrder(Utility.readDataFromPropertyFile("labInvoiceNewUserName"));
    }

    @Then("The uploaded report should be displayed correctly")
    public void the_uploaded_report_should_be_displayed_correctly() throws IOException, InterruptedException {
        invoicePage.viewTestReport(Utility.readDataFromPropertyFile("pathologyTestName"));
        Thread.sleep(3000);
        Utility.clickOnBackButton();
        Thread.sleep(1000);
        servicesPage.closeBottomSheet();
    }

    @When("User selects a lab invoice clicks on Download button")
    public void user_selects_a_lab_invoice_clicks_on_download_button() throws InterruptedException, IOException {
        invoicePage.clickOnDownloadInvoiceButton(Utility.readDataFromPropertyFile("labInvoiceNewUserName"));
    }

    @Then("The invoice should be downloaded successfully")
    public void the_invoice_should_be_downloaded_successfully() throws InterruptedException {
        Thread.sleep(3000);
        Utility.clickOnBackButton();
        servicesPage.verifyUserIsPresentOnExpectedScreen("Invoice ");
    }

    @When("User applies a date range filter")
    public void user_applies_a_date_range_filter() {
        invoicePage.applyDateFilter("This month");
    }

    @Then("The invoice list should display only invoices within the selected date range")
    public void the_invoice_list_should_display_only_invoices_within_the_selected_date_range() {
        invoicePage.verifyDateFilterResults("This month");
    }

    @When("User selects a status filter")
    public void user_selects_a_status_filter() {
        invoicePage.clickOnFilterIconOnInvoiceScreen();
        invoicePage.selectFilterValue("Status","Paid");
        invoicePage.clickOnApplyFilter();
    }

    @Then("The invoice list should display only invoices matching the selected status")
    public void the_invoice_list_should_display_only_invoices_matching_the_selected_status() throws InterruptedException {
        invoicePage.verifyInvoiceTag("Paid");
    }

    @When("User selects a payment mode filter")
    public void user_selects_a_payment_mode_filter() {
        invoicePage.clickOnFilterIconOnInvoiceScreen();
        invoicePage.selectFilterValue("Mode","Cash");
        invoicePage.clickOnApplyFilter();
    }

    @Then("The invoice list should display only invoices matching the selected payment mode")
    public void the_invoice_list_should_display_only_invoices_matching_the_selected_payment_mode() throws InterruptedException {
        invoicePage.verifyInvoiceTag("Cash");
    }

    @When("User selects a filter for invoice type")
    public void user_selects_a_filter_for_invoice_type() {
        invoicePage.clickOnFilterIconOnInvoiceScreen();
        invoicePage.selectFilterValue("Type","Lab order");
        invoicePage.clickOnApplyFilter();
    }

    @Then("The invoice list should display only invoices matching the selected type")
    public void the_invoice_list_should_display_only_invoices_matching_the_selected_type() throws InterruptedException {
        invoicePage.verifyInvoiceType("Lab orders");
    }
}
