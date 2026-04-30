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
        Thread.sleep(2000);
        ScreenshotUtils.captureScreenshot(driver, "Login Submitted");
        homePage.clickHomeMenuButton();
        Thread.sleep(5000);
        homePage.clickAdminPanelButton();
        Thread.sleep(5000);
        homePage.clickManageCoursesButton();
        Thread.sleep(2000);
        homePage.clickCreateNewCourseButton();
        Thread.sleep(2000);
        homePage.enterCourseTitle("Bachelor Of Science In Information Technology At AyaSK7 University");
        Thread.sleep(2000);
        homePage.enterDescription("The BSc (Software Engineering) qualification prepares you for work in a range of IT jobs");
        Thread.sleep(2000);
        homePage.enterDuration("8 weeks");
        Thread.sleep(2000);
        homePage.selectLevel();
        Thread.sleep(2000);
        homePage.enterPrice("2000");
        Thread.sleep(2000);
        homePage.enterThumbnailUrl("https://AyandaSK.com/thumbnail.jpg");
        Thread.sleep(2000);
        homePage.enterMeetingUrl("https://Bhc Teams Meeting URL");
        Thread.sleep(3000);
        homePage.clickCreateCourseButton();
        Thread.sleep(4000);
        homePage.verifyCourseCreated("Bachelor Of Science In Information Technology At AyaSK7 University");
        Thread.sleep(2000);
        homePage.clickAdminMenuButton();
        Thread.sleep(2000);
        homePage.clickLogoutButton();
        Thread.sleep(2000);



    }

    @Test(dependsOnMethods = "LoginWithValidCredentials")
    public void quitDriver() {
        //This method is responsible for cleaning up after the test execution. It quits the driver session.
        super.tearDown();
    }


}
