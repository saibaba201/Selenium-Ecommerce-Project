package AutomationWIthJava;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Automation.pageobjects.CartPage;
import Automation.pageobjects.CheckOutPage;
import Automation.pageobjects.ConfirmationPage;
import Automation.pageobjects.OrderPage;
import Automation.pageobjects.ProductCatalogue;
import AutomationWIthJava.TestComponents.BaseTest;

public class StandAloneTest extends BaseTest {


	@Test(dataProvider = "getJsonData",groups= {"purchase"})
	public void submitOrderTest(HashMap<String,String> input) throws IOException, InterruptedException {

		ProductCatalogue productCatlogue = loginPage.loginWebPage(input.get("email"), input.get("password"));
		List<WebElement> products = productCatlogue.getProducts();
		productCatlogue.addToCart(input.get("prductName"));
		CartPage cartPage = productCatlogue.goToCartPage();
		boolean productMatch = cartPage.VerifyProductDisplay(input.get("prductName"));
		Assert.assertTrue(productMatch);
		CheckOutPage checkout = cartPage.checkOut();
		ConfirmationPage confirmPage = checkout.selectCountry("india");
		String confirmText = confirmPage.getConfirmMessage();
		Assert.assertTrue(confirmText.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	@Test(dependsOnMethods = {"submitOrderTest"})
	public void orderConfirmation() {
		String ProdName = "ADIDAS ORIGINAL";
		String userMail = "learnJava@gmail.com";
		String userPass = "Saibaba123#";
		ProductCatalogue productCatlogue = loginPage.loginWebPage(userMail, userPass);
		OrderPage orderPage=productCatlogue.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDetails(ProdName));
		
	}
	
	@DataProvider
	public Object[][] getJsonData() throws IOException {
		List<HashMap<String,String>>data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\AutomationWIthJava\\data\\dataProvider.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
	

}		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
//		List<WebElement> cartProds = driver.findElements(By.cssSelector(".cartSection h3"));
//		Boolean productMatch = cartProds.stream().anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase(ProdName));
//		Assert.assertTrue(productMatch);
//		driver.findElement(By.cssSelector(".totalRow button")).click();

//		Actions a = new Actions(driver);
//		a.sendKeys(driver.findElement(By.cssSelector("[class='form-group'] input")), "India").build().perform();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
//		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("window.scrollBy(0,700);");

//		if N/W issue ccours please add the below line of code
//		Thread.sleep(1000); 
//		driver.findElement(By.xpath("//div[@class='actions']/a")).click();

//		String confirmMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
//		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

//		driver.quit();

