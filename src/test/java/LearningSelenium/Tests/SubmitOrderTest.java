package LearningSelenium.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import LearningSelenium.AbstractComponents.OrderPage;
import LearningSelenium.TestComponents.BaseTest;
import LearningSelenium.pageobjects.CartPage;
import LearningSelenium.pageobjects.CheckoutPage;
import LearningSelenium.pageobjects.ConfirmationPage;
import LearningSelenium.pageobjects.LandingPage;
import LearningSelenium.pageobjects.ProductCatalouge;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest{

	
	String productName= "ZARA COAT 3";
	
	@Test(dataProvider="getData", groups= {"Purchase"})
	//arguments in the method will get the data from getdata with help of dataprovider
	public void SubmitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
	
		
		//as object is returned from the landing page its stored in object name : productCatalouge
		ProductCatalouge productCatalouge=landingPage.LoginApplication(input.get("email"), input.get(("password"))); //calling the method LoginApplication and passing the values 
		

		List<WebElement>products=productCatalouge.getProductList(); //calling getProductList() method from ProductCatalouge class and storing list of products into one weElement
		productCatalouge.addProductToCart(input.get("product")); //zaracoat 3 will be paases in this args and will be sent to the method 
		
		CartPage cartPage=productCatalouge.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(input.get("product")); // method returns the true value which will come here and get stored into match 
		CheckoutPage checkout= cartPage.gotoCheckoutPage();
		//validations will always be in main() class and will not go in page object class. Page object is only there to perform actions
		Assert.assertTrue(match); //putting validation as true if  productName = zara coat 3
		 
		checkout.SelectCountry("India");
		ConfirmationPage confirmationPage = checkout.submitOrder();
		String confirmMessage= confirmationPage.VerifyConfirmationMessage();
		AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
	}
	
	//test to verify if orders page has the zara coat 3 available in the list 
	@Test(dependsOnMethods={"SubmitOrder"}) //dependsOnMethods ethods will run the SubmitOrder(above method) first and then self
	public void OrderHistoryTest() {
		
		ProductCatalouge productCatalouge=landingPage.LoginApplication("helloselenium@gmail.com", "Chauhan@123"); 
		OrderPage orderPage= productCatalouge.goToOrderPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
		
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
//		HashMap<String,String> map = new HashMap<String,String>(); //creating a hashmap to store key value pair data
//		map.put("email", "helloselenium@gmail.com");
//		map.put("password", "Chauhan@123");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "helloselenium1111111@gmail.com");
//		map1.put("password", "Hello@123");
//		map1.put("product", "ADIDAS ORIGINAL");
		
		
		//calling getJsonDataToMap method  and storing into data with returnType- List<HashMap<String,String>>
		List<HashMap<String,String>>data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\LearningSelenium\\data\\PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}}; 
		
		// method returns the 2maps 
		//return new Object[][] {{"helloselenium@gmail.com","Chauhan@123","ZARA COAT 3"},{"helloselenium1111111@gmail.com","Hello@123","ADIDAS ORIGINAL"}};//two dimentional object array. Object array is a type that contains all the type of data, int, string, etc. Its a generic one which accecpts any kind of data type 
	}
	
}

		
		
		
		
		
		
		
		
		
		
		
				

 
		
			
		
		
		
		

	
	


