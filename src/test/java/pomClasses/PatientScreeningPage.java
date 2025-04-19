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
    PersonalInformationPage personalInformationPage;
    ClinicInformationPage clinicInformationPage;
    TemplatePage templatePage;
    public PatientScreeningPage(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)),this);
        servicesPage=new ServicesPage(driver);
        personalInformationPage=new PersonalInformationPage(driver);
        clinicInformationPage=new ClinicInformationPage(driver);
        templatePage=new TemplatePage(driver);
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
    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,'Social history')]//android.widget.TextView[@text=\"Edit\"]") private WebElement editButtonOfSocialHistory;
    @AndroidFindBy(xpath = "(//android.widget.ScrollView[@index=4]//android.view.ViewGroup//android.widget.EditText)[1]") private WebElement searchOrAddDoctorFieldOnReferADoctorScreen;
    @AndroidFindBy(xpath = "(//android.widget.ScrollView[@index=4]//android.view.ViewGroup//android.widget.EditText)[2]") private WebElement specialityFieldOnReferADoctorScreen;
    @AndroidFindBy(xpath = "(//android.widget.ScrollView[@index=4]//android.view.ViewGroup//android.widget.EditText)[3]") private WebElement addressFieldOnReferADoctorScreen;
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Preview\"]") private WebElement previewButtonPatientScreening;
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Preview Rx\"]") private WebElement previewRxButtonPatientScreening;
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"End encounter\"]") private WebElement endEncounterButtonPatientScreening;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,\"APT\")]//android.view.View[@content-desc=\"Pharmacy\"]") private WebElement pharmacyTabOnEndEncounterPopup;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,\"APT\")]//android.view.View[@content-desc=\"Lab\"]") private WebElement labTabOnEndEncounterPopup;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,\"APT\")]//android.view.ViewGroup[contains(@content-desc,\"Whats app\")]") private WebElement whatsAppOptionOnEndEncounterPopup;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,\"APT\")]//android.view.ViewGroup[contains(@content-desc,\"Email\")]") private WebElement emailOptionOnEndEncounterPopup;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,\"APT\")]//android.view.ViewGroup[contains(@content-desc,\"English\")]") private WebElement englishOptionOnEndEncounterPopup;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,\"APT\")]//android.view.ViewGroup[contains(@content-desc,\"Telugu\")]") private WebElement teluguOptionOnEndEncounterPopup;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,\"APT\")]//android.widget.Button[@content-desc=\"Send\"]")private WebElement sendButtonOnEndEncounterPopup;





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
        Thread.sleep(2000);
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
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.ViewGroup[contains(@content-desc,'"+expectedData+"')]//android.view.ViewGroup//android.widget.ImageView"))).click();
        String lowerCaseData = expectedData.toLowerCase();
        String xpath = "//android.view.ViewGroup[" + "contains(" + "translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '" + lowerCaseData + "')]" + "//android.view.ViewGroup//android.widget.ImageView";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
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
        int maxAttempts = 7; // Prevent infinite loop
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

    public void verifyHistoryOrDefaultMessage(String expectedDefaultMessage ) throws InterruptedException {
        Thread.sleep(1000);
        List<WebElement> defaultMessageAsList=driver.findElements(By.xpath("//android.widget.TextView[@text='"+expectedDefaultMessage+"']"));
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
    public void clickOnTemplatesOptionPatientScreening(){
        Utility.explicitlyWait(templateButton,driver,10);
        templateButton.click();
        logger.info("Clicked on Templates option");
    }
    public boolean isTemplateAvailable() throws InterruptedException {
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
            //Expand the template card
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.ViewGroup[@index=3]//android.widget.ScrollView//android.view.ViewGroup[contains(@content-desc,'"+expectedTemplateName+"')]"))).click();
            logger.info("Template card is expanded");
            Thread.sleep(2000);
            String medicineName=driver.findElement(By.xpath("(//android.widget.ScrollView[@index=0]//android.view.ViewGroup//android.widget.CheckBox/following-sibling::android.widget.TextView)[1]")).getText();
            System.out.println("Medicine Name in Template: "+medicineName);
            clickOnAddToPrescription(expectedTemplateName);
            Thread.sleep(1000);
            addedValues(Collections.singletonList(medicineName));
        }
        else {
            logger.info("Templates not available and Default message is: "+noTemplatesAsList.stream().map(WebElement::getText).collect(Collectors.joining()));
            Utility.tapOutsideToCloseBottomSheet(driver,300,150);
        }
    }
    public void applyTemplateAndViewInvestigationNames(String expectedTemplateName) throws InterruptedException {
        Thread.sleep(1000);
        if(isTemplateAvailable()){
            //Expand the template card
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.ViewGroup[@index=3]//android.widget.ScrollView//android.view.ViewGroup[contains(@content-desc,'"+expectedTemplateName+"')]"))).click();
            logger.info("Template card is expanded");
            Thread.sleep(2000);
            String investigationName=driver.findElement(By.xpath("(//android.widget.TextView[@text='Investigation']/following-sibling::android.widget.TextView)[1]")).getText();
            System.out.println("Investigation Name in Template: "+investigationName);
            clickOnAddToPrescription(expectedTemplateName);
            Thread.sleep(1000);
            addedValues(Collections.singletonList(investigationName));
        }
        else {
            logger.info("Templates not available and Default message is: "+noTemplatesAsList.stream().map(WebElement::getText).collect(Collectors.joining()));
            Utility.tapOutsideToCloseBottomSheet(driver,300,150);
        }
    }
    public void clickOnAddToPrescription(String expectedTemplateName) throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//android.widget.Button[@content-desc='Add to prescription']")).click();
        logger.info("Clicked on Add to prescription button");
    }
    public void closeBottomSheetInScreening(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//android.view.ViewGroup[@index=3]//android.view.ViewGroup//android.widget.ImageView)[1]"))).click();
    }
    String medicineNames;
    public void addMedicineThroughHistory() throws InterruptedException {
        clickOnHistory();
        Thread.sleep(1000);
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
    public void clickOnExpectedSectionInOverview(String expectedSection){
       WebElement overviewsSection=driver.findElement(By.xpath("//android.view.ViewGroup[contains(@content-desc,'"+expectedSection+"')]"));
       Utility.explicitlyWait(overviewsSection,driver,10);
       overviewsSection.click();
        logger.info("Clicked on "+expectedSection);
    }
    public void addStartedFromDuration(String expectedDuration, String expectedUnit) throws InterruptedException {
        personalInformationPage.sendInputToField("Started from *",expectedDuration);
        clinicInformationPage.selectOptions(Collections.singletonList(expectedUnit));
    }

    public void addDrugAllergies(String expectedDrugName,String expectedDuration,String expectedUnit) throws InterruptedException {
        Thread.sleep(1000);
        clickOnExpectedSectionInOverview("Drug sensitivity");
        searchAndSelect(expectedDrugName);
        addStartedFromDuration(expectedDuration,expectedUnit);
        templatePage.clickOnSaveButtonOfBottomSheet();
        logger.info("Drug is added to Drug sensitivity section"+expectedDrugName);
    }

    public void verifyAddedValuesInOverview(String expectedSectionInOverview,List<String>expectedValues) {
        try{
            List<WebElement> addedDataInOverview = driver.findElements(By.xpath("//android.view.ViewGroup[contains(@content-desc,'" + expectedSectionInOverview + "')]//android.view.ViewGroup[contains(@content-desc,'Clear ')]/following-sibling::android.view.ViewGroup[contains(@content-desc,' ')]"));
            if (addedDataInOverview.isEmpty()) {
                logger.info("No data is added in " + expectedSectionInOverview);
            } else {
                List<String> actualAddedValues = new ArrayList<>();
                for (WebElement value : addedDataInOverview) {
                    String text = value.getDomAttribute("content-desc").trim();
                    actualAddedValues.add(text);
                }
                System.out.println("List: " + String.join(",", actualAddedValues));
                for (String expectedValue : expectedValues) {
                    if (actualAddedValues.stream().noneMatch(actual -> actual.contains(expectedValue))) {
                        System.out.println("Missing data: " + expectedValue);
                        Assert.fail("Expected data not found: " + expectedValue);
                    }
                }
                System.out.println("All selected values are added");
                logger.info("All values are added successfully");
            }
        } catch (Exception e) {
            List<WebElement> addedDataInOverview = driver.findElements(By.xpath("//android.view.ViewGroup[contains(@content-desc,'" + expectedSectionInOverview + "')]//android.view.ViewGroup[contains(@content-desc,'Clear ')]/following-sibling::android.view.ViewGroup[contains(@content-desc,' ')]"));
            if (addedDataInOverview.isEmpty()) {
                logger.info("No data is added in " + expectedSectionInOverview);
            } else {
                List<String> actualAddedValues = new ArrayList<>();
                for (WebElement value : addedDataInOverview) {
                    String text = value.getDomAttribute("content-desc").trim();
                    actualAddedValues.add(text);
                }
                System.out.println("List: " + String.join(",", actualAddedValues));
                for (String expectedValue : expectedValues) {
                    if (actualAddedValues.stream().noneMatch(actual -> actual.contains(expectedValue))) {
                        System.out.println("Missing data: " + expectedValue);
                        Assert.fail("Expected data not found: " + expectedValue);
                    }
                }
                System.out.println("All selected values are added");
                logger.info("All values are added successfully");
            }
        }
    }

    public void clickOnClearAllForParticularSectionInOverview(String expectedSectionInOverview) {
       List<WebElement> clearAllButton=driver.findElements(By.xpath("//android.view.ViewGroup[contains(@content-desc,'"+expectedSectionInOverview+"')]//android.view.ViewGroup[contains(@content-desc,'Clear ')]"));
       if(clearAllButton.isEmpty()) {
           logger.info("Clear all button is not available");
       }
       else {
           clearAllButton.get(0).click();
           logger.info("Clicked on Clear all for "+expectedSectionInOverview);
       }
    }

    public void addFoodAllergies(String expectedFoodName, String expectedDuration, String expectedUnit) throws InterruptedException {
        Thread.sleep(1000);
        clickOnExpectedSectionInOverview("Food sensitivity");
        searchAndSelect(expectedFoodName);
        addStartedFromDuration(expectedDuration,expectedUnit);
        templatePage.clickOnSaveButtonOfBottomSheet();
        logger.info("Food is added to Food sensitivity section"+expectedFoodName);
    }

    public void addEnvironmentAllergies(String expectedEnvironmentName, String expectedDuration, String expectedUnit) throws InterruptedException {
        Thread.sleep(1000);
        clickOnExpectedSectionInOverview("Environmental sensitivity");
        searchAndSelect(expectedEnvironmentName);
        addStartedFromDuration(expectedDuration,expectedUnit);
        templatePage.clickOnSaveButtonOfBottomSheet();
        logger.info("Food is added to Food sensitivity section"+expectedEnvironmentName);
    }
    public void removeDataUsingRemoveIconInOverview(String expectedData){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.ViewGroup[contains(@content-desc,'"+expectedData+"')]/following-sibling::android.view.ViewGroup//android.widget.ImageView"))).click();
        logger.info("Removed: "+expectedData);
    }
    public void addDuration(String expectedDuration, String expectedUnit) throws InterruptedException {
        personalInformationPage.sendInputToField("Duration *",expectedDuration);
        clinicInformationPage.selectOptions(Collections.singletonList(expectedUnit));
    }
    public void addProblemList(String expectedMedicalCondition,String expectedDuration,String expectedUnit,String expectedStatus ) throws InterruptedException {
        Thread.sleep(1000);
        clickOnExpectedSectionInOverview("Problem list");
        searchAndSelect(expectedMedicalCondition);
        addDuration(expectedDuration,expectedUnit);
        clinicInformationPage.selectOptions(Collections.singletonList(expectedStatus));
        templatePage.clickOnSaveButtonOfBottomSheet();
        logger.info("Drug is added to Drug sensitivity section"+expectedMedicalCondition);
    }

    public void addMedicationInOverview(String expectedMedicine,String expectedDosageRoute,String expectedFrequency,String expectedTiming,String expectedDuration,String expectedUnit) throws InterruptedException {
        Thread.sleep(1000);
        clickOnExpectedSectionInOverview("Medications");
        searchAndSelect(expectedMedicine);
        clinicInformationPage.selectOptions(Collections.singletonList(expectedDosageRoute));
        clinicInformationPage.selectOptions(Collections.singletonList(expectedFrequency));
        Utility.customizeScrollByCoordinates(driver,700,2100,700,1100);
        clinicInformationPage.selectOptions(Collections.singletonList(expectedTiming));
        addDuration(expectedDuration,expectedUnit);
        templatePage.clickOnSaveButtonOfBottomSheet();
        logger.info("Drug is added to Drug sensitivity section "+expectedMedicine);
    }

    public void addFamilyHistory(String expectedCondition, String expectedRelation) throws InterruptedException {
        Thread.sleep(1000);
        clickOnExpectedSectionInOverview("Family history");
        personalInformationPage.sendInputToField("Family condition*",expectedCondition);
        clinicInformationPage.selectOptions(Collections.singletonList(expectedRelation));
        templatePage.clickOnSaveButtonOfBottomSheet();
        logger.info("Family History is added ");
    }

    public void addPastSurgicalHistory(String expectedProcedureDescription, String expectedDuration, String expectedUnit, String expectedOutcome) throws InterruptedException {
        Thread.sleep(1000);
        clickOnExpectedSectionInOverview("Past surgical history");
        searchAndSelect(expectedProcedureDescription);
        addDuration(expectedDuration,expectedUnit);
        personalInformationPage.sendInputToField("Outcome*",expectedOutcome);
        templatePage.clickOnSaveButtonOfBottomSheet();
        logger.info("Drug is added to Drug sensitivity section "+expectedProcedureDescription);
    }

    public void addPastMedicalConditions(String expectedProcedureDescription, String expectedDuration, String expectedUnit, String expectedOutcome) throws InterruptedException {
        Thread.sleep(1000);
        clickOnExpectedSectionInOverview("Past medical conditions");
        searchAndSelect(expectedProcedureDescription);
        addDuration(expectedDuration,expectedUnit);
        personalInformationPage.sendInputToField("Outcome*",expectedOutcome);
        templatePage.clickOnSaveButtonOfBottomSheet();
        logger.info("Drug is added to Drug sensitivity section "+expectedProcedureDescription);
    }
    public void clickOnEditButtonOfSocialHistory(){
        Utility.explicitlyWait(editButtonOfSocialHistory,driver,10);
        editButtonOfSocialHistory.click();
        logger.info("Clicked on edit button of social history");
    }
    public void selectYesOrNoForQuestion(String expectedQuestion, String expectedAnswer) throws InterruptedException {
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='"+expectedQuestion+"']/following-sibling::android.view.ViewGroup[@content-desc='Yes' or @content-desc='No'][@content-desc='"+expectedAnswer+"']"))).click();
        logger.info("Clicked on "+expectedAnswer+" for "+expectedQuestion);
    }
    public void addOrUpdateSocialHistory() throws InterruptedException {
        Thread.sleep(1000);
        clickOnEditButtonOfSocialHistory();
        clinicInformationPage.selectOptions(Collections.singletonList("Disturbed"));//Sleep
        clinicInformationPage.selectOptions(Collections.singletonList("Vegan"));//Diet
        clinicInformationPage.selectOptions(Collections.singletonList("Occasional"));//Smoking
        clinicInformationPage.selectOptions(Collections.singletonList("0.5"));
        Utility.customizeScrollByCoordinates(driver,700,1850,700,800);
        clinicInformationPage.selectOptions(Collections.singletonList("Regular"));//Alcohol
        clinicInformationPage.selectOptions(Collections.singletonList("50"));
        selectYesOrNoForQuestion("Tobacco consumption","No");//Tobacco consumption
        selectYesOrNoForQuestion("Recreational drugs","No");//Recreational Drugs
        Utility.customizeScrollByCoordinates(driver,700,1850,700,1000);
        selectYesOrNoForQuestion("Exercise","Yes");//Exercise
        clinicInformationPage.selectOptions(Collections.singletonList("Single"));//Martial status
        Utility.customizeScrollByCoordinates(driver,700,1850,700,800);
        clinicInformationPage.selectOptions(Arrays.asList("Father","Mother"));//Social Support
        clinicInformationPage.selectOptions(Collections.singletonList("Doctorate"));//Education status
        clinicInformationPage.selectOptions(Collections.singletonList("Employed"));//Occupation
        templatePage.clickOnSaveButtonOfBottomSheet();
    }
    public void verifyAddedValuesInSocialOverview(String expectedSectionInOverview,List<String>expectedValues) {
        try{
            List<WebElement> addedDataInOverview = driver.findElements(By.xpath("//android.view.ViewGroup[contains(@content-desc,'" + expectedSectionInOverview + "')]//android.widget.TextView[@text='Edit']/following-sibling::android.view.ViewGroup[@content-desc]"));
            if (addedDataInOverview.isEmpty()) {
                logger.info("No data is added in " + expectedSectionInOverview);
            } else {
                List<String> actualAddedValues = new ArrayList<>();
                for (WebElement value : addedDataInOverview) {
                    String text = value.getDomAttribute("content-desc").trim();
                    actualAddedValues.add(text);
                }
                System.out.println("List: " + String.join(",", actualAddedValues));
                for (String expectedValue : expectedValues) {
                    if (actualAddedValues.stream().noneMatch(actual -> actual.contains(expectedValue))) {
                        System.out.println("Missing data: " + expectedValue);
                        Assert.fail("Expected data not found: " + expectedValue);
                    }
                }
                System.out.println("All selected values are added");
                logger.info("All values are added successfully");
            }
        } catch (Exception e) {
            List<WebElement> addedDataInOverview = driver.findElements(By.xpath("//android.view.ViewGroup[contains(@content-desc,'" + expectedSectionInOverview + "')]//android.view.ViewGroup[contains(@content-desc,'Clear ')]/following-sibling::android.view.ViewGroup[contains(@content-desc,' ')]"));
            if (addedDataInOverview.isEmpty()) {
                logger.info("No data is added in " + expectedSectionInOverview);
            } else {
                List<String> actualAddedValues = new ArrayList<>();
                for (WebElement value : addedDataInOverview) {
                    String text = value.getDomAttribute("content-desc").trim();
                    actualAddedValues.add(text);
                }
                System.out.println("List: " + String.join(",", actualAddedValues));
                for (String expectedValue : expectedValues) {
                    if (actualAddedValues.stream().noneMatch(actual -> actual.contains(expectedValue))) {
                        System.out.println("Missing data: " + expectedValue);
                        Assert.fail("Expected data not found: " + expectedValue);
                    }
                }
                System.out.println("All selected values are added");
                logger.info("All values are added successfully");
            }
        }
    }
    public void clickOnExpectedAssessment(String expectedAssessment){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.ViewGroup[contains(@content-desc,'"+expectedAssessment+"')]"))).click();
        logger.info("Clicked on "+expectedAssessment+" Assessment");
    }
    public void selectExpectedDropdownValue(String expectedDropdown, String expectedDropdownValue){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[contains(@text,'"+expectedDropdown+"')]/following-sibling::*[1][@content-desc]"))).click();
        clinicInformationPage.selectOptions(Collections.singletonList(expectedDropdownValue));
        logger.info("Selected "+expectedDropdownValue+" in "+expectedDropdown);
    }
    public void addExtraDetailsInAssessment(String expectedLabelName, String expectedLabelContent) throws InterruptedException {
        Utility.customizeScrollByCoordinates(driver,700,2050,700,1650);
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.Button[@content-desc='ADD']"))).click();
            personalInformationPage.sendInputToField("Label name",expectedLabelName);
            personalInformationPage.sendInputToField("Label content",expectedLabelContent);
        } catch (Exception e) {
            logger.info("Extra details is already added");
        }

    }
    public void addNeuroAssessment() throws InterruptedException {
        clickOnExpectedAssessment("Neuro");
        selectExpectedDropdownValue("Mental status","Unresponsive");
        selectExpectedDropdownValue("Pupils","PERLA");
        selectExpectedDropdownValue("Pupils size(left)","3mm");
        selectExpectedDropdownValue("Pupils size(right)","2mm");
        selectExpectedDropdownValue("Affect","Flat");
        addExtraDetailsInAssessment("NeuroLabelName","NeuroLabelContent");
        templatePage.clickOnSaveButtonOfBottomSheet();
        logger.info("Neuro Assessment added");
    }
    public void addRespiratoryAssessments() throws InterruptedException {
        clickOnExpectedAssessment("Respiratory");
        selectExpectedDropdownValue("Effort","Labored");
        selectExpectedDropdownValue("Sounds (left) ","Clear");
        selectExpectedDropdownValue("Sounds (right)","Crackles");
        selectExpectedDropdownValue("Cough","Moist");
        addExtraDetailsInAssessment("RespiratoryLabelName","RespiratoryLabelContent");
        templatePage.clickOnSaveButtonOfBottomSheet();
        logger.info("Respiratory Assessment added");
    }
    public void addCardiovascularAssessments() throws InterruptedException {
        clickOnExpectedAssessment("Cardiovascular");
        selectExpectedDropdownValue("Central pulses", "Weak");
        selectExpectedDropdownValue("Heart sounds", "S1, S2, S3");
        selectExpectedDropdownValue("Peripheral pulses", "WNL");
        addExtraDetailsInAssessment("CardiovascularLabelName", "CardiovascularLabelContent");
        templatePage.clickOnSaveButtonOfBottomSheet();
        logger.info("Cardiovascular Assessment added");
    }
    public void addAbdominalAssessments() throws InterruptedException {
        clickOnExpectedAssessment("Abdominal");
        selectExpectedDropdownValue("Abdomen is","Round");
        selectExpectedDropdownValue("Bowell sounds","Hyperactive");
        selectExpectedDropdownValue("Quadrants","Bilateral upper");
        addExtraDetailsInAssessment("AbdominalLabelName","AbdominalLabelContent");
        templatePage.clickOnSaveButtonOfBottomSheet();
        logger.info("Abdominal Assessment added");
    }
    public void addMusculoskeletalAssessments() throws InterruptedException {
        clickOnExpectedAssessment("Muscoloskeltal");//Need to correct spelling.
        selectExpectedDropdownValue("Movement","Full Passive ROM");
        selectExpectedDropdownValue("Contracture","None");
        addExtraDetailsInAssessment("MusculoskeletalLabelName","MusculoskeletalLabelContent");
        templatePage.clickOnSaveButtonOfBottomSheet();
        logger.info("Musculoskeletal Assessment added");
    }
    public void addGlassLowComaScaleAssessments() throws InterruptedException {
        clickOnExpectedAssessment("Glaslow Coma Scale(GCS SCALE)");
        selectExpectedDropdownValue("Pts./Eye opening response ","Opens to verbal command, speech, or shout");
        selectExpectedDropdownValue("Pts verbal response ","Inappropriate response, words discernible");
        selectExpectedDropdownValue("Pts motor response ","Withdraws from pain");
        addExtraDetailsInAssessment("GlassLowComaScaleLabelName","GlassLowComaScaleLabelContent");
        templatePage.clickOnSaveButtonOfBottomSheet();
        logger.info("GlassLowComaScale Assessment added");
    }
    public void addIntegumentaryAssessments() throws InterruptedException {
        clickOnExpectedAssessment("Integumentary");
        selectExpectedDropdownValue("Temparature","Cold");//Temperature spelling is wrong.
        selectExpectedDropdownValue("Moisture","Wet");
        selectExpectedDropdownValue("Color","Other");
        selectExpectedDropdownValue("Type of wound","Surgical");
        addExtraDetailsInAssessment("IntegumentaryLabelName","IntegumentaryLabelContent");
        templatePage.clickOnSaveButtonOfBottomSheet();
        logger.info("Integumentary Assessment added");
    }
    public void addNeurovascularAssessments() throws InterruptedException {
        clickOnExpectedAssessment("Neurovascular");
        selectExpectedDropdownValue("Extremity","Left Upper Extremity");
        selectExpectedDropdownValue("Pain","3");
        selectExpectedDropdownValue("Pulses","Weak");
        selectExpectedDropdownValue("Color","Pale");
        selectExpectedDropdownValue("Movement","Unable to Move");
        selectExpectedDropdownValue("Sensation","Absent");
        selectExpectedDropdownValue("Temperature","Cold");
        addExtraDetailsInAssessment("NeurovascularLabelName","NeurovascularLabelContent");
        templatePage.clickOnSaveButtonOfBottomSheet();
        logger.info("Neurovascular Assessment added");
    }

    public void addReferDoctorDetails(String expectedDrname,String expectedDrSpeciality,String expectedDrAddress) {
        Utility.explicitlyWait(searchOrAddDoctorFieldOnReferADoctorScreen,driver,10);
        searchOrAddDoctorFieldOnReferADoctorScreen.sendKeys(expectedDrname);
        clinicInformationPage.selectOptions(Collections.singletonList(expectedDrname));
        Utility.explicitlyWait(specialityFieldOnReferADoctorScreen,driver,10);
        specialityFieldOnReferADoctorScreen.sendKeys(expectedDrSpeciality);
        Utility.explicitlyWait(addressFieldOnReferADoctorScreen,driver,10);
        addressFieldOnReferADoctorScreen.sendKeys(expectedDrAddress);
        logger.info("Refer Doctor details added");
    }

    public void verifyReferDoctorDetails(String expectedDrName,String expectedDrSpeciality,String expectedDrAddress) {
        String actualDrName=searchOrAddDoctorFieldOnReferADoctorScreen.getDomAttribute("text");
        String actualDrSpeciality=specialityFieldOnReferADoctorScreen.getDomAttribute("text");
        String actualDrAddress=addressFieldOnReferADoctorScreen.getDomAttribute("text");
        Assert.assertEquals(actualDrName,expectedDrName,"Refer Doctor Name is not matching");
        Assert.assertEquals(actualDrSpeciality,expectedDrSpeciality,"Refer Doctor Speciality is not matching");
        Assert.assertEquals(actualDrAddress,expectedDrAddress,"Refer Doctor Address is not matching");
        logger.info("Refer Doctor details verified");
    }

    public void addFollowUpDetails(String expectedTime,String expectedTimeUnit) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText"))).sendKeys(expectedTime);
        clinicInformationPage.selectOptions(Collections.singletonList(expectedTimeUnit));
    }

    public void verifyFollowUpDetails(String expectedFollowUpTime) {
        String actualTime=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText"))).getDomAttribute("text");
        Assert.assertEquals(actualTime,expectedFollowUpTime,"Follow Up Time is not matching");
        logger.info("Follow Up details verified");
    }
    public void clickOnPreview() throws InterruptedException {
        Utility.explicitlyWait(previewButtonPatientScreening,driver,5);
        previewButtonPatientScreening.click();
        servicesPage.verifyUserIsPresentOnExpectedScreen("Patient Screening");
        logger.info("Clicked on Preview and user navigates to Patient Screening main page");
    }
    public void clickOnPreviewRx(){
        Utility.explicitlyWait(previewRxButtonPatientScreening,driver,5);
        previewRxButtonPatientScreening.click();
        logger.info("Clicked on Preview Rx button");
    }
    public void clickOnEndEncounterButton(){
        Utility.explicitlyWait(endEncounterButtonPatientScreening,driver,5);
        endEncounterButtonPatientScreening.click();
        logger.info("Clicked on End Encounter button");
    }
    public void selectPharmacyTabOnEndEncounterPopup(){
        Utility.explicitlyWait(pharmacyTabOnEndEncounterPopup,driver,10);
        pharmacyTabOnEndEncounterPopup.click();
    }
    public void selectLabTabOnEndEncounterPopup(){
        Utility.explicitlyWait(labTabOnEndEncounterPopup,driver,10);
        labTabOnEndEncounterPopup.click();
    }
    public void selectWhatsAppOptionOnEndEncounterPopup(){
        Utility.explicitlyWait(whatsAppOptionOnEndEncounterPopup,driver,10);
        whatsAppOptionOnEndEncounterPopup.click();
    }
    public void selectEmailOptionOnEndEncounterPopup(){
        Utility.explicitlyWait(emailOptionOnEndEncounterPopup,driver,10);
        emailOptionOnEndEncounterPopup.click();
    }
    public void clickOnSendButtonOnEndEncounterPopup(){
        Utility.explicitlyWait(sendButtonOnEndEncounterPopup,driver,10);
        sendButtonOnEndEncounterPopup.click();
    }
    public void selectCheckBoxForExpectedPharmacyOrLab(String expectedPharmacyOrLab){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[contains(@text,'"+expectedPharmacyOrLab+"')]/preceding-sibling::android.widget.CheckBox"))).click();
        logger.info("Selected the "+expectedPharmacyOrLab);
    }
    public void selectLanguageOptionOnEndEncounterPopup(String expectedLanguage){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.ViewGroup[contains(@content-desc,\"APT\")]//android.view.ViewGroup[contains(@content-desc,'"+expectedLanguage+"')]"))).click();
        logger.info("Language selected: "+expectedLanguage);
    }

}
