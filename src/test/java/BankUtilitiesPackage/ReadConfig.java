package BankUtilitiesPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
	
	Properties prop;
	public ReadConfig() throws IOException
	{
		File src=new File("./Configuration/config.properties");
		FileInputStream fl =new FileInputStream(src);
		prop =new Properties();
		prop.load(fl);
		
	}
	
	public String getURL()
	{
		return prop.getProperty("baseURL");
		
	}

}
