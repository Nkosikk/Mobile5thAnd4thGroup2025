package Utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Properties;

public class DriverFactory {

    static AppiumDriver driver;

    public static void initDriver(Properties config) throws MalformedURLException {

        if (driver != null) return;

        String platformName = config.getProperty("platformName").trim();
        String executionType = config.getProperty("executionType").trim();
        String appiumUrl = config.getProperty("appiumServer").trim();

        if (platformName.equalsIgnoreCase("Android")) {
            try {
                initAndroidDriver(config, executionType, appiumUrl);
            } catch (MalformedURLException e) {
                throw new RuntimeException("Invalid Appium server URL: " + appiumUrl, e);
            }
        } else if (platformName.equalsIgnoreCase("iOS")) {
            initIOSDriver(config, executionType, appiumUrl);
        } else {
            throw new RuntimeException("Unsupported platformName: " + platformName);
        }

    }

    private static void initAndroidDriver(Properties config, String executionType, String appiumUrl) throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setAutomationName(config.getProperty("automationName"))
                .setPlatformName(config.getProperty("platformName"));

        if (executionType.equalsIgnoreCase("mobileWeb")) {
            options.withBrowserName(config.getProperty("browserName"));
        }else if(executionType.equalsIgnoreCase("nativeApp")) {
            String appPath = System.getProperty("user.dir") + "/" + config.getProperty("appPath");
            options.setApp(appPath);
        } else {
            throw new RuntimeException("Unsupported executionType for Android: " + executionType);
        }
        driver = new AppiumDriver(URI.create(appiumUrl).toURL(), options);
        if (executionType.equalsIgnoreCase("mobileWeb")) {
            String webUrl = config.getProperty("webUrl");
            driver.get(webUrl);
        }

    }
    private static void initIOSDriver(Properties config, String executionType, String appiumUrl) throws MalformedURLException {
        XCUITestOptions options = new XCUITestOptions()
                .setAutomationName(config.getProperty("automationName"))
                .setPlatformName(config.getProperty("platformName"));

        if (executionType.equalsIgnoreCase("mobileWeb")) {
            options.withBrowserName(config.getProperty("browserName"));
        } else if (executionType.equalsIgnoreCase("nativeApp")) {
            String appPath = System.getProperty("user.dir") + "/" + config.getProperty("appPath");
            options.setApp(appPath);
        } else {
            throw new RuntimeException("Unsupported executionType for iOS: " + executionType);
        }
        driver = new AppiumDriver(URI.create(appiumUrl).toURL(), options);
        if (executionType.equalsIgnoreCase("mobileWeb")) {
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
