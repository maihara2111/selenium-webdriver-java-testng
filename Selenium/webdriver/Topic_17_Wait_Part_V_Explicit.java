package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_Part_V_Explicit {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	By startButtonBy = By.cssSelector("div#start button");
	By loadingIconBy = By.cssSelector("#loading");
	By helloWorldTextBy = By.cssSelector("#finish h4");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "//BrowserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();
		
		explicitWait = new WebDriverWait(driver, 15);
		
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//Explicit wait: tường minh (có trạng thái cụ thể: visible/ presence/...
	//...invisible/ staleness/ clickable/ numberless/ greater/...)
	
	@Test
	public void TC_01_Less_Than() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		explicitWait = new WebDriverWait(driver, 15);
		
		//Truoc khi click thi wait cho start button có thể được click chưa
		explicitWait.until(ExpectedConditions.elementToBeClickable(startButtonBy));
		driver.findElement(startButtonBy).click();
		
		explicitWait = new WebDriverWait(driver, 3);
		
		//Business: If loading icon biến mất = helloworld text hiển thị
		
		//Wait for loading icon biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIconBy));
		
		Assert.assertEquals(driver.findElement(helloWorldTextBy).getText(), "Hello World!");
	}

	@Test
	public void TC_02_Enough() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		explicitWait = new WebDriverWait(driver, 15);
		
		//Truoc khi click thi wait cho start button có thể được click chưa
		explicitWait.until(ExpectedConditions.elementToBeClickable(startButtonBy));
		driver.findElement(startButtonBy).click();
		
		explicitWait = new WebDriverWait(driver, 5);
		
		//Business: If loading icon biến mất = helloworld text hiển thị
		
		//Wait for loading icon biến mất
		//explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIconBy));
		
		//Wait for helloworld text hien thi
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloWorldTextBy));
		
		Assert.assertEquals(driver.findElement(helloWorldTextBy).getText(), "Hello World!");
	}

	@Test
	public void TC_03_Greater_Than() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		explicitWait = new WebDriverWait(driver, 15);
		
		//Truoc khi click thi wait cho start button có thể được click chưa
		explicitWait.until(ExpectedConditions.elementToBeClickable(startButtonBy));
		driver.findElement(startButtonBy).click();
		
		explicitWait = new WebDriverWait(driver, 15);
		
		//Business: If loading icon biến mất = helloworld text hiển thị
		
		//Wait for loading icon biến mất
		//explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIconBy));
		
		//Wait for helloworld text hien thi
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloWorldTextBy));

		Assert.assertEquals(driver.findElement(helloWorldTextBy).getText(), "Hello World!");
	}

	@Test
	public void TC_04_Ajax_Loading() {
		explicitWait = new WebDriverWait(driver, 15);
		
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(), "No Selected Dates to display.");
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='1']")));
		
		driver.findElement(By.xpath("//a[text()='1']")).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']")));
		
		Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(), "Wednesday, September 1, 2021");
	
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']/a[text()='1']")));

		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']/a[text()='1']")).isDisplayed());
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}