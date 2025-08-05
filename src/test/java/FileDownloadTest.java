import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;

public class FileDownloadTest {
    WebDriver driver;
    String FileType = "PDF";

    @BeforeTest
    public void initialSetup(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        HashMap<String, Object> prefs = new HashMap<>();
        prefs.put("plugins.always_open_pdf_externally", true);
        prefs.put("download.default_directory","D:\\SampleDownload");
        options.setExperimentalOption("prefs",prefs);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void DowloadPDFFile() throws InterruptedException {
        driver.get("https://demo.automationtesting.in/FileDownload.html");
        WebElement element = driver.findElement(By.id("pdfbox"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500); // optional wait

        element.sendKeys("Test PDF");
        driver.findElement(By.id("createPdf")).click();
        driver.findElement(By.id("pdf-link-to-download")).click();

        driver.quit();

    }
}
