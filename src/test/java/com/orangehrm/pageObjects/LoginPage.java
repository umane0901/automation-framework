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
		WaitUtil.waitForVisibility(loginBtn, Config.SMALL_PAUSE);
		userNameTxb.sendKeys(username);
		passwordTxb.sendKeys(password);
		LoggerUtil.logMessage("Username password entered in the respective field.");
		loginBtn.click();
	}
}