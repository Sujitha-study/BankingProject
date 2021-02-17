package bankTestPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;


import BankUtilitiesPackage.ReadConfig;

public class BaseClass {

	WebDriver driver;
	Logger log;
	@BeforeClass	
	@Parameters("Browser")
	public void Browsersetup(String Browser) throws IOException
	{
		ReadConfig rd =new ReadConfig();
		log =Logger.getLogger("testing");
		PropertyConfigurator.configure("log4j.properties");
		if(Browser.equalsIgnoreCase("chrome"))
		{
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
		System.out.println("Chrome started ");
		log.info("Chrome selected");
		//Creating an object of ChromeDriver
		 driver = new ChromeDriver();
		 log.info("Chrome initiated");
		}
//		System.setProperty("webdriver.gecko.driver", "C:\\selenium\\geckodriver-v0.28.0-win64\\geckodriver.exe");
		//driver =new FirefoxDriver();
		driver.get(rd.getURL());
		log.info("url loaded");
		//driver.get("http://www.demo.guru99.com/V4/");
		driver.manage().window().maximize();
		log.info("window maximised");
		
	}
	@AfterClass
	public void Teardown()
	{
		log.info("Driver quit");
		driver.quit();
	}
	@DataProvider(name="Testdata")
	public Object[][] getdetailsfromexcel() throws IOException
	{
		XSSFWorkbook wb;
		// XSSFSheet sheet;
		File src = new File("C:\\Users\\sujia\\OneDrive\\Desktop\\Selenium\\Selenium-Webdriver\\Data_driven\\Gurulogindetails.xlsx");
		 FileInputStream fis = new FileInputStream(src);
		 wb = new XSSFWorkbook(fis);
		int rowcount =wb.getSheetAt(0).getLastRowNum();
		rowcount=rowcount+1;
		System.out.println(rowcount);
		Object[][] searchitems =new Object[rowcount][2];
		System.out.println("object created");
		for(int i=0;i<rowcount;i++)
		{
			searchitems[i][0]=wb.getSheetAt(0).getRow(i).getCell(0).getStringCellValue();
			searchitems[i][1]=wb.getSheetAt(0).getRow(i).getCell(1).getStringCellValue();
			System.out.println(searchitems[i][0]+"  "+searchitems[i][1]);
		}
		wb.close();
		return searchitems;
	}
}
