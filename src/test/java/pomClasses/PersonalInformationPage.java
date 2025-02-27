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

public class PersonalInformationPage {
    public PersonalInformationPage(AndroidDriver driver)
    {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(2)),this);
    }
    public static Logger logger= LoggerFactory.getLogger(PersonalInformationPage.class);
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Personal information\"]")private WebElement personalInformationScreenTitle;
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text=\"is required\")]")private List<WebElement> errorMessagesOnPersonalInformation;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Continue\"]")private WebElement continueButton;

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
    public void sendInputToField(String title,String userInput) throws InterruptedException {
        Thread.sleep(1000);
        WebElement fieldTitle=driver.findElement(By.xpath("//android.widget.TextView[@text='"+title+"']"));
        Utility.explicitlyWait(fieldTitle,driver,10);
        fieldTitle.click();
        Thread.sleep(1000);
        WebElement fieldPlaceholder=driver.findElement(By.xpath("//android.view.ViewGroup[android.widget.TextView[@text='"+title+"']]//android.widget.EditText"));
        Utility.explicitlyWait(fieldTitle,driver,10);
        fieldPlaceholder.sendKeys(userInput);
        logger.info("Data entered to input field");
    }
    public void verifyAllPersonalInformationIsAccepted()
    {
        try {
            if(!errorMessagesOnPersonalInformation.isEmpty() && errorMessagesOnPersonalInformation.get(0).isDisplayed())
            {
                logger.error("Error messages are displayed on personal information screen");
            }
            else {
                logger.info("Personal information details is accepted");
            }
        }
        catch (Exception e)
        {
            logger.error("Unexpected exception: " + e.getMessage());
        }
    }

    public void clickOnContinue() {
        Utility.explicitlyWait(continueButton,driver,10);
        continueButton.click();
        logger.info("Clicked on Continue button");
    }
}
