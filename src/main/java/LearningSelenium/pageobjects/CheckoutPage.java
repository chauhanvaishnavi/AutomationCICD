package LearningSelenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import LearningSelenium.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {

WebDriver driver;
	
	//creating a constructor so that it will be run 1st before all the methods in this class
	public CheckoutPage(WebDriver driver) { //the value of driver is coming from StandAlone class 
		
		super(driver); //every child class should send the driver to parent class with super keyword
		this.driver = driver; //assigning instance variable value to class variable value(driver)
		PageFactory.initElements(driver, this); // this triggers all the webelements writtien down in pageFactory to the driver.findelemnet form
	
	}
	
	//declaring the webelements as private so that accesible in this class only 
	@FindBy(css=".action__submit")
	private WebElement submit;
	
	@FindBy(css="input[placeholder='Select Country']")
	private WebElement country;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	private WebElement selectCountry;
	
	private By results=By.cssSelector(".ta-results");
	
	
	public void SelectCountry(String countryName) {
		
		Actions a = new Actions(driver);
		a.sendKeys(country,countryName).build().perform();
		WaitForElementToAppear(results);
		
		selectCountry.click();
		
		
	}
	
	public ConfirmationPage submitOrder() {
		submit.click();
		return new ConfirmationPage(driver);
		
	}
	
}

	
