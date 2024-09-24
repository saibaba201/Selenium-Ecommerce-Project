package Automation.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getExtentReports() {
		String path=System.getProperty("user.dir")+"//reports//index.html";
		ExtentSparkReporter spartReporter= new ExtentSparkReporter(path);
		spartReporter.config().setReportName("Execution Report");
		spartReporter.config().setDocumentTitle("Test Results");
		
		ExtentReports extent= new ExtentReports();
		extent.attachReporter(spartReporter);
		extent.setSystemInfo("Tester", "Saibaba Areti");
		return extent;
	}

}
