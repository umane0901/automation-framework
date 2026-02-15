package com.orangehrm.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orangehrm.config.Config;
import com.orangehrm.utils.DriverManager;
import com.orangehrm.utils.LoggerUtil;
import com.orangehrm.utils.WaitUtil;

public class LoginPage {
	public LoginPage() {
		PageFactory.initElements(DriverManager.getDriver(), this);
	}

	@FindBy(name = "username")
	public WebElement userNameTxb;

	@FindBy(name = "password")
	public WebElement passwordTxb;

	@FindBy(xpath = "//button")
	public WebElement loginBtn;

	@FindBy(css = ".oxd-userdropdown-name")
	public WebElement userProfile;

	@FindBy(xpath = "//a[text()='Logout']")
	public WebElement logoutLnk;

	public void enterUsername(String username) {
		userNameTxb.sendKeys(username);
	}

	public void enterPassword(String password) {
		passwordTxb.sendKeys(password);
	}

	public void clickOnLoginButton() {
		loginBtn.click();
	}

	public void loginToApp(String username, String password) {
		WaitUtil.waitForElementToBeVisible(loginBtn, Config.SMALL_PAUSE);
		userNameTxb.sendKeys(username);
		passwordTxb.sendKeys(password);
		LoggerUtil.logMessage("Username password entered in the respective field.");
		loginBtn.click();
	}

	public boolean logOutOrangeHRM() {
		boolean status = false;
		if(WaitUtil.isPresent(userProfile, Config.XSMALL_PAUSE)) {
			userProfile.click();
			logoutLnk.click();
	
			if (WaitUtil.isPresent(loginBtn, Config.SMALL_PAUSE)) {
				status = true;
			}
		}
		return status;
	}
}