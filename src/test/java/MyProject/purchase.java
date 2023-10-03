package MyProject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;

import MyProect.pagefatory.cartPage;
import MyProect.pagefatory.checkOutPage;
import MyProect.pagefatory.loginPage;
import MyProect.pagefatory.ordersPage;
import MyProect.pagefatory.productList;
import Tests.baseTest;

public class purchase extends baseTest {


	@Test(dataProvider="getdata", groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException,JsonParseException {

		productList pl = lp.login(input.get("email"), input.get("password"));

		List<WebElement> list = pl.getProductList();
		pl.getProductClick(list, input.get("product"));
		pl.goToCart();

		cartPage cp = new cartPage(driver);
		cp.checkOut();
		cp.fillupAdress(input.get("address"));

		List<WebElement> values = cp.dropwonvalues();
		cp.selectAddress(values, input.get("address"));
		cp.scrollPage();

		checkOutPage cop = new checkOutPage(driver);
		cop.placeOrder();
		String confirmation = cop.getTextMessage();
		Assert.assertTrue(confirmation.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	@DataProvider
	public Object[][] getdata() throws IOException {
		
		List<HashMap<String, String>> data = getJsontoHash("C:\\Users\\anusha.patti\\eclipse-workspace\\SeleniumProject\\src\\test\\java\\data\\testdata.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	

//	@Test(dependsOnMethods = { "submitOrder" })
//	public void orderHistory() {
//		productList pl = lp.login(username, password);
//		ordersPage op = pl.gotoorders();
//		Assert.assertTrue(op.isproductmatch(product));
//	}

//	@DataProvider
//	public Object[][] getdata() {
//		return new Object [][] {{"rajpatti@gmail.com","Revariya@2019","ADIDAS ORIGINAL","india"}, {"rajpatti2@gmail.com","Revariya@2019","ADIDAS ORIGINAL","india"}};
//	}
	
	
//	HashMap<String, String> map= new HashMap<String, String>();
//	map.put("email","rajpatti@gmail.com");
//	map.put("password","Revariya@2019");
//	map.put("product","ADIDAS ORIGINAL");
//	map.put("address","india");
//	
//	HashMap<String, String> map1= new HashMap<String, String>();
//	map1.put("email","rajpatti2@gmail.com");
//	map1.put("password","Revariya@2019");
//	map1.put("product","IPHONE 13 PRO");
//	map1.put("address","india");
}
