import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SearchTest {

    @Test
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Newfolder\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // Open Google homepage
        driver.get("https://www.google.com");

        // Locate the search box using its name attribute
        WebElement searchBox = driver.findElement(By.name("q"));

        // Enter the search term "Selenium WebDriver"
        searchBox.sendKeys("Selenium WebDriver");

        // Submit the search
        searchBox.submit();

        // Wait for results and print page title
        System.out.println("Page Title after search: " + driver.getTitle());

        // Close the browser
        driver.quit();
    }


}
