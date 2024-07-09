package LearningSelenium.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;

import LearningSelenium.TestComponents.BaseTest;
import LearningSelenium.pageobjects.CartPage;
import LearningSelenium.pageobjects.CheckoutPage;
import LearningSelenium.pageobjects.ConfirmationPage;
import LearningSelenium.pageobjects.LandingPage;
import LearningSelenium.pageobjects.ProductCatalouge;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationImp extends BaseTest{
	
	
	public LandingPage landingPage; //declaring the object - landingPage which is getting returned from baseTest class and catching here 
	public ProductCatalouge productCatalouge; //declaring the object - productCatalouge
	public CartPage cartPage;					 //-----------""------------
	public CheckoutPage checkout;				 //-----------""------------
	public ConfirmationPage confirmationPage;
	
	
	//@Given is an cucumber annotation which gets matched with the given statement in the feature file and provide the statement inside 
	@Given("I landed on ecommerce page")
	public void I_landed_on_ecommerce_page() throws IOException  {
		landingPage=launchApplication();
	}
	
	//bellow has parameters so regular expression is written
	@Given("^Logged in with username (.+) and password (.+)$") //^ $ denotes that its an regular exp
	public void Logged_in_with_username_and_password(String username, String password){ //values in username and password is stored from the feature file 
		
		productCatalouge=landingPage.LoginApplication(username,password); 
	}
	
	@When("^I add the product (.+) to cart$")
	public void i_add_product_to_cart(String ProductName) throws InterruptedException {
		List<WebElement>products=productCatalouge.getProductList();
		productCatalouge.addProductToCart(ProductName);
	}
	
	@When("^checkout (.+) and submit the order$")
	public void checkout_productName_and_submit_order(String ProductName) {
		cartPage=productCatalouge.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(ProductName); 
		checkout= cartPage.gotoCheckoutPage();
		Assert.assertTrue(match);
		checkout.SelectCountry("India");
		confirmationPage = checkout.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string)  {
		String confirmMessage= confirmationPage.VerifyConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	@Then("{string} message is displayed")
	public void incorrect_Email_Password_message(String string) {
		Assert.assertEquals(string, landingPage.getErrorMessage());
		driver.close();
	}
			

}
