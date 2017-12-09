package generic;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;

public class BaseTest implements IAutoConstant{

	public static WebDriver driver;


	static{
		System.setProperty(GECKO_KEY, GECKO_VALUE);
		//System.setProperty(CHROME_KEY, CHROME_VALUE)
	}
	@BeforeMethod
	public void openApplication(){

		driver= new FirefoxDriver();
		//	driver.get("http://localhost/login.do");
		String url=Lib.getProperty(CONFIG_PATH, "URL");
		driver.get(url);
		/*	String ITO=Lib.getProperty(CONFIG_PATH, "implicitTimeOut");
		int timeoutPeriod=Integer.parseInt(ITO);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);*/

		String ITO = Lib.getProperty(CONFIG_PATH, "ImplicitTimeOut");
		int timeoutPeriod = Integer.parseInt(ITO);
		driver.manage().timeouts().implicitlyWait(timeoutPeriod, TimeUnit.SECONDS);

	}
	public void closeApplication(){
		driver.close();
	}
	//create takes screenshot method in this class because already web driver is available here instead of cretaing in Lib

	public void takeScreenshot(String testname){

		Date d= new Date();
		String currentdate=d.toString().replaceAll(":", "_");

		TakesScreenshot ts= (TakesScreenshot) driver;
		File srcFile= ts.getScreenshotAs(OutputType.FILE);
		File destFIle= new File(".\\screenshot"+currentdate+"\\"+testname+"_screenshot.png");

		try {
			FileUtils.copyFile(srcFile, destFIle);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


