package Stepdefination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import MyProect.pagefatory.cartPage;
import MyProect.pagefatory.checkOutPage;
import MyProect.pagefatory.loginPage;
import MyProect.pagefatory.productList;
import Tests.baseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

	
public class stepdefi_implementation extends baseTest{
	
	public loginPage lp;
	public productList pl;
	List<WebElement> list;
	cartPage cp;
	checkOutPage cop;
	
	@Given("land on ecommerce page")
	public void landing_page() throws IOException {
		
		lp=launchapp();
	}
	
	@Given("^login with username (.+) and password (.+)$")
	public void login_us_pw(String name, String password) {
		pl = lp.login(name, password);
	}
	
	@When("^I select (.+) and click on submit order$")
	
	public void place_order(String product) throws InterruptedException {
		list = pl.getProductList();
		pl.getProductClick(list, product);
		pl.goToCart();
		
		cp = new cartPage(driver);
		cp.checkOut();
		cp.fillupAdress("india");

		List<WebElement> values = cp.dropwonvalues();
		cp.selectAddress(values, "india");
		cp.scrollPage();
		
		cop = new checkOutPage(driver);
		cop.placeOrder();
		
	}
	
	@Then("{string} messgae should be displayed")
	public void confirmationmsg(String string) {
		String confirmation = cop.getTextMessage();
		Assert.assertTrue(confirmation.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} should be displayed")
	public void errormsg(String string) {
		String msg=lp.getErrorText();
		Assert.assertEquals("Incorrect email or password.", msg);
		driver.close();
	}
	
}
