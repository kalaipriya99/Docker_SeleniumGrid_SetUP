package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.ExtentManager;
import com.aventstack.extentreports.Status;
import java.net.URL;
import java.time.Duration;
import java.net.URI;

public class BaseTest {
    protected WebDriver driver;

    
	@BeforeClass
    public void setUp() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--headless=new"); // important for Docker
        options.addArguments("--remote-debugging-port=9222"); // helps DevToolsActivePort issue
        options.setPageLoadTimeout(Duration.ofSeconds(60)); // avoid long hangs on slow pages
        options.setScriptTimeout(Duration.ofSeconds(30));   // script execution timeout
        driver = new RemoteWebDriver(new URI("http://selenium-hub:4444").toURL(), options);
        // Implicit wait (optional)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
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
