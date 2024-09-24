package Automation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Automation.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(css="[class='form-group'] input")
	WebElement country;
	
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement selectingIndia;
	
	@FindBy(xpath="//div[@class='actions']/a")
	WebElement placeOrder;
	
	By result=By.cssSelector(".ta-results");

	public ConfirmationPage selectCountry(String countryName) throws InterruptedException {
		Actions a = new Actions(driver);
		a.sendKeys(country,countryName).build().perform();
		waitForElementToAppear(result);
		selectingIndia.click();
		windowScroll(0,700);
		Thread.sleep(1000); 
		placeOrder.click();
		return new ConfirmationPage(driver);
	}
	
	
}
