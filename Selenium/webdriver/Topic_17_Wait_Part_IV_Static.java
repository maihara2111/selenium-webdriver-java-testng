package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
//import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//Topic 30
public class Topic_17_Wait_Part_IV_Static {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	By startButtonBy = By.cssSelector("div#start button");
	By loadingIconBy = By.cssSelector("#loading");
	By helloWorldTextBy = By.cssSelector("#finish h4");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "//BrowserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();

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
	public void TC_01_Less_Than() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(startButtonBy).click();
		
		//Time bị thiếu
		sleepInSecond(2);

		//Thiếu 3s
		
		Assert.assertEquals(driver.findElement(helloWorldTextBy).getText(), "Hello World!");
	}

	@Test
	public void TC_02_Enough() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(startButtonBy).click();
		
		//Time vừa đủ
		//Khó biết bao nhiu s là đủ
		//Lúc này xx time
		//Lúc khác <xx time
		//Lúc khác >xx time
		sleepInSecond(5);

		Assert.assertEquals(driver.findElement(helloWorldTextBy).getText(), "Hello World!");

	}

	@Test
	public void TC_03_Grater_Than() {
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(startButtonBy).click();

		//Time bị dư => Pass testcase
		//chay đc/chay đúng nhưng chưa tối ưu
		//1 step dư mất 5s
		//Set sleep này cho nhìu step trong 1 testcase
		//1 testcase có 30 step
		//Set 1 nửa các step: 15 step x 5s = 75s ~ 1m15s/ 1 testcase
		//200-300 TCs x 1m15s ~ > 3 tiếng
		
		Assert.assertEquals(driver.findElement(helloWorldTextBy).getText(), "Hello World!");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}