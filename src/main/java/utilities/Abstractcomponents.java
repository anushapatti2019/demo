package utilities;

import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import MyProect.pagefatory.cartPage;
import MyProect.pagefatory.ordersPage;

public class Abstractcomponents {
	
	WebDriver driver;	
	public Abstractcomponents(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="[routerlink*='cart']")
	WebElement cart;
	
	@FindBy(css = "[routerlink='/dashboard/myorders']")
	WebElement orders;
	
	By addedproductmsg=By.id("toast-container");
	

	public void waitForElementToAppear(By products) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(products));
	}
	public void waitForWebElementToAppear(WebElement products) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(products));
	}
	
	public void waitForElementToDissappear(By products) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(products));
	}
	
	public void scrollPage() throws InterruptedException {
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1200)");
		Thread.sleep(2000);
	}

	public void goToCart() {
		waitForElementToDissappear(addedproductmsg);
		cart.click();
		
	}
	public ordersPage gotoorders() {
		waitForWebElementToAppear(orders);
		orders.click();
		ordersPage op= new ordersPage(driver);
		return op;
	}
	
	

}
