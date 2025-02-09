package pomClasses;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.Utility;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static steps.Base.driver;

public class LoginPage
{
   public LoginPage(AndroidDriver driver)
   {
       PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(2)),this);
   }
    public static Logger logger= LoggerFactory.getLogger(LoginPage.class);
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='cianacare']") private WebElement cianacareApplication;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Mobile number*\"]")private WebElement mobileNumberField;
    @AndroidFindBy(xpath = "//android.widget.EditText[@text=\"Mobile\"]")private WebElement mobileNumberInputField;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Lets get started\"]") private WebElement letsGetStartedTitle;
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Continue\"]")private WebElement continueButton;
    @AndroidFindBy(xpath = "//android.widget.EditText[contains(@resource-id,'otp_input_')]")private List<WebElement> otpFields;
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Verify OTP\"]")private WebElement verifyOTPButton;
    @AndroidFindBy(xpath = "(//android.view.ViewGroup[contains(@content-desc, 'home_prof_pic_id')])[1]")private WebElement clinicCard;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Home\"]") private WebElement homeButtonOnDashboard;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"home_nav_to_prof_id\"]") private WebElement profileIconOnDashboard;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Settings\"]")private WebElement settingsButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Log out\"]") private WebElement logoutOPtionOnSettingsScreen;
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"android:id/button2\"]")private WebElement logoutButton;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"header_LG\"]/android.view.ViewGroup") private WebElement goBackHeaderButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Sign up\"]")private WebElement signUpButton;




    public void opencianacare()
    {
        Utility.explicitlyWait(cianacareApplication,driver,10);
        cianacareApplication.click();
        logger.info("Clicked on cianacare application");
    }
    public void verifyLoginScreenLoaded()
    {
        if(letsGetStartedTitle.isDisplayed() && mobileNumberField.isDisplayed())
        {
            logger.info("Application loads successfully and login screen displayed");
        }
        else
        {
            logger.info("Application not loaded successfully and login screen not displayed");
        }
    }
    public void enterValidMobileNumber() throws IOException, InterruptedException {
        Utility.explicitlyWait(mobileNumberField,driver,10);
        mobileNumberField.click();
        Utility.explicitlyWait(mobileNumberInputField,driver,10);
        mobileNumberInputField.sendKeys(Utility.readDataFromPropertyFile("validMobileNumber"));
        logger.info("Valid mobile number entered");
    }
    public void enterInValidMobileNumber() throws IOException {
        Utility.explicitlyWait(mobileNumberField,driver,10);
        mobileNumberField.sendKeys(Utility.readDataFromPropertyFile("invalidMobileNumber"));
        logger.info("InValid mobile number entered");
    }
    public void clickOnContinueButton() throws InterruptedException {
        Utility.handleKeyboard();
        Utility.explicitlyWait(continueButton,driver,10);
        continueButton.click();
        logger.info("Clicked on Continue Button");
    }
    public void enterOTP() throws IOException, InterruptedException {
        if (otpFields.size() == 6) {
            for (int i = 0; i < otpFields.size(); i++) {
                otpFields.get(i).sendKeys(String.valueOf(Utility.readDataFromPropertyFile("OTP").charAt(i)));
            }
        }
        logger.info("OTP entered");
        Utility.handleKeyboard();
    }
    public void clickOnVerifyOTPButton()
    {
        Utility.explicitlyWait(verifyOTPButton,driver,10);
        verifyOTPButton.click();
    }
    public void selectClinic() throws IOException {
        Utility.explicitlyWait(clinicCard,driver,10);
        List<WebElement>clinicDetails=driver.findElements(By.xpath("//android.view.ViewGroup[contains(@content-desc, 'home_prof_pic_id')]//android.widget.TextView"));
        for(WebElement clinic:clinicDetails)
        {
            if(clinic.getText().contains(Utility.readDataFromPropertyFile("clinicName")))
            {
                System.out.println("Matching clinic found: " + clinic.getText());
                clinic.click();
            }
        }
    }
    public void verifyUserIsOnDashboard()
    {
        Utility.explicitlyWait(homeButtonOnDashboard,driver,10);
        if(homeButtonOnDashboard.isDisplayed())
        {
            logger.info("User is present on dashboard and Home button is displayed");
        }
        else {
            logger.error("Home button is not displayed");
        }
    }
    public void logoutFromDahsboard() throws InterruptedException {
        Utility.explicitlyWait(settingsButton,driver,5);
        settingsButton.click();
        Utility.explicitlyWait(logoutOPtionOnSettingsScreen,driver,5);
        logoutOPtionOnSettingsScreen.click();
        Utility.explicitlyWait(logoutButton,driver,5);
        logoutButton.click();
        Thread.sleep(1000);
        logger.info("User loggedout successfully");

    }
    public void clickOnHome() throws InterruptedException {
        ((PressesKey)driver).pressKey(new KeyEvent().withKey(AndroidKey.HOME));
        logger.info("Clicked on HOME button.");
    }
    public void verifySignUpButtonIsDisplayed()
    {
        Utility.explicitlyWait(signUpButton,driver,10);
        if(signUpButton.isDisplayed())
        {
            logger.info("Sign Up button on Login screen is displayed.");
        }
        else
        {
            logger.error("Sign Up button on Login screen is not displayed.");
        }
    }
    public void clickOnSignUpButton()
    {
        Utility.explicitlyWait(signUpButton,driver,10);
        signUpButton.click();
        logger.info("Clicked on Sign Up button");
    }
}
