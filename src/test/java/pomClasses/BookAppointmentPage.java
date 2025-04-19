package pomClasses;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import utility.Utility;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static steps.Base.driver;

public class BookAppointmentPage {
    ServicesPage servicesPage;
    public BookAppointmentPage(AndroidDriver driver)
    {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(2)),this);
        servicesPage=new ServicesPage(driver);
    }
    public static Logger logger= LoggerFactory.getLogger(BookAppointmentPage.class);
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Book\"]")private WebElement bookButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Quick appointment\"]")private WebElement quickAppointmentOption;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Schedule appointment\"]")private WebElement scheduleAppointmentOption;
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Bill patient\"]")private WebElement billPatientButton;
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Bill later\"]")private WebElement bilLaterButton;
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Cancel\"]")private WebElement cancelButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,\"is required\")]")private List<WebElement> requiredFieldErrorMessagesList;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,'Booking confirmed') or contains(@content-desc,'successfully')]") private WebElement successMessageForBookAppointment;
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"OK\"]") private WebElement okButtonForSuccessMessage;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Appointment time*\"]/following-sibling::android.view.ViewGroup[@content-desc][1]")private WebElement appointmentTimeField;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,\"Add new member\")]")private List<WebElement> addNewMemberOptionsAsList;



    public void clickOnBookButton() {
        Utility.explicitlyWait(bookButton,driver,10);
        bookButton.click();
        logger.info("Clicked on book button");
    }

    public void selectQuickAppointmentOption() {
        Utility.explicitlyWait(quickAppointmentOption,driver,10);
        quickAppointmentOption.click();
        logger.info("Selected quick appointment option");
    }
    public void selectScheduleAppointmentOption() {
        Utility.explicitlyWait(scheduleAppointmentOption,driver,10);
        scheduleAppointmentOption.click();
        logger.info("Selected schedule appointment option");
    }

    public void clickOnBillPatientButton() {
        Utility.explicitlyWait(billPatientButton,driver,10);
        billPatientButton.click();
        logger.info("Clicked on bill patient button");
    }
    public void clickOnBillLaterButton() {
        Utility.explicitlyWait(bilLaterButton,driver,10);
        bilLaterButton.click();
        logger.info("Clicked on bill later button");
    }
    public void verifyErrorMessagesOnBookAppointmentScreen() {
        List<String> expectedErrors = Arrays.asList(
                "Mobile number is required",
                "Patient name is required",
                "Gender is required",
                "Date of birth is required",
                "Age is required",
                "Relation is required"
        );
        Utility.explicitlyWait(requiredFieldErrorMessagesList.get(0),driver,10);
        List<String> actualErrors = new ArrayList<>();
        for (WebElement errorElement : requiredFieldErrorMessagesList) {
            actualErrors.add(errorElement.getText().trim());
        }
        Assert.assertEqualsNoOrder(actualErrors.toArray(), expectedErrors.toArray(), "Mismatch in required field error messages.");
        logger.info("Required field error messages are displayed.");
    }
    public void clickOnCancelButton() {
        Utility.explicitlyWait(cancelButton,driver,10);
        cancelButton.click();
        logger.info("Clicked on cancel button");
    }
    WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
    public void selectPatientByNameOrRelation(String expectedPatientNameOrRelation) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ScrollView//android.view.ViewGroup[contains(@content-desc,'"+expectedPatientNameOrRelation+"')]"))).click();
        logger.info("Selected patient by name or relation "+expectedPatientNameOrRelation);
    }
    public void verifySuccessFullAppointmentBooking() {
        Utility.explicitlyWait(successMessageForBookAppointment,driver,10);
        Assert.assertTrue(successMessageForBookAppointment.isDisplayed(), "Success message for book appointment is not displayed.");
        logger.info("Success message for book appointment is: "+successMessageForBookAppointment.getDomAttribute("content-desc"));
    }
    public void clickOnOkButtonForSuccessMessage() {
        Utility.explicitlyWait(okButtonForSuccessMessage,driver,10);
        okButtonForSuccessMessage.click();
        logger.info("Clicked on ok button for success message");
    }

    public void slideToPayAmount() throws InterruptedException {
        servicesPage.verifyUserIsPresentOnExpectedScreen("Billing and Payments");
        Thread.sleep(2000);
        Utility.customizeScrollByCoordinates(driver,124,2220,930,2220);
    }

    public void clickOnAppointmentTimeField() {
        Utility.explicitlyWait(appointmentTimeField,driver,10);
        appointmentTimeField.click();
        logger.info("Clicked on appointment time field");
    }

    public void selectTimeSlot() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElements(By.xpath("//android.view.ViewGroup[contains(@content-desc,\":\")]")).get(0).click();
        logger.info("Time slot is selected");
    }

    public void selectAddNewMemberOptionFromPatientList() throws InterruptedException {
        Thread.sleep(2000);
        int numberOfScroll=5;
        for(int i=0;i<numberOfScroll;i++){
            if (!addNewMemberOptionsAsList.isEmpty() && addNewMemberOptionsAsList.get(0).isDisplayed()) {
                addNewMemberOptionsAsList.get(0).click();
                logger.info("Selected add new member option from patient list");
                break;
            } else {
                Utility.customizeScrollByCoordinates(driver, 700, 1200, 700, 850);
            }
        }
    }
}
