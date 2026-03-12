package Tests;

import Base.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @Test
    public void SetUp() throws IOException {
        //This is the setup metho from the BaseTest Class
        setUp();
    }
    @Test(dependsOnMethods = "SetUp")
    public void LoginWithValidCredentials() {
        //This is the login test method that performs the login action
        loginPage.clickBurgerMenuButton();
        loginPage.clickSignInButton();
        loginPage.enterEmail(config.getProperty("email"));
        loginPage.enterPassword(config.getProperty("password"));
        loginPage.clickLoginButton();
    }

    @Test(dependsOnMethods = "LoginWithValidCredentials")
    public void quitDriver() {
        //This method is responsible for cleaning up after the test execution. It quits the driver session.
        super.tearDown();
    }


}
