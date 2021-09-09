package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
//import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdown_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "//BrowserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void TC_01_JQuery() {
		driver.get("http://indrimuska.github.io/jquery-editable-select/");

		//input
		driver.findElement(By.cssSelector("#default-place input")).sendKeys("Audi");
		driver.findElement(By.cssSelector("#default-place input")).sendKeys(Keys.TAB);
		
		//Verify
		String inputValue = (String) jsExecutor.executeScript("return document.querySelector('#default-place input').value");
		Assert.assertEquals(inputValue, "Audi");
		
		//input
		driver.findElement(By.cssSelector("#default-place input")).clear();
		driver.findElement(By.cssSelector("#default-place input")).sendKeys("BMW");
		driver.findElement(By.cssSelector("#default-place input")).sendKeys(Keys.TAB);
		
		//Verify
		inputValue = (String) jsExecutor.executeScript("return document.querySelector('#default-place input').value");
		Assert.assertEquals(inputValue, "BMW");
		
	}

	@Test
	public void TC_02() {

	}

	@Test
	public void TC_03() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}