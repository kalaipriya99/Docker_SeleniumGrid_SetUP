package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import utils.ExtentManager;
import utils.Utils;

import java.time.Duration;

public class GoogleTest extends BaseTest {

    @Test
    public void openGoogle() throws Exception {
        // Navigate to Google
        driver.get("https://www.google.com");
        ExtentManager.getTest().log(Status.INFO, "Navigated to Google");

        // Screenshot after page load
        String screenshotPath = Utils.takeScreenshot(driver, "GoogleHomePage");
        ExtentManager.getTest().addScreenCaptureFromPath(screenshotPath, "Google Home Page");

        // Verify title
        String title = driver.getTitle();
        if (title == null) title = "";
        ExtentManager.getTest().log(Status.INFO, "Page title: " + title);
        Assert.assertTrue(title.toLowerCase().contains("google"), "Title does not contain 'Google'");
        ExtentManager.getTest().log(Status.PASS, "Google title verified successfully");

        // Enter text in search box
        driver.findElement(By.name("q")).sendKeys("Selenium WebDriver");
        screenshotPath = Utils.takeScreenshot(driver, "GoogleSearchInput");
        ExtentManager.getTest().log(Status.INFO, "Entered text in search box");
        ExtentManager.getTest().addScreenCaptureFromPath(screenshotPath, "Google Search Input");

        // Press Enter and wait for results
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        ExtentManager.getTest().log(Status.INFO, "Pressed Enter key");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search"))); // Wait for search results container

        screenshotPath = Utils.takeScreenshot(driver, "GoogleSearchResult");
        ExtentManager.getTest().log(Status.INFO, "Google search results loaded");
        ExtentManager.getTest().addScreenCaptureFromPath(screenshotPath, "Google Search Result");
    }
}