import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HandleFramesTest {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void initialSetup(){
        WebDriverManager.chromedriver().setup();
        driver.manage().window().maximize();
    }

    @Test
    public void loadURL() throws InterruptedException {
        driver.get("https://www.hyrtutorials.com/p/frames-practice.html");
        Thread.sleep(4000);
        driver.findElement(By.id("name")).sendKeys("Channa");
    }

    @Test
    public void SimpleFrame() throws InterruptedException {
        //Navigate to the iframe
        driver.switchTo().frame("frm1");
        //Accessing the dropdown
        Select CourseNameDD = new Select(driver.findElement(By.id("course")));
        CourseNameDD.selectByVisibleText("Java");
        Thread.sleep(4000);
        driver.switchTo().defaultContent();
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("Thisal");

    }

    @Test
    public void NestedFrames() throws InterruptedException {
        //Navigate to the first frame
        driver.switchTo().frame("frm3");
        //Navigate to nested frame
        driver.switchTo().frame("frm2");
        driver.findElement(By.id("firstName")).sendKeys("Thisal");
        driver.findElement(By.id("lastName")).sendKeys("Kodithuwakku");
        //Navigate back to parent ifgrame.Not the main page
        driver.switchTo().parentFrame();
        driver.findElement(By.id("name")).sendKeys("Thisal");
        //Navigate back to main page
        driver.switchTo().defaultContent();
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("Channa");

    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
