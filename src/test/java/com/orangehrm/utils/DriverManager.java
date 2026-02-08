package com.orangehrm.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverManager {
	
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	public static void init(String browser) {
		if(driver.get() == null) {
			switch(browser.toLowerCase()) {
				case "chrome":
					driver.set(new ChromeDriver());
				break;
				case "edge":
					driver.set(new EdgeDriver());
			}
		}
	}
	
	 public static WebDriver getDriver() {
		 return driver.get();
	 }
	 
	 public static void closeBrowser() {
		driver.get().quit();
	}
	
}
