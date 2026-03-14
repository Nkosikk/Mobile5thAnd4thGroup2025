package Base;

import Pages.LoginPage;
import Utilities.DriverFactory;
import io.appium.java_client.AppiumDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static Base.AppiumServerManager.startAppiumServer;

public class BaseTest {

    //The reason we use protected in BaseTest is about visibility and inheritance.
    //My test classes extend BaseTest, so they need access to those variables.
    //Visibility: Protected members are accessible within the same package and by subclasses, even if they are in different packages.
    // This allows any test class that extends BaseTest to access the driver and config without needing to create new instances or pass them around.
    protected AppiumDriver driver;
    protected Properties config;
    protected LoginPage loginPage;

    public void setUp() throws IOException {
        startAppiumServer();
        //loading the config.properties file to read the configuration values for the test execution.
        config = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/configs/config.properties");
        config.load(fileInputStream);

        //initializing the Appium driver using the DriverFactory class and also getting the driver instance to be used in the tests.
        DriverFactory.initDriver(config);
        driver = DriverFactory.getDriver();

        //Initialise the LoginPage object here if you want to use it in the tests.
        loginPage = new LoginPage(driver,config);
    }

    //This method is responsible for cleaning up after the test execution. It quits the driver session.
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
