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

    @Test (dependsOnMethods = "LoginWithValidCredentials")
    public void verifyLoginSuccess() throws InterruptedException {
        //This method is responsible for verifying that the login was successful by checking for the presence of a specific element on the page after login.
        adminPage.getWelcomeMessage();
        Thread.sleep(2000); // Adding a short wait to ensure the welcome message is fully loaded before taking a screenshot
        ScreenshotUtils.captureScreenshot(driver, "Login Successful - Welcome Message Displayed");
    }
    @Test (dependsOnMethods = "verifyLoginSuccess")
    public void verifyAdminPanelAccess() throws InterruptedException {
        //This method is responsible for verifying that the user has access to the admin panel by clicking on the burger menu and checking for the presence of the admin panel option.
        adminPage.clickBurgerMenuButton();
        Thread.sleep(2000); // Adding a short wait to ensure the menu is fully loaded before taking a screenshot
        ScreenshotUtils.captureScreenshot(driver, "Burger Menu Clicked - Verifying Admin Panel Access");
        adminPage.clickAdminPanelOption();
        Thread.sleep(2000); // Adding a short wait to ensure the admin panel is fully loaded before taking a screenshot
        ScreenshotUtils.captureScreenshot(driver, "Admin Panel Accessed");
    }
    @Test (dependsOnMethods = "verifyAdminPanelAccess")
    public void verifyAdminDashboard() throws InterruptedException {
        //This method is responsible for verifying that the user is on the admin dashboard by checking for the presence of a specific element on the page that indicates the user is on the admin dashboard.
        adminPage.getWelcomeMessageNative();
        Thread.sleep(2000); // Adding a short wait to ensure the admin dashboard title is fully loaded before taking a screenshot
        ScreenshotUtils.captureScreenshot(driver, "Admin Dashboard Displayed");
        adminPage.getAdminPanelTitle();
        Thread.sleep(2000); // Adding a short wait to ensure the admin panel title is fully loaded before taking a screenshot
        ScreenshotUtils.captureScreenshot(driver, "Admin Panel Title Displayed");
        adminPage.clickCoursesButton();
        Thread.sleep(2000); // Adding a short wait to ensure the menu is fully loaded before taking a screenshot
        ScreenshotUtils.captureScreenshot(driver, "Courses Page Accessed from Admin Dashboard");
    }
    @Test (dependsOnMethods = "verifyAdminDashboard")
    public void createCourse() throws InterruptedException {
        //This method is responsible for verifying that the user can create a course by clicking on the create course button and checking for the presence of the create course form.
        coursePage.clickCreateNewCourseButton();
        Thread.sleep(2000); // Adding a short wait to ensure the create course form is fully loaded before taking a screenshot
        ScreenshotUtils.captureScreenshot(driver, "Create Course Form Displayed");
        coursePage.enterCourseTitle("Test Course Title");
        Thread.sleep(2000); // Adding a short wait to ensure the course title is entered before taking a screenshot
        ScreenshotUtils.captureScreenshot(driver, "Course Title Entered");
        coursePage.enterDescription("This is a test course description.");
        Thread.sleep(2000); // Adding a short wait to ensure the description is entered before taking a screenshot
        ScreenshotUtils.captureScreenshot(driver, "Course Description Entered");
        coursePage.enterDuration("60");
        Thread.sleep(2000); // Adding a short wait to ensure the duration is entered before taking a screenshot
        ScreenshotUtils.captureScreenshot(driver, "Course Duration Entered");
        coursePage.selectLevel("Intermediate");
        Thread.sleep(2000); // Adding a short wait to ensure the level is selected before taking a screenshot
        ScreenshotUtils.captureScreenshot(driver, "Course Level Selected");
        coursePage.enterPrice("1000");
        Thread.sleep(2000); // Adding a short wait to ensure the price is entered before taking a screenshot
        ScreenshotUtils.captureScreenshot(driver, "Course Price Entered");
        /*coursePage.enterThumbnailUrl("https://example.com/thumbnail.jpg");
        Thread.sleep(2000); // Adding a short wait to ensure the thumbnail URL is entered before taking a screenshot
        ScreenshotUtils.captureScreenshot(driver, "Course Thumbnail URL Entered");
        coursePage.enterMeetingUrl("https://example.com/meeting");
        Thread.sleep(2000); // Adding a short wait to ensure the meeting URL is entered before taking a screenshot
        ScreenshotUtils.captureScreenshot(driver, "Course Meeting URL Entered");*/
        coursePage.setPublishedCheckbox(true);
        Thread.sleep(2000); // Adding a short wait to ensure the published checkbox is set before taking a screenshot
        ScreenshotUtils.captureScreenshot(driver, "Course Published Checkbox Set");
        coursePage.clickCreateCourseButton();
        Thread.sleep(2000); // Adding a short wait to ensure the course creation is submitted before taking a screenshot
        ScreenshotUtils.captureScreenshot(driver, "Create Course Button Clicked");
        /*coursePage.clickCancelButton();
        Thread.sleep(2000); // Adding a short wait to ensure the cancel action is completed before taking a screenshot
        ScreenshotUtils.captureScreenshot(driver, "Cancel Button Clicked - Course Creation Cancelled");*/
    }
    @Test (dependsOnMethods = "createCourse")
    public void verifyCourseCreation() throws InterruptedException {
        //This method is responsible for verifying that the course was created successfully by checking for the presence
        //of the course in the course list after creation.
        manageCoursePage.getManageCoursesTitle();
        Thread.sleep(2000); // Adding a short wait to ensure the manage courses title is fully loaded before taking a screenshot
        ScreenshotUtils.captureScreenshot(driver, "Manage Courses Title Displayed");
        manageCoursePage.verifyCourseDetails();
        Thread.sleep(2000); // Adding a short wait to ensure the course details are fully loaded before taking a screenshot
        ScreenshotUtils.captureScreenshot(driver, "Course Details Verified in Manage Courses");
        manageCoursePage.clickEditCourseButton();
        Thread.sleep(2000); // Adding a short wait to ensure the edit course form is fully loaded before taking a screenshot
        ScreenshotUtils.captureScreenshot(driver, "Edit Course Button Clicked - Edit Course Form Displayed");
        manageCoursePage.clickSaveCourseButton();
        Thread.sleep(2000); // Adding a short wait to ensure the save course action is completed before taking a screenshot
        ScreenshotUtils.captureScreenshot(driver, "Save Course Button Clicked - Course Changes Saved");
        manageCoursePage.clickBurgerMenuButton();
        Thread.sleep(2000); // Adding a short wait to ensure the menu is fully loaded before taking a screenshot
        ScreenshotUtils.captureScreenshot(driver, "Burger Menu Clicked - Verifying Logout Option");
        manageCoursePage.clickLogoutButton();
        Thread.sleep(2000); // Adding a short wait to ensure the logout action is completed before taking a screenshot
        ScreenshotUtils.captureScreenshot(driver, "Logout Button Clicked - User Logged Out");
    }
    @Test(dependsOnMethods = "verifyCourseCreation")
    public void quitDriver() {
        //This method is responsible for cleaning up after the test execution. It quits the driver session.
        super.tearDown();
    }


}
