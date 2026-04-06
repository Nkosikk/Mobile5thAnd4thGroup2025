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

public class AdminPanelPage {
    AppiumDriver driver;
    Properties config;
    WebDriverWait wait;

    public AdminPanelPage(AppiumDriver driver, Properties config) {
        this.driver = driver;
        this.config = config;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

    }

    private By adminDashburgerMenuButtonNative = By.xpath("//android.view.View[.//android.view.View[@content-desc='Admin Dashboard']]//android.widget.Button");
    private By adminDashburgerMenuButtonWeb = By.xpath("//android.widget.Button[@clickable='true'][1]"); // or your real web locator

    private By adminPortalButtonNative = By.xpath("//android.widget.Button[@content-desc='Admin Panel']");
    private By adminPortalButtonWeb = By.id("admin-panel");

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

    public void clickBurgerMenuButtonOnAdminDashboard() {
        getElement(adminDashburgerMenuButtonNative, adminDashburgerMenuButtonWeb).click();
    }

    public void selectTheAdminPanelButton() {
        getElement(adminPortalButtonNative, adminPortalButtonWeb).click();

    }
}

