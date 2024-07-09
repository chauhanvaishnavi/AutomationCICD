package LearningSelenium.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	
	public static ExtentReports getReportObject() {
		
		String path = System.getProperty("user.dir")+"\\reports\\index.html";   //creating folder reports and index.html file 
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results"); //config method is used to set all the configurations in the index.html file 
		reporter.config().setDocumentTitle("Test Results");
		
		ExtentReports extentReports= new ExtentReports(); //this class is responsible for consolidating all the test results into the html
		extentReports.attachReporter(reporter); //attach reporter will attach the ExtentSparkReporter class object i.e it gets all the configuartions
		extentReports.setSystemInfo("Tester", "Vaishnavi Jethwa");  //given the tester name 
		
		return extentReports;
	
	}

}
