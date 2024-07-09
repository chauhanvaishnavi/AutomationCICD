package LearningSelenium.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import LearningSelenium.AbstractComponents.AbstractComponents;

public class ConfirmationPage  extends AbstractComponents {

WebDriver driver;
	
	//creating a constructor so that it will be run 1st before all the methods in this class
	public ConfirmationPage(WebDriver driver) { //the value of driver is coming from StandAlone class 
		
		super(driver); //every child class should send the driver to parent class with super keyword
		this.driver = driver; //assigning instance variable value to class variable value(driver)
		PageFactory.initElements(driver, this); // this triggers all the webelements writtien down in pageFactory to the driver.findelemnet form
	
	}
	 @FindBy(css= ".hero-primary")
	 WebElement ConfirmationMessage;
	 
	 
	 public String VerifyConfirmationMessage() {
		 
		 return ConfirmationMessage.getText();
		 
	 }
	 
	 
	 
}

