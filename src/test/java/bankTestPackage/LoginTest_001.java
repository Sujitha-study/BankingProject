package bankTestPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import bankingPagePackage.LoginPage;

public class LoginTest_001 extends BaseClass {
	

@Test(dataProvider = "Testdata")
public void logindetails(String username,String password) throws InterruptedException
{
	String status;
	LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	log.info("Calling login methods for data");
	status = loginPage.loginDetails(username,password);
	System.out.println(status);
	
}

}
