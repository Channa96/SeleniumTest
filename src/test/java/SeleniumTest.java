import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SeleniumTest {

    @Test
    public static void main(String[] args)
    {
            // Set up the path to chromedriver
            System.setProperty("webdriver.chrome.driver", "C:\\Newfolder\\ChromeDriver\\chromedriver.exe");

            // Initialize WebDriver
            WebDriver driver = new ChromeDriver();

            // Open Google homepage
            driver.get("https://www.google.com");

            // Print the page title
            System.out.println("Page Title: " + driver.getTitle());

            // Close the browser
            driver.quit();

    }

}
