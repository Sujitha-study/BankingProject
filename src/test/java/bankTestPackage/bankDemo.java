package bankTestPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import bankingUI.LoginPage;

public class bankDemo {
	WebDriver driver;
@BeforeMethod
public void Browsersetup()
{
	System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver_win32\\chromedriver.exe");

	//Creating an object of ChromeDriver
	 driver = new ChromeDriver();
//	System.setProperty("webdriver.gecko.driver", "C:\\selenium\\geckodriver-v0.28.0-win64\\geckodriver.exe");
	//driver =new FirefoxDriver();
	driver.get("http://www.demo.guru99.com/V4/");
	driver.manage().window().maximize();
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

@Test(dataProvider = "Testdata")
public void logindetails(String username,String password) throws InterruptedException
{
	String status;
	LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	status = loginPage.loginDetails(username,password);
	System.out.println(status);
	
}
@AfterMethod
public void quittingdriver()
{
	driver.quit();
}
}
