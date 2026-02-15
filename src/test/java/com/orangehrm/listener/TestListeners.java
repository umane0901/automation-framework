package com.orangehrm.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.orangehrm.utils.DriverManager;
import com.orangehrm.utils.ExtentReporter;
import com.orangehrm.utils.ScreenShotUtil;

public class TestListeners implements ITestListener{

    private static ExtentReports extent = ExtentReporter.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest =
                extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
	    public void onTestSuccess(ITestResult result) {
	        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.get().fail(result.getThrowable());

        // Example screenshot call
        String path = ScreenShotUtil.captureScreenshot(
                DriverManager.getDriver(),
                result.getMethod().getMethodName());

        try {
            test.get().addScreenCaptureFromPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test Skipped");
	    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}

