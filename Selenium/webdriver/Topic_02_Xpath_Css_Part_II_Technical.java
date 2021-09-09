package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css_Part_II_Technical {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		// Time to find element
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
	public void TC_01_Login_Empty_Email_Password() {
		// Open Home page
		driver.get("http://live.demoguru99.com/index.php");

		// Open My Account Page at footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("pass")).sendKeys("");
		driver.findElement(By.name("send")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(), "This is a required field.");
	}

	@Test
	public void TC_02_Login_Invalid_Email() {
		// Open Home page
		driver.get("http://live.demoguru99.com/index.php");

		// Open My Account Page at footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("123@345.111");
		driver.findElement(By.id("pass")).sendKeys("2221111");
		driver.findElement(By.name("send")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
		
		//use import static org.testng...
		assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void TC_03_Login_Invalid_Password() {
		// Open Home page
		driver.get("http://live.demoguru99.com/index.php");

		// Open My Account Page at footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		driver.findElement(By.id("email")).sendKeys("mai@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("1111");
		driver.findElement(By.name("send")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	
	}

	@Test
	public void TC_04_Login_Incorrect_Password() {
		// Open Home page
		driver.get("http://live.demoguru99.com/index.php");

		// Open My Account Page at footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		driver.findElement(By.id("email")).sendKeys("mai@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("11112222");
		driver.findElement(By.name("send")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password.");
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}