package com.orangehrm.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShotUtil {
	public static String captureScreenshot(WebDriver driver, String name) {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = "target/screenshots/" + name + ".png";

		try {
			FileUtils.copyFile(src, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

}
