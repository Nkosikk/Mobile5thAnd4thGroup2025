package Listener;

import Utilities.DriverFactory;
import Utilities.ScreenshotUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    @Override
    public void onTestSuccess(ITestResult result) {
        takeScreenshot(result, "PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        takeScreenshot(result, "FAILED");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        takeScreenshot(result, "SKIPPED");
    }

    private void takeScreenshot(ITestResult result, String status) {
        ScreenshotUtils.captureScreenshot(
                DriverFactory.getDriver(),
                result.getName() + "_" + status
        );
    }

}
