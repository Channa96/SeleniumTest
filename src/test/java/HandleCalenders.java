import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.text.*;
import java.util.*;

public class HandleCalenders {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void initialSetup(){
        WebDriverManager.chromedriver().setup();
        driver.manage().window().maximize();
    }

    @Test
    public void loadURL(){
        driver.get("https://www.hyrtutorials.com/p/calendar-practice.html");
    }

    @Test
    public void callDates() throws Exception {
        driver.findElement(By.id("second_date_picker")).click();
        selectDate(driver, "05/Aug/2025", "dd/MMM/yyyy");

        Thread.sleep(5000);
        driver.findElement(By.id("second_date_picker")).click();
        selectDate(driver, "05/Feb/2021", "dd/MMM/yyyy");

        Thread.sleep(5000);
        driver.findElement(By.id("second_date_picker")).click();
        selectDate(driver, "15/Mar/2027", "dd/MMM/yyyy");
    }

    @Test
    public void selectDate(WebDriver driver,String targetDate, String dateFormat)throws Exception{
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat targetDateFormat = new SimpleDateFormat(dateFormat);
        Date formattedTargetDate;
        try {
            targetDateFormat.setLenient(false);
            formattedTargetDate = targetDateFormat.parse(targetDate);
            calendar.setTime(formattedTargetDate);

            int targetDay = calendar.get(Calendar.DAY_OF_MONTH);
            int targetMonth = calendar.get(Calendar.MONTH);
            int targetYear = calendar.get(Calendar.YEAR);

            String actualDate = driver.findElement(By.className("ui-datepicker-title")).getText();
            calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(actualDate));

            int actualMonth = calendar.get(Calendar.MONTH);
            int actualYear = calendar.get(Calendar.YEAR);

            while(targetMonth < actualMonth || targetYear < actualYear) {
                driver.findElement(By.className("ui-datepicker-prev")).click();
                actualDate = driver.findElement(By.className("ui-datepicker-title")).getText();
                calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(actualDate));

                actualMonth = calendar.get(Calendar.MONTH);
                actualYear = calendar.get(Calendar.YEAR);
            }

            while(targetMonth > actualMonth || targetYear > actualYear) {
                driver.findElement(By.className("ui-datepicker-next")).click();
                actualDate = driver.findElement(By.className("ui-datepicker-title")).getText();
                calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(actualDate));

                actualMonth = calendar.get(Calendar.MONTH);
                actualYear = calendar.get(Calendar.YEAR);
            }

            driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']//td[not(contains(@class,'ui-datepicker-other-month'))]/a[text()="+targetDay+"]")).click();

        } catch (ParseException e) {
            throw new Exception("Invalid date is provided, please check input date");
        }
    }

    @AfterTest
    public void tearDown()
    {
        driver.quit();
    }
}
