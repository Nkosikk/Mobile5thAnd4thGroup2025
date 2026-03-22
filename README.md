# 📱 Mobile Automation Testing Framework

A robust mobile automation testing framework built with **Java**, **Appium**, and **TestNG** supporting both **Android** and **iOS** platforms. This framework enables automated testing for both **native mobile apps** and **mobile web** applications.

---

## 📋 Table of Contents

- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Prerequisites](#-prerequisites)
- [Installation & Setup](#-installation--setup)
- [Configuration](#-configuration)
- [Running Tests](#-running-tests)
- [Test Reports & Screenshots](#-test-reports--screenshots)
- [Framework Architecture](#-framework-architecture)
- [Contributing](#-contributing)

---

## ✨ Features

- ✅ **Cross-Platform Support** - Works on both Android and iOS devices/emulators
- ✅ **Dual Execution Mode** - Supports native app testing and mobile web testing
- ✅ **Page Object Model (POM)** - Clean separation of test logic and page elements
- ✅ **Automatic Screenshots** - Captures screenshots on test pass, fail, and skip
- ✅ **Configurable Setup** - Easy configuration via properties file
- ✅ **TestNG Integration** - Powerful test management with listeners and reporting
- ✅ **Explicit Waits** - Smart synchronization using WebDriverWait

---

## 🛠 Tech Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 21 | Programming Language |
| Appium Java Client | 10.0.0 | Mobile Automation |
| Selenium | 4.39.0 | Web Element Interaction |
| TestNG | 7.9.0 | Test Framework |
| Maven | 3.x | Build & Dependency Management |
| Maven Surefire | 3.2.5 | Test Execution |

---

## 📁 Project Structure

```
Mobile5thAnd4thGroup2025/
├── pom.xml                                    # Maven configuration & dependencies
├── README.md                                  # Project documentation
├── app/
│   └── app-qa-release.apk                     # Android application package
└── src/
    └── test/
        ├── java/
        │   ├── Base/
        │   │   └── BaseTest.java              # Base test class with setup/teardown
        │   ├── Listener/
        │   │   └── TestListener.java          # TestNG listener for screenshots
        │   ├── Pages/
        │   │   └── LoginPage.java             # Login page object class
        │   ├── Tests/
        │   │   └── LoginTest.java             # Login test cases
        │   └── Utilities/
        │       ├── DriverFactory.java         # Appium driver initialization
        │       └── ScreenshotUtils.java       # Screenshot capture utility
        └── resources/
            ├── configs/
            │   └── config.properties          # Test configuration file
            └── testrunner/
                └── testng.xml                 # TestNG suite configuration
```

---

## 📝 Prerequisites

Before running this project, ensure you have the following installed:

### Required Software

1. **Java JDK 21**
   ```bash
   java -version  # Should show version 21
   ```

2. **Maven 3.x**
   ```bash
   mvn -version
   ```

3. **Appium Server 2.x**
   ```bash
   npm install -g appium
   appium -v
   ```

4. **Appium Drivers**
   ```bash
   # For Android
   appium driver install uiautomator2
   
   # For iOS
   appium driver install xcuitest
   ```

### For Android Testing

- Android Studio with SDK tools
- Android Emulator or physical device with USB debugging enabled
- `ANDROID_HOME` environment variable set

### For iOS Testing

- macOS with Xcode installed
- iOS Simulator or physical device
- WebDriverAgent configured

---

## 🚀 Installation & Setup

### 1. Clone the Repository

```bash
git clone <repository-url>
cd Mobile5thAnd4thGroup2025
```

### 2. Install Dependencies

```bash
mvn clean install -DskipTests
```

### 3. Start Appium Server

```bash
appium
appium --allow-insecure=uiautomator2:chromedriver_autodownload
```

The server will start at `http://127.0.0.1:4723` by default.

### 4. Start Android Emulator or Connect Device

```bash
# List available emulators
emulator -list-avds

# Start an emulator
emulator -avd <emulator_name>

# Verify device connection
adb devices
```

---

## ⚙️ Configuration

All test configurations are managed in `src/test/resources/configs/config.properties`:

```properties
# Application Type
executionType=mobileWeb          # Options: mobileWeb, nativeApp
webUrl=https://your-app-url.com  # URL for mobile web testing

# Android Configuration
browserName=chrome               # For mobile web
appPath=app/app-qa-release.apk  # For native app
automationName=UiAutomator2
platformName=Android

# iOS Configuration
browserName=safari               # For mobile web
appPath=app/app-qa-release.app  # For native app
automationName=XCUITest
platformName=iOS

# Appium Server
appiumServer=http://127.0.0.1:4723

# Test Credentials
email=your-email@example.com
password=your-password
```

### Switching Between Platforms

**For Android Testing:**
```properties
platformName=Android
automationName=UiAutomator2
browserName=chrome
```

**For iOS Testing:**
```properties
platformName=iOS
automationName=XCUITest
browserName=safari
```

### Switching Between Native App and Mobile Web

**For Mobile Web:**
```properties
executionType=mobileWeb
browserName=chrome    # or safari for iOS
webUrl=https://your-app-url.com
```

**For Native App:**
```properties
executionType=nativeApp
appPath=app/app-qa-release.apk
```

---

## ▶️ Running Tests

### Run All Tests via Maven

```bash
mvn clean test
```

### Run Tests with TestNG XML

```bash
mvn test -DsuiteXmlFile=src/test/resources/testrunner/testng.xml
```

### Run Specific Test Class

```bash
mvn test -Dtest=LoginTest
```

### Run with Verbose Output

```bash
mvn test -X
```

---

## 📊 Test Reports & Screenshots

### Screenshots

Screenshots are automatically captured and saved to the `screenshots/` directory:

- **On Test Actions** - Manual screenshots during test execution
- **On Test Pass** - `<TestName>_PASSED.png`
- **On Test Failure** - `<TestName>_FAILED.png`
- **On Test Skip** - `<TestName>_SKIPPED.png`

### TestNG Reports

After test execution, reports are generated at:
```
target/surefire-reports/
├── index.html              # HTML report
├── testng-results.xml      # XML results
└── emailable-report.html   # Email-friendly report
```

---

## 🏗 Framework Architecture

### Page Object Model (POM)

The framework follows the Page Object Model design pattern:

```
┌─────────────────┐     ┌─────────────────┐     ┌─────────────────┐
│   Test Class    │────▶│   Page Object   │────▶│  Appium Driver  │
│  (LoginTest)    │     │  (LoginPage)    │     │                 │
└─────────────────┘     └─────────────────┘     └─────────────────┘
         │                       │
         ▼                       ▼
┌─────────────────┐     ┌─────────────────┐
│   BaseTest      │     │  DriverFactory  │
│ (Setup/Teardown)│     │ (Driver Init)   │
└─────────────────┘     └─────────────────┘
```

### Key Components

| Component | Description |
|-----------|-------------|
| **BaseTest** | Initializes driver, loads config, provides common test setup/teardown |
| **DriverFactory** | Manages Appium driver lifecycle for Android/iOS |
| **LoginPage** | Contains web elements and actions for login functionality |
| **TestListener** | TestNG listener for automatic screenshot capture |
| **ScreenshotUtils** | Utility class for capturing and saving screenshots |

### Dual Locator Strategy

The framework supports both native and web locators:

```java
// Native locator
private By emailFieldNative = By.xpath("(//android.widget.EditText)[1]");

// Web locator
private By emailFieldWeb = By.id("login-email");
```

---

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📧 Contact

**Mobile 5th and 4th Group 2025**

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

> **Note**: Make sure to update the `config.properties` file with your test credentials and environment-specific configurations before running tests.

