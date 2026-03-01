package com.orangehrm.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalizer implements IRetryAnalyzer {

	int count = 0;
	private static final int retry = 1;

	@Override
	public boolean retry(ITestResult result) {

		if (count < retry) {
			count++;
			return true;
		}
		return false;
	}
}
