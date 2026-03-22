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
    public void ClickAdminDashboardMenu(){
        adminPanelPage.clickBurgerMenuButtonOnAdminDashboard();
        ScreenshotUtils.captureScreenshot(driver, "Burger Menu Clicked for admin dashboard");
    }

    @Test(dependsOnMethods = "LoginWithValidCredentials")
    public void selectTheAdminPanelButton(){
        adminPanelPage.selectTheAdminPanelButton();
        ScreenshotUtils.captureScreenshot(driver, "Click Admin Panel Button");
    }

    @Test(dependsOnMethods = "selectTheAdminPanelButton")
    public void clickBurgerMenuAdminPanel(){
        adminDashboardPage.clickBurgerMenuAdminPanel();
        ScreenshotUtils.captureScreenshot(driver, "Click Admin Panel Burger Menu");

    }
    @Test(dependsOnMethods = "clickBurgerMenuAdminPanel")
    public void clickOnCourses() {
        adminDashboardPage.clickOnCourses();
        ScreenshotUtils.captureScreenshot(driver, "Click Courses");

    }

   @Test(dependsOnMethods = "clickOnCourses")
    public void clickOnCreateCourse() throws InterruptedException {
        courseManagementPage.clickOnCreateCourse();
        ScreenshotUtils.captureScreenshot(driver, "Click Create Course");
        Thread.sleep(3000);
    }



   /* @Test(dependsOnMethods = "clickOnCourses")
    public void quitDriver() {
        //This method is responsible for cleaning up after the test execution. It quits the driver session.
        super.tearDown();
    }*/



}
