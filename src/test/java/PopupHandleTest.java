import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PopupHandleTest {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void initialSetup(){
        WebDriverManager.chromedriver().setup();
        driver.manage().window().maximize();
    }

    @Test
    public void loadURL(){
        driver.get("https://www.hyrtutorials.com/p/alertsdemo.html");
    }

    @Test
    public void SimpleAlert() throws InterruptedException {
        driver.findElement(By.id("alertBox")).click();
        Alert SimpleA = driver.switchTo().alert();
        System.out.println(SimpleA.getText());
        Thread.sleep(2000);
        SimpleA.accept();
        Thread.sleep(2000);
    }

    @Test
    public void ConfirmantionAlert() throws InterruptedException {
        driver.findElement(By.id("confirmBox")).click();
        Alert ConfirmationA = driver.switchTo().alert();
        System.out.println(ConfirmationA.getText());
        Thread.sleep(2000);
        ConfirmationA.accept();
        Thread.sleep(2000);
        System.out.println(driver.findElement(By.id("output")).getText());
    }

    @Test
    public void PromptAlert() throws InterruptedException {
        driver.findElement(By.id("promptBox")).click();
        Alert PromptA = driver.switchTo().alert();
        System.out.println(PromptA.getText());
        Thread.sleep(2000);
        PromptA.sendKeys("Channa");
        PromptA.accept();
        Thread.sleep(2000);
        System.out.println(driver.findElement(By.id("output")).getText());
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

}
