import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class HandleScreenShotsTest {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void initialSetup(){
        WebDriverManager.chromedriver().setup();
        driver.manage().window().maximize();
    }

    @Test
    public void loadURL(){
        driver.get("https://github.com/Channa96");
    }

    @Test
    public void screenShotImg() throws IOException {
        driver.get("https://github.com/Channa96");
        //Casting driver object into TakeScreenShot method and save it into SS object
        TakesScreenshot SS = (TakesScreenshot)driver;
        //Save the screenshot into file object
        File file = SS.getScreenshotAs(OutputType.FILE);
        //Saving the screenshot into local folder
        FileUtils.copyFile(file,new File("./ScreenShots/TestImg1.jpg"));
    }

    @Test
    public void screenShotString() throws IOException {
        driver.get("https://github.com/Channa96/CypressAutomation");
        //Casting driver object into TakeScreenShot method and save it into SS object
        TakesScreenshot SS = (TakesScreenshot)driver;
        //Save the screenshot into string object
        String ScrShot = SS.getScreenshotAs(OutputType.BASE64);
        //Converting String object into byte array
        byte[] bytArr = Base64.getDecoder().decode(ScrShot);
        //Saving the screenshot into local folder
        FileOutputStream fos = new FileOutputStream(new File("./ScreenShots/TestImg2.png"));
        fos.write(bytArr);
        fos.close();
    }

    @Test
    public void screenShotByt() throws IOException {
        driver.get("https://github.com/Channa96/SeleniumTest");
        //Casting driver object into TakeScreenShot method and save it into SS object
        TakesScreenshot SS = (TakesScreenshot)driver;
        //Save the screenshot into file object
        byte[] Arr = SS.getScreenshotAs(OutputType.BYTES);
        //Saving the screenshot into local folder
        FileOutputStream fos = new FileOutputStream(new File("./ScreenShots/TestImg3.png"));
        fos.write(Arr);
        fos.close();
        driver.quit();
    }


}
