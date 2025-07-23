import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;
import java.util.Set;

public class TestAddToCart {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void initialSetup(){
        WebDriverManager.chromedriver().setup();
        driver.manage().window().maximize();
    }

    @Test
    public void loadURL(){
        driver.get("https://www.ebay.com/");
    }

    @Test
    public void searchProduct(){
        driver.findElement(By.xpath("//input[@id='gh-ac']")).sendKeys("Iphone 16");
        driver.findElement(By.id("gh-search-btn")).click();
    }

    @Test
    public void addToCart() throws InterruptedException {

        String parentWindow = driver.getWindowHandle();
        Thread.sleep(3000);

        //Passing xpath text value through a variable
        String productName = "Apple iPhone 16 A3081 Spectrum Only 128GB Pink Very Good" ;
        driver.findElement(By.xpath("//span[text()='"+ productName +"']")).click();
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement SeeInCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='See in cart'])[2]")));

        SeeInCart.click();
        Thread.sleep(5000);

        String HeaderValue = "Shopping cart";
        String actualPgHeader = driver.findElement(By.xpath("//h1[text()='"+HeaderValue+"']")).getText();
        Assert.assertEquals(actualPgHeader, "Shopping cart");
        System.out.println("Text Validation Passed: "+actualPgHeader);
        driver.close();

        driver.switchTo().window(parentWindow);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0)");
        Thread.sleep(5000);

        driver.findElement(By.xpath("//input[@id='gh-ac']")).clear();
        driver.findElement(By.xpath("//input[@id='gh-ac']")).sendKeys("Channa");

        driver.findElement(By.id("gh-search-btn")).click();
        Thread.sleep(5000);

    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

}
