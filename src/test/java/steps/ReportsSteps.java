package steps;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pomClasses.*;
import utility.Utility;

import java.util.Arrays;

public class ReportsSteps {
    AndroidDriver driver;
    LoginPage loginPage;
    TemplatePage templatePage;
    ServicesPage servicesPage;
    UserListPage userListPage;
    ReportsPage reportsPage;
    InvoicePage invoicePage;
    public ReportsSteps(){
        driver= Utility.getDriver();
        loginPage=new LoginPage(driver);
        templatePage=new TemplatePage(driver);
        servicesPage=new ServicesPage(driver);
        userListPage=new UserListPage(driver);
        reportsPage=new ReportsPage(driver);
        invoicePage=new InvoicePage(driver);
    }

    @Given("The user is present on dashboard")
    public void the_user_is_present_on_dashboard() {
        loginPage.verifyUserIsOnDashboard();
    }

    @When("The user clicks on the Reports feature")
    public void the_user_clicks_on_the_reports_feature() throws InterruptedException {
        reportsPage.clickOnReports();
    }

    @Then("The user should be navigated to the Reports screen")
    public void the_user_should_be_navigated_to_the_reports_screen() throws InterruptedException {
        servicesPage.verifyUserIsPresentOnExpectedScreen("Reports ");
    }

    @Given("The user is on the Reports screen")
    public void the_user_is_on_the_reports_screen() throws InterruptedException {
        servicesPage.verifyUserIsPresentOnExpectedScreen("Reports ");
    }

    @When("The user has no reports available")
    public void the_user_has_no_reports_available() {
        reportsPage.isReportsNotAvailableForDoctor();
    }

    @Then("A default message No reports available should be displayed")
    public void a_default_message_no_reports_available_should_be_displayed() {
        reportsPage.verifyDefaultMessageForNoReports();
    }

    @When("The user selects a date range for consultation revenue reports")
    public void the_user_selects_a_date_range_for_consultation_revenue_reports() {
        invoicePage.applyDateFilter("Today");
    }

    @Then("The filtered consultation revenue reports should be displayed date wise")
    public void the_filtered_consultation_revenue_reports_should_be_displayed_date_wise() {
        invoicePage.verifyDateFilterResults("Today");
    }

    @When("The user selects an Appointment type filter")
    public void the_user_selects_an_appointment_type_filter() {
        reportsPage.clickOnFilterIconOnReportsScreen();
        invoicePage.selectFilterValue("Appointment type","All");
        invoicePage.selectFilterValue("Appointment type","Inclinic");
        invoicePage.clickOnApplyFilter();
    }

    @Then("The filtered consultation revenue reports should be displayed according to appointment type")
    public void the_filtered_consultation_revenue_reports_should_be_displayed_according_to_appointment_type() throws InterruptedException {
        reportsPage.verifyReportType("Walkin");
    }

    @When("The user selects a Payment mode filter")
    public void the_user_selects_a_payment_mode_filter() {
        reportsPage.clickOnFilterIconOnReportsScreen();
        invoicePage.selectFilterValue("Payment mode","All");
        invoicePage.selectFilterValue("Payment mode","Cash");
        invoicePage.clickOnApplyFilter();
    }

    @Then("The filtered consultation revenue reports should be displayed according to payment mode")
    public void the_filtered_consultation_revenue_reports_should_be_displayed_according_to_payment_mode() throws InterruptedException {
        reportsPage.verifyReportsModeOfPayment("Cash");
    }

    @When("The user selects a doctor from the filter options")
    public void the_user_selects_a_doctor_from_the_filter_options() throws InterruptedException {
        reportsPage.clickOnFilterIconOnReportsScreen();
        Thread.sleep(1000);
        invoicePage.selectFilterValue("Doctors","Sarita");
        Thread.sleep(1000);
        invoicePage.clickOnApplyFilter();

    }

    @Then("The filtered consultation revenue reports should be displayed for the selected doctor")
    public void the_filtered_consultation_revenue_reports_should_be_displayed_for_the_selected_doctor() throws InterruptedException {
        reportsPage.verifyDoctorNamesOnReports("Sarita");
    }

    @When("The user clicks on Export to Excel for consultation revenue")
    public void the_user_clicks_on_export_to_excel_for_consultation_revenue() {
        reportsPage.clickOnExportIcon();
        reportsPage.clickOnExcelOption();
    }

    @Then("The consultation revenue report should be displayed in Excel format")
    public void the_consultation_revenue_report_should_be_displayed_in_excel_format() throws InterruptedException {
        servicesPage.verifyUserIsPresentOnExpectedScreen("Reports ");
    }

