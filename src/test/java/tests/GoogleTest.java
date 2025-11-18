package tests;

import java.awt.AWTException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import utils.ExtentManager;
import utils.Utils;

public class GoogleTest extends BaseTest {

    @Test
    public void openGoogle() throws Exception {
        driver.get("https://www.google.com");
        String screenshotPath = Utils.takeScreenshot(driver, "GoogleTitleTest");
        ExtentManager.getTest().log(Status.INFO, "Navigated to Google");
        ExtentManager.getTest().addScreenCaptureFromPath(screenshotPath, "Navigated to Google");
        String title = driver.getTitle();
        if (title == null) title = "";
        ExtentManager.getTest().log(Status.INFO, "Page title: " + title);
        Assert.assertTrue(title.toLowerCase().contains("google"));
        ExtentManager.getTest().log(Status.PASS, "Google title verified successfully");
        driver.findElement(By.name("q")).sendKeys("Selenium WebDriver");
        screenshotPath = Utils.takeScreenshot(driver, "GoogleTitleTest");
        ExtentManager.getTest().log(Status.INFO, "Entered text in search box");
        ExtentManager.getTest().addScreenCaptureFromPath(screenshotPath, "Entered text in search box");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        ExtentManager.getTest().log(Status.INFO, "Pressed Enter key");
        Thread.sleep(3000); // Wait for results to load
        screenshotPath = Utils.takeScreenshot(driver, "GoogleSearchResult");
        ExtentManager.getTest().log(Status.INFO, "Google search result");
        ExtentManager.getTest().addScreenCaptureFromPath(screenshotPath, "Google search result");
        
        
    }

	
}
