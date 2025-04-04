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
import java.util.List;
import java.util.stream.Collectors;

import static steps.Base.driver;

public class ReportsPage {

    public ReportsPage(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)),this);
    }
    public static Logger logger= LoggerFactory.getLogger(ReportsPage.class);
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Reports\"]")private WebElement reportsOptionOnDashboard;
    @AndroidFindBy(xpath = "//android.widget.ScrollView//android.widget.TextView[@text=\"No Reports Available\"]") private WebElement defaultMessageForNoReports;
    @AndroidFindBy(xpath = "(//android.widget.CheckBox[@resource-id=\"checkbox\"])[2]") private WebElement selectAllCheckBoxInFilter;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id=\"cross-fade-icon-current\"]")private WebElement exportIconOnReports;
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Excel\"]//android.view.ViewGroup[@resource-id=\"fab-container\"]")private WebElement excelOption;
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Pdf\"]//android.view.ViewGroup[@resource-id=\"fab-container\"]")private WebElement pdfOptions;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Service revenue\"]")private WebElement serviceRevenueTab;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Patients\"]")private WebElement patientsTab;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Age\"]") private WebElement ageOptioninFilter;
    @AndroidFindBy(xpath = "(//android.widget.HorizontalScrollView/following-sibling::android.view.ViewGroup//android.widget.EditText)[1]")private WebElement minAgeField;
    @AndroidFindBy(xpath = "(//android.widget.HorizontalScrollView/following-sibling::android.view.ViewGroup//android.widget.EditText)[2]")private WebElement maxAgeField;

    public void clickOnReports() throws InterruptedException {
        Thread.sleep(1000);
        Utility.customizeScrollByCoordinates(driver,900,300,100,300);
        Utility.explicitlyWait(reportsOptionOnDashboard,driver,10);
        Thread.sleep(1000);
        reportsOptionOnDashboard.click();
        logger.info("Clicked on Services");
    }
    public boolean isReportsNotAvailableForDoctor() {
        try {
            List<WebElement> reportList = driver.findElements(By.xpath("//android.widget.ScrollView[@index='8']//android.view.ViewGroup//android.view.ViewGroup[contains(@content-desc,\"APT\")]"));
            if (!reportList.isEmpty()) {
                for (WebElement report : reportList) {
                    String reportDetails = report.getDomAttribute("text");
                    logger.info("Reports are available");
                    return false;
                }
            }

        } catch (Exception e) {
            logger.info("Doctor don't have reports");
            throw new RuntimeException(e);
        }
        return true;
    }
    public void verifyDefaultMessageForNoReports(){
        System.out.println("**************** "+isReportsNotAvailableForDoctor());
        if(isReportsNotAvailableForDoctor()){

            logger.info("Default message displayed: "+defaultMessageForNoReports.getText());
        }
        else {
            logger.info("Reports are present so no default message");
        }
    }
    public void clickOnFilterIconOnReportsScreen(){
        try {
            WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
            WebElement filterIcon=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//android.widget.ScrollView//android.view.ViewGroup//android.widget.ImageView)[4]")));
            filterIcon.click();
            logger.info("Clicked on filter icon");
            Assert.assertTrue(true, "Clicked on filter icon");
        } catch (Exception e) {
            System.out.println("Exception Occurred: "+e.getMessage());
        }
    }
    public void verifyReportType(String expectedType) throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> listOfReportTypes=driver.findElements(By.xpath("//android.widget.TextView[contains(@text,'"+expectedType+"')]"));
        Thread.sleep(1000);
        if(listOfReportTypes.isEmpty()){
            WebElement defaultMessageOfNoInvoice=driver.findElement(By.xpath("//android.widget.TextView[@text=\"No Reports Available\"]"));
            if(defaultMessageOfNoInvoice.isDisplayed()){
                logger.info("Default message is displayed: "+defaultMessageOfNoInvoice.getText());
            }
            else {
                logger.error("Default message not displayed");
            }
        }
        else {
            for(WebElement type:listOfReportTypes){
                System.out.println("************** "+type.getText());
                if(type.getText().contains(expectedType)){
                    logger.info("Test Passed: expected type displayed properly");
                    Assert.assertTrue(true,"Expected and actual type matched");
                }
                else {
                    logger.error("Test Failed: expected type not displayed");
                    Assert.fail("Expected and actual type not matching");
                }
            }
        }
    }
    public void verifyReportsModeOfPayment(String expectedReportsModeOfPayment) throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> listOfReportsStatus=driver.findElements(By.xpath("//android.view.ViewGroup[contains(@content-desc,'"+expectedReportsModeOfPayment+"')]//android.widget.TextView"));
        Thread.sleep(1000);
        if(listOfReportsStatus.isEmpty()){
            WebElement defaultMessageOfNoInvoice=driver.findElement(By.xpath("//android.widget.TextView[@text=\"No Reports Available\"]"));
            if(defaultMessageOfNoInvoice.isDisplayed()){
                logger.info("Default message is displayed: "+defaultMessageOfNoInvoice.getText());
            }
            else {
                logger.error("Default message not displayed");
            }
        }
        else {
            for(WebElement status:listOfReportsStatus){
                System.out.println("************** "+status.getText());
                if(status.getText().equals(expectedReportsModeOfPayment)){
                    logger.info("Test Passed: expected status displayed properly");
                    Assert.assertTrue(true,"Expected and actual status matched");
                }
                else {
                    logger.error("Test Failed: expected status not displayed");
                    Assert.fail("Expected and actual status not matching");
                }
            }
        }
    }
    WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
    public void selectDoctorFromFilter(String expectedDoctor){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ScrollView//android.view.ViewGroup[contains(@content-desc,'"+expectedDoctor+"')]"))).click();
        logger.info("Clicked on Doctor");
    }
    public void verifyDoctorNamesOnReports(String expectedDoctorName) throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> listOfDoctorNamesOnReport=driver.findElements(By.xpath("//android.widget.TextView[contains(@text,'"+expectedDoctorName+"')]"));
        Thread.sleep(1000);
        if(listOfDoctorNamesOnReport.isEmpty()){
            WebElement defaultMessageOfNoInvoice=driver.findElement(By.xpath("//android.widget.TextView[@text=\"No Reports Available\"]"));
            if(defaultMessageOfNoInvoice.isDisplayed()){
                logger.info("Default message is displayed: "+defaultMessageOfNoInvoice.getText());
            }
            else {
                logger.error("Default message not displayed");
            }
        }
        else {
            for(WebElement name:listOfDoctorNamesOnReport){
                System.out.println("************** "+name.getText());
                if(name.getText().contains(expectedDoctorName)){
                    logger.info("Test Passed: expected doctor names are displayed properly");
                    Assert.assertTrue(true,"Expected and actual names matched");
                }
                else {
                    logger.error("Test Failed: expected type not displayed");
                    Assert.fail("Expected and actual type not matching");
                }
            }
        }
    }
    public void clickOnExportIcon(){
        Utility.explicitlyWait(exportIconOnReports,driver,5);
        exportIconOnReports.click();
        logger.info("CLicked on export icon");
    }
    public void clickOnExcelOption(){
        Utility.explicitlyWait(excelOption,driver,5);
        excelOption.click();
        logger.info("CLicked on excel icon");
    }
    public void clickOnPdfOption(){
        Utility.explicitlyWait(pdfOptions,driver,5);
        pdfOptions.click();
        logger.info("CLicked on pdf icon");
    }

    public void selectServiceRevenueTab() {
        Utility.explicitlyWait(serviceRevenueTab,driver,8);
        serviceRevenueTab.click();
        logger.info("CLicked on Service revenue tab");
    }
    public void verifyServiceRevenueReportsDisplayed() throws InterruptedException {
        Thread.sleep(1000);
        List<WebElement> serviceRevenueDetails = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//android.widget.ScrollView[@index='6']//android.view.ViewGroup//android.view.ViewGroup[@index='0']")));
        System.out.println("############# "+serviceRevenueDetails.size());
        if (serviceRevenueDetails.size()==1) {
            logger.info("Default message displayed for Service revenue: " + defaultMessageForNoReports.getText());
        } else {
            List<WebElement> serviceDetails=driver.findElements(By.xpath("//android.widget.ScrollView[@index='6']//android.view.ViewGroup//android.view.ViewGroup[@index='0']//android.widget.TextView"));
            System.out.println(serviceDetails.stream().map(WebElement::getText).collect(Collectors.joining("|")));
            logger.info("Reports are displayed");
        }
    }

    public void selectPatientsTab() {
        Utility.customizeScrollByCoordinates(driver,980,300,360,300);
        Utility.explicitlyWait(patientsTab,driver,5);
        patientsTab.click();
        logger.info("CLicked on patients tab");
    }
    public void verifyPatientsGender(String expectedGender) throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> listOfPatientsWithGender=driver.findElements(By.xpath("//android.widget.TextView[contains(@text,'"+expectedGender+"')]"));
        Thread.sleep(1000);
        if(listOfPatientsWithGender.isEmpty()){
            WebElement defaultMessageOfNoReports=driver.findElement(By.xpath("//android.widget.TextView[@text=\"No Reports Available\"]"));
            if(defaultMessageOfNoReports.isDisplayed()){
                logger.info("Default message is displayed: "+defaultMessageOfNoReports.getText());
            }
            else {
                logger.error("Default message not displayed");
            }
        }
        else {
            for(WebElement type:listOfPatientsWithGender){
                System.out.println("************** "+type.getText());
                if(type.getText().contains(expectedGender)){
                    logger.info("Test Passed: expected gender displayed properly");
                    Assert.assertTrue(true,"Expected and actual type matched");
                }
                else {
                    logger.error("Test Failed: expected type not displayed");
                    Assert.fail("Expected and actual type not matching");
                }
            }
        }
    }

    public void enterAgeRangeInFilter(int minAge, int maxAge) {
        Utility.explicitlyWait(ageOptioninFilter,driver,10);
        ageOptioninFilter.click();
        Utility.explicitlyWait(minAgeField,driver,5);
        maxAgeField.clear();
        maxAgeField.sendKeys(Integer.toString(maxAge));
        minAgeField.clear();
        minAgeField.sendKeys(Integer.toString(minAge));
    }

    public void verifyAgeWithinTheRange(int minAge,int maxAge) throws InterruptedException {
        Thread.sleep(2000);
        try {
            List<WebElement> ageOnCards=driver.findElements(By.xpath("//android.widget.TextView[contains(@text,\" -\")]"));
            if(ageOnCards.isEmpty()){
                WebElement defaultMessageOfNoReports=driver.findElement(By.xpath("//android.widget.TextView[@text=\"No Reports Available\"]"));
                if(defaultMessageOfNoReports.isDisplayed()){
                    logger.info("Default message is displayed: "+defaultMessageOfNoReports.getText());
                }
                else {
                    logger.error("Default message not displayed");
                }
            }
            else {
                for(WebElement age:ageOnCards){
                    int actualAge= Integer.parseInt(age.getText().split(" ")[2]);
                    System.out.println("Actual age: "+actualAge);
                    if (actualAge>=minAge && actualAge<=maxAge){
                        logger.info("Test Passed:Age is withing the range.");
                    }
                    else {
                        logger.error("Age is not within the range");
                        Assert.fail("Age is not within the range");
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
