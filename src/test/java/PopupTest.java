import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class PopupTest {

    WebDriver driver;

    @BeforeTest
    public void initialSetup() {
        WebDriverManager.chromedriver().setup();

        // Set Chrome preferences for permission popups
        Map<String, Object> prefs = new HashMap<>();
        Map<String, Object> profile = new HashMap<>();
        Map<String, Object> contentSettings = new HashMap<>();

        // Permissions: 0 = Default, 1 = Allow, 2 = Block
        //contentSettings.put("media_stream", 1);  // Allow camera/microphone
        contentSettings.put("geolocation", 2);  // Optional: allow location
        //contentSettings.put("notifications", 2);  // Optional: block notifications

        profile.put("managed_default_content_settings", contentSettings);
        prefs.put("profile", profile);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void permissionPopupTest() throws InterruptedException {
        //driver.get("https://webcamtests.com/");
        //driver.get("https://mictests.com/");
        //driver.get("https://web-push-book.gauntface.com/demos/notification-examples/");
        driver.get("https://whatmylocation.com/#google_vignette");
        //Thread.sleep(5000);
        //driver.findElement(By.id("webcam-launcher")).click();
        //Thread.sleep(10000); // Wait to see webcam popup effect (if any)
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
