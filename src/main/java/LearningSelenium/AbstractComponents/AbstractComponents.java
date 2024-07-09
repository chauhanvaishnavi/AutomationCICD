package LearningSelenium.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import LearningSelenium.pageobjects.CartPage;

public class AbstractComponents {
	
	WebDriver driver;
	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		
		
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;

	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	
	
	//By is locator 
	public void WaitForElementToAppear(By findBy) {
	WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(5)); //creating a webdriver wait class to access the WebDriverWait methods(for ex: until)
	wait.until(ExpectedConditions.visibilityOfElementLocated((findBy)));
	}
	
	public void WaitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(5)); //creating a webdriver wait class to access the WebDriverWait methods(for ex: until)
		wait.until(ExpectedConditions.visibilityOf(findBy));
		}
	
	public void WaitForElementToDisAppear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating")))); //waiting for the loading spinner to disapear
	}
	
	public CartPage goToCartPage() {
		
		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	 
public OrderPage goToOrderPage() {
		
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}

}
