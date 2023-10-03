package MyProect.pagefatory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Abstractcomponents;

public class cartPage extends Abstractcomponents{
	WebDriver driver;
	public cartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	By checkout =By.xpath("//button[contains(text(),'Checkout')]");
	By addresspage=By.cssSelector("[class='ta-results list-group ng-star-inserted']");
	
	@FindBy(xpath="//button[contains(text(),'Checkout')]")
	WebElement checkOutCart;
	
	@FindBy(css="[class='form-group'] [class='input txt text-validated']")
	WebElement address;
	
	@FindBy(css="[class='ta-item list-group-item ng-star-inserted']")
	List<WebElement> dropdown;
	
	public void checkOut() {
		waitForElementToAppear(checkout);
		checkOutCart.click();
	}
	
	public void fillupAdress(String add) {
		
		address.sendKeys(add);
		waitForElementToAppear(addresspage);
	}
	
	public List<WebElement> dropwonvalues() {
		
		return dropdown;

	}
	
	public void selectAddress(List<WebElement> values, String add) {
		
		for(int i=0;i<values.size();i++) {
			String seletion = values.get(i).findElement(By.cssSelector("[class='ng-star-inserted']")).getText();
			if(seletion.equalsIgnoreCase(add)) {
				values.get(i).findElement(By.cssSelector("[class='ng-star-inserted']")).click();
				break;
			}
		}
	}

	
	
	

}
