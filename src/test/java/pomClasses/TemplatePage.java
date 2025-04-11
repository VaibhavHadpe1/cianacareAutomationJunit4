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

import java.sql.SQLOutput;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

import static steps.Base.driver;

public class TemplatePage {
    ServicesPage servicesPage;
    ClinicInformationPage clinicInformationPage;
    PersonalInformationPage personalInformationPage;
    InvoicePage invoicePage;
    UserListPage userListPage;
    public TemplatePage(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(2)),this);
        servicesPage=new ServicesPage(driver);
        clinicInformationPage=new ClinicInformationPage(driver);
        personalInformationPage=new PersonalInformationPage(driver);
        invoicePage=new InvoicePage(driver);
        userListPage=new UserListPage(driver);
    }
    public static Logger logger= LoggerFactory.getLogger(TemplatePage.class);
    WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Templates\"]")private WebElement templatesOptionOnDashboard;
    @AndroidFindBy(xpath = "(//android.widget.ScrollView//android.view.ViewGroup//android.widget.ImageView)[last()]") private WebElement addIcon;
    @AndroidFindBy(xpath = "//android.widget.ScrollView//android.widget.TextView[@text=\"No Templates Found\"]") private WebElement defaultMessageForNoTemplates;
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Save\"]") private WebElement saveButton;
    @AndroidFindBy(xpath = "(//android.widget.TextView[@text=\"Disease *\"]/following-sibling::android.view.ViewGroup//android.widget.EditText)[1]") private WebElement diseaseField;
    @AndroidFindBy(xpath = "(//android.widget.TextView[@text=\"Disease *\"]/following-sibling::android.view.ViewGroup//android.widget.EditText)[2]") private WebElement lineOfTreatmentField;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Add medicine\"]") private WebElement addMedicineButton;
    @AndroidFindBy(xpath = "(//android.view.ViewGroup//android.widget.TextView[@text=\"Drug name *\"]/following-sibling::android.view.ViewGroup//android.widget.EditText)[1]") private WebElement drugNameField;
    @AndroidFindBy(xpath = "(//android.view.ViewGroup//android.widget.TextView[@text=\"Drug name *\"]/following-sibling::android.view.ViewGroup//android.widget.EditText)[2]") private WebElement durationField;
    @AndroidFindBy(xpath = "(//android.widget.TextView[@text=\"Dosage Route *\"]/following-sibling::android.view.ViewGroup//android.widget.TextView)[1]") private WebElement dosageRouteDropdown;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Save\"]") private WebElement saveButtonOnBottomSheet;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Add test\"]") private WebElement addTestButton;
    @AndroidFindBy(xpath = "(//android.view.ViewGroup//android.widget.TextView[@text=\"Test name *\"]/following-sibling::android.view.ViewGroup//android.widget.EditText)[1]") private WebElement testNameField;


    public void clickOnTemplatesFeature()
    {
        Utility.explicitlyWait(templatesOptionOnDashboard,driver,10);
        templatesOptionOnDashboard.click();
        logger.info("Clicked on templates feature from dashboard");
    }
    public void verifyDefaultMessageForNoTemplate(){
        System.out.println("****************"+isTemplatesNotAvailableForDoctor());
        if(isTemplatesNotAvailableForDoctor()){

           logger.info("Default message displayed: "+defaultMessageForNoTemplates.getText());
        }
        else {
            logger.info("Templates are present so no default message");
        }
    }

    public boolean isTemplatesNotAvailableForDoctor() {
        try {
            List<WebElement> templateList = driver.findElements(By.xpath("//android.widget.ScrollView//android.view.ViewGroup//android.view.ViewGroup[contains(@content-desc,',')]"));
            if (!templateList.isEmpty()) {
                for (WebElement template : templateList) {
                    String templateDetails = template.getDomAttribute("content-desc");
                    System.out.println("Template displayed: " + templateDetails);
                    logger.info("Templates are present");
                    return false;
                }
            }

        } catch (Exception e) {
            logger.info("Doctor don't have templates");
            throw new RuntimeException(e);
        }
        return true;
    }

    public void clickOnAddIcon() {
        Utility.explicitlyWait(addIcon,driver,10);
        addIcon.click();
        logger.info("Clicked on add icon for template creation");
    }
    public void clickOnSaveButton(){
        Utility.explicitlyWait(saveButton,driver,10);
        saveButton.click();
        logger.info("Clicked on Save button");
    }
    public void verifyErrorMessagesForMandatoryFields(List<String> expectedErrorMessages) {
       List<WebElement>errorMessagesForRequiredFields= wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//android.widget.ScrollView//android.view.ViewGroup//android.widget.TextView[contains(@text,'required')]")));
        List<String> actualErrorMessages = errorMessagesForRequiredFields.stream().map(WebElement::getText).toList();
        Assert.assertTrue(actualErrorMessages.containsAll(expectedErrorMessages),
                "Some expected error messages are missing. \nExpected: " + expectedErrorMessages + "\nActual: " + actualErrorMessages);
        logger.info("Expected error messages are displayed.");
        System.out.println(actualErrorMessages);
    }

    public void enterTemplateDetails(String diseaseName,String lineOfTreatment,String gender,String ageGroup) throws InterruptedException {
        Utility.explicitlyWait(diseaseField,driver,10);
        diseaseField.sendKeys(diseaseName);
        Thread.sleep(3000);
        clinicInformationPage.selectOptions(Collections.singletonList(diseaseName));
        wait.until(ExpectedConditions.visibilityOf(lineOfTreatmentField)).sendKeys(lineOfTreatment);
        selectGender(gender);
        selectAgeGroup(ageGroup);
    }
    public void selectGender(String gender){
        clinicInformationPage.selectOptions(Collections.singletonList(gender));
    }
    public void selectAgeGroup(String ageGroup) throws InterruptedException {
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Age group *']/following-sibling::android.view.ViewGroup//android.widget.Button[contains(@content-desc,'"+ageGroup+"')]"))).click();
    }
    public void clickOnSaveButtonOfBottomSheet(){
        Utility.explicitlyWait(saveButtonOnBottomSheet,driver,10);
        saveButtonOnBottomSheet.click();
        logger.info("Clicked on save button of bottom sheet");
    }
    public void addMedication(String drugName,String dosageRoute,String frequency,String timing,String duration,String particularDuration) throws InterruptedException {
        Utility.explicitlyWait(addMedicineButton,driver,5);
        addMedicineButton.click();
        logger.info("Clicked on Add medicine button");
        Utility.explicitlyWait(drugNameField,driver,5);
        drugNameField.sendKeys(drugName);
        Thread.sleep(2000);
        clinicInformationPage.selectOptions(Collections.singletonList(drugName));
        Utility.explicitlyWait(dosageRouteDropdown,driver,10);
        dosageRouteDropdown.click();
        clinicInformationPage.selectOptions(Collections.singletonList(dosageRoute));
        clinicInformationPage.selectOptions(Collections.singletonList(frequency));
        clinicInformationPage.selectOptions(Collections.singletonList(timing));
        Utility.explicitlyWait(durationField,driver,5);
        durationField.sendKeys(duration);
        Thread.sleep(1000);
        clinicInformationPage.selectOptions(Collections.singletonList(particularDuration));
        Thread.sleep(1000);
        clickOnSaveButtonOfBottomSheet();
    }
    public void verifyTemplateDetails(String expectedTemplate){
       WebElement templateDetails=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.ViewGroup[contains(@content-desc,'"+expectedTemplate+"')]")));
       String templateInfo=templateDetails.getDomAttribute("contentDescription");
        assert templateInfo != null;
        String[] template=templateInfo.split(",");
        String templateName=template[0];
        String gender=template[1];
        String ageGroup=template[2];
        String treatment=template[3];
        String medicineCount=template[4];
        String investigationCount=template[5];
        System.out.println("Template Name: "+templateName);
        System.out.println("Gender: "+gender);
        System.out.println("Age group: "+ageGroup);
        System.out.println("Line of Treatment: "+treatment);
        System.out.println("Medicine count: "+medicineCount);
        System.out.println("Investigation count: "+investigationCount);

    }

    public void addInvestigation(String expectedTestName) throws InterruptedException {
        Utility.explicitlyWait(addTestButton,driver,5);
        addTestButton.click();
        logger.info("Clicked on Add test button");
        Utility.explicitlyWait(testNameField,driver,5);
        testNameField.sendKeys(expectedTestName);
        Thread.sleep(2000);
        clinicInformationPage.selectOptions(Collections.singletonList(expectedTestName));
        personalInformationPage.sendInputToField("Instructions","Test");
        personalInformationPage.sendInputToField("Reason for study", "Test");
        clickOnSaveButtonOfBottomSheet();
    }

    public void clickOnTemplateToEdit(String expectedTemplateName) {
        WebElement template=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.ViewGroup[contains(@content-desc,'"+expectedTemplateName+"')]")));
        template.click();
        logger.info("Clicked on template to edit");
    }

    public void clickOnDeleteForExpectedTemplate(String expectedTemplateName) {
        WebElement template=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//android.view.ViewGroup[contains(@content-desc,'"+expectedTemplateName+"')]//android.view.ViewGroup)[1]")));
        template.click();
        logger.info("Clicked on delete icon of template");
    }
    public void verifyTemplateIsDeleted(String expectedTemplateName){
        boolean isTemplateDeleted = wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//android.view.ViewGroup[contains(@content-desc,'" + expectedTemplateName + "')]")));
        logger.info("Template is deleted and not displayed in the list");
        Assert.assertTrue(isTemplateDeleted, "Template was not deleted successfully");
    }
    public void verifySearchResults(String expectedTemplateName ) throws InterruptedException {
        Thread.sleep(1000);
        try{
            List<WebElement> templateDetails=driver.findElements(By.xpath("//android.view.ViewGroup[contains(@content-desc,'"+expectedTemplateName+"')]"));
            if(!templateDetails.isEmpty()){
                verifyTemplateDetails(expectedTemplateName);
            }
            else {
                logger.info("Default message is displayed: "+defaultMessageForNoTemplates.getText());
            }
        } catch (Exception e) {
            logger.error("No default message and no search results displayed.");
        }

    }
}
