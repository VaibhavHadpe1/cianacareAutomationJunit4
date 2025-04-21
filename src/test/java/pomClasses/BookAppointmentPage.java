package pomClasses;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import utility.Utility;

import java.time.Duration;
import java.util.*;

import static steps.Base.driver;

public class BookAppointmentPage {
    ServicesPage servicesPage;
    ClinicInformationPage clinicInformationPage;
    TemplatePage templatePage;
    public BookAppointmentPage(AndroidDriver driver)
    {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(2)),this);
        servicesPage=new ServicesPage(driver);
        clinicInformationPage=new ClinicInformationPage(driver);
        templatePage=new TemplatePage(driver);
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
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"+ Add service\"]")private WebElement addServiceButton;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@index=3]//android.view.ViewGroup[@index=1]//android.view.ViewGroup[@index=0]//android.widget.EditText")private WebElement addServiceInputField;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@index=3]//android.view.ViewGroup[@index=1]//android.view.ViewGroup[@index=1]//android.widget.EditText")private WebElement serviceChargesInputField;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@index=3]//android.view.ViewGroup[@index=1]//android.view.ViewGroup[@index=2]//android.widget.EditText")private WebElement serviceDiscountInputField;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@index=3]//android.view.ViewGroup[@index=1]//android.view.ViewGroup[@index=3]//android.widget.EditText")private WebElement serviceAmountInputField;
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='+ Add service']/preceding::android.widget.TextView[contains(@text,'₹')]")private List<WebElement> addedServiceAmountAsList;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Select doctor *\"]/following-sibling::android.view.ViewGroup[@content-desc][1]")private WebElement doctorDropdown;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Symptoms \"]/following-sibling::android.view.ViewGroup[@content-desc][1]")private WebElement symptomsDropdown;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Appointment date *\"]/following-sibling::android.view.ViewGroup[@content-desc][1]")private WebElement appointmentDatePicker;
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"android:id/button1\"]")private WebElement okButtonOnDatePicker;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Afternoon\"]") private WebElement afternoonSection;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Evening\"]") private WebElement eveningSection;

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

    public void clickOnBillPatientButton() throws InterruptedException {
        Utility.explicitlyWait(billPatientButton,driver,10);
        billPatientButton.click();
        logger.info("Clicked on bill patient button");
        Thread.sleep(1000);
        List<WebElement> selDiffSlotMsg = driver.findElements(By.xpath("//android.widget.TextView[" +
                "    contains(@text, 'Doctor is Not Available on selected Date') or " +
                "    contains(@text, 'Selected slot is already booked by another user') or" +
                "    contains(@text, 'Patient has an appointment with another Doctor')" +
                "]"));
        if(!selDiffSlotMsg.isEmpty()){
            okButtonForSuccessMessage.click();
            Thread.sleep(1000);
            clickOnAppointmentTimeField();
            Thread.sleep(1000);
            List<WebElement> timeSlots = driver.findElements(By.xpath("(//android.widget.ScrollView[@index=3]//android.view.ViewGroup//android.view.ViewGroup[contains(@content-desc,':')])"));
            timeSlots.get(timeSlots.size() - 1).click();
            Thread.sleep(1000);
            billPatientButton.click();
        }

    }
    public void clickOnBillLaterButton() throws InterruptedException {
        Utility.explicitlyWait(bilLaterButton,driver,10);
        bilLaterButton.click();
        logger.info("Clicked on bill later button");
        Thread.sleep(1000);
        List<WebElement> selDiffSlotMsg = driver.findElements(By.xpath("//android.widget.TextView[" +
                "    contains(@text, 'Doctor is Not Available on selected Date') or " +
                "    contains(@text, 'Selected slot is already booked by another user') or" +
                "    contains(@text, 'Patient has an appointment with another Doctor')" +
                "]"));
        //android.widget.Button[@content-desc="OK"]//android.widget.TextView[contains(@text,'Patient has an appointment with another doctor on this same date and time, please select different slot')]
        if(!selDiffSlotMsg.isEmpty()){
            okButtonForSuccessMessage.click();
            Thread.sleep(1000);
            clickOnAppointmentTimeField();
            Thread.sleep(1000);
            List<WebElement> timeSlots = driver.findElements(By.xpath("(//android.widget.ScrollView[@index=3]//android.view.ViewGroup//android.view.ViewGroup[contains(@content-desc,':')])"));
            timeSlots.get(timeSlots.size() - 1).click();
            Thread.sleep(1000);
            clickOnBillLaterButton();
        }
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
    public void selectAptTime() throws InterruptedException {
        List<WebElement> timeSlots = driver.findElements(By.xpath("(//android.widget.ScrollView[@index=3]//android.view.ViewGroup//android.view.ViewGroup[contains(@content-desc,':')])"));
        if (!timeSlots.isEmpty()) {
            timeSlots.get(0).click();  // Click first available slot
            //System.out.println("Selected time slot in Morning: " + timeSlots.get(0).getText());
        } else {
            afternoonSection.click();
            Thread.sleep(1000);  // Wait for UI to update
            // Re-fetch time slots after clicking Afternoon
            timeSlots = driver.findElements(By.xpath("(//android.widget.ScrollView[@index=3]//android.view.ViewGroup//android.view.ViewGroup[contains(@content-desc,':')])"));
            if (!timeSlots.isEmpty()) {
                timeSlots.get(0).click();
                //System.out.println("Selected time slot in Afternoon: " + timeSlots.get(0).getText());
            } else {
                // Switch to Evening
                eveningSection.click();
                Thread.sleep(1000);  // Wait for UI to update
                // Re-fetch time slots after clicking Evening
                timeSlots = driver.findElements(By.xpath("(//android.widget.ScrollView[@index=3]//android.view.ViewGroup//android.view.ViewGroup[contains(@content-desc,':')])"));
                if (!timeSlots.isEmpty()) {
                    timeSlots.get(0).click();
                    //System.out.println("Selected time slot in Evening: " + timeSlots.get(0).getText());
                } else {
                    throw new RuntimeException("No time slots available for booking.");
                }
            }
        }
        Thread.sleep(1000);
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

    public void addExistingService() {
        Utility.explicitlyWait(addServiceButton,driver,10);
        addServiceButton.click();
        logger.info("Clicked on add service button");
        Utility.explicitlyWait(addServiceInputField,driver,10);
        addServiceInputField.click();
        String serviceName=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//android.view.ViewGroup[@index=3]//android.widget.ScrollView//android.view.ViewGroup[@content-desc])[1]"))).getDomAttribute("content-desc");
        System.out.println("Service Name is: "+serviceName);
        clinicInformationPage.selectOptions(Collections.singletonList(serviceName));
        logger.info("Selected service: "+serviceName);
        templatePage.clickOnSaveButtonOfBottomSheet();
    }
    public void addUserDefinedService(String expectedServiceName,String expectedServiceCharges,String expectedServiceDiscountPercentage){
        Utility.explicitlyWait(addServiceButton,driver,10);
        addServiceButton.click();
        logger.info("Clicked on add service button");
        Utility.explicitlyWait(addServiceInputField,driver,10);
        addServiceInputField.sendKeys(expectedServiceName);
        clinicInformationPage.selectOptions(Collections.singletonList(expectedServiceName));
        logger.info("Entered service name");
        Utility.explicitlyWait(serviceChargesInputField,driver,10);
        serviceChargesInputField.sendKeys(expectedServiceCharges);
        logger.info("Entered service charges");
        Utility.explicitlyWait(serviceDiscountInputField,driver,10);
        serviceDiscountInputField.sendKeys(expectedServiceDiscountPercentage);
        logger.info("Entered service discount");
        Utility.explicitlyWait(serviceAmountInputField,driver,10);
        int serviceCharges = Integer.parseInt(expectedServiceCharges);
        int discountPercentage = Integer.parseInt(expectedServiceDiscountPercentage);
        double expectedServiceAmount = (double)serviceCharges-(double) (serviceCharges * discountPercentage) / 100;
        String actualServiceAmountText = Objects.requireNonNull(serviceAmountInputField.getDomAttribute("text")).trim();
        double actualServiceAmount = Double.parseDouble(actualServiceAmountText);
        Assert.assertEquals(actualServiceAmount, expectedServiceAmount, "Service amount is not calculated correctly");
        logger.info("Service amount is: " + actualServiceAmountText);
        templatePage.clickOnSaveButtonOfBottomSheet();
    }

    public void editServices(String expectedServiceName,String newServiceDiscountPercentage) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='"+expectedServiceName+"']/following-sibling::android.view.ViewGroup[@content-desc=\"\uE3C9\"]"))).click();
        serviceDiscountInputField.clear();
        serviceDiscountInputField.sendKeys(newServiceDiscountPercentage);
        templatePage.clickOnSaveButtonOfBottomSheet();
    }

    public void calculateFinalAmountToBePaid() {
        double totalCalculatedAmount = 0.00;

        for (WebElement amountElement : addedServiceAmountAsList) {
            String amountText = amountElement.getText().replace("₹", "").trim();
            if (!amountText.isEmpty()) {
                totalCalculatedAmount += Double.parseDouble(amountText);
            }
        }
        System.out.println("Total Calculated Amount: ₹" + totalCalculatedAmount);
        String actualTotalAmount=driver.findElement(By.xpath("//android.widget.TextView[@text=\"Total Amount\"]/following-sibling::android.widget.TextView")).getText().replace("₹","").trim();
        System.out.println("Actual Total Amount: ₹"+actualTotalAmount);
        Assert.assertEquals(totalCalculatedAmount,Double.parseDouble(actualTotalAmount),"Total amount is not calculated correctly");
    }

    public void selectDoctorFromDropdown(String expectedDoctorName) throws InterruptedException {
        Utility.explicitlyWait(doctorDropdown,driver,10);
        doctorDropdown.click();
        Thread.sleep(1000);
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.ViewGroup[contains(@content-desc,'"+expectedDoctorName+"')]"))).click();
            logger.info("Selected doctor from dropdown: "+expectedDoctorName);
        }catch (Exception e){
            logger.error("Doctor: "+expectedDoctorName+" is not displayed");
            Utility.tapOutsideToCloseBottomSheet(driver,820,860);
        }
    }
    public void clickOnSymptomDropdown() throws InterruptedException {
        Thread.sleep(1000);
        Utility.customizeScrollByCoordinates(driver, 700, 1800, 700, 1050);
        Utility.explicitlyWait(symptomsDropdown, driver, 10);
        symptomsDropdown.click();
        logger.info("Clicked on symptoms dropdown");
    }
    public void addSymptomsWhileBookingAppointment(String expectedSymptom) throws InterruptedException {
        WebElement symptomInputField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ScrollView[@index=1]/preceding::android.widget.EditText")));
        symptomInputField.clear();
        Thread.sleep(1000);
        symptomInputField.sendKeys(expectedSymptom);
        Thread.sleep(500);
        clinicInformationPage.selectOptions(Collections.singletonList(expectedSymptom));
        logger.info("Selected symptom: "+expectedSymptom);
    }
    public void verifySymptomsAdded(List<String> expectedSymptoms){
        List<WebElement> symptomsList=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//android.widget.TextView[@text=\"Symptoms \"]/following-sibling::android.view.ViewGroup[@content-desc][1]//android.widget.Button")));
        List<String> actualSymptoms=new ArrayList<>();
        for (WebElement symptomElement : symptomsList) {
            actualSymptoms.add(symptomElement.getDomAttribute("content-desc"));
        }
        System.out.println("Actual symptoms: "+actualSymptoms);
        System.out.println("Expected symptoms: "+expectedSymptoms);
        Assert.assertEquals(actualSymptoms,expectedSymptoms,"Symptoms are not added correctly");

    }

    public void selectFutureDateWhileBookingAppointment() throws InterruptedException {
        Utility.explicitlyWait(appointmentDatePicker,driver,10);
        appointmentDatePicker.click();
        Thread.sleep(1000);
        Utility.customizeScrollByCoordinates(driver,521,1299,521,1050);
        Thread.sleep(1000);
        clickOnOkOfDatePicker();
    }
    public void clickOnOkOfDatePicker() throws InterruptedException {
        Thread.sleep(1000);
        okButtonOnDatePicker.click();
        logger.info("Clicked on ok button of date picker");
    }
}
