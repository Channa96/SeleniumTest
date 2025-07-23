import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.Set;

public class TestAddToCart {

    public static void main(String args[]) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.ebay.com/");

        driver.findElement(By.xpath("//input[@id='gh-ac']")).sendKeys("Iphone 16");
        driver.findElement(By.id("gh-search-btn")).click();

        //Window handling section
        String parentWindow = driver.getWindowHandle(); //Saving Parent window
        Thread.sleep(3000);
        driver.findElement(By.xpath("//span[text()='Apple iPhone 16 A3081 Spectrum Only 128GB Pink Very Good']")).click();
        Thread.sleep(3000);
        Set<String> childWindow = driver.getWindowHandles(); //Accessing the child window
        Thread.sleep(3000);
        for (String test:childWindow) {
            if(!test.equals(parentWindow)){
                driver.switchTo().window(test); //Switch to child window
                driver.findElement(By.id("atcBtn_btn_1")).click();
                Thread.sleep(5000);
            }
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement SeeInCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='See in cart'])[2]")));

        SeeInCart.click();
        Thread.sleep(5000);

        String actualPgHeader = driver.findElement(By.xpath("//h1[text()='Shopping cart']")).getText();
        Assert.assertEquals(actualPgHeader, "Shopping cart");
        System.out.println("Text Validation Passed: "+actualPgHeader);
        driver.close(); //Close the child window

        driver.switchTo().window(parentWindow); // Switch back to parent window

        //Page scroll using javascript(Scroll to top)
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0)");
        Thread.sleep(5000);

        driver.findElement(By.xpath("//input[@id='gh-ac']")).clear();
        driver.findElement(By.xpath("//input[@id='gh-ac']")).sendKeys("Channa");

        driver.findElement(By.id("gh-search-btn")).click();
        Thread.sleep(5000);
        driver.quit();

    }

}
