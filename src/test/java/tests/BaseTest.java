package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import utils.ExtentManager;
import com.aventstack.extentreports.Status;

import java.net.URL;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @Parameters({"platformName"})
    @BeforeClass
    public void setUp(String platformName) throws Exception {
        // Hub URL: Docker network or fallback
        String hubUrl = System.getenv("HUB_URL");
        if (hubUrl == null || hubUrl.isEmpty()) {
            hubUrl = "http://selenium-hub:4444/wd/hub";
        }

        if (platformName.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless=new");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--disable-dev-shm-usage");
            chromeOptions.addArguments("--window-size=1920,1080");
            chromeOptions.addArguments("--remote-allow-origins=*");
            chromeOptions.setPageLoadTimeout(Duration.ofSeconds(120));
            chromeOptions.setScriptTimeout(Duration.ofSeconds(60));

            driver = new RemoteWebDriver(new URL(hubUrl), chromeOptions);

        } else if (platformName.equalsIgnoreCase("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--headless");
            firefoxOptions.addArguments("--no-sandbox");
            firefoxOptions.addArguments("--disable-dev-shm-usage");
            firefoxOptions.addArguments("--width=1920");
            firefoxOptions.addArguments("--height=1080");
            firefoxOptions.setPageLoadTimeout(Duration.ofSeconds(120));
            firefoxOptions.setScriptTimeout(Duration.ofSeconds(60));

            driver = new RemoteWebDriver(new URL(hubUrl), firefoxOptions);

        } else {
            throw new IllegalArgumentException("Unsupported platform: " + platformName);
        }

        // Optional global waits
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(5));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(60));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        // Extent Reports initialization
        ExtentManager.createTest(getClass().getSimpleName());
        ExtentManager.getTest().log(Status.INFO, "Driver initialized for " + getClass().getSimpleName());
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            ExtentManager.getTest().log(Status.INFO, "Browser closed.");
        }
        ExtentManager.flush();
    }
}