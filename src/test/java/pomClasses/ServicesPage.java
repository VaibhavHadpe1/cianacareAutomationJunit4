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

import static steps.Base.driver;

public class ServicesPage {

    public ServicesPage(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(2)),this);
    }
    public static Logger logger= LoggerFactory.getLogger(ServicesPage.class);
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Services\"]")private WebElement servicesOptionOnDashboard;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Services\"]") private WebElement servicesScreenTitle;
    @AndroidFindBy(xpath = "//android.widget.ScrollView/following-sibling::android.view.ViewGroup//android.widget.ImageView") private WebElement addIcon;
    @AndroidFindBy(xpath = "//android.widget.ScrollView//android.view.ViewGroup//android.widget.EditText/following-sibling::android.widget.TextView") private List<WebElement> errorMessagesOnAddServiceBottomSheet;
    @AndroidFindBy(xpath = "(//android.widget.ScrollView//android.widget.ImageView)[1]") private WebElement closeButtonOfBottomSheet;


    public void clickOnServices() throws InterruptedException {
        Thread.sleep(1000);
        Utility.customizeScrollByCoordinates(driver,900,300,100,300);
        Utility.explicitlyWait(servicesOptionOnDashboard,driver,10);
        Thread.sleep(1000);
        servicesOptionOnDashboard.click();
        logger.info("Clicked on Services");
    }
    public void verifyUserIsPresentOnExpectedScreen(String expectedScreenName) throws InterruptedException {
        Thread.sleep(1000);
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement screenTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='" + expectedScreenName + "']")));
            logger.info("User is present on " + expectedScreenName + " page.");
            Assert.assertTrue(screenTitle.isDisplayed(), "User is present on expected screen");
        } catch (Exception e) {
            logger.error("Exception occurred: "+e.getMessage());
            Assert.fail("User not present on expected page");
        }
    }
    public void clickOnAddServiceButton(){
        Utility.explicitlyWait(addIcon,driver,10);
        addIcon.click();
        logger.info("Clicked on Add Icon");
    }
    public void verifyErrorMessages(List<String> expectedErrorMessages) throws InterruptedException {
        Thread.sleep(1000);
        List<String> actualErrorMessages = errorMessagesOnAddServiceBottomSheet.stream()
                .map(WebElement::getText)
                .toList();
        // Assert that actual error messages contain all expected messages
        Assert.assertTrue(actualErrorMessages.containsAll(expectedErrorMessages),
                "Some expected error messages are missing. \nExpected: " + expectedErrorMessages + "\nActual: " + actualErrorMessages);
        logger.info("Expected error messages are displayed.");
        System.out.println(actualErrorMessages);
    }
    public void closeBottomSheet(){
        Utility.explicitlyWait(closeButtonOfBottomSheet,driver,10);
        closeButtonOfBottomSheet.click();
        logger.info("Clicked on close button of bottom sheet");
    }

    public void verifyServiceDetails(String expectedServiceName,String expectedServiceCost,String expectedServiceDiscount,String expectedServiceRespectivePerson) throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement>serviceDetails=driver.findElements(By.xpath("//android.widget.TextView[@text='"+expectedServiceName+"']/following-sibling::android.widget.TextView"));
        String serviceCost=serviceDetails.get(0).getText();
        String serviceDiscount=serviceDetails.get(1).getText();
        String serviceRespectivePerson=serviceDetails.get(3).getText();
        System.out.println(serviceCost + serviceDiscount +" Respective Person: "+serviceRespectivePerson);
        Assert.assertTrue(serviceCost.contains(expectedServiceCost),"Service cost mismatch/not available");
        Assert.assertTrue(serviceDiscount.contains(expectedServiceDiscount),"Service discount mismatch/not available");
        Assert.assertTrue(serviceRespectivePerson.contains(expectedServiceRespectivePerson),"Service Respective person mismatch/not available");
    }

    public void clickOnEditButtonOfExpectedService(String expectedServiceName) throws InterruptedException {
        Thread.sleep(3000);
        WebElement editIcon=driver.findElement(By.xpath("(//android.view.ViewGroup[android.widget.TextView[@text='"+expectedServiceName+"']]/parent::android.view.ViewGroup//android.widget.ImageView)[1]"));
        Utility.explicitlyWait(editIcon,driver,10);
        editIcon.click();
        logger.info("Clicked on edit icon of service");
    }
    public void clickOnDeleteButtonOfExpectedService(String expectedServiceName) throws InterruptedException {
        Thread.sleep(3000);
        WebElement deleteIcon=driver.findElement(By.xpath("(//android.widget.ScrollView[@index=2]//android.view.ViewGroup//android.widget.TextView[contains(@text,'"+expectedServiceName+"')]/following-sibling::android.view.ViewGroup//android.widget.ImageView)[2]"));
        Utility.explicitlyWait(deleteIcon,driver,10);
        deleteIcon.click();
        logger.info("Clicked on delete icon of service");
    }

    public void verifyDeletedServiceNotDisplayed(String expectedServiceName) throws InterruptedException {
        Thread.sleep(3000);
        List<WebElement>serviceDetails=driver.findElements(By.xpath("//android.widget.TextView[@text='"+expectedServiceName+"']"));
        if(serviceDetails.isEmpty()){
            logger.info("Service is deleted and not displayed");
            Assert.assertTrue(true,"Service is deleted");
        }
        else{
            logger.error("Service not deleted");
            Assert.fail("Service not deleted and displayed in the list");
        }
    }
}
