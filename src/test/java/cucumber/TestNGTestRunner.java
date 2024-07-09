package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//monochrome gives the results in readable format (key:value pair format)
//plugin is which format to store the result and 
	@CucumberOptions(features="src/test/java/cucumber",glue="LearningSelenium.stepDefinations",
			monochrome=true, tags="@ErrorValidation", plugin= {"html:target/cucumber.html"}) 
	
	
	//AbstractTestNGCucumberTests is a class in cucumber which has capabiliy to read the testNG code for integration 
public class TestNGTestRunner extends AbstractTestNGCucumberTests{
 
	
}
