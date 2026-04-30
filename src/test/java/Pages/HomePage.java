package Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Properties;

public class HomePage {

    AppiumDriver driver;
    Properties config;
    WebDriverWait wait;

    public HomePage(AppiumDriver driver, Properties config) {
        this.driver = driver;
        this.config = config;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private By homeMenuButtonNative = By.className("android.widget.Button");
    private By homeMenuButtonWeb = By.xpath("//div[@class='nav-container']/button");
    private By adminPanelButton = By.xpath("//android.widget.Button[@content-desc='Admin Panel']");
    private By manageCoarsesButton = By.xpath("//android.view.View[@content-desc='Manage Courses']");
    private By createNewCourseButton = By.xpath("//android.widget.Button[@content-desc='+ Create New Course']");
    private By courseTitleField = By.xpath("//android.widget.EditText[@hint='Course Title *']");
    private By descriptionField = By.xpath("//android.widget.EditText[@hint='Description *']");
    private By durationField = By.xpath("//android.widget.EditText[@hint='Duration']");
    private By levelField = By.xpath("//android.widget.Button[contains(@content-desc,'Level')]");
    private By levelOptionAdvanced = By.xpath("//android.widget.Button[@content-desc='Advanced']");
    private By priceField = By.xpath("//android.widget.EditText[@hint='Price (R)']");
    private By thumbnailUrlField = By.xpath("//android.widget.EditText[@hint='Thumbnail URL (optional)']");
    private By meetingUrlField = By.xpath("//android.widget.EditText[@hint='Meeting URL (optional)']");
    private By createCourseButton = By.xpath("//android.widget.Button[@content-desc='Create Course']");
    private By adminMenuButton = By.xpath("//android.widget.ScrollView/android.view.View[1]");
    private By logoutButton = By.xpath("//android.widget.Button[@content-desc=\"Logout\"]");



    private WebElement getElement(By nativeLocator, By webLocator) {
        String execType = config.getProperty("executionType").trim();
        if (execType.equalsIgnoreCase("nativeApp")) {
            return wait.until(ExpectedConditions.elementToBeClickable(nativeLocator));
        } else if (execType.equalsIgnoreCase("mobileWeb")) {
            return wait.until(ExpectedConditions.elementToBeClickable(webLocator));
        } else {
            throw new RuntimeException("Unsupported executionType: " + execType);
        }
    }

    public void clickHomeMenuButton() {
        getElement(homeMenuButtonNative, homeMenuButtonWeb).click();
    }

    public void clickAdminPanelButton() {
        String execType = config.getProperty("executionType").trim();
        if (execType.equalsIgnoreCase("nativeApp")) {
            driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().description(\"Admin Panel\"))"));
        }
        getElement(adminPanelButton, adminPanelButton).click();
    }

    public void clickManageCoursesButton() {
        getElement(manageCoarsesButton, manageCoarsesButton).click();
    }

    public void clickCreateNewCourseButton() {
        getElement(homeMenuButtonNative, createNewCourseButton).click();
    }


    public void enterCourseTitle(String CourseTitle) {
        WebElement TitleField = getElement(courseTitleField, courseTitleField);
        TitleField.click();
        TitleField.sendKeys(CourseTitle);
    }

public void enterDescription(String Description) {
    WebElement DescriptionField = getElement(descriptionField, descriptionField);
    DescriptionField.click();
    DescriptionField.sendKeys("The BSc (Software Engineering) qualification prepares you for work in a range of IT jobs");
}

    public void enterDuration(String Duration) {
        WebElement DurationField = getElement(durationField, durationField);
        DurationField.click();
        DurationField.sendKeys("8 weeks");
    }

    public void selectLevel() {
        WebElement levelDropdown = getElement(levelField, levelField);
        levelDropdown.click();
        WebElement levelAdvanced = getElement(levelOptionAdvanced, levelOptionAdvanced);
        levelAdvanced.click();

    }

     public void enterPrice(String Course_Price) {
        WebElement PriceField = getElement(priceField, priceField);
        PriceField.click();
        PriceField.clear();
        PriceField.sendKeys(Course_Price);

     }

     public void enterThumbnailUrl(String ThumbnailUrl) {
        WebElement thumbnailUrlFieldElement = getElement(thumbnailUrlField, thumbnailUrlField);
        thumbnailUrlFieldElement.click();
        thumbnailUrlFieldElement.clear();
        thumbnailUrlFieldElement.sendKeys(ThumbnailUrl);
     }

     public void enterMeetingUrl(String MeetingUrl) {
        WebElement meetingUrlFieldElement = getElement(meetingUrlField, meetingUrlField);
        meetingUrlFieldElement.click();
        meetingUrlFieldElement.clear();
        meetingUrlFieldElement.sendKeys(MeetingUrl);
     }

     public void clickCreateCourseButton() {
         String execType = config.getProperty("executionType").trim();
         if (execType.equalsIgnoreCase("nativeApp")) {
             driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().description(\"Create Course\"))"));
         }
        getElement(createCourseButton, createCourseButton).click();
     }

    public void verifyCourseCreated(String courseName) {
        By courseLocator = By.xpath("//android.view.View[contains(@content-desc, '" + courseName + "')]");

        WebElement createdCourse = wait.until(
                ExpectedConditions.visibilityOfElementLocated(courseLocator)
        );

        Assert.assertTrue(createdCourse.isDisplayed(),
                "Course '" + courseName + "' was not found in the course list.");
    }

    public void clickAdminMenuButton() {
        getElement(adminMenuButton, adminMenuButton).click();
    }


    public void clickLogoutButton() {
         getElement(logoutButton, logoutButton).click();
    }

}

