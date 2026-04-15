package Tests;

import Base.BaseTest;
import Utilities.ScreenshotUtils;
import org.testng.annotations.Test;

public class AdminDashboardTest extends BaseTest {
    @Test(dependsOnMethods = "PerformAdminDashboardFunctions")
    public void PerformActionsOnAdminDashboard() {
        adminDashboardPage.clickBurgerMenuAdminPanel();
        ScreenshotUtils.captureScreenshot(driver, "Click Admin Panel Burger Menu");
        adminDashboardPage.clickOnCourses();
        ScreenshotUtils.captureScreenshot(driver, "Click Courses");
    }
}
