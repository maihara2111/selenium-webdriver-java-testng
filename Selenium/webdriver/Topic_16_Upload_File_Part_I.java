package webdriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
//import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//Topic 26 - Ubuntu - Linux
public class Topic_16_Upload_File_Part_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String uploadFilePath = projectPath + File.separator +"\\UploadFiles\\" + File.separator;
	
	String mh1Name = "MH1.jpg";
	String mh2Name = "MH2.jpg";
	String mh3Name = "MH3.jpg";
	
	String mh1FilePath = uploadFilePath + mh1Name;
	String mh2FilePath = uploadFilePath + mh2Name;
	String mh3FilePath = uploadFilePath + mh3Name;
	

	@BeforeClass
	public void beforeClass() {
		/*
		 * System.setProperty("webdriver.gecko.driver", projectPath +
		 * "//BrowserDrivers/geckodriver.exe");
		 * driver = new FirefoxDriver();
		 */
		//System.setProperty("webdriver.chrome.driver", projectPath + "//BrowserDrivers/chromedriver.exe");
		//driver = new ChromeDriver();
		
		System.setProperty("webdriver.edge.driver", projectPath + "//BrowserDrivers/msedgedriver.exe");
		driver = new EdgeDriver();
		
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
	public void TC_01_Sendkey_OneFile() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		By uploadFile = By.xpath("//input[@type='file']");
		
		//LoadFile
		driver.findElement(uploadFile).sendKeys(mh1FilePath);
		sleepInSecond(1);
		driver.findElement(uploadFile).sendKeys(mh2FilePath);
		sleepInSecond(1);
		driver.findElement(uploadFile).sendKeys(mh3FilePath);		sleepInSecond(1);
		sleepInSecond(1);
		
		//Verify file loaded success
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ mh1Name +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ mh2Name +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ mh3Name +"']")).isDisplayed());
		
		//Click to start button > Upload
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button[class*='start']"));
		
		for (WebElement start : startButtons) {
			start.click();
			sleepInSecond(2);
		}
		
		//Verify file uploaded success
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+ mh1Name +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+ mh2Name +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+ mh3Name +"']")).isDisplayed());
	}

	@Test
	public void TC_02_Sendkey_MultipleFile() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		By uploadFile = By.xpath("//input[@type='file']");
		
		//Note: Vs firefox old version 47 > not work
		//Vs firefox new version > work
		//LoadFile
		driver.findElement(uploadFile).sendKeys(mh1FilePath + "\n" + mh2FilePath + "\n" + mh3FilePath);
		
		//Verify file loaded success
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ mh1Name +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ mh2Name +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ mh3Name +"']")).isDisplayed());
		
		//Click to start button > Upload
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button[class*='start']"));
		
		for (WebElement start : startButtons) {
			start.click();
			sleepInSecond(2);
		}
		
		//Verify file uploaded success
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+ mh1Name +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+ mh2Name +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+ mh3Name +"']")).isDisplayed());
		
	}

	@Test
	public void TC_03() {
		//Case upload file ko có thẻ input
		//try find element có thẻ input trong đó
		//chắc chắn 100% có thẻ input nên tìm sẽ thấy
		//senkey ko care visible or presence vì nó ko phải là hành vi user
		//click thì cần phải visible
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}