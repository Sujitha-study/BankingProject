package bankingPagePackage;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
public class LoginPage {
	WebDriver driver;
public LoginPage(WebDriver driver)
{
	this.driver= driver;
}
@FindBy (how= How.NAME,using ="uid")
@CacheLookup WebElement username;

@FindBy (how=How.NAME,using ="password")
@CacheLookup WebElement password;

@FindBy (how= How.NAME,using ="btnLogin")
@CacheLookup WebElement Login;

public String loginDetails(String uname,String pword) throws InterruptedException
{
	String ret=null;
	String managerid = null;
	username.sendKeys(uname);
	password.sendKeys(pword);
	
	Login.click();
	//WebDriverWait wait=new WebDriverWait(driver,3);
	//if(wait.until(ExpectedConditions.alertIsPresent())==null)
	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	if(!isAlertPresent())
	{
		managerid=driver.findElement(By.xpath("//table//tr[@class='heading3']")).getText();
		System.out.println(managerid);
	if(driver.getTitle().contains("Guru99 Bank Manager HomePage") && managerid.contains(uname))
	{
		
		System.out.println("login successful");
	
	 ret ="login successful";
	}
	else if (driver.getTitle().contains("Guru99 Bank Manager HomePage"))
	{
			System.out.println("logged in but with unknown details");
			ret ="unknown loggin";
			
	}
	}
	else
	{   
		System.out.println("entered login unsuccessful");
		//Alert alt =driver.switchTo().alert();
		//alt.accept();
		System.out.println(driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "Guru99 Bank Manager HomePage");
		System.out.println("login unsuccessful");
		ret ="login unsuccessful";
	}
	System.out.println(uname);
	File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	try {
	FileUtils.copyFile(srcFile, new File("C:/Users/sujia/OneDrive/Desktop/Selenium/Screenshots/Bankdemo"
	+ "ScreenshotsTaken/tests_"+uname+".jpg"));
	} catch (IOException e) {
	e.printStackTrace();
	}
	return ret;
	}
public boolean isAlertPresent() 
{ 
    try 
    { 
        driver.switchTo().alert().accept();;
       
        return true; 
    }   // try 
    catch (NoAlertPresentException Ex) 
    { 
        return false; 
    }   // catch 
}  	


}
