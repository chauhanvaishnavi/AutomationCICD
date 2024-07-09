package LearningSelenium.AbstractComponents;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import LearningSelenium.pageobjects.CheckoutPage;

public class OrderPage extends AbstractComponents {
	
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productNames;

	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	WebDriver driver;
	
	//creating a constructor so that it will be run 1st before all the methods in this class
	public OrderPage(WebDriver driver) { //the value of driver is coming from StandAlone class 
		
		super(driver); //every child class should send the driver to parent class with super keyword
		this.driver = driver; //assigning instance variable value to class variable value(driver)
		PageFactory.initElements(driver, this); // this triggers all the webelements writtien down in pageFactory to the driver.findelemnet form
	
	}
	public Boolean VerifyOrderDisplay(String productName) {
		
		Boolean match = productNames.stream().anyMatch(product-> product.getText().equalsIgnoreCase(productName)); //checking if the zara coat 3 is available in the cart by returning the value as true by anymatch method 
		return match;
	
	}
	
	public CheckoutPage gotoCheckoutPage() {
		checkoutEle.click();
		return new CheckoutPage(driver);
	}
		
		
}
	

	




