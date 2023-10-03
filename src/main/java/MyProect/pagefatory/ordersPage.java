package MyProect.pagefatory;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Abstractcomponents;

public class ordersPage extends Abstractcomponents {
	
	WebDriver driver;
	
	@FindBy (css ="tr td:nth-child(3)")
	private List <WebElement> productsInCart;
	
	@FindBy (xpath="//h1[contains(text(),'Your Orders')]")
	WebElement pagenote;
	
	public ordersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	public boolean isproductmatch(String productName) {
		waitForWebElementToAppear(pagenote);
		boolean b=productsInCart.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return b;
	}
				

}
