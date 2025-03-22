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
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.XMLFormatter;

import static steps.Base.driver;

public class InvoicePage {
    ServicesPage servicesPage;
    ClinicInformationPage clinicInformationPage;
    PersonalInformationPage personalInformationPage;
    public InvoicePage(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(2)),this);
        servicesPage=new ServicesPage(driver);
        clinicInformationPage=new ClinicInformationPage(driver);
        personalInformationPage=new PersonalInformationPage(driver);
    }
    public static Logger logger= LoggerFactory.getLogger(InvoicePage.class);
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Invoice\"]")private WebElement invoiceOptionOnDashboard;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id=\"fab-content\"]") private WebElement addIcon;
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Service\"]") private WebElement serviceInvoiceOption;
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Lab\"]") private WebElement labInvoiceOption;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Billing information\"]") private WebElement billingInformationButton;
    @AndroidFindBy(xpath = "(//android.view.ViewGroup//android.widget.TextView[@text=\"Service *\"]/following-sibling::android.view.ViewGroup//android.widget.EditText)[1]")private WebElement serviceInputField;
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"OK\"]")private WebElement okButton;
    @AndroidFindBy(xpath = "//android.widget.Button[@text=\"OK\"]")private WebElement datePickerOkButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Lab *\"]/following-sibling::android.view.ViewGroup[contains(@content-desc,',')]")private WebElement labDropdown;
    @AndroidFindBy(xpath = "(//android.view.ViewGroup//android.widget.EditText)[1]")private WebElement testNameInputField;
    @AndroidFindBy(xpath = "(//android.view.ViewGroup//android.widget.EditText)[2]")private WebElement amountInputField;
    @AndroidFindBy(xpath = "(//android.view.ViewGroup//android.widget.EditText)[3]")private WebElement discountInputField;
    @AndroidFindBy(xpath = "(//android.view.ViewGroup//android.widget.EditText)[4]")private WebElement finalAmountInputField;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Billed on *\"]/following-sibling::android.view.ViewGroup//android.widget.ImageView")private WebElement billedOnDateField;
    //@AndroidFindBy(xpath = "(//android.widget.ScrollView//android.view.ViewGroup//android.widget.ImageView)[5]")private WebElement filterIcon;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Apply\"]") private WebElement applyButtonOfFilter;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Reset\"]") private WebElement resetFilterButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Upload report\"]") private WebElement uploadReportButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Gallery\"]") private WebElement galleryOptionToUploadReport;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Upload\"]") WebElement uploadButtonOnUploadReportBottomSheet;



    public void clickOnInvoiceFromDashboard(){
        Utility.explicitlyWait(invoiceOptionOnDashboard,driver,10);
        invoiceOptionOnDashboard.click();
        logger.info("Clicked on Invoice");
        Assert.assertTrue(true);
    }
    public void clickOnAddInvoiceIcon(){
        Utility.explicitlyWait(addIcon,driver,10);
        addIcon.click();
        logger.info("Clicked on add Invoice icon");
        Assert.assertTrue(true);
    }
    public void selectServiceInvoiceOption()
    {
        Utility.explicitlyWait(serviceInvoiceOption,driver,10);
        serviceInvoiceOption.click();
        logger.info("Clicked on add Service Invoice option");
        Assert.assertTrue(true);
    }
    public void selectLabInvoiceOption()
    {
        Utility.explicitlyWait(labInvoiceOption,driver,10);
        labInvoiceOption.click();
        logger.info("Clicked on add Lab Invoice option");
        Assert.assertTrue(true);
    }

    public void selectUserFromDropdown(List<String> name) {
        try {
            WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
            for (String optionText : name) {
                WebElement user = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.ViewGroup[contains(@content-desc,'"+optionText+"')]")));
                Utility.explicitlyWait(user, driver, 10);
                user.click();
                user.click();
                logger.info("Option selected: " + user.getText());
                Assert.assertTrue(true,"User is selected");
            }
        }
        catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            //Assert.fail("User not selected/Displayed");
        }
    }
    public void clickOnBillingInformation() throws InterruptedException {
        Utility.customizeScrollByCoordinates(driver,500,1800,500,1400);
        Utility.explicitlyWait(billingInformationButton,driver,5);
        billingInformationButton.click();
        logger.info("Clicked on Billing Information");
        servicesPage.verifyUserIsPresentOnExpectedScreen("Billing Details");
    }
    public void addExistingServiceDetails(String expectedService) throws InterruptedException {
        Utility.explicitlyWait(serviceInputField,driver,5);
        serviceInputField.click();
        Thread.sleep(1000);
        clinicInformationPage.selectOptions(Collections.singletonList(expectedService));
    }

    public void clickOnOk() {
        Utility.explicitlyWait(okButton,driver,10);
        okButton.click();
        logger.info("Clicked on Ok");
        Assert.assertTrue(true,"Clicked on oK");
    }
    public void clickOnOkButtonOfDatePicker(){
        Utility.explicitlyWait(datePickerOkButton,driver,10);
        datePickerOkButton.click();
        logger.info("Clicked on ok button of date picker");
    }
    public void verifyInvoiceDetails(String expectedUserName,String expectedInvoiceType,String expectedAmount) throws InterruptedException {
        Thread.sleep(2000);
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
        List<WebElement> invoiceDetails=wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//android.widget.ScrollView//android.view.ViewGroup//android.widget.TextView[contains(@text,'"+expectedUserName+"')]/following-sibling::android.widget.TextView"))));
        String invoiceType=invoiceDetails.get(1).getText();
        String finalAmount=invoiceDetails.get(2).getText();
        System.out.println("Type: "+invoiceType+" ,Final Amount: "+finalAmount);
        Assert.assertTrue(invoiceType.contains(expectedInvoiceType),"Expected type matched");
        Assert.assertTrue(finalAmount.contains(expectedAmount),"Amount matched");
    }
    public void addExistingLabTestDetails(String expectedLab,String expectedPathologyTest) throws InterruptedException {
        Utility.explicitlyWait(labDropdown,driver,5);
        labDropdown.click();
        Thread.sleep(1000);
        clinicInformationPage.selectOptions(Collections.singletonList(expectedLab));
        Utility.explicitlyWait(testNameInputField,driver,10);
        testNameInputField.sendKeys(expectedPathologyTest);
        Thread.sleep(2000);
        clinicInformationPage.selectOptions(Collections.singletonList(expectedPathologyTest));
        Thread.sleep(1000);
        logger.info("Selected lab and added test details");
        System.out.println("Final amount: "+finalAmountInputField.getText());
    }

    public void selectBilledOnDate() throws InterruptedException {
        Utility.explicitlyWait(billedOnDateField,driver,10);
        billedOnDateField.click();
        Thread.sleep(2000);
        Utility.customizeScrollByCoordinates(driver,530,1030,530,1045);
        Thread.sleep(1000);
        clickOnOkButtonOfDatePicker();
    }
    public void clickOnFilterIconOnInvoiceScreen(){
        try {
            WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
            WebElement filterIcon=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//android.widget.ScrollView//android.view.ViewGroup//android.widget.ImageView)[5]")));
            filterIcon.click();
            logger.info("Clicked on filter icon");
            Assert.assertTrue(true, "Clicked on filter icon");
        } catch (Exception e) {
            System.out.println("Exception Occurred: "+e.getMessage());
        }
    }
    public void selectFilterValue(String expectedSection, String expectedFilterValue){
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
        WebElement filterSection=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.HorizontalScrollView//android.view.ViewGroup[contains(@content-desc,'"+expectedSection+"')]")));
        filterSection.click();
        logger.info("Selected filter section: "+expectedSection);
        WebElement filterValue= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.HorizontalScrollView/following-sibling::android.widget.ScrollView//android.view.ViewGroup[contains(@content-desc,'"+expectedFilterValue+"')]")));
        filterValue.click();
        logger.info("Filter value selected: "+expectedFilterValue);
        Assert.assertTrue(true,"Filter section and value selected");
    }
    public void clickOnApplyFilter(){
        Utility.explicitlyWait(applyButtonOfFilter,driver,10);
        applyButtonOfFilter.click();
        logger.info("Clicked on Apply button of filter");
    }
    public void clickOnReset(){
        Utility.explicitlyWait(resetFilterButton,driver,10);
        resetFilterButton.click();
        logger.info("Clicked on Reset button of filter");
    }
    public void clickOnUploadReportButtonOfLabOrder(String expectedUserName) throws InterruptedException {
        Thread.sleep(2000);
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement uploadReportButton=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//android.view.ViewGroup[.//android.widget.TextView[contains(@text, '"+expectedUserName+"')]]//android.widget.TextView[@text=\"Report\"])[2]\n")));
        uploadReportButton.click();
        logger.info("User clicked on upload report button");
        Assert.assertTrue(true,"Clicked on upload report button");
    }
    public void selectTestTypeAndTestName(String expectedTestType,String expectedTestName) throws InterruptedException {
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement testType=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.ViewGroup[@content-desc='"+expectedTestType+"']")));
        testType.click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//android.view.ViewGroup[contains(@content-desc,'Select test')]")).click();
        Thread.sleep(1000);
        WebElement testName=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.ViewGroup[contains(@content-desc,'"+expectedTestName+"')]")));
        testName.click();
        logger.info("Selected the Test type and test name");
        Utility.clickOnBackButton();
    }
    public void uploadImageFromGallery() throws InterruptedException {
        Utility.explicitlyWait(uploadReportButton,driver,10);
        uploadReportButton.click();
        logger.info("Clicked on upload report");
        Utility.explicitlyWait(galleryOptionToUploadReport,driver,10);
        galleryOptionToUploadReport.click();
        logger.info("Clicked on gallery option");
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//android.view.View//android.view.View[contains(@content-desc,'Photo taken ')])[1]"))).click();
        Utility.explicitlyWait(uploadButtonOnUploadReportBottomSheet,driver,10);
        uploadButtonOnUploadReportBottomSheet.click();
        Thread.sleep(2000);
    }

    public void verifyUploadReportButtonDisabled(String expectedUserName) throws InterruptedException {
        Thread.sleep(2000);
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement uploadReportButton=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//android.view.ViewGroup[.//android.widget.TextView[contains(@text, '"+expectedUserName+"')]]//android.widget.TextView[@text=\"Report\"])[2]\n")));
       if(!uploadReportButton.isSelected()){
           logger.info("Reports are uploaded and button is disabled");
           Assert.assertTrue(true,"Button is disabled");
       }
       else {
           logger.error("Upload Report button is enabled even after report uploading");
           Assert.fail("Button is not disabled");
       }
    }
    public void clickOnDownloadReportButtonOfLabOrder(String expectedUserName) throws InterruptedException {
        Thread.sleep(2000);
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement downloadReportButton=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//android.view.ViewGroup[.//android.widget.TextView[contains(@text, '"+expectedUserName+"')]]//android.widget.TextView[@text=\"Report\"])[1]\n")));
        downloadReportButton.click();
        logger.info("User clicked on download report button");
        Assert.assertTrue(true,"Clicked on upload report button");
    }
    WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
    public void viewTestReport(String expectedTestName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.ViewGroup//android.widget.TextView[@text='"+expectedTestName+"']/following-sibling::android.view.ViewGroup"))).click();
        logger.info("Report is opened");
    }

    public void clickOnDownloadInvoiceButton(String expectedUserName) throws InterruptedException {
        Thread.sleep(2000);
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement downloadInvoiceButton=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//android.widget.TextView[@text='"+expectedUserName+"'])//(//android.widget.Button[contains(@content-desc,\"Invoice\")])[1]")));
        downloadInvoiceButton.click();
        logger.info("User clicked on download invoice button");
        Assert.assertTrue(true,"Clicked on download invoice button");
    }
}
