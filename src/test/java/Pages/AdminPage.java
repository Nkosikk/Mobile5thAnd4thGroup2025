package Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;

public class AdminPage {

    AppiumDriver driver;
    Properties config;
    WebDriverWait wait;

    public AdminPage(AppiumDriver driver, Properties config) {
        this.driver = driver;
        this.config = config;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private By welcomeMessageNative = By.xpath("//android.view.View[@content-desc=\"Admin Dashboard\"]");
    private By welcomeMessageWeb = By.xpath("//*[@id='app-root']/div/main/div/div[1]/h1");

    private By burgerMenuButtonNative = By.className("android.widget.Button");
    private By burgerMenuButtonWeb = By.xpath("//*[@id='app-root']/nav/div[1]/button");

    private By adminPanelOptionNative = By.xpath("//android.widget.Button[@content-desc=\"Admin Panel\"]");
    private By adminPanelOptionWeb = By.xpath("//*[@id='app-root']/nav/div[2]/div[5]/div/a[1]");

    private By adminDashboardTitleNative = By.xpath("//android.view.View[@content-desc='Admin Dashboard']");
    private By adminDashboardTitleWeb = By.xpath("//*[@id='app-root']/div/main/div/div[1]/h1");

    private By adminPanelTitleNative = By.xpath("//android.view.View[@content-desc='Admin Panel']");
    private By adminPanelTitleWeb = By.xpath("//*[@id='app-root']/div/main/div/div[1]/h1");

    private By coursesButtonNative = By.xpath("//android.widget.Button[@content-desc='Courses']");
    private By coursesButtonWeb = By.xpath("//*[@id='app-root']/div/main/div/div[2]/div[1]/div[1]/a");

        // Implement methods to interact with the admin page elements here

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

    public void getWelcomeMessage() {
        getElement(welcomeMessageNative, welcomeMessageWeb).isDisplayed();
    }
    public void clickBurgerMenuButton() {
        getElement(burgerMenuButtonNative, burgerMenuButtonWeb).click();
    }
    public void clickAdminPanelOption() {
        getElement(adminPanelOptionNative, adminPanelOptionWeb).click();
    }
    public void getWelcomeMessageNative() {
        getElement(adminDashboardTitleNative, adminDashboardTitleWeb).isDisplayed();
    }
    public void getAdminPanelTitle() {
        getElement(adminPanelTitleNative, adminPanelTitleWeb).isDisplayed();
    }
    public void clickCoursesButton() {
        getElement(coursesButtonNative, coursesButtonWeb).click();
    }
}
