package pomClasses;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.Utility;

import java.time.Duration;
import java.util.List;

import static steps.Base.driver;

public class ClinicInformationPage {
    public ClinicInformationPage(AndroidDriver driver)
    {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(2)),this);
    }
    public static Logger logger= LoggerFactory.getLogger(ClinicInformationPage.class);
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Clinic information\"]")private WebElement clinicInformationScreenTitle;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Upload clinic logo\"]")private WebElement uploadClinicLogoPlaceholder;
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text=\"is required\")]")private List<WebElement> errorMessagesOnClinicInformation;

    public void verifyUserIsPresentOnClinicInformationScreen()
    {
        Utility.explicitlyWait(clinicInformationScreenTitle, driver,10);
        if(clinicInformationScreenTitle.isDisplayed())
        {
            logger.info("User is present on clinic information screen");
        }
        else {
            logger.error("User is not present on clinic information screen ");
        }
    }
    public void uploadDocument(String uploadPlaceholder) throws InterruptedException {
        try {
            WebElement placeholderForUpload=driver.findElement(By.xpath("//android.widget.TextView[@text='"+uploadPlaceholder+"']"));
            Utility.explicitlyWait(placeholderForUpload,driver,10);
            placeholderForUpload.click();
            Thread.sleep(2000);
            // Select the first available image
            driver.findElement(By.xpath("(//androidx.cardview.widget.CardView)[1]")).click();
            logger.info("Clicked on upload placeholder and uploaded the image");
        } catch (Exception e) {
            logger.error("Exception Occured "+e.getMessage());
        }
    }
    public void selectOptions(List<String> optionsList) {
        try {
            Utility.implicitlyWait(driver, 10);

            for (String optionText : optionsList) {
                WebElement chipsOptionFormat = driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'"+optionText+"')]"));
                Utility.explicitlyWait(chipsOptionFormat, driver, 10);
                chipsOptionFormat.click();
                logger.info("Option selected: " + chipsOptionFormat.getText());
            }

        }
        catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
        }
    }

    public void verifyAllClinicInfoIsAccepted()
    {
        try {
            if(!errorMessagesOnClinicInformation.isEmpty() && errorMessagesOnClinicInformation.get(0).isDisplayed())
            {
                logger.error("Error messages are displayed on clinic information screen");
            }
            else {
                logger.info("Clinic information details is accepted");
            }
        }
        catch (Exception e)
        {
            logger.error("Unexpected exception: " + e.getMessage());
        }
    }
}
