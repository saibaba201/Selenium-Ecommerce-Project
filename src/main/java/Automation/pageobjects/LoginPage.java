package Automation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Automation.AbstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent {
	
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	@FindBy(id="userEmail")
    WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(css="[class*='login-btn']")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMsg;
	
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
		
	}
	
	
	public ProductCatalogue loginWebPage(String mail, String pass) {
		userEmail.sendKeys(mail);
		userPassword.sendKeys(pass);
		submit.click();
		return new ProductCatalogue(driver);
	}
	

	public String getMeErroMsg() {
		waitForElementToAppear(errorMsg);
		String msg=errorMsg.getText();
		return msg;
	}
	
}
