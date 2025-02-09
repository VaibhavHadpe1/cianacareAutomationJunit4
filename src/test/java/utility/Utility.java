package utility;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.Base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Utility extends Base
{
    public static void implicitlyWait(AndroidDriver driver, int time) {
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    public static void explicitlyWait(WebElement element, AndroidDriver driver, int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public static String readDataFromPropertyFile(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream myfile = new FileInputStream("D:\\cianacareAutomation\\src\\test\\resources\\config\\config1.properties");
        prop.load(myfile);
        //return prop.getProperty(key);
        String value = prop.getProperty(key);
        return value;
    }
    public static void handleKeyboard() throws InterruptedException {   Thread.sleep(1000);
        if(driver.isKeyboardShown())
        {
            driver.hideKeyboard();
        }
        else {
            System.out.println("Keyboard is not displayed.");
        }
    }
    public static void clickOnBackButton()
    {
        ((PressesKey) driver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
    }

}
