package com.orangehrm.utils;

import java.time.LocalDateTime;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {
	private static ExtentReports extent;
		
	public static ExtentReports getInstance() {
		if (extent == null) {
			ExtentSparkReporter spark = new ExtentSparkReporter("reports/ExtentReport.html");

			spark.config().setReportName("Automation Report");
			spark.config().setDocumentTitle("Test Execution Report");

			extent = new ExtentReports();
			extent.attachReporter(spark);
			extent.setSystemInfo("Environment", "QA");
	        extent.setSystemInfo("Platform", System.getProperty("os.name"));
	        extent.setSystemInfo("Browser", "Chrome");
	        extent.setSystemInfo("Tester", "Umesh Mane");
	        extent.setSystemInfo("Execution Time", 
	        		LocalDateTime.now().toString());
		}

		return extent;
	}
}
