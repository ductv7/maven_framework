package com.selenium.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.selenium.common.*;
/**
 * @author Tran Viet Duc
 * 
 * @version: 0.1
 *
 */
public class BaseTestUI {

	public WebDriver driver = null;
	
	public Properties pros = null;
	
	public WebElement ele = null;
	
	//private Logger logger = Logger.getLogger(BaseTestUI.class.getName());
	
	BasicProperties bp = new BasicProperties();
	
	public WebDriver  initializeDriver() throws IOException {
		
		pros = bp.load(Global.FileEnv);

		if (pros.getProperty("REMOTE_URL").isEmpty())

		{
			if (pros.getProperty("BROWSER").equalsIgnoreCase("firefox")) {

				System.setProperty("webdriver.gecko.driver",
						Global.DriverFFrepo);
				driver = new FirefoxDriver();

			} else if (pros.getProperty("BROWSER").equalsIgnoreCase("chrome")) {

				System.setProperty("webdriver.chrome.driver",
						Global.DriverChromerepo);
				driver = new ChromeDriver();

			} else {

				driver = new InternetExplorerDriver();
			}

		}

		else {

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setBrowserName(pros.getProperty("BROWSER"));
			capabilities.setPlatform(Platform.fromString(pros
					.getProperty("PLATFORM")));
			
			driver = new RemoteWebDriver(new URL(pros.getProperty("REMOTE_URL")), capabilities);
			
		}
		
		return driver;
		
	}
	
	/*
	private Properties loadObjectRepositories() throws IOException{
		
		pros = new Properties();
		
		FileInputStream fis = new FileInputStream(Global.FileObjRepository);//Code will read object repos

		pros.load(fis);

		return pros;
	}
	/*
	private Properties loadEnvRepositories() throws IOException{
		
		pros = new Properties();
		
		FileInputStream fis = new FileInputStream(Global.FileEnv);//Code will read environment

		pros.load(fis);

		return pros;
	}
		*/
	
	private By getBy(String objPath) {
		pros = bp.load(Global.FileObjRepository);
		By r = null;
		if (pros.getProperty(objPath + ".Id") != null) {
			r = By.id(pros.getProperty(objPath + ".Id"));
		} else if (pros.getProperty(objPath + ".Name") != null) {
			r = By.name(pros.getProperty(objPath + ".Name"));
		} else if (pros.getProperty(objPath + ".TagName") != null) {
			r = By.tagName(pros.getProperty(objPath + ".TagName"));
		} else if (pros.getProperty(objPath + ".LinkText") != null) {
			r = By.linkText(pros.getProperty(objPath + ".LinkText"));
		} else if (pros.getProperty(objPath + ".PartialLinkText") != null) {
			r = By.partialLinkText(pros.getProperty(objPath + ".PartialLinkText"));
		} else if (pros.getProperty(objPath + ".CssSelector") != null) {
			r = By.cssSelector(pros.getProperty(objPath + ".CssSelector"));
		} else if (pros.getProperty(objPath + ".ClassName") != null) {
			r = By.className(pros.getProperty(objPath + ".ClassName"));
		} else if (pros.getProperty(objPath + ".XPath") != null) {
			r = By.xpath(pros.getProperty(objPath + ".XPath"));
		} else if (pros.getProperty(objPath) != null) {
			r = By.id(pros.getProperty(objPath));
		}

		return r;
	}
	
	private Boolean getElement(String objPath) {
		Boolean r = false;
		try {
			ele = driver.findElement(getBy(objPath));
			r = true;
		} catch (Exception e) {
			
		}

		return r;
	}


	
	public void navigateBrowser() throws IOException{
		
		driver = initializeDriver();
				
		driver.get(bp.load(Global.FileEnv).getProperty("URL"));
	
	}		
	
	/**
	 * 
	 * @param objPath: object element
	 * @param fieldName: value will get from excel file
	 * @throws FileNotFoundException 
	 */
	
	public void sendText(String objPath, String fieldName) throws FileNotFoundException{
		
		String text = ExcelData.getCellValueData(fieldName);
		
		driver.findElement(getBy(objPath)).sendKeys(text);
	}
	
	public void click(String objPath) {

		try {
			driver.findElement(getBy(objPath)).click();
		} catch (Exception e) {
			Logger.getLogger(objPath);
		}
	}
	
}