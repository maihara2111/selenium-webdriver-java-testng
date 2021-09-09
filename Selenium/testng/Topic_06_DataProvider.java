package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_06_DataProvider {
	WebDriver driver;
	By emailTextbox = By.id("email");
	By passwordTextbox = By.id("pass");
	By loginButton = By.id("send2");
	
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "//BrowserDrivers/geckodriver.exe");

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
  @Test(dataProvider="user_pass")
  public void TC_01_Login_To_System(String username, String password) {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		
		driver.findElement(emailTextbox).sendKeys(username);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(loginButton).click();

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(username));
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		
		//Action
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
  }
  
  @DataProvider(name="user_pass")
  public Object[][] UserAndPasswordData() {
	  return new Object[][] {
		  {"selenium_11_01@gmail.com", "111111"},
		  {"selenium_11_02@gmail.com", "111111"},
		  {"selenium_11_03@gmail.com", "111111"}};
}
  
  @Test()
  public void TC_02() {
	  System.out.println("Run TC 02");
  }

}
