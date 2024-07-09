package LearningSelenium.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import LearningSelenium.resources.ExtentReporterNG;

// ITestListener is an Interface which has 
public class Listeners extends BaseTest implements ITestListener {
	
	ExtentTest test;
	ExtentReports extentReports= ExtentReporterNG.getReportObject(); //calling method from listeners class. ExtentReports is return type and calling object with classname.methodname. created object as extentReports
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal <ExtentTest>(); //creating thread object for every test so that each test runs concurrently in parallel 
	
	
	
	@Override
	public void onTestStart(ITestResult result) { //result variable hold the info about the test which going to get executed inthe method 
		
		 test = extentReports.createTest(result.getMethod().getMethodName()); // getMethod will tell to get a testNG method and getMethodName will tell which testNG method (name) we need to add in the method 
		 extentTest.set(test); //creates unique thread id of Error Validation test
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "test Passed");
		
		//take a screentshot
		
		
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable()); //this will print the failed error in the report 
		
		try{
		 driver= (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance()); //getting the driver from the test method 
		}
		catch(Exception e1){
			e1.printStackTrace();
		}
		 
		//taking a screentshot on test failure
		
		//as the  getScreenshot() returns the path where screenshot is saved so we will store the path in filePath variable
		//automatically turned into TRY catch block 
		String filePath=null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
}
	
	@Override
	public void onFinish(ITestContext context) {
		extentReports.flush(); //generates the report 
		
		
		
		
	}
	
}
