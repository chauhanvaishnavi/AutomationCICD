package LearningSelenium.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import LearningSelenium.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
	
		//newest comments for GitHub and CICD
		
		String productName= "ZARA COAT 3";
		
		WebDriverManager.chromedriver().setup(); //chromedriver will be automatically downloaded from the jars/dependencies in our system
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //globally giving the implicit wait 
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		
		LandingPage landingPage = new LandingPage(driver); //creating object of the class LandingPage and passing the parameter(will be now called as constructor) as driver 
		
		driver.findElement(By.id("userEmail")).sendKeys("helloselenium@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Chauhan@123");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(5)); //creating a webdriver wait class to access the WebDriverWait methods(for ex: until)
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3"))); // this expilicit wait will wait until the add to cart message is popped up
		
		//getting all the items available into a List
		List<WebElement>products=driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod=products.stream().filter(product -> 
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);//using streams first getting the text and checking if equals to the required prod
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click(); //add to cart button is clicked 
		
				
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container"))); // this expilicit wait will wait until the add to cart message is popped up 
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating")))); //waiting for the loading spinner to disapear
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3")); // creating a list of elements present in cart
		Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName)); //checking if the zara coat 3 is available in the cart by returning the value as true by anymatch method 
		Assert.assertTrue(match); //putting validation as true if  productName = zara coat 3
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
//		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("Ind");
//		List<WebElement> options = driver.findElements(By.cssSelector(".ta-item"));
//		 WebElement selectedOptions = (WebElement) options.stream().filter(option->option.getText().equalsIgnoreCase("India"));
//		 selectedOptions.click();
	 
		//another alternative
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"India").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmMessage = driver.findElement(By.cssSelector("hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		driver.close();
		
		}
	}

	


