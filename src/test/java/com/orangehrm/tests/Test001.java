package com.orangehrm.tests;

import org.testng.annotations.Test;

import com.orangehrm.pageObjects.DashboardPage;
import com.orangehrm.pageObjects.LoginPage;

public class Test001 extends BaseClass{
	
	@Test
	public void loginTest() throws InterruptedException {
		LoginPage loginPage = new LoginPage();
		DashboardPage dashboardPage = new DashboardPage();
		
		loginPage.loginToApp(userName, password);
		dashboardPage.ValidateDashboard();
	}

}
