package LearningSelenium.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import LearningSelenium.TestComponents.Retry;
import LearningSelenium.TestComponents.BaseTest;
import LearningSelenium.pageobjects.CartPage;
import LearningSelenium.pageobjects.ProductCatalouge;

public class ErrorValidationsTest extends BaseTest  {

	@Test(groups={"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidations() throws IOException, InterruptedException {
		landingPage.LoginApplication("helloselenium@gmail.com", "Chauhan@@123"); // calling the method LoginApplication
																					// and passing the values
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}

	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";

		// as object is returned from the landing page its stored in object name :
		// productCatalouge
		ProductCatalouge productCatalouge = landingPage.LoginApplication("helloselenium@gmail.com", "Chauhan@123"); // calling
																													// the
																													// method
																													// LoginApplication
																													// and
																													// passing
																													// the
																													// values

		List<WebElement> products = productCatalouge.getProductList(); // calling getProductList() method from
																		// ProductCatalouge class and storing list of
																		// products into one weElement
		productCatalouge.addProductToCart(productName);
		CartPage cartPage=productCatalouge.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33"); // method returns the true value which will come here and get stored into match 
		Assert.assertFalse(match);

	}
}