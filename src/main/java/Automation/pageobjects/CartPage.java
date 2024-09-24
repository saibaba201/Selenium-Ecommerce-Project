package Automation.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProds;

	@FindBy(css = ".totalRow button")
	WebElement procced;

	public boolean VerifyProductDisplay(String cartProdName) {

		Boolean productMatch = cartProds.stream()
				.anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase(cartProdName));
		return productMatch;
	}

	public CheckOutPage checkOut() {

		procced.click();
		return new CheckOutPage(driver); 
	}

}
