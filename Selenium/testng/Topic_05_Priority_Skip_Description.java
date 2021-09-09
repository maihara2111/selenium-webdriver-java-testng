package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_05_Priority_Skip_Description {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void initBrowser() {
		System.setProperty("webdriver.gecko.driver", projectPath + "//BrowserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();
		System.out.println("Open browser");
		
		Assert.assertTrue(false);
	}

	@Test(groups = { "user" })
	public void TC_01_Create_User() {

	}

	@Test(groups = { "employee", "admin" })
	public void TC_02_View_User() {

	}

	@Test(groups = { "employee", "admin" })
	public void TC_03_Edit_User() {

	}

	@Test(groups = { "employee", "admin" })
	public void TC_04_Delete_User() {
	}

	// If before fail thì after still run if use alwaysRun = true
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		System.out.println("Run After Class");
		driver.quit();
	}
}
