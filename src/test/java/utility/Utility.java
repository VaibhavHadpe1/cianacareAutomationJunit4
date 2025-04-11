package utility;

import com.google.common.collect.ImmutableList;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.Base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
            System.out.println("Keyboard is displayed and closed");
        }
        else {
            System.out.println("Keyboard is not displayed.");
        }
    }
    public static void clickOnBackButton()
    {
        ((PressesKey) driver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
    }

    public static void swipeDown(AndroidDriver driver) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 0);

        int screenWidth = driver.manage().window().getSize().width;
        int screenHeight = driver.manage().window().getSize().height;

        int startX = screenWidth / 2;
        int startY = (int) (screenHeight * 0.8);  // Start from 80% of the screen height
        int endY = (int) (screenHeight * 0.2);    // End at 20% of the screen height

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(), startX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }

    public static void customizeScrollByCoordinates(AndroidDriver driver, int startX, int startY, int endX, int endY) {
        // Define touch input
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 0);

        // Move from starting position to ending position
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(List.of(swipe));
    }
    public static void tapOutsideToCloseBottomSheet(AndroidDriver driver,int x,int y) {
        // Create a touch input
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(tap));
    }
}
