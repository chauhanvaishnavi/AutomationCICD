package LearningSelenium.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import LearningSelenium.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage; //initializing the landing page object variable

	
	public WebDriver Initializedriver() throws IOException {
		
		
		//properties class
		
		Properties prop = new Properties(); //creating object of properties class which is already available in java in Util package
		//converting properties file into FileInputStream type as prop.load(); loads file as FileInputStream type
		//user.dir it will directly give the project path 
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\LearningSelenium\\resources\\GlobalData.properties");
		prop.load(fis);
		//adding step so that the value of browser is read from the maven command
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser"); //Ternary operater
		
		 prop.getProperty("browser"); //getting browser from properties file 
		
		
		if(browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup(); //chromedriver will be automatically downloaded from the jars/dependencies in our system
			if(browserName.contains("headless")) {
				options.addArguments("--headless=new"); //agrs to run the script/chrome in headless mode 
				driver = new ChromeDriver(options);
				driver.manage().window().setSize(new Dimension(1440,900)); //maximizing the window with dimensions 
			} else {
				driver = new ChromeDriver();
			}
		}
		 
		
		else if(browserName.equalsIgnoreCase("firefox")){
		 driver = new FirefoxDriver();
		 WebDriverManager.firefoxdriver().setup();

		}
		
		else if(browserName.equalsIgnoreCase("edge")){
			driver = new EdgeDriver();
			WebDriverManager.edgedriver().setup();
			
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //globally giving the implicit wait 
		driver.manage().window().maximize();
		return driver; //returning driver to method 
			
		
	}
	@BeforeMethod(alwaysRun=true) //this will always run not matter if the 
	public LandingPage launchApplication() throws IOException {
		driver = Initializedriver(); //driver = because it returns driver 
		landingPage = new LandingPage(driver); //creating object of the class LandingPage and passing the parameter(will be now called as constructor) as driver 
		landingPage.GoTo();
		return landingPage; //returning landing page to SubmitOrderTest to access 
	}
	

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		
		//reading Json and storing into the string
		//"new File" is creating an object of File in args and storing into a string variable
		String jsonContent = FileUtils.readFileToString(new File(filePath), 
				StandardCharsets.UTF_8);
		
		//convert string to hashmap with help of jackson databind dependency
		
		//created an object of ObjectMapper class
		ObjectMapper mapper = new ObjectMapper();
		
		//ObjectMapper class has the method readValue(string which we got from json file, how to save string into hashMap based upon no. of index, in jason we have 2 list)
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){
		});
		return data; 
		
	}
	
	//utility to take screenshot 
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		
		//caste or converting  the driver object 
		TakesScreenshot ts = (TakesScreenshot)driver;
		
		File source = ts.getScreenshotAs(OutputType.FILE); // taking screenshot and storing it into a file format and storing into variable "source"
		File file = new File(System.getProperty("user.dir")+"\\reports\\"+ testCaseName +".png"); // testCaseName is where we wanted to store the it in the test so that we will know if it gets failed 
		FileUtils.copyFile(source, file); // takes the agrs in the form of object so created the object of destination file up
		return System.getProperty("user.dir")+"\\reports\\"+ testCaseName +".png";
	}
	
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.quit();
	}
}
