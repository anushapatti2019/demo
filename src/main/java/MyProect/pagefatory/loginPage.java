package MyProect.pagefatory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Abstractcomponents;

public class loginPage extends Abstractcomponents {

	WebDriver driver;

	public loginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement username;

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(id = "login")
	WebElement submit;
	
	@FindBy(css="div[aria-label='Incorrect email or password.']")
	WebElement errormsg;

	public productList login(String name, String pw) {
		username.sendKeys(name);
		password.sendKeys(pw);
		submit.click();
		productList productpage = new productList(driver);
		return productpage;
	}

	public void gotoURL() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorText() {
		waitForWebElementToAppear(errormsg);
		return errormsg.getText();
		
	}
	
	
}
