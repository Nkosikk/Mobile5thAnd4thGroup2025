package Base;

import io.appium.java_client.service.local.AppiumDriverLocalService;

public class AppiumServerManager {
    private static AppiumDriverLocalService service;

    public static void startAppiumServer() {

        System.out.println("Appium server is not running. Starting the server...");

        service = AppiumDriverLocalService.buildDefaultService();
        service.start();

        // Optionally, you can check if the process is alive
        if (service.isRunning()) {
            System.out.println("Appium server started successfully.");
        } else {
            System.out.println("Failed to start Appium server.");
        }

    }

    public static void stopAppiumServer() {
        if (service != null && service.isRunning()) {
            service.stop();
            System.out.println("Appium server stopped successfully.");
        } else {
            System.out.println("Appium server is not running.");
        }
    }
}
