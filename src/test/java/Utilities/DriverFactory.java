package Utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Properties;

public class DriverFactory {

    static AppiumDriver driver;

    public static void initDriver(Properties config) throws MalformedURLException {
        if (driver != null) return;

        //The type of execution you want to run: mobileWeb or nativeApp
        String execType = config.getProperty("executionType").trim();

        //These are the capabilities that are common for both mobile web and native app testing. We set them up based on the values in the config.properties file.
        UiAutomator2Options options = new UiAutomator2Options()
                .setAutomationName(config.getProperty("automationName"))
                .setPlatformName(config.getProperty("platformName"));
        options.setCapability("appium:deviceName", "Android Emulator");

        //if execution type is mobile web, we set the browser name capability.
        if (execType.equalsIgnoreCase("mobileWeb")) {
            //setting the browser to launch
            options.withBrowserName(config.getProperty("browserName"));
            // FIX: automatically download correct chromedriver
            options.setCapability("appium:chromedriverAutodownload", true);
        }
        //if execution type is native app, we will install and launch the app on the device or emulator.
        else if (execType.equalsIgnoreCase("nativeApp")) {
            //we set the app path capability.
            String appPath = System.getProperty("user.dir") + config.getProperty("appPath");
            options.setApp(appPath);
        }

        //the driver must now start the Appium Session using the Appium server URL and the capabilities we defined in the options object.
        driver = new AppiumDriver(
                URI.create(config.getProperty("appiumServer")).toURL(),
                options
        );

        if (execType.equalsIgnoreCase("mobileWeb")) {
            String webUrl = config.getProperty("webUrl");
            driver.get(webUrl);
        }
    }

    public static AppiumDriver getDriver() {

        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
