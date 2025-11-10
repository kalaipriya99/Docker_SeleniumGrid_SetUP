package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import utils.ExtentManager;

public class GoogleTest extends BaseTest {

    @Test
    public void openGoogle() {
        driver.get("https://www.google.com");
        ExtentManager.getTest().log(Status.INFO, "Navigated to Google");
        String title = driver.getTitle();
        if (title == null) title = "";
        ExtentManager.getTest().log(Status.INFO, "Page title: " + title);
        Assert.assertTrue(title.toLowerCase().contains("google"));
        ExtentManager.getTest().log(Status.PASS, "Google title verified successfully");
    }
}
