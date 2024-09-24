package AutomationWIthJava;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Automation.pageobjects.CartPage;
import Automation.pageobjects.ProductCatalogue;
import AutomationWIthJava.TestComponents.BaseTest;
import AutomationWIthJava.TestComponents.RetryFailedTests;

public class ErrorValidationTest extends BaseTest {
	
	@Test(groups={"ErrorHandling"},retryAnalyzer =RetryFailedTests.class )
	public void loginErrorValidation() throws IOException, InterruptedException {
		String userMail = "learnJava@gmail.com";
		String userPass = "Saibaba13#";
		
		loginPage.loginWebPage(userMail, userPass);
		Assert.assertEquals("Incorrect email or password.", loginPage.getMeErroMsg());

	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException {
		String ProdName = "ADIDAS ORIGINAL";
		String userMail = "learnJava@gmail.com";
		String userPass = "Saibaba123#";

		ProductCatalogue productCatlogue = loginPage.loginWebPage(userMail, userPass);
		List<WebElement> products = productCatlogue.getProducts();
		productCatlogue.addToCart(ProdName);
		CartPage cartPage = productCatlogue.goToCartPage();
		boolean productMatch = cartPage.VerifyProductDisplay("ADIDAS ORIGINAL123");
		Assert.assertFalse(productMatch);
	}

}
