package steps;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.URL;

public class Base
{
    public static AndroidDriver driver;

    public static void setDriver() throws InterruptedException {
//        EmulatorLauncher.startEmulator();
//        AppiumServerClass.startAppium();

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Pixel_7_API_33")
                .setPlatformVersion("13.0")
                .setUdid("emulator-5554")
                .setAutomationName("UiAutomator2")
                .setPlatformName("Android")
                .setAutoGrantPermissions(true)
                .setNoReset(true)
                .setAppPackage("com.aciana.docisnplus")
                .setAppActivity("com.aciana.docisnplus.MainActivity")
                .setCapability("enforceXPath1", true);
        try
        {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
            System.out.println("Driver initiated");
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public static AndroidDriver getDriver()
    {
        System.out.println("This is get driver");
        return driver;
    }

}
