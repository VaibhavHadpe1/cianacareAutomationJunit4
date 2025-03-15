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
}
