package Pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;

public class CoursePage {

    AppiumDriver driver;
    Properties config;
    WebDriverWait wait;

    public CoursePage(AppiumDriver driver, Properties config) {
        this.driver = driver;
        this.config = config;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
     }

     // Define locators and methods for interacting with the course page here

    private By createNewCourseButtonNative = By.xpath("//android.widget.Button[@content-desc='+ Create New Course']");
    private By createNewCourseButtonWeb = By.xpath("//*[@id='app-root']/div/main/div/div[2]/div[1]/div[1]/a");

    private By courseTitleInputNative = By.xpath("//android.widget.EditText[@hint='Course Title *']");
    private By courseTitleInputWeb = By.xpath("//*[@id='course-title']");

    private By descriptionInputNative = By.xpath("//android.widget.EditText[@hint='Description *']");
    private By descriptionInputWeb = By.xpath("//*[@id='course-description']");

    private By durationInputNative = By.xpath("//android.widget.EditText[@hint='Duration']");
    private By durationInputWeb = By.xpath("//*[@id='course-duration']");

    private By levelDropdownNative = By.xpath("//android.widget.Button[@content-desc=\"Level\n" +
            "Beginner\"]");
    private By levelDropdownWeb = By.xpath("//*[@id='course-level']");

    private By priceInputNative = By.xpath("//android.widget.EditText[contains(@hint, 'Price')]");
    private By priceInputWeb = By.xpath("//*[@id='course-price']");

    /*private By thumbnailUrlInputNative = By.xpath("//android.widget.EditText[@hint='Thumbnail URL (optional)']");
    private By thumbnailUrlInputWeb = By.xpath("//*[@id='course-thumbnail']");

    private By meetingUrlInputNative = By.xpath("//android.widget.EditText[@hint='Meeting URL (optional)']");
    private By meetingUrlInputWeb = By.xpath("//*[@id='course-meeting-url']");*/

    private By publishedCheckboxNative = By.className("android.widget.CheckBox");
    private By publishedCheckboxWeb = By.xpath("//*[@id='course-published']");

    private By createCourseButtonNative = By.xpath("//android.widget.Button[@content-desc='Create Course']");
    private By createCourseButtonWeb = By.xpath("//*[@id='course-create']");

    /*private By cancelButtonNative = By.xpath("//android.widget.Button[@content-desc='Cancel']");
    private By cancelButtonWeb = By.xpath("//*[@id='course-cancel']");*/

     // Implement methods to interact with the course page elements here

    public WebElement getElement(By nativeLocator, By webLocator) {
        String execType = config.getProperty("executionType").trim();
        if (execType.equalsIgnoreCase("nativeApp")) {
            return wait.until(driver -> driver.findElement(nativeLocator));
        } else if (execType.equalsIgnoreCase("mobileWeb")) {
            return wait.until(driver -> driver.findElement(webLocator));
        } else {
            throw new RuntimeException("Unsupported executionType: " + execType);
        }
    }

     public void clickCreateNewCourseButton() {
         getElement(createNewCourseButtonNative, createNewCourseButtonWeb).click();
     }

     // Implement other methods to interact with the course page elements here

    public void enterCourseTitle(String title) {
        WebElement courseTitleInput = getElement(courseTitleInputNative, courseTitleInputWeb);
        courseTitleInput.click();
        courseTitleInput.sendKeys(title);
    }
    public void enterDescription(String description) {
        WebElement descriptionInput = getElement(descriptionInputNative, descriptionInputWeb);
        descriptionInput.click();
        descriptionInput.sendKeys(description);
    }
    public void enterDuration(String duration) {
        WebElement durationInput = getElement(durationInputNative, durationInputWeb);
        durationInput.click();
        durationInput.sendKeys(duration);
    }
    public void selectLevel(String level) {
        WebElement levelDropdown = getElement(levelDropdownNative, levelDropdownWeb);
        levelDropdown.click();
    }
    public void enterPrice(String price) {
        WebElement priceInput = getElement(priceInputNative, priceInputWeb);
        priceInput.clear();
        priceInput.sendKeys(price);
    }
    /*public void enterThumbnailUrl(String thumbnailUrl) {
        WebElement thumbnailUrlInput = getElement(thumbnailUrlInputNative, thumbnailUrlInputWeb);
        thumbnailUrlInput.click();
        thumbnailUrlInput.sendKeys(thumbnailUrl);
    }
    public void enterMeetingUrl(String meetingUrl) {
        WebElement meetingUrlInput = getElement(meetingUrlInputNative, meetingUrlInputWeb);
        meetingUrlInput.click();
        meetingUrlInput.sendKeys(meetingUrl);
    }*/
    public void setPublishedCheckbox(boolean isChecked) {
        WebElement publishedCheckbox = getElement(publishedCheckboxNative, publishedCheckboxWeb);
        if (publishedCheckbox.isSelected() != isChecked) {
            publishedCheckbox.click();
        } else {
            // Checkbox is already in the desired state, do nothing
        }
    }
    public void clickCreateCourseButton() {
        getElement(createCourseButtonNative, createCourseButtonWeb).click();
    }
    /*public void clickCancelButton() {
        getElement(cancelButtonNative, cancelButtonWeb).click();
    }*/
}
