package com.orangehrm.tests;

import org.testng.annotations.Test;
import com.orangehrm.utils.LoggerUtil;

public class Test002 extends BaseClass{
	@Test
	public void createUser() {
		LoggerUtil.logMessage("This is from Test002");
	}
	
}
