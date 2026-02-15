package com.orangehrm.utils;

import java.time.Duration;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.orangehrm.config.Config;

public class WaitUtil {
	public static Boolean waitForElementToBeVisible(WebElement element, int timeoutInSeconds) {
		Boolean status = false;
		FluentWait<WebDriver> fluentWait;
		try {
			fluentWait = new FluentWait<>(DriverManager.getDriver())
					.withTimeout(Duration.ofSeconds(Config.MEDIUM_PAUSE))
					.pollingEvery(Duration.ofMillis(Config.POLLING_TIME))
					.ignoring(NoSuchElementException.class);

			fluentWait.until(ExpectedConditions.elementToBeClickable(element));
			status = true;
		} catch (Exception e) {
			LoggerUtil.logError("Webelement not visible: " + element.toString());
		}

		return status;
	}

	public static WebElement waitForClickability(WebElement element, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutInSeconds));
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void pause(int milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException("Thread was interrupted during pause", e);
		}
	}

	/**********************************************************************************************
	 * Verifies element is present
	 * 
	 * @param webElement {@link WebElement} - WebElement to wait for visibility
	 * @param timeOut    {@link Integer} - The amount of time in milliseconds to
	 *                   pause.
	 * @return status {@link boolean} - true/false
	 * @author Umesh Mane created February 08, 2026
	 * @version 1.0
	 ***********************************************************************************************/
	public static boolean isPresent(WebElement webElement, int timeOut) {
		boolean status = false;

		waitForElementToBeVisible(webElement, timeOut);
		try {
			status = webElement.isDisplayed();
		} catch (Exception e) {
			LoggerUtil.logError("Webelement is not present: " + webElement.toString());
		}
		return status;
	}

}
