package tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportDemo {
	ExtentReports extent;
	ExtentHtmlReporter htmlReporter;

	@BeforeSuite
	public void reportSetUp()
	{
		htmlReporter= new ExtentHtmlReporter("extent.html");
		extent= new ExtentReports();
		extent.attachReporter(htmlReporter);

	}

	@AfterSuite
	public void reportTearDown()
	{
		extent.flush();
	}

}
