import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Set;

public class TestAddToCart {

    public static void main(String args[]) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.ebay.com/");

        driver.findElement(By.xpath("//input[@id='gh-ac']")).sendKeys("Iphone 16");
        driver.findElement(By.id("gh-search-btn")).click();

        String parentWindow = driver.getWindowHandle();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//span[text()='Apple iPhone 16 A3081 Spectrum Only 128GB Pink Very Good']")).click();
        Thread.sleep(3000);
        Set<String> childWindow = driver.getWindowHandles();
        Thread.sleep(3000);
        for (String test:childWindow) {
            if(!test.equals(parentWindow)){
                driver.switchTo().window(test);
                driver.findElement(By.id("atcBtn_btn_1")).click();
                Thread.sleep(5000);
            }
        }
    }

}
