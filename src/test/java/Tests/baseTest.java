package Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import MyProect.pagefatory.loginPage;

public class baseTest {
	
	public WebDriver driver;
	public loginPage lp;
	
	public WebDriver initializeBrowser() throws IOException {
	
		//properties Class
		Properties pro= new Properties();
		FileInputStream fs=new FileInputStream("C:\\Users\\anusha.patti\\eclipse-workspace\\SeleniumProject\\src\\main\\java\\Resources\\GlobalData.Properties");
		pro.load(fs);
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser") : pro.getProperty("browser");
		//String browserName = pro.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
			 driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox")){
			 driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge")){
			 driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public loginPage launchapp() throws IOException {
		driver = initializeBrowser();
		lp = new loginPage(driver);
		lp.gotoURL();
		return lp;
	}
	@AfterMethod(alwaysRun=true)
	public void closebrowser() {
		driver.close();
	}
	
	public List<HashMap<String, String>> getJsontoHash(String filepath) throws IOException, JsonParseException {
		//json to string
		String json=FileUtils.readFileToString(new File(filepath),
				StandardCharsets.UTF_8);
		
		//string to hashmap
		ObjectMapper om= new ObjectMapper();
		
		List<HashMap<String, String>> data=om.readValue(json, new TypeReference<List<HashMap<String, String>>>(){});
		return data;
	}
	
	public String screenshot(String testCaseName, WebDriver driver) throws IOException {
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File path = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, path);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}
}
