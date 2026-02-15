package com.orangehrm.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orangehrm.pageObjects.DashboardPage;
import com.orangehrm.pageObjects.LoginPage;
import com.orangehrm.utils.ExcelUtil;

public class OrangeHRMTest_4 extends BaseClass{
	protected static String className;	
	protected static HashMap<Integer, HashMap<String, String>> testData;
	
	@DataProvider
	public Iterator<Object[]> getData(){
		className = this.getClass().getSimpleName();
		testData = ExcelUtil.getTestData(className);
		ArrayList<Object[]> dataProvider = new ArrayList<Object[]>();
		for(Integer key : testData.keySet()) {
			dataProvider.add(new Object[] {testData.get(key)});
		}
		return dataProvider.iterator();
	}
	
	@Test(dataProvider = "getData")
	public void verifyAddingUserTest(HashMap<String, String> testData) throws InterruptedException {
		LoginPage loginPage = new LoginPage();
		DashboardPage dashboardPage = new DashboardPage();
//		AddUserPage addUserPage = new AddUserPage();
		
		loginPage.loginToApp(testData.get("UserName"), testData.get("Password"));
		boolean status = dashboardPage.ValidateDashboard();
		Assert.assertTrue(status, "User login failed");
		
		status= loginPage.logOutOrangeHRM();
		Assert.assertTrue(status, "Sucessfully logout from the application..");
		
//		dashboardPage.naviteToUsersModule();
//		status = addUserPage.validateAddUserPage();
//		Assert.assertTrue(status,"Users module navigation failed.");
//		
//		status = addUserPage.setUserDetails(testData);
//		Assert.assertTrue(status, "Users details not set properly.");
	}

}
