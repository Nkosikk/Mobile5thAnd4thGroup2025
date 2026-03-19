package Utilities;

import Base.BaseTest;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ScreenshotUtils {

    public static void captureScreenshot(AppiumDriver driver, String testName) {
        try {
            File src = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);
            File file = new File("screenshots/" + testName + ".png");
            file.getParentFile().mkdirs();
            Files.copy(src.toPath(), file.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Screenshot saved: " + file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
