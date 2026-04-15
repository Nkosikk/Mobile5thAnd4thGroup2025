 package Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;

public class CourseManagementPage {
    AppiumDriver driver;
    Properties config;
    WebDriverWait wait;

    public CourseManagementPage(AppiumDriver driver, Properties config) {
        this.driver = driver;
        this.config = config;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    private By createCourseNative = AppiumBy.accessibilityId("+ Create New Course");
    private By createCourseWeb = By.xpath("//button[@aria-label='+ Create New Course' or normalize-space()='+ Create New Course']");

    private By courseTitleNative = AppiumBy.xpath("//android.widget.EditText[@hint='Course Title *']");
    private By courseTitleWeb = By.xpath("//input[@placeholder='Course Title *']");

    private By descriptionNative = AppiumBy.xpath("//android.widget.EditText[@hint='Description *']");
    private By descriptionWeb = By.xpath("//textarea[@placeholder='Description *']");

    private By durationNative = AppiumBy.xpath("//android.widget.EditText[@hint='Duration']");
    private By durationWeb = By.xpath("//input[@placeholder='Duration']");

    private By priceNative = AppiumBy.xpath("//android.widget.EditText[@hint='Price (R)']");
    private By priceWeb = By.xpath("//input[@placeholder='Price (R)']");

    private By createCourseBtnNative = AppiumBy.xpath("//android.widget.Button[@content-desc='Create Course']");
    private By createCourseBtnWeb = By.xpath("//button[normalize-space()='Create Course']");


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

    public void clickOnCreateCourse() {
        getElement(createCourseNative, createCourseWeb).click();
    }

    public void enterCourseTitle() {
        WebElement titleField = getElement(courseTitleNative, courseTitleWeb);
        titleField.click();    // focus the field
        titleField.clear();    // remove any pre-filled text
        titleField.sendKeys("TestKB");
    }

    public void enterCourseDescription() {
        WebElement titleField = getElement(descriptionNative, descriptionWeb);
        titleField.click();    // focus the field
        titleField.clear();    // remove any pre-filled text
        titleField.sendKeys("budbwu6434t349");

    }

    public void enterCourseDuration() {
        WebElement titleField = getElement(durationNative, durationWeb);
        titleField.click();
        titleField.clear();
        titleField.sendKeys("6 months");

    }

    public void enterCoursePrice() {
        WebElement titleField = getElement(priceNative, priceWeb);
        titleField.click();
        titleField.clear();
        titleField.sendKeys("R600");
        driver.navigate().back();
    }
   /*public void hideKeyboard() {
        try {
            ((AndroidDriver) driver).hideKeyboard();
        } catch (Exception e1) {
            try {
                ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
            } catch (Exception e2) {
                // keyboard likely already closed
            }
        }
        }

        public void selectCreateCourse () {
            hideKeyboard();
            getElement(createCourseBtnNative, createCourseBtnWeb).click();
        }*/

    public void hideKeyboard() {
        try {
            ((AndroidDriver) driver).hideKeyboard();
        } catch (Exception e1) {
            try {
                ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
            } catch (Exception e2) {
                // keyboard likely already closed or BACK did nothing
            }
        }
    }

    public void selectCreateCourse() {
        hideKeyboard();

        try {
            Thread.sleep(500); // allows keyboard animation to finish
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        getElement(createCourseBtnNative, createCourseBtnWeb).click();
    }



        public String getSuccessMessage () {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement message = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.accessibilityId("Course created.")
                    )
            );
            System.out.println(driver.getPageSource());

            return message.getAttribute("content-desc");
        }

    }