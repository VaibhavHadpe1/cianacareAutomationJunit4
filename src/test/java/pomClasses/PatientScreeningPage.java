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
import java.util.stream.Collectors;

import static steps.Base.driver;

public class PatientScreeningPage {
    ServicesPage servicesPage;
    public PatientScreeningPage(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)),this);
        servicesPage=new ServicesPage(driver);
    }
    public static Logger logger= LoggerFactory.getLogger(PatientScreeningPage.class);
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Appointments\"]")private WebElement appointmentsFeatureOnDashboard;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"No Events Available\"]")private WebElement defaultMessageForNoAppointments;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc, \"Start\") or contains(@content-desc, \"Resume\")]") private WebElement initiateButton;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,'Clear ')]") private WebElement clearAllButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Added\"]") private List<WebElement> addedButtonAsList;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Quick type\"]") private List<WebElement> quickTypeTitleAsList;
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@content-desc,\"Next:\")]") private WebElement nextButtonOnPatientScreening;
    @AndroidFindBy(xpath = "(//android.widget.ScrollView//android.view.ViewGroup[@content-desc]//android.view.ViewGroup)[1]") private WebElement closeButtonForVitalsBottomSheet;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Skip\"]") private WebElement skipButtonOnVitals;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Save\"]") private WebElement saveButtonOnVitals;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"History\"]")private WebElement historyButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Templates\"]") private WebElement templateButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"No Templates Available\"]") private List<WebElement> noTemplatesAsList;

    WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
    public void clickOnAppointmentsFeature(){
        Utility.explicitlyWait(appointmentsFeatureOnDashboard,driver,10);
        appointmentsFeatureOnDashboard.click();
        logger.info("Clicked on Appointments feature from dashboard");
    }
    public void selectAppointment(String expectedTime) throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> appointmentsList=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//android.widget.ScrollView[@index=6]//android.view.ViewGroup[contains(@content-desc,\":\")]")));
        System.out.println("Number of appointments: "+appointmentsList.size());
        if (!appointmentsList.isEmpty()){
            Thread.sleep(2000);
            WebElement appointment=driver.findElement(By.xpath("(//android.widget.ScrollView[@index=6]//android.view.ViewGroup[contains(@content-desc,'"+expectedTime+"')]//android.view.ViewGroup//android.widget.TextView)[4]"));
            Utility.explicitlyWait(appointment,driver,10);
            appointment.click();
            logger.info("Appointment is selected");
        }
        else {
            logger.info("Default message: "+defaultMessageForNoAppointments.getText());
            logger.info("No appointment is available");
        }

    }

    public void initiateTheAppointment() {
        Utility.explicitlyWait(initiateButton,driver,7);
        initiateButton.click();
        logger.info("Clicked on initiateButton");
    }
    public void checkAppointmentStatus() throws InterruptedException {
        Thread.sleep(2000);
        String appointmentStatus=driver.findElement(By.xpath("//android.view.ViewGroup[contains(@content-desc, \"Start\") or contains(@content-desc, \"Resume\")]//android.widget.TextView")).getText();
        System.out.println("************ "+appointmentStatus);
        if(appointmentStatus.equals("Resume")){
            logger.info("Appointment is initiated earlier");
        }
        else {
            logger.info("Appointment is not initiated earlier");
        }
    }

    public void verifyExpectedPatientScreeningFeaturesAreDisplayed1(List<String> expectedPatientScreeningComponents) {
        Set<String> actualFeaturesSet = new LinkedHashSet<>(); // Using Set to avoid duplicates
        boolean canScrollMore = true;
        String lastElementText = "";
        while (canScrollMore) {
            // Get current visible features
            List<WebElement> visibleFeatures = driver.findElements(By.xpath("//android.widget.ScrollView[@index='1']//android.view.ViewGroup//android.view.ViewGroup[@content-desc]//android.widget.TextView"));
            String currentLastText = "";
            for (WebElement element : visibleFeatures) {
                String text = element.getText().trim();
                actualFeaturesSet.add(text);
                currentLastText = text; // Keep track of last element seen
            }
            // If scrolling doesn't show new content, break
            if (currentLastText.equals(lastElementText)) {
                canScrollMore = false;
            } else {
                lastElementText = currentLastText;
                Utility.swipeDown(driver);
            }
        }
        // Convert set to list for easy comparison
        List<String> actualFeatures = new ArrayList<>(actualFeaturesSet);
        //Compare expected vs actual features
        for (String expected : expectedPatientScreeningComponents) {
            if (!actualFeatures.contains(expected)) {
                System.out.println("Missing feature: " + expected);
                Assert.fail("Expected feature not found in Patient Screening screen: " + expected);
            }
        }
        System.out.println("All expected features are displayed in Patient Screening.");
        logger.info("All expected features are displayed");
    }

    public void searchAndSelect(String expectedSearchInput) throws InterruptedException {
        WebElement searchInputField=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.ViewGroup//android.widget.EditText")));
        searchInputField.clear();
        searchInputField.sendKeys(expectedSearchInput);
        Thread.sleep(1000);
        logger.info("Entered search input");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[contains(@text,'"+expectedSearchInput+"')]"))).click();
        logger.info("Selected results from searched value");
    }

    public void selectValueFromSearchResults(String expectedSearchResult) {

    }
    public void addedValues(List<String> expectedAddedValues) throws InterruptedException {
        Thread.sleep(1000);
        List<WebElement> listOfAddedValues=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//android.view.ViewGroup[contains(@content-desc,'Clear ')]/following-sibling::android.view.ViewGroup[@content-desc]//android.widget.TextView")));
        List<String> actualAddedValues=new ArrayList<>();
        for(WebElement value:listOfAddedValues){
           String text=value.getText().trim();
           actualAddedValues.add(text);
        }
        System.out.println("List: "+ String.join(" ", actualAddedValues));
        for(String expectedValue:expectedAddedValues){
            if(actualAddedValues.stream().noneMatch(actual -> actual.contains(expectedValue))){
                System.out.println("Missing data: " + expectedValue);
                Assert.fail("Expected data not found: " + expectedValue);
            }
        }
        System.out.println("All selected values are added");
        logger.info("All values are added successfully");
    }
    public void clickOnClearAll(){
        Utility.explicitlyWait(clearAllButton,driver,10);
        clearAllButton.click();
        logger.info("Clicked on clear all button");
    }
    public void removeDataUsingRemoveIcon(String expectedData){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.ViewGroup[contains(@content-desc,'"+expectedData+"')]//android.view.ViewGroup//android.widget.ImageView"))).click();
        logger.info("Removed: "+expectedData);
    }
    public void verifyRemovedValuesAreNotDisplayed(List<String> expectedValues) throws InterruptedException {
        Thread.sleep(1000);
        if(addedButtonAsList.isEmpty()){
            logger.info("All added data is removed");
        }
        else{
            Thread.sleep(1000);
            List<WebElement> listOfAddedValues=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//android.view.ViewGroup[contains(@content-desc,'Clear ')]/following-sibling::android.view.ViewGroup[@content-desc]//android.widget.TextView")));
            List<String> actualAddedValues=new ArrayList<>();
            for(WebElement value:listOfAddedValues){
                String text=value.getText().trim();
                actualAddedValues.add(text);
            }
            System.out.println("List: "+ String.join(",", actualAddedValues));
            for(String expectedValue:expectedValues){
                if(!actualAddedValues.contains(expectedValue)){
                    System.out.println("Removed data: " + expectedValue);
                    logger.info("Expected data is removed");
                    break;
                }
            }
            System.out.println("Expected data is not removed from the list");
            logger.error("Expected data is not removed");
        }
    }
    public void addValuesFromQuickType(String expectedQuickTypeValue){
        if (quickTypeTitleAsList.isEmpty())
        {
            logger.info("Quick Type is not available");
        }
        else {
            List<WebElement> quickTypeList=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//android.widget.TextView[@text=\"Quick type\"]/following-sibling::android.view.ViewGroup//android.view.ViewGroup//android.widget.TextView")));
            for(WebElement quickTypeValue:quickTypeList){
                if (quickTypeValue.getText().contains(expectedQuickTypeValue)){
                    quickTypeValue.click();
                    logger.info("Selected value: "+quickTypeValue.getText());
                    break;
                }
            }
        }
    }

    public void clickOnNext() {
        Utility.explicitlyWait(nextButtonOnPatientScreening,driver,10);
        nextButtonOnPatientScreening.click();
    }

    public void addVitals(String expectedVital) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.ViewGroup[contains(@content-desc,'"+expectedVital+"')]"))).click();
        Thread.sleep(1000);
        scrollToTargetValue(80.0);//Heart Rate
        clickOnSave();
        scrollToTargetValue(80.0);//SPO2
        clickOnSave();
        scrollToTargetValue(91.0);//Temperature
        clickOnSave();
        scrollToTargetValue(150.0);//Height
        clickOnSave();
        scrollToTargetValue(60.0);//Weight
        clickOnSave();
        scrollToTargetValue(70.0);//Systolic blood pressure
        clickOnSave();
        scrollToTargetValue(80);//Diastolic blood pressure
        clickOnSave();

    }
    public void scrollToTargetValue(double expectedValue) throws InterruptedException {
        int maxAttempts = 15; // Prevent infinite loop
        int attempts = 0;
        while (attempts < maxAttempts) {
            Thread.sleep(1000);
            String currentVitalValue=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("((//android.widget.ScrollView//android.view.ViewGroup[@content-desc]/following-sibling::android.view.ViewGroup)[3]//android.view.ViewGroup[1]//android.widget.TextView)[1]"))).getText();
           // currentVitalValue = currentVitalValue.replaceAll("[^\\d.]", "");
            double currentValue = Double.parseDouble(currentVitalValue);
            logger.info("Current value: " + currentValue + " | Expected: " + expectedValue);
            if(currentValue==expectedValue){
                logger.info("Scrolled to expected value");
                break;
            }
            if(currentValue<expectedValue){
                //ScrollToRight means swipe left
                Utility.customizeScrollByCoordinates(driver,880,2070,130,2070);
                logger.info("Scrolling to increasing order");
            }
            else if (currentValue>expectedValue) {
                //Scroll To left means swipe right
                Thread.sleep(1000);
                Utility.customizeScrollByCoordinates(driver,100,2073,900,2073);
                logger.info("Scrolling to decreasing order");
            }
            attempts++;
        }
        if (attempts >= maxAttempts) {
            logger.warn("Max attempts reached. Could not scroll to expected value: " + expectedValue);
        }
    }
    public void clickOnSave(){
        Utility.explicitlyWait(saveButtonOnVitals,driver,5);
        saveButtonOnVitals.click();
        logger.info("Clicked on save");
    }
    public void clickOnSkip(){
        Utility.explicitlyWait(skipButtonOnVitals,driver,5);
        skipButtonOnVitals.click();
        logger.info("Clicked on skip");
    }
    public void clickOnCloseVitalsSheet(){
        Utility.explicitlyWait(closeButtonForVitalsBottomSheet,driver,5);
        closeButtonForVitalsBottomSheet.click();
        logger.info("Clicked on close icon for vitalsBottomSheet");
    }

    public void clickOnHistory() {
        Utility.explicitlyWait(historyButton,driver,10);
        historyButton.click();
        logger.info("Clicked on History");
    }

    public void verifyHistoryOrDefaultMessage(String expectedDefaultMessage ) {
        List<WebElement> defaultMessageAsList=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//android.widget.TextView[@text='"+expectedDefaultMessage+"']")));
        if(defaultMessageAsList.isEmpty()){
            logger.info("History is available and displayed");
        }
        else {
            logger.info("History is not available and default message is displayed");
            for (WebElement defaultMessage:defaultMessageAsList){
                logger.info("Default message is "+defaultMessage.getText());
            }
        }
    }

    public boolean isTemplateAvailable() throws InterruptedException {
        Utility.explicitlyWait(templateButton,driver,10);
        templateButton.click();
        logger.info("Clicked on Templates option");
        Thread.sleep(1000);
        if(!noTemplatesAsList.isEmpty()){
            logger.info("Templates not available and Default message is: "+noTemplatesAsList.stream().map(WebElement::getText).collect(Collectors.joining()));
            Utility.tapOutsideToCloseBottomSheet(driver,300,150);
            return false;
        }
        else {
            logger.info("Templates are available");
            return true;
        }
    }
    public void applyTemplateAndViewMedicineNames(String expectedTemplateName) throws InterruptedException {
        Thread.sleep(1000);
        if(isTemplateAvailable()){
            logger.info("Templates are available");
            //Expand the template card
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.ViewGroup[@index=3]//android.widget.ScrollView//android.view.ViewGroup[contains(@content-desc,'"+expectedTemplateName+"')]"))).click();
            logger.info("Template card is expanded");
            Thread.sleep(1000);
            String medicineName=driver.findElement(By.xpath("(//android.widget.ScrollView[@index=0]//android.view.ViewGroup//android.widget.CheckBox/following-sibling::android.widget.TextView)[1]")).getText();
            System.out.println("Medicine Name in Template: "+medicineName);
            clickOnAddToPrescription(expectedTemplateName);
            addedValues(Collections.singletonList(medicineName));
        }
        else {
            logger.info("Templates not available and Default message is: "+noTemplatesAsList.stream().map(WebElement::getText).collect(Collectors.joining()));
            Utility.tapOutsideToCloseBottomSheet(driver,300,150);
        }
    }
    public void clickOnAddToPrescription(String expectedTemplateName) throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Add to prescription\"]")).click();
        logger.info("Clicked on Add to prescription button");
    }
    public void closeBottomSheetInScreening(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//android.view.ViewGroup[@index=3]//android.view.ViewGroup//android.widget.ImageView)[1]"))).click();
    }
    String medicineNames;
    public void addMedicineTroughHistory(){
        clickOnHistory();
        List<WebElement> defaultMessageAsList=driver.findElements(By.xpath("//android.widget.TextView[@text='No History Available']"));
        if(defaultMessageAsList.isEmpty()){
            logger.info("History is available and displayed");
            WebElement medicienName=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//android.widget.ScrollView[@index=1]//android.view.ViewGroup//android.widget.TextView[@index=1])[1]")));
            medicineNames=medicienName.getText();
            logger.info("Medicine name in prescription history: "+medicineNames);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//android.widget.Button[@content-desc=\"Repeat\"])[1]"))).click();
            closeBottomSheetInScreening();
            logger.info("Clicked on Repeat button and closed the bottom sheet");
        }
        else {
            logger.info("History is not available and default message is displayed");
            for (WebElement defaultMessage:defaultMessageAsList){
                logger.info("Default message is "+defaultMessage.getText());
            }
        }
    }
    public void verifyAddedMedicineThroughHistoryDisplayed() throws InterruptedException {
        if(medicineNames != null){
            addedValues(Collections.singletonList(medicineNames));
            logger.info("Added medicine through history is displayed");
        }
        else {
            logger.info("Added medicine through history is not displayed");
        }
    }
    public static String normalizeText(String text) {
        // Remove bullet points and special characters like •, *, -, etc.
        text = text.replaceAll("[•\\-*]", "");
        // Convert to lowercase
        text = text.toLowerCase();
        // Replace multiple whitespaces (including newlines and tabs) with a single space
        text = text.replaceAll("\\s+", " ").trim();
        return text;
    }

    public void verifyAddedPatientInstructionsDisplayed(String expectedPatientInstructions) {
        WebElement patientInstructions=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText[@resource-id=\"content\"]")));
        String actualPatientInstructions=patientInstructions.getDomAttribute("text");

        String normalizedActual = normalizeText(actualPatientInstructions);
        String normalizedExpected = normalizeText(expectedPatientInstructions);

        System.out.println("Normalized Actual: " + normalizedActual);
        System.out.println("Normalized Expected: " + normalizedExpected);

        if (normalizedActual.equals(normalizedExpected)) {
            logger.info("Actual and Expected Patient Instructions match");
        } else {
            logger.error("Actual and Expected Patient Instructions do not match");
            Assert.fail("Actual and Expected Patient Instructions do not match");
        }
    }
}
