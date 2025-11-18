package utils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static String takeScreenshot(WebDriver driver, String screenshotName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        // Add timestamp to avoid overwriting screenshots
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String path = System.getProperty("user.dir") + "/screenshots/" + screenshotName + "_" + timestamp + ".png";

        File destination = new File(path);
        try {
            Files.createDirectories(destination.getParentFile().toPath());
            Files.copy(source.toPath(), destination.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}