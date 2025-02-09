package pomClasses;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.Utility;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static steps.Base.driver;

public class SignupPage {
    public SignupPage(AndroidDriver driver)
    {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(2)),this);
    }
    public static Logger logger= LoggerFactory.getLogger(SignupPage.class);
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Mobile number*\"]")private WebElement mobileNumberField;
    @AndroidFindBy(xpath = "//android.widget.EditText[@text=\"Mobile\"]")private WebElement mobileNumberInputField;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Clinic registration number*\"]")private WebElement clinicRegistrationNumberField;
    @AndroidFindBy(xpath = "//android.widget.EditText[@text=\"Registration number\"]")private WebElement clinicRegistrationNumberInputField;
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Continue\"]")private WebElement continueButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Enter 6 - digit code')]")private WebElement instructionMessageOnOTPScreen;


    public void verifyUserIsPresentOnSignUpScreen() {
        if(mobileNumberField.isDisplayed() && clinicRegistrationNumberField.isDisplayed()){
            logger.info("User is present on SignUp screen");
        }
        else {
            logger.error("User is not present on SignUp screen");
        }
    }
    public void enterRegistrationMobileNumber() throws IOException, InterruptedException {   Utility.explicitlyWait(mobileNumberField,driver,10);
        mobileNumberField.click();
        Utility.explicitlyWait(mobileNumberInputField,driver,10);
        mobileNumberInputField.sendKeys(Utility.readDataFromPropertyFile("registrationMobileNumber"));
        Utility.handleKeyboard();
        logger.info("Entered valid registration number");
    }
    public void enterClinicRegistrationNumber() throws IOException {
        Utility.explicitlyWait(clinicRegistrationNumberField,driver,10);
        clinicRegistrationNumberField.click();
        Utility.explicitlyWait(clinicRegistrationNumberInputField,driver,10);
        clinicRegistrationNumberInputField.sendKeys(Utility.readDataFromPropertyFile("clinicRegistrationNumber"));
    }
    public void clickOnContinueButton() throws InterruptedException {
        Utility.handleKeyboard();
        Utility.explicitlyWait(continueButton,driver,10);
        continueButton.click();
        logger.info("Clicked on Continue Button");
    }
    private boolean isElementPresent(WebElement element) {
        try {
            return element != null && element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public void verifyEnteredDetailsAcceptedOnSignUpPage() {
        try {
            Thread.sleep(2000);

            List<WebElement> mobileErrors = driver.findElements(By.xpath("//android.widget.TextView[@text='Enter a valid 10-digit mobile number.']"));
            List<WebElement> clinicErrors = driver.findElements(By.xpath("//android.widget.TextView[@text='Clinic already registered']"));

            logger.info("Checking for error messages...");

            if (!mobileErrors.isEmpty() && mobileErrors.get(0).isDisplayed()) {
                logger.error("Mobile number error: " + mobileErrors.get(0).getText());
            }
            else if (!clinicErrors.isEmpty() && clinicErrors.get(0).isDisplayed()) {
                logger.error("Clinic registration number error: " + clinicErrors.get(0).getText());
            }
            else {
                logger.info("No errors found and all details accepted");
//                Thread.sleep(1000);  // Small delay for stability
//                ((PressesKey) driver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
//                logger.info("Successfully clicked BACK button.");
            }
        } catch (Exception e) {
        logger.error("Unexpected exception: " + e.getMessage());
        }
    }

    public void verifyUserIsPresentOnOTPScreen() {
        Utility.explicitlyWait(instructionMessageOnOTPScreen,driver,10);
        if(instructionMessageOnOTPScreen.isDisplayed())
        {
            logger.info("User is present on OTP screen:"+instructionMessageOnOTPScreen.getText());
        }
        else {
            logger.error("User is not present on OTP screen");
        }
    }
}
