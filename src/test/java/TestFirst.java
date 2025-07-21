import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TestFirst {
    public static void main (String[] args){
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.get("https://web.facebook.com/r.php?entry_point=login");

        driver.manage().window().maximize();

        driver.findElement(By.name("firstname")).sendKeys("Channa");
        driver.findElement(By.name("lastname")).sendKeys("Jayawickrama");

        driver.findElement(By.id("day")).sendKeys("9");
        driver.findElement(By.id("month")).sendKeys("Feb");
        driver.findElement(By.id("year")).sendKeys("1996");

        WebElement maleRadio = driver.findElement(By.xpath("//input[@type='radio' and @name='sex' and @value='2']"));
        maleRadio.click();

        //Thread.sleep(3000);

        driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("email/mobile number");
        driver.findElement(By.xpath("//input[@id='password_step_input']")).sendKeys("Password");

        driver.findElement(By.xpath("//button[@name='websubmit']")).click();
    }

}