    @When("The user clicks on Export to PDF for consultation revenue")
    public void the_user_clicks_on_export_to_pdf_for_consultation_revenue() {
        reportsPage.clickOnExportIcon();
        reportsPage.clickOnPdfOption();
    }

    @Then("The consultation revenue report should be displayed in PDF format")
    public void the_consultation_revenue_report_should_be_displayed_in_pdf_format() throws InterruptedException {
        Thread.sleep(3000);
        Utility.clickOnBackButton();
        servicesPage.verifyUserIsPresentOnExpectedScreen("Reports ");
    }

    @When("The user clicks on the Service Revenue tab")
    public void the_user_clicks_on_the_service_revenue_tab() {
        reportsPage.selectServiceRevenueTab();
    }

    @Then("The service revenue report should be displayed")
    public void the_service_revenue_report_should_be_displayed() throws InterruptedException {
        reportsPage.verifyServiceRevenueReportsDisplayed();
    }

    @When("The user selects a date range for service revenue reports")
    public void the_user_selects_a_date_range_for_service_revenue_reports() {
        invoicePage.applyDateFilter("Last 7 days");
    }

    @Then("The filtered service revenue reports should be displayed by date")
    public void the_filtered_service_revenue_reports_should_be_displayed_by_date() {
        invoicePage.verifyDateFilterResults("Last 7 days");
    }

    @When("The user clicks on Export to PDF for service revenue")
    public void the_user_clicks_on_export_to_pdf_for_service_revenue() {
        reportsPage.clickOnExportIcon();
        reportsPage.clickOnPdfOption();
    }

    @Then("The service revenue report should be displayed in PDF format")
    public void the_service_revenue_report_should_be_displayed_in_pdf_format() throws InterruptedException {
        Thread.sleep(3000);
        Utility.clickOnBackButton();
        servicesPage.verifyUserIsPresentOnExpectedScreen("Reports ");
    }

    @When("The user clicks on the Patients tab")
    public void the_user_clicks_on_the_patients_tab() {
        reportsPage.selectPatientsTab();
    }

    @Then("The patient details should be displayed")
    public void the_patient_details_should_be_displayed() throws InterruptedException {
        reportsPage.verifyServiceRevenueReportsDisplayed();
    }

    @When("The user selects a date range for patient records")
    public void the_user_selects_a_date_range_for_patient_records() {
        invoicePage.applyDateFilter("Today");
    }

    @Then("The filtered patient records should be displayed according to dates")
    public void the_filtered_patient_records_should_be_displayed_according_to_dates() throws InterruptedException {
        reportsPage.verifyServiceRevenueReportsDisplayed();
    }

    @When("The user selects a Gender filter")
    public void the_user_selects_a_gender_filter() {
        reportsPage.clickOnFilterIconOnReportsScreen();
        invoicePage.selectFilterValue("Gender","Female");
        invoicePage.clickOnApplyFilter();
    }

    @Then("The filtered patient records should be displayed according to gender")
    public void the_filtered_patient_records_should_be_displayed_according_to_gender() throws InterruptedException {
        reportsPage.verifyPatientsGender("Female");
    }

    @When("The user selects an Age filter")
    public void the_user_selects_an_age_filter() {
        reportsPage.clickOnFilterIconOnReportsScreen();
        reportsPage.enterAgeRangeInFilter(20,50);
        invoicePage.clickOnApplyFilter();
    }

    @Then("The filtered patient records should be displayed accordingly")
    public void the_filtered_patient_records_should_be_displayed_accordingly() throws InterruptedException {
        reportsPage.verifyAgeWithinTheRange(20,50);
    }

    @When("The user clicks on Export to Excel for patient details")
    public void the_user_clicks_on_export_to_excel_for_patient_details() {
        reportsPage.clickOnExportIcon();
        reportsPage.clickOnExcelOption();
    }

    @Then("The patient details report should be displayed in Excel format")
    public void the_patient_details_report_should_be_displayed_in_excel_format() throws InterruptedException {
        servicesPage.verifyUserIsPresentOnExpectedScreen("Reports ");
    }

    @When("The user clicks on Export to PDF for patient details")
    public void the_user_clicks_on_export_to_pdf_for_patient_details() {
        reportsPage.clickOnExportIcon();
        reportsPage.clickOnPdfOption();
    }

    @Then("The patient details report should be displayed in PDF format")
    public void the_patient_details_report_should_be_displayed_in_pdf_format() throws InterruptedException {
        Thread.sleep(4000);
        Utility.clickOnBackButton();
        servicesPage.verifyUserIsPresentOnExpectedScreen("Reports ");
        Utility.clickOnBackButton();
    }

    @Then("User logout from dashboard")
    public void user_logout_from_dashboard() throws InterruptedException {
        loginPage.logoutFromDashboard();
    }
}
