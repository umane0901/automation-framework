package com.orangehrm.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orangehrm.config.Config;
import com.orangehrm.utils.DriverManager;
import com.orangehrm.utils.LoggerUtil;
import com.orangehrm.utils.WaitUtil;

public class DashboardPage {
	
	public DashboardPage() {
		PageFactory.initElements(DriverManager.getDriver(), this);
	}
	
	@FindBy(xpath = "//span[@class='oxd-topbar-header-breadcrumb']/h6")
	public WebElement dashboardHdr;
	
	@FindBy(id = "welcome")
	public WebElement welcomeUserLnk;
	
	@FindBy(xpath = "//span[text()='Admin']")
	public WebElement adminLnk;
	
	@FindBy(xpath  = "//span[normalize-space()='User Management']")
	public WebElement userManagementLnk;
	
	@FindBy(css = ".oxd-topbar-body-nav-tab-link")
	public WebElement usersLnk;
	
	
	public boolean ValidateDashboard() throws InterruptedException {
		boolean status = false;
		if(WaitUtil.isPresent(dashboardHdr, Config.XSMALL_PAUSE)) {
			LoggerUtil.logMessage("Login in to the app is successful.");
			status = true;
		}
		return status;
	}
	
	public void naviteToUsersModule() {
		adminLnk.click();
		LoggerUtil.logMessage("Clicked on Admin link");
		WaitUtil.waitForClickability(userManagementLnk, Config.MEDIUM_PAUSE);
		userManagementLnk.click();
		usersLnk.click();
		LoggerUtil.logMessage("Clicked on User Link");
	}

}
