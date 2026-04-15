package Tests;
import Base.BaseTest;
import Utilities.ScreenshotUtils;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertEquals;

public class AddCourseTest extends BaseTest {

    @Test(dependsOnMethods = "PerformActionsOnAdminDashboard")
    public void PerformActionsToAddTheCourse() {
        courseManagementPage.clickOnCreateCourse();
        ScreenshotUtils.captureScreenshot(driver, "Click Create Course");
        System.out.println(driver.getPageSource());

        courseManagementPage.enterCourseTitle();
        ScreenshotUtils.captureScreenshot(driver, "enter course title");

        courseManagementPage.enterCourseDescription();
        ScreenshotUtils.captureScreenshot(driver, "Course description entered");

        courseManagementPage.enterCourseDuration();
        ScreenshotUtils.captureScreenshot(driver, "Enter Course Duration");

        courseManagementPage.enterCoursePrice();
        ScreenshotUtils.captureScreenshot(driver, "Enter Course Price");


        courseManagementPage.selectCreateCourse();
        ScreenshotUtils.captureScreenshot(driver, "Select Create Course Button");
        System.out.println(driver.getPageSource());


        String actualMessage = courseManagementPage.getSuccessMessage();
        String expectedMessage = "Course created.";

        assertEquals(actualMessage, expectedMessage);

        System.out.println("Message displayed: " + actualMessage);

    }
   /* @Test(dependsOnMethods = "PerformActionsToAddTheCourse")
    public void quitDriver() {
        //This method is responsible for cleaning up after the test execution. It quits the driver session.
        super.tearDown();
    }*/

}
