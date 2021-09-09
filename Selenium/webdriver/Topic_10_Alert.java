package webdriver;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//import junit.framework.Assert;

public class Topic_10_Alert {
	WebDriver driver;
	WebDriverWait expliciWait;
	Alert alert;
	String projectPath = System.getProperty("user.dir");
	String authAutoIT = projectPath + "/autoIT/authen_firefox.exe";
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "//BrowserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();

		expliciWait = new WebDriverWait(driver, 10);
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
	public void TC_01_Accept_Alert_1() {
		driver.get("http://demo.guru99.com/v4/index.php");

		driver.findElement(By.name("btnLogin")).click();

		// wait for alert xuat hien + Switch qua alert
		alert = expliciWait.until(ExpectedConditions.alertIsPresent());
		sleepInSecond(3);

		// Verify alert text
		Assert.assertEquals(alert.getText(), "User or Password is not valid");

		// Accept
		alert.accept();
	}

	@Test
	public void TC_01_Accept_Alert_2() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSecond(1);

		alert = expliciWait.until(ExpectedConditions.alertIsPresent());

		// Verify alert text
		Assert.assertEquals(alert.getText(), "I am a JS Alert");

		// Accept
		alert.accept();
		sleepInSecond(1);

		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");
	}

	@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

		alert = expliciWait.until(ExpectedConditions.alertIsPresent());

		// Verify alert text
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");

		// Cancel alert -> Mat alert
		alert.dismiss();
		sleepInSecond(1);

		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");
	}

	@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String fullname = "Mai Hara";

		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

		alert = expliciWait.until(ExpectedConditions.alertIsPresent());

		// Verify alert text
		Assert.assertEquals(alert.getText(), "I am a JS prompt");

		// Sendkey to alert
		alert.sendKeys(fullname);
		sleepInSecond(1);

		// Accept -> Mat alert
		alert.accept();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: " + fullname);

	}

	@Test
	public void TC_04_Authentication_Alert() {
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		sleepInSecond(3);

		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		
	}

	@Test
	public void TC_05_Authentication_Alert() {
		driver.get("http://the-internet.herokuapp.com");
		
		String hrefValue = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		System.out.println(hrefValue);
		
		//http://the-internet.herokuapp.com/basic_auth
		//http://admin:admin@the-internet.herokuapp.com/basic_auth
		
		passValueToUrl(hrefValue, "admin", "admin");
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());

	}
	
	@Test
	public void TC_06_Authentication_Alert_AutoIT() throws IOException  {
		//Execute script truoc
		Runtime.getRuntime().exec(new String[] { authAutoIT, "admin", "admin" });
		
		//Open app sau
		driver.get("http://the-internet.herokuapp.com");
		sleepInSecond(5);

		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		//error
	}
	public void passValueToUrl(String url, String username, String password) {
		String[] hrefValue = url.split("//");
		url = hrefValue[0] + "//" + username + ":" + password + "@" + hrefValue[1];
		driver.get(url);
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}