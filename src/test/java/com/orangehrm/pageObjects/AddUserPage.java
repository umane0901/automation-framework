package com.orangehrm.pageObjects;

import java.util.List;
import java.util.Map;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.orangehrm.config.Config;
import com.orangehrm.utils.DriverManager;
import com.orangehrm.utils.LoggerUtil;
import com.orangehrm.utils.WaitUtil;

public class AddUserPage {
		@FindBy(xpath = "//h5[text()='System Users']")
		public WebElement systemUserInfoFrm;
		
		@FindBy(xpath = "//button[normalize-space()='Search']")
		public WebElement searchBtn;
		
		@FindBy(xpath = "//button[normalize-space()='Add']")
		public WebElement addBtn;
		
		@FindBy(xpath = "//div[@class='oxd-table-card']/div[@role='row']/div[4]/div")
		public List<WebElement> employeeNameLst;
		
		@FindBy(xpath = "(//div[@class='oxd-select-text-input'])[1]")
		public WebElement roleSelect;

		@FindBy(xpath = "(//div[@class='oxd-select-text-input'])[2]")
		public WebElement statusSelect;
		
		@FindBy(xpath = "//div[@role='option']/span")
		public List<WebElement> userRoleLst;
		
		@FindBy(xpath = "//div[@role='option']/span")
		public List<WebElement> statusLst;
		
		@FindBy(xpath = "//input[@placeholder='Type for hints...']")
		public WebElement employeeNameTxb;
		
		@FindBy(id = "systemUser_userName")
		public WebElement addUserNameTxb;
		
		@FindBy(id = "systemUser_password")
		public WebElement newPasswordTxb;
		
		@FindBy(id = "systemUser_confirmPassword")
		public WebElement confirmPasswordTxb;
		
		@FindBy(id = "btnSave")
		public WebElement saveBtn;
		
		public AddUserPage() {
			PageFactory.initElements(DriverManager.getDriver(), this);
		}

		public boolean validateAddUserPage() {
			boolean status = false;
			WaitUtil.waitForElementToBeVisible(systemUserInfoFrm, Config.XSMALL_PAUSE);
			if(systemUserInfoFrm.isDisplayed())
				status = true;
			
			LoggerUtil.logMessage("Navigated on User page.");
			return status;
		}
		
		public boolean setUserDetails(Map<String, String> testData) {
			boolean status = false;
			WaitUtil.pause(5000);
			WaitUtil.waitForElementToBeVisible(employeeNameLst.get(0), Config.SMALL_PAUSE);
			String employeeName = employeeNameLst.get(0).getText();
			LoggerUtil.logMessage(employeeName);
			
			
			addBtn.click();
			LoggerUtil.logMessage("Clicked on Add button on User Page");
			WaitUtil.waitForElementToBeVisible(roleSelect, Config.SMALL_PAUSE);
			roleSelect.click();
			for(WebElement role : userRoleLst) {
				if(role.getText().equalsIgnoreCase(testData.get("UserRole"))) {
					role.click();
					break;
				}
			}
			LoggerUtil.logMessage("User Role is selected");
			
			employeeNameTxb.sendKeys(employeeName);
			employeeNameTxb.sendKeys(Keys.TAB);
			addUserNameTxb.sendKeys(testData.get("AddUserName"));
	
			
			newPasswordTxb.sendKeys(testData.get("NewPassword"));
			confirmPasswordTxb.sendKeys(testData.get("ConfirmPassword"));
			LoggerUtil.logMessage("User details set successfully.");
			saveBtn.click();

			if (searchBtn.isDisplayed()) {
				LoggerUtil.logMessage("Users added successfully.");
				status = true;
			} else {
				LoggerUtil.logMessage("Users not added successfully.");
			}
			return status;
		}

		
		
}
