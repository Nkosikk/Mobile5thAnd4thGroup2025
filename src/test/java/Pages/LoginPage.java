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

public class LoginPage {

    AppiumDriver driver;
    Properties config;
    WebDriverWait wait;

    public LoginPage(AppiumDriver driver, Properties config) {
        this.driver = driver;
        this.config = config;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private By burgerMenuButtonNative = By.className("android.widget.Button");
    private By burgerMenuButtonWeb = By.xpath("//*[@id=\"app-root\"]/nav/div[1]/button/svg");

    private By signInButtonNative = By.xpath("//android.widget.Button[@content-desc='Login / Sign Up']");
    private By signInButtonWeb = By.xpath("//*[@id='app-root']/nav/div[2]/div[5]/button/span[2]");

    private By emailFieldNative = By.xpath("//android.widget.EditText[@hint='Email']");
    private By emailFieldWeb = By.id("login-email");

    private By passwordFieldNative = By.xpath("//android.widget.EditText[@hint='Password']");
    private By passwordFieldWeb = By.id("login-password");

    private By loginButtonNative = By.xpath("//android.widget.Button[@content-desc='Login']");
    private By loginButtonWeb = By.id("login-submit");


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

    public void clickBurgerMenuButton() {
        getElement(burgerMenuButtonNative, burgerMenuButtonWeb).click();
    }

    public void clickSignInButton() {
        getElement(signInButtonNative, signInButtonWeb).click();
    }

    public void enterEmail(String email) {
        WebElement emailField = getElement(emailFieldNative, emailFieldWeb);
        emailField.click();
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordField = getElement(passwordFieldNative, passwordFieldWeb);
        passwordField.click();
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        getElement(loginButtonNative, loginButtonWeb).click();
    }

}


