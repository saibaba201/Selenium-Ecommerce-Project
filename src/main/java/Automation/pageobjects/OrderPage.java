package Automation.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Automation.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "td:nth-child(3)")
	List<WebElement> ordersProducts;

	public List<WebElement> getOrderProducts() {
		return ordersProducts;
	}

	public Boolean verifyOrderDetails(String orderProduct) {
		Boolean orderMatch = getOrderProducts().stream()
				.anyMatch(prod -> prod.getText().equalsIgnoreCase(orderProduct));
		return orderMatch;
	}
}
