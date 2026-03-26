package Tests;

import Base.BaseTest;
import org.testng.annotations.*;

public class AdminTest extends BaseTest {

    @Test(dependsOnMethods = "LoginTest.LoginWithValidCredentials")
    public void createANewCourse(){
        adminDashboard.clickAdminMenuButton();
        adminDashboard.clickAdminPanelButton();
        adminDashboard.clickManageCourseButton();
        adminDashboard.clickCreateNewCourseButton();
        adminDashboard.enterCourseTitle("title");
        adminDashboard.enterCourseDescription("description");
        adminDashboard.enterCourseDuration("duration");
        adminDashboard.clickCourseLevelDropdown();
        adminDashboard.selectCourseLevelOption();
        adminDashboard.enterCoursePriceField("price");
        adminDashboard.clickCreateCourseButton();
        adminDashboard.clickLogOutButton();
    }

    @Test(dependsOnMethods = "createANewCourse")
    public void quitDriver() {
        //This method is responsible for cleaning up after the test execution. It quits the driver session.
        super.tearDown();
    }
}
