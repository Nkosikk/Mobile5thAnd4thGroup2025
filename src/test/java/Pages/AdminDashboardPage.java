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

public class AdminDashboardPage {
    AppiumDriver driver;
    Properties config;
    WebDriverWait wait;

    public AdminDashboardPage(AppiumDriver driver, Properties config) {
        this.driver = driver;
        this.config = config;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    private By adminDashBurgerMenuNative =
            By.xpath("(//android.view.View[@clickable='true'])[1]");
    private By adminDashBurgerMenuWeb =
            By.xpath("//button[contains(@class,'menu') or contains(@class,'hamburger')]");
    private By coursesNative =
            By.xpath("//android.widget.Button[@content-desc='Courses']");
    private By coursesWeb =
            By.xpath("//*[contains(text(),'Courses')]");


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

    public void clickBurgerMenuAdminPanel() {
        getElement(adminDashBurgerMenuNative, adminDashBurgerMenuWeb).click();
    }

    public void clickOnCourses() {
        getElement(coursesNative, coursesWeb).click();
    }

}

