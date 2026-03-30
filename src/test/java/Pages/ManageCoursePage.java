package Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;

public class ManageCoursePage {

    AppiumDriver driver;
    Properties config;
    WebDriverWait wait;

    public ManageCoursePage(AppiumDriver driver, Properties config) {
        this.driver = driver;
        this.config = config;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private By manageCoursesTitleNative = By.xpath("//android.view.View[@content-desc='Manage Courses']");
    private By manageCoursesTitleWeb = By.xpath("//*[@id='app-root']/div/main/div/div[1]/h1");

    private By testerAnalystCourseCardNative = By.xpath("//android.view.View[contains(@content-desc,'Tester Analyst Course')]");
    private By testerAnalystCourseCardWeb = By.xpath("//*[@id='app-root']/div/main/div/div[2]/div[1]/div[2]/div[1]");

    private By burgerMenuButtonNative = By.className("android.widget.Button");
    private By burgerMenuButtonWeb = By.xpath("//*[@id='app-root']/nav/div[1]/button");

    private By logoutButtonNative = By.xpath("//android.widget.Button[@content-desc='Logout']");
    private By logoutButtonWeb = By.xpath("//*[@id='app-root']/nav/div[2]/div[5]/button/span[2]");


     // Implement methods to interact with the manage course page elements here

    private WebElement getElement(By nativeLocator, By webLocator) {
        String execType = config.getProperty("executionType").trim();
        if (execType.equalsIgnoreCase("nativeApp")) {
            return wait.until(driver -> driver.findElement(nativeLocator));
        } else if (execType.equalsIgnoreCase("mobileWeb")) {
            return wait.until(driver -> driver.findElement(webLocator));
        } else {
            throw new RuntimeException("Unsupported executionType: " + execType);
        }
    }
    public void getManageCoursesTitle() {
        getElement(manageCoursesTitleNative, manageCoursesTitleWeb).isDisplayed();
    }
    public void verifyTesterAnalystCourseCard() {
        getElement(testerAnalystCourseCardNative, testerAnalystCourseCardWeb).isDisplayed();
    }
    public void clickBurgerMenuButton() {
        getElement(burgerMenuButtonNative, burgerMenuButtonWeb).click();
    }
}
