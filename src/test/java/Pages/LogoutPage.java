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

public class LogoutPage {
    AppiumDriver driver;
    Properties config;
    WebDriverWait wait;

    public LogoutPage(AppiumDriver driver, Properties config) {
        this.driver = driver;
        this.config = config;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private By BurgerMenuButtonNative1 = By.xpath("//android.view.View[@clickable='true' and @bounds='[42,108][152,218]']");
    private By BurgerMenuButtonWeb1 = By.xpath("//button[contains(@class,'menu') or contains(@class,'hamburger')]"); // or your real web locator

    private By LogoutButtonNative = By.xpath("//android.widget.Button[@content-desc='Logout']");
    private By LogoutButtonWeb = By.xpath("//button[@aria-label='Logout']");

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

    public void clickBurgerMenuButtonOnAdminDashboard1() {
        getElement(BurgerMenuButtonNative1, BurgerMenuButtonWeb1 ).click();
        System.out.println(driver.getPageSource());
    }
    public void selectLogoutButton(){
        getElement(LogoutButtonNative, LogoutButtonWeb).click();
    }
}
