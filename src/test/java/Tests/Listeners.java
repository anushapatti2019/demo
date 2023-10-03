package Tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Resources.ExtentReportsNG;

public class Listeners extends baseTest implements ITestListener {
	ExtentTest test;
	ExtentReports ex=ExtentReportsNG.reports();
	ThreadLocal<ExtentTest> extent= new ThreadLocal<ExtentTest> ();
	
	@Override
	public void onTestStart(ITestResult result) {
		test=ex.createTest(result.getMethod().getMethodName());
		extent.set(test);
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Pass");
	}
	@Override
	public void onTestFailure(ITestResult result) {
		
		extent.get().fail(result.getThrowable());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String filepath = null;
		try {
			filepath = screenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
	}
	
	public void OnFinish(ITestResult context) {
		ex.flush();
	}
}
