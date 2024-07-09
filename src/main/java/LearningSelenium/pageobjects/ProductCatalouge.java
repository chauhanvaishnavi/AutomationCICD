package LearningSelenium.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import LearningSelenium.AbstractComponents.AbstractComponents;

public class ProductCatalouge extends AbstractComponents {

	WebDriver driver;
	
	//creating a constructor so that it will be run 1st before all the methods in this class
	public ProductCatalouge(WebDriver driver) { //the value of driver is coming from StandAlone class 
		
		super(driver); //every child class should send the driver to parent class with super keyword
		this.driver = driver; //assigning instance variable value to class variable value(driver)
		PageFactory.initElements(driver, this); // this triggers all the webelements writtien down in pageFactory to the driver.findelemnet form
	
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	
	By productsBy = By.cssSelector(".mb-3"); // creating variable to store the BY locator and passing the variable in the WaitForElementToAppear() method
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage= By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		
		WaitForElementToAppear(productsBy); //passing By locator as argument to this method to wait for it until 5 secs 
		return products; //returning the list of products 
	}
	
	public WebElement getProductByName(String productName) { //productName will have the productname whis is added to cart  
		
		WebElement prod=getProductList().stream().filter(product -> 
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);//using streams first getting the text and checking if equals to the required prod
		return prod;
	}
	
	public void addProductToCart(String productName) {
		WebElement prod = getProductByName( productName); //calling above method and storing the webelemnt(the one which needs to be added to the cart) into prod
		prod.findElement(addToCart).click(); //add to cart button is clicked 
		WaitForElementToAppear(toastMessage);
		WaitForElementToDisAppear(spinner);
	}
}

	

