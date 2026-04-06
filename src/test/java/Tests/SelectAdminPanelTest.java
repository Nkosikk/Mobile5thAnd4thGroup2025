package Tests;

import Base.BaseTest;
import Utilities.ScreenshotUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class SelectAdminPanelTest extends BaseTest {

    @Test(dependsOnMethods = "LoginWithValidCredentials")
    public void PerformAdminDashboardFunctions() {
        adminPanelPage.clickBurgerMenuButtonOnAdminDashboard();
        ScreenshotUtils.captureScreenshot(driver, "Burger Menu Clicked for admin dashboard");
        adminPanelPage.selectTheAdminPanelButton();
        ScreenshotUtils.captureScreenshot(driver, "Click Admin Panel Button");
    }
}
