package Listener;

import Utilities.DriverFactory;
import Utilities.ScreenshotUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        ScreenshotUtils.captureScreenshot(
                DriverFactory.getDriver(),
                result.getName()
        );
    }
}
