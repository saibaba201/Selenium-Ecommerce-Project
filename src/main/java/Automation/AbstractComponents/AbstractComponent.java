package Automation.AbstractComponents;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Automation.pageobjects.CartPage;
import Automation.pageobjects.OrderPage;


public class AbstractComponent {
	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void waitForElementToAppear(By findby) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	}
	
	public void waitForElementToAppear(WebElement findby) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findby));
	}

	public void waitForElementToDisppear(WebElement element) {
//		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	
	public void windowScroll(int x,int y) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy("+x+","+y+");");
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartIcon;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderIcon;
	
	public CartPage goToCartPage() {
		cartIcon.click();
		return new CartPage(driver);
	}
	
	public OrderPage goToOrderPage() {
		orderIcon.click();
		return new OrderPage(driver);
	}
	
}
