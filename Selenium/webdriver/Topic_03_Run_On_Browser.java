package webdriver;

import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Run_On_Browser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	

	@BeforeClass
	//public void beforeClass() {
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	//}

	@Test
	public void TC_01_Run_On_Firefox() {
		// Older Firefox version (47.0.2)
		//driver = new FirefoxDriver();
		
		//Lastest version(>=48)
		//System.setProperty("webdriver.gecko.driver", projectPath + "//BrowserDrivers/geckodriver.exe");
		//driver = new FirefoxDriver();
		
		//driver.get("http://live.demoguru99.com");
		//driver.quit();
	}

	@Test
	public void TC_02_Run_On_Chrome() {
		System.setProperty("webdriver.chrome.driver", projectPath + "//BrowserDrivers/chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.get("http://live.demoguru99.com");
		driver.quit();
	}

	@Test
	public void TC_03_Run_On_Edge_Chromium() {
		System.setProperty("webdriver.edge.driver", projectPath + "//BrowserDrivers/msedgedriver.exe");
		driver = new EdgeDriver();
		
		driver.get("http://live.demoguru99.com");
		driver.quit();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}