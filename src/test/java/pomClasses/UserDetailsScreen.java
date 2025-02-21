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
import utility.Utility;

import java.io.IOException;
import java.time.Duration;

import static steps.Base.driver;


public class UserDetailsScreen {
    public UserDetailsScreen(AndroidDriver driver) throws IOException {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(2)),this);
    }
    public static Logger logger= LoggerFactory.getLogger(UserDetailsScreen.class);
    @AndroidFindBy(xpath = "(//android.widget.TextView[@text=\"User details\"])[1]")private WebElement userDetailsScreenTitle;
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"User details\"]")private WebElement userDetailsTab;
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Clinic details\"]")private WebElement clinicDetailsTab;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Verification pending\"]") private WebElement verificationPendingStatus;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Verified\"]") private WebElement verifiedStatus;
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Logout\"]") private WebElement logoutButtonOnUserDetailsScreen;


    public boolean verifyUserDetails() throws IOException {
        String expectedName=Utility.readDataFromPropertyFile("fullName");
        String expectedPhone=Utility.readDataFromPropertyFile("registrationMobileNumber");
        String expectedEmail=Utility.readDataFromPropertyFile("emailAddress");
        String expectedRole=Utility.readDataFromPropertyFile("userRoleAdmin");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            // Verify Name
            WebElement nameElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//android.widget.TextView[@text='" + expectedName + "']")));

            // Verify Phone & Email
            WebElement phoneEmailElement = driver.findElement(
                    By.xpath("//android.widget.TextView[contains(@text, '" + expectedPhone + "')]")
            );

            WebElement emailElement = driver.findElement(
                    By.xpath("//android.widget.TextView[contains(@text, '" + expectedEmail + "')]")
            );

            // Verify Role (Admin)
            WebElement roleElement = driver.findElement(
                    By.xpath("//android.widget.TextView[@text='" + expectedRole + "']")
            );

            // Check if all elements are displayed
            return nameElement.isDisplayed() && phoneEmailElement.isDisplayed() &&
                    emailElement.isDisplayed() && roleElement.isDisplayed();

        } catch (Exception e) {
            System.out.println("Verification failed: " + e.getMessage());
            return false;
        }
    }
    boolean isVerified=verifyUserDetails();

   public void verifyUserInformation()
   {
       Utility.implicitlyWait(driver,10);
       if(isVerified)
       {
           logger.info("User details is matched");
       }
       else
       {
           logger.error("User details not matching or displayed");
       }
   }
    public void clinicVerificationIsPending()
    {
        Utility.explicitlyWait(verificationPendingStatus,driver,30);
        if(verificationPendingStatus.isDisplayed())
        {
            logger.info("Clinic status: "+ verificationPendingStatus.getText());
        }
        else{
            logger.error("Clinic is verified");
        }
    }
    public void clinicIsVerified()
    {
        Utility.explicitlyWait(verifiedStatus,driver,15);
        if(verifiedStatus.isDisplayed())
        {
            logger.info("Clinic status: "+ verifiedStatus.getText());
        }
        else {
            logger.error("Clinic is not verified");
        }
    }
   public void logoutFromUserDetailsScreen()
   {
        Utility.explicitlyWait(logoutButtonOnUserDetailsScreen,driver,10);
        logoutButtonOnUserDetailsScreen.click();
        logger.info("User clicked on logout");
   }

}
