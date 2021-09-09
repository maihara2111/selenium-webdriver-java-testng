package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_User_Interaction_Part_I {
	WebDriver driver;
	Actions action;
	
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "//BrowserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();		
		action = new Actions(driver);
		
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
	public void TC_01_Hover_Mouse_I() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		
		//hover on textbox
		action.moveToElement(driver.findElement(By.id("age"))).perform();
		sleepInSecond(4);
		
		Assert.assertEquals(driver.findElement(By.cssSelector(".ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
		
		
		//driver.findElement(By.xpath("")).click();
		
		//action.click(driver.findElement(By.xpath("")));
	}

	@Test
	public void TC_01_Hover_Mouse_Tiki() {
		driver.get("https://tiki.vn");
		action.moveToElement(driver.findElement(By.cssSelector(".profile-icon"))).perform();
		sleepInSecond(3);

		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='tel']")).isDisplayed());
		
	}

	@Test
	public void TC_01_Hover_Mouse_II() {
		driver.get("https://www.myntra.com/");
		
		action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Kids']"))).perform();
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//a[@href='/kids-home-bath']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb' and text()='Kids Home Bath']")).isDisplayed());
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.myntra.com/kids-home-bath");
	}
	
	@Test
	public void TC_02_Click_And_Hold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");

		List<WebElement> rectangleNumber = driver.findElements(By.cssSelector("#selectable>li"));
		System.out.println("Number of rectangle = " + rectangleNumber.size());
		
		//Click and Hold to fisrt element > hover to end element > nha chuot trai ra
		action.clickAndHold(rectangleNumber.get(0))
		.moveToElement(rectangleNumber.get(3))
		.release()
		.perform();
		
		Assert.assertEquals(driver.findElements(By.cssSelector("#selectable>li.ui-selected")).size(), 4);
	}

	@Test
	public void TC_02_Click_And_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");

		List<WebElement> rectangleNumber = driver.findElements(By.cssSelector("#selectable>li"));
		
		//Type ctrl
		action.keyDown(Keys.CONTROL).perform();
		
		//choose elements (1-3-6-11)
		action.click(rectangleNumber.get(0));
		action.click(rectangleNumber.get(2));
		action.click(rectangleNumber.get(5));
		action.click(rectangleNumber.get(10)).perform();
		
		//Nhả phím ctrl ra
		action.keyUp(Keys.CONTROL);
		sleepInSecond(4);
		
		Assert.assertEquals(driver.findElements(By.cssSelector("#selectable>li.ui-selected")).size(), 4);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}