package pomClasses;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.Utility;

import java.time.Duration;

import static steps.Base.driver;

public class PersonalInformationPage {
    public PersonalInformationPage(AndroidDriver driver)
    {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(2)),this);
    }
    public static Logger logger= LoggerFactory.getLogger(SignupPage.class);
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Personal information\"]")private WebElement personalInformationScreenTitle;

    public void verifyUserIsPresentOnPersonalInformationScreen()
    {
        Utility.explicitlyWait(personalInformationScreenTitle,driver,10);
        if(personalInformationScreenTitle.isDisplayed())
        {
            logger.info("User is present On Personal Information screen");
        }
        else {
            logger.error("User is not present on Personal Information screen");
        }
    }
}
