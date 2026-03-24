package Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;


public class AdminDashboard {
    AppiumDriver driver;
    Properties config;
    WebDriverWait wait;

    public AdminDashboard(AppiumDriver driver, Properties config) {
        this.driver = driver;
        this.config = config;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    private By adminMenuButtonNative = By.xpath("//android.widget.Button");
    private By adminMenuButtonWeb = By.xpath("//*[@id=\"app-root\"]/nav/div[1]/button/svg");

    private By adminPanelButtonNative = By.xpath("//android.widget.Button[@content-desc=\"Admin Panel\"]");
    private By adminPanelButtonWeb = By.xpath("//*[@id=\"app-root\"]/nav/div[2]/div[5]/button[6]/span[2]");

    private By manageCoursesButtonNative = By.xpath("//android.view.View[@content-desc=\"Manage Courses\"]");
    private By manageCoursesButtonWeb = By.xpath("//*[@id=\"app-root\"]/div/div[2]/nav/button[7]");

    private By createNewCourseButtonNative = By.xpath("//android.widget.Button[@content-desc=\"+ Create New Course\"]\n");
    private By createNewCourseButtonWeb = By.xpath("//*[@id=\"app-root\"]/div/div[3]/div/div[1]/button");

    private By courseTitleFieldNative = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]");
    private By courseTitleFieldWeb = By.xpath("//*[@id=\"app-root\"]/div/div[3]/div/div[3]/div/form/div[1]/input");


    private By courseDescriptionFieldNative = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]");
    private By courseDescriptionFieldWeb = By.xpath("//*[@id=\"app-root\"]/div/div[3]/div/div[3]/div/form/div[2]/textarea");

    private By courseDurationFieldNative = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[3]");
    private By courseDurationFieldWeb = By.xpath("//*[@id=\"app-root\"]/div/div[3]/div/div[3]/div/form/div[3]/div[1]/input");

    private By courseLevelDropdownNative = By.xpath("//android.widget.Button[@content-desc=\"Level\n" + "Beginner\"]");
    private By courseLevelDropdownWeb = By.xpath("//*[@id=\"app-root\"]/div/div[3]/div/div[3]/div/form/div[3]/div[2]/select");

    private By courseLevelOptionNative = By.xpath("//android.widget.Button[@content-desc=\"Intermediate\"]");
    private By courseLevelOptionWeb = By.xpath("//*[@id=\"app-root\"]/div/div[3]/div/div[3]/div/form/div[3]/div[2]/select/option[2]");

    private By coursePriceFieldNative = By.xpath("//android.widget.EditText[@text=\"0\"]");
    private By coursePriceFieldWeb = By.xpath("//*[@id=\"app-root\"]/div/div[3]/div/div[3]/div/form/div[4]/div[1]/input");

    private By createCourseButtonNative = By.xpath("//android.widget.Button[@content-desc=\"Create Course\"]");
    private By createCourseButtonWeb = By.xpath("//*[@id=\"app-root\"]/div/div[3]/div/div[3]/div/form/div[7]/button[1]");

    private By logOutButtonNative = By.xpath("//android.widget.Button[@content-desc=\"Logout\"]");
    private By logOutButtonWeb = By.xpath("//*[@id=\"app-root\"]/nav/div[2]/div[5]/button[7]/span[2]");

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

    public void clickAdminMenuButton() {
        getElement(adminMenuButtonNative, adminMenuButtonWeb).click();
    }

    public void clickAdminPanelButton() {
        getElement(adminPanelButtonNative, adminPanelButtonWeb).click();
    }

    public void clickManageCourseButton() {
        getElement(manageCoursesButtonNative, manageCoursesButtonWeb).click();
    }

    public void clickCreateNewCourseButton() {
        getElement(createNewCourseButtonNative, createNewCourseButtonWeb).click();
    }

    public void enterCourseTitle(String title) {
        WebElement titleField = getElement(courseTitleFieldNative, courseTitleFieldWeb);
        titleField.click();
        titleField.sendKeys(title);
    }

    public void enterCourseDescription(String description) {
        WebElement descriptionField = getElement(courseDescriptionFieldNative, courseDescriptionFieldWeb);
        descriptionField.click();
        descriptionField.sendKeys(description);
    }

    public void enterCourseDuration(String duration) {
        WebElement durationField = getElement(courseDurationFieldNative, courseDurationFieldWeb);
        durationField.clear();
        durationField.sendKeys(duration);
    }

    public void clickClickCourseLevelDropdown() {
        getElement(courseLevelDropdownNative, courseLevelDropdownWeb).click();
    }

    public void selectCourseLevelOption() {
        getElement(courseLevelOptionNative, courseLevelOptionWeb).click();
    }

    public void enterCoursePriceField(String price) {
        WebElement priceField = getElement(coursePriceFieldNative, coursePriceFieldWeb);
        priceField.clear();
        priceField.sendKeys(price);
    }

    public void clickCreateCourseButton() {
        getElement(createCourseButtonNative, createCourseButtonWeb).click();
    }

    public void clickLogOutButton() {
        getElement(logOutButtonNative, logOutButtonWeb).click();
    }
}
