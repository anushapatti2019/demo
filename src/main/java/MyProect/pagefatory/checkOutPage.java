package MyProect.pagefatory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Abstractcomponents;

public class checkOutPage extends Abstractcomponents {

	WebDriver driver;

	public checkOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By text = By.cssSelector(".hero-primary");

	@FindBy(xpath = "//a[contains(text(),'Place Order')]")
	WebElement placeOrder;

	@FindBy(css = ".hero-primary")
	WebElement textMessage;

	public void placeOrder() {
		Actions a = new Actions(driver);
		a.moveToElement(placeOrder).click().build().perform();
		waitForElementToAppear(text);
	}

	public String getTextMessage() {
		return textMessage.getText();

	}

}
