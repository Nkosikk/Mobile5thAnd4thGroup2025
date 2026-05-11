package Tests;

import Base.BaseTest;
import Pages.LogoutPage;
import Utilities.ScreenshotUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class LogoutTest extends BaseTest {

    @Test
    public void SetUp() throws IOException {
        //This is the setup metho from the BaseTest Class
        setUp();
    }
    @Test(dependsOnMethods = "SetUp" )
    public void SuccessfullLogout () throws InterruptedException {
        logoutPage.clickBurgerMenuButtonOnAdminDashboard1();
        ScreenshotUtils.captureScreenshot(driver, "Burger Menu Selected successfully");
        logoutPage.selectLogoutButton();
        ScreenshotUtils.captureScreenshot(driver, "Logged out successfully");
        Thread.sleep(20);

    }

}