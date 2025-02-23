package pomClasses;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.Utility;

import java.io.IOException;
import java.time.Duration;

import static steps.Base.driver;

public class LocationInformation {
    public LocationInformation(AndroidDriver driver)
    {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(2)),this);
    }
    public static Logger logger= LoggerFactory.getLogger(LocationInformation.class);
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Location information\"]")private WebElement locationInformationScreenTitle;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Address*\"]") private WebElement addressFieldTitle;
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.EditText")private WebElement addressInputField;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Submit\"]")private WebElement submitButton;


    public void verifyUserIsPresentOnLocationInformationScreen()
    {
        try {
            Utility.explicitlyWait(locationInformationScreenTitle,driver,10);
            if (locationInformationScreenTitle.isDisplayed())
            {
                logger.info("User is present on location information screen");
            }
            else
            {
                logger.error("User is not present on location information screen");
            }
        } catch (Exception e) {
            logger.error("Exception occurred: "+e.getMessage());
        }
    }
    public void enterAddress() throws IOException {
        Utility.explicitlyWait(addressFieldTitle,driver,20);
        addressFieldTitle.click();
        Utility.implicitlyWait(driver,2);
        addressInputField.sendKeys(Utility.readDataFromPropertyFile("addressOfTheClinic"));
    }
    public void clickOnSubmit() throws InterruptedException {
        Utility.swipeDown(driver);
        //Utility.explicitlyWait(submitButton,driver,10);
        Thread.sleep(1000);
        submitButton.click();
        logger.info("Scrolled up to Submit button and Clicked on Submit");
    }

    public void verifyUserCompletedTheClinicOnboarding() throws InterruptedException {
        Thread.sleep(1000);
        logger.info("User has completed the clinic onboarding");
    }
}
