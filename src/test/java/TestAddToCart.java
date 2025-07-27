import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

public class TestAddToCart {
    WebDriver driver = new ChromeDriver();
    String productName;
    String searchProduct;

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
        driver.findElement(By.xpath("//input[@id='gh-ac']")).sendKeys(searchProduct);
        driver.findElement(By.id("gh-search-btn")).click();
    }

    @Test
    public void extractData() throws IOException {
        File excelFile = new File("src/test/resources/TestFile.xlsx");
        FileInputStream channa = new FileInputStream(excelFile);
        Workbook testWorkBook = new XSSFWorkbook(channa);
        Sheet sheet = testWorkBook.getSheet("Sheet1");

        int rowCount = sheet.getPhysicalNumberOfRows();
        int columnCount = sheet.getRow(0).getLastCellNum();

        searchProduct = sheet.getRow(1).getCell(0).getStringCellValue();
        productName = sheet.getRow(1).getCell(2).getStringCellValue();

        testWorkBook.close();
        channa.close();
    }

    String parentWindow = driver.getWindowHandle();

    @Test
    public void selectItem() throws InterruptedException {

        Thread.sleep(3000);

        //Passing xpath text value through a variable

        driver.findElement(By.xpath("//span[text()='" + productName + "']")).click();
        Thread.sleep(3000);
    }

    @Test
    public void addToCart() throws InterruptedException {
        Set<String> childWindow = driver.getWindowHandles();
        Thread.sleep(3000);
        for (String test : childWindow) {
            if (!test.equals(parentWindow)) {
                driver.switchTo().window(test);
                driver.findElement(By.id("atcBtn_btn_1")).click();
                Thread.sleep(5000);
            }
        }
    }

    @Test
    public void viewCart()throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement SeeInCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='See in cart'])[2]")));

        SeeInCart.click();
        Thread.sleep(5000);

        String HeaderValue = "Shopping cart";
        String actualPgHeader = driver.findElement(By.xpath("//h1[text()='" + HeaderValue + "']")).getText();
        Assert.assertEquals(actualPgHeader, "Shopping cart");
        //Validation for verify the navigation to the cart
        System.out.println("Text Validation Passed: " + actualPgHeader);
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
