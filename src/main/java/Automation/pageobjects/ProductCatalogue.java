package Automation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Automation.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By findby = By.xpath("//*[contains(@class,'mb-3')]");
	By productBy = By.cssSelector(".card-body button:last-of-type");
	By toast = By.cssSelector(".toast-container");

	@FindBy(xpath = "//*[contains(@class,'mb-3')]")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement animating;
	
	
	
	//Action Methods
	public List<WebElement> getProducts() {
		waitForElementToAppear(findby);
		return products;
	}

	public WebElement getProductByName(String prodName) {
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(prodName)).findFirst()
				.orElse(null);
		return prod;

	}

	public void addToCart(String prodName) {
		WebElement prod = getProductByName(prodName);
		prod.findElement(productBy).click();
		waitForElementToAppear(toast);
		waitForElementToDisppear(animating);

	}
	
}
