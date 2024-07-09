package LearningSelenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import LearningSelenium.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;
	
	//creating a constructor so that it will be run 1st before all the methods in this class
	public LandingPage(WebDriver driver) { //the value of driver is coming from StandAlone class 
		
		super(driver); //sending driver to parent class(AbstractComponents) with super keyword 
		this.driver = driver; //assigning instance variable value to class variable value(driver)
		PageFactory.initElements(driver, this); // this triggers all the webelements writtien down in pageFactory to the driver.findelemnet form
	
	}
	//WebElement userName=driver.findElement(By.id("userEmail"));
	//another way of writing driver.findElement(By.id("userEmail")); in testNG annotation with @FindBy //known as pageFactory 
	
	@FindBy(id="userEmail")
	WebElement userName;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;

	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatalouge LoginApplication(String email, String password ) {
		userName.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		
		ProductCatalouge productCatalouge = new ProductCatalouge(driver); // creating object of the next page: productCatalouge class 
		return productCatalouge; // returning the object of the class to this method 
}
	
	public String getErrorMessage() {
		
		WaitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

	public void GoTo(){
		
		driver.get("https://rahulshettyacademy.com/client");
	}

}
