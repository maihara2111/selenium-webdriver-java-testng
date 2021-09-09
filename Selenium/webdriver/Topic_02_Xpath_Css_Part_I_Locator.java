package webdriver;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css_Part_I_Locator {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
	}
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void TC_01_ID() {
		driver.findElement(By.id("FirstName")).sendKeys("Automation Testing");
		
		driver.findElement(By.id("Email")).sendKeys("AutomationTesting@gmail.com");
	}

	@Test
	//Classname is unique
	public void TC_02_Classname() {
		driver.findElement(By.className("search-box-text")).sendKeys("Macbook");
	}

	@Test
	public void TC_03_Name() {
		driver.findElement(By.name("Company")).sendKeys("Selenium");
	}
	@Test
	public void TC_04_Tagname() {
		System.out.println(driver.findElements(By.tagName("select")).size());;
	}
	@Test
	public void TC_05_Link_Text() {
		Assert.assertTrue(driver.findElement(By.linkText("Register")).isDisplayed());
		driver.findElement(By.linkText("Log in")).click();
		sleepInSecond(3);
	}


	@Test
	public void TC_06_Partial_Link_Text() {
		Assert.assertTrue(driver.findElement(By.partialLinkText("Digital")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.partialLinkText("downloads")).isDisplayed());
		driver.findElement(By.partialLinkText("Recently viewed products")).click();
		sleepInSecond(3);
	}
	@Test
	public void TC_07_Css() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		
		driver.findElement(By.cssSelector("#Password")).sendKeys("P@ssword");
		sleepInSecond(3);
		
		//driver.findElement(By.cssSelector("input[name='ConfirmPassword']")).sendKeys("P@ssword");
		sleepInSecond(3);
		
		driver.findElement(By.cssSelector("input[id='FirstName']")).sendKeys("Mai");
		sleepInSecond(3);
		
		driver.findElement(By.cssSelector("input[class='search-box-text ui-autocomplete-input']")).sendKeys("Phone");
		sleepInSecond(3);
		
		driver.findElement(By.cssSelector("input[name='Email']")).sendKeys("dtnmaihara11@gmail.com");
		sleepInSecond(3);
		
		driver.findElement(By.cssSelector("a[href*='login']")).click();
		sleepInSecond(3);
	}

	@Test
	public void TC_08_Xpath() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		
		driver.findElement(By.xpath("//input[@name='ConfirmPassword']")).clear();
		sleepInSecond(3);
		
		//driver.findElement(By.xpath("//input[@name='ConfirmPassword']")).sendKeys("P@ssword");
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Automation Test");
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//input[@class='search-box-text ui-autocomplete-input']")).sendKeys("Automation Test");
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//a[text()='Log in']")).click();
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//a[contains(text(),'Recently viewed')]")).click();
		sleepInSecond(3);
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}