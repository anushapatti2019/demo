package MyProect.pagefatory;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Abstractcomponents;

public class productList extends Abstractcomponents {

	WebDriver driver;

	public productList(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@class='card-body']/h5/b")
	List<WebElement> productList;

	By products = By.xpath("//*[@class='card-body']");
	By addedToCartMessage = By.id("toast-container");

	public List<WebElement> getProductList() {

		waitForElementToAppear(products);
		return productList;

	}

	public void getProductClick(List<WebElement> list, String product) {

		for (int i = 0; i < list.size(); i++) {
			String name = list.get(i).getText();
			if (name.equalsIgnoreCase(product)) {
				driver.findElements(By.xpath("//*[@class='btn w-10 rounded']")).get(i).click();
				break;
			}
		}
		waitForElementToDissappear(addedToCartMessage);

	}

	
}
