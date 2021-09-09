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


//Topic 29
public class Topic_17_Wait_Part_III_Implicit {
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

	// Implicit wait: ngầm định (ko có cho 1 trạng thái nào cụ thể)
	// - duoc su dung cho find element/find elements
	// - tim thay element thi ko can chờ hết timeout
	// - ko tim thay thi co cơ chế tim lai > tim lai ko thay lun > output
	// (findElement/findElements)
	// - Neu nhu ko set thi mac dinh = 0
	// - Neu nhu set roi ma sau đó ở 1 step sau dc set lai thi se bi overide

	@Test
	public void TC_01() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.get("https://www.facebook.com/");
		driver.findElement(By.name("lastname")).sendKeys("Auto");
	}

	@Test
	public void TC_02() {
		driver.get("https://www.facebook.com/");
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

		driver.findElement(By.name("firstname")).sendKeys("Auto");
	}

	@Test
	public void TC_03() {
		driver.get("https://www.facebook.com/");
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

		driver.findElement(By.name("login")).sendKeys("Auto");

	}

	@Test
	public void TC_01_Less_Than() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(startButtonBy).click();

		// Mất 5s thì helloworld mới xuất hiện trong DOM

		// Tim element helloWorldTextBy
		// 0.5 đầu tiên tìm ko thấy
		// 0.5 tiếp theo tìm ko thấy
		// 0.5 tiếp theo tìm ko thấy
		// 0.5 tiếp theo tìm ko thấy > hết 2s - timeout hết

		Assert.assertEquals(driver.findElement(helloWorldTextBy).getText(), "Hello World!");
	}

	@Test
	public void TC_02_Enough() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(startButtonBy).click();

		// Mất 5s thì helloworld mới xuất hiện trong DOM

		// Tim element helloWorldTextBy
		// 0.5 đầu tiên tìm ko thấy
		// 0.5 tiếp theo tìm ko thấy
		// 0.5 tiếp theo tìm ko thấy
		// 0.5 tiếp theo tìm ko thấy
		// 0.5 tiếp theo tìm ko thấy
		// 0.5 tiếp theo tìm ko thấy
		// 0.5 tiếp theo tìm ko thấy
		// 0.5 tiếp theo tìm ko thấy
		// 0.5 tiếp theo tìm ko thấy
		// 0.5 tiếp theo tìm thấy

		Assert.assertEquals(driver.findElement(helloWorldTextBy).getText(), "Hello World!");

	}

	@Test
	public void TC_03_Grater_Than() {
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(startButtonBy).click();

		// Mất 5s thì helloworld mới xuất hiện trong DOM

		// Tim element helloWorldTextBy
		// 0.5 đầu tiên tìm ko thấy
		// 0.5 tiếp theo tìm ko thấy
		// 0.5 tiếp theo tìm ko thấy
		// 0.5 tiếp theo tìm ko thấy
		// 0.5 tiếp theo tìm ko thấy
		// 0.5 tiếp theo tìm ko thấy
		// 0.5 tiếp theo tìm ko thấy
		// 0.5 tiếp theo tìm ko thấy
		// 0.5 tiếp theo tìm ko thấy
		// 0.5 tiếp theo tìm thấy - giây thứ 5
		// 0.5 tiếp theo ko thấy - giây thứ 6/7/8

		Assert.assertEquals(driver.findElement(helloWorldTextBy).getText(), "Hello World!");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}