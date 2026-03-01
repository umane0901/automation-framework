package com.orangehrm.tests;

import org.testng.annotations.Test;

import com.orangehrm.utils.RetryAnalizer;

public class RetryTest {
	
	@Test(retryAnalyzer = RetryAnalizer.class)
	public void demoTest() {
		System.out.println("Demo test from TestRetty!!"+ 10/0);
	}
}
