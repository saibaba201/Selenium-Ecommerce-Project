package AutomationWIthJava.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Automation.Resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentReports extent = ExtentReporterNG.getExtentReports();
	ExtentTest test;
	ThreadLocal<ExtentTest> threadTest= new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		threadTest.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		threadTest.get().log(Status.PASS, "Test Case passed");
	}

	public void onTestFailure(ITestResult result) {
		threadTest.get().fail(result.getThrowable());

		String testCaseName = result.getMethod().getMethodName();
		String path = null;
		 try {
			driver= (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		} 

		try {
			path = getScreenShot(testCaseName, driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		threadTest.get().addScreenCaptureFromPath(path, testCaseName);

	}

	

	public void onFinish(ITestContext result) {
		// not implemented
		extent.flush();
	}
}
