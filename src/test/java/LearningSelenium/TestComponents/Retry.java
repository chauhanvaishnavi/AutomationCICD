package LearningSelenium.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

//implemenenting an interface to retry an failed test case one more time to make sure 
public class Retry implements IRetryAnalyzer{
	
	int count = 0;
	int maxTry = 1; //this is the count for how many time I need to run the failed test 
	@Override
	public boolean retry(ITestResult result) {  
		
		if(count<maxTry) {   //first no rerun so count=0
			count ++; 
			return true; //if the condition is true the method is returning true an getting rerun 
		}
		return false; //once if condition is false then the method will stop re run
	}

	
}
