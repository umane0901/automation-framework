package com.orangehrm.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.orangehrm.config.Config;
import com.orangehrm.utils.ConfigReader;
import com.orangehrm.utils.DriverManager;
import com.orangehrm.utils.LoggerUtil;
import com.orangehrm.utils.WaitUtil;

public class BaseClass {
	
	public WebDriver driver;
	public static String userName;
	public static String password;
	public static String url;
	public static String browser;
	
	
	public BaseClass() {
		url = ConfigReader.get("url");
		browser = ConfigReader.get("browser");
		userName = ConfigReader.get("userName");
		password = ConfigReader.get("password");
	}
	
	@BeforeClass
	public void setup() throws InterruptedException {
		DriverManager.init(browser);
		driver = DriverManager.getDriver();
		LoggerUtil.logMessage(browser + " Browser has been launched successfully.");
		driver.navigate().to(url);
		LoggerUtil.logMessage("Application URL has been opened successfully in the browser.");
	}
	
	@AfterClass
	public void cleanup() {
		DriverManager.closeBrowser();
		LoggerUtil.logMessage("Browser has been closed.");
	}

}
