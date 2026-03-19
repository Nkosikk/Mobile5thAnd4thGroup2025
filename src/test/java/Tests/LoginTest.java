package Tests;

import Base.BaseTest;
import Utilities.ScreenshotUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @Test
    public void SetUp() throws IOException {
        //This is the setup metho from the BaseTest Class
        setUp();
    }
    @Test(dependsOnMethods = "SetUp")
    public void LoginWithValidCredentials() throws InterruptedException {
        //This is the login test method that performs the login action
        loginPage.clickBurgerMenuButton();
        Thread.sleep(2000); // Adding a short wait to ensure the menu is fully loaded before taking a screenshot
        ScreenshotUtils.captureScreenshot(driver, "Burger Menu Clicked");
        loginPage.clickSignInButton();
        ScreenshotUtils.captureScreenshot(driver, "Sign In Page");
        loginPage.enterEmail(config.getProperty("email"));
        ScreenshotUtils.captureScreenshot(driver, "Email Entered");
        loginPage.enterPassword(config.getProperty("password"));
        ScreenshotUtils.captureScreenshot(driver, "Password Entered");
        loginPage.clickLoginButton();
        ScreenshotUtils.captureScreenshot(driver, "Login Submitted");
    }

    @Test(dependsOnMethods = "LoginWithValidCredentials")
    public void quitDriver() {
        //This method is responsible for cleaning up after the test execution. It quits the driver session.
        super.tearDown();
    }


}
