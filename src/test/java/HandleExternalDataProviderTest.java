import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HandleExternalDataProviderTest {
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
        public void extractData() throws IOException {
            File excelFile = new File("src/test/resources/TestFile.xlsx");
            FileInputStream channa = new FileInputStream(excelFile);
            Workbook testWorkBook = new XSSFWorkbook(channa);
            Sheet sheet = testWorkBook.getSheet("Sheet1");

            int rowCount = sheet.getPhysicalNumberOfRows();
            int columnCount = sheet.getRow(0).getLastCellNum();

            for (int i=1; i< rowCount; i++){
                for (int j=0; j<columnCount; j++){
                    System.out.println(sheet.getRow(i).getCell(j).getStringCellValue());
                }
            }

            testWorkBook.close();
            channa.close();
        }


}
