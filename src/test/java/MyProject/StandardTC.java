package MyProject;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

public class StandardTC {

	public static void main(String[] args) throws InterruptedException  {
		String product="ADIDAS ORIGINAL";
		String address="india";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.findElement(By.id("userEmail")).sendKeys("rajpatti@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Revariya@2019");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='card-body']")));
		List<WebElement> list = driver.findElements(By.xpath("//*[@class='card-body']"));
		
		
		for(int i=0;i<list.size();i++) {
			String name = driver.findElements(By.xpath("//*[@class='card-body']/h5/b")).get(i).getText();
			if(name.equalsIgnoreCase(product)) {
				driver.findElements(By.xpath("//*[@class='btn w-10 rounded']")).get(i).click();
			}
		}
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("toast-container"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
				
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Checkout')]")));
		driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();
		driver.findElement(By.cssSelector("[class='form-group'] [class='input txt text-validated']")).sendKeys(address);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='ta-results list-group ng-star-inserted']")));
		
		List<WebElement> values = driver.findElements(By.cssSelector("[class='ta-item list-group-item ng-star-inserted']"));
		for(int i=0;i<values.size();i++) {
			String seletion = values.get(i).findElement(By.cssSelector("[class='ng-star-inserted']")).getText();
			if(seletion.equalsIgnoreCase(address)) {
				values.get(i).findElement(By.cssSelector("[class='ng-star-inserted']")).click();
				break;
			}
		}
		
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1200)");
		Thread.sleep(1000);
			
		Actions a= new Actions(driver);
		a.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'Place Order')]"))).click().build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
		String confirmation = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmation.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
	}

}
