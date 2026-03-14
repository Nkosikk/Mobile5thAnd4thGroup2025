package Base;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class AppiumServerManager {
    private static Process appiumProcess;

    public static boolean isAppiumServerRunning() {
        try {
            URL url = new URL("http://127.0.0.1:4723/status");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000); // Timeout after 3 seconds
            connection.connect();

            // Check if the response code is 200 (OK)
            return connection.getResponseCode() == 200;
        } catch (IOException e) {
            return false; // Server is not running
        }
    }
    public static void startAppiumServer() {

        if (isAppiumServerRunning()) {
            System.out.println("Appium server is already running.");
            return;
        }else {
            System.out.println("Appium server is not running. Starting the server...");
            // Command to start the Appium server with the required arguments
            String[] command = {
                    "appium",
                    "--allow-insecure", "chromedriver_autodownload",
                    "--relaxed-security"
            };

            try {
                // Use ProcessBuilder to execute the command
                ProcessBuilder processBuilder = new ProcessBuilder(command);
                processBuilder.inheritIO(); // Redirect output and error streams to the console
                appiumProcess = processBuilder.start();

                // Optionally, you can check if the process is alive
                if (appiumProcess.isAlive()) {
                    System.out.println("Appium server started successfully.");
                }
            } catch (IOException e) {
                System.err.println("Failed to start Appium server: " + e.getMessage());
            }
        }

    }

    public static void stopAppiumServer() {
        if (appiumProcess != null && appiumProcess.isAlive()) {
            appiumProcess.destroy();
            System.out.println("Appium server stopped successfully.");
        } else {
            System.out.println("Appium server is not running.");
        }
    }
}
