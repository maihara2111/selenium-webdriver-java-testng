package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
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

import com.sun.glass.events.KeyEvent;

//Topic 27 - AutoIT - Robot
public class Topic_16_Upload_File_Part_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String uploadFilePath = projectPath + File.separator + "UploadFiles" + File.separator;

	String firefoxOnePath = projectPath + "\\autoIT\\firefoxUploadOneTime.exe";
	String firefoxMultiPath = projectPath + "\\autoIT\\firefoxUploadMultiple.exe";
	String chromeOnePath = projectPath + "\\autoIT\\chromeUploadOneTime.exe";
	String chromeMultiPath = projectPath + "\\autoIT\\chromeUploadMultiple.exe";

	String mh1Name = "MH1.jpg";
	String mh2Name = "MH2.jpg";
	String mh3Name = "MH3.jpg";

	String mh1FilePath = uploadFilePath + mh1Name;
	String mh2FilePath = uploadFilePath + mh2Name;
	String mh3FilePath = uploadFilePath + mh3Name;

	@BeforeClass
	public void beforeClass() {
		// System.setProperty("webdriver.gecko.driver", projectPath +
		// "//BrowserDrivers/geckodriver.exe");
		// driver = new FirefoxDriver();

		// System.setProperty("webdriver.chrome.driver", projectPath +
		// "//BrowserDrivers/chromedriver.exe");
		// driver = new ChromeDriver();

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
	public void TC_01_AutoIT_OneFile() throws IOException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		// click to add file button > show open file Dialog
		driver.findElement(By.cssSelector("span.btn-success")).click();
		if (driver.toString().contains("firefox")) {
			Runtime.getRuntime().exec(new String[] { firefoxOnePath, mh1FilePath });
		} else if (driver.toString().contains("chrome") || driver.toString().contains("edge")) {
			Runtime.getRuntime().exec(new String[] { chromeOnePath, mh1FilePath });
		}
		// Open file dialog > use AutoIT de xu li doan load file len
		sleepInSecond(5);

		driver.findElement(By.cssSelector("span.btn-success")).click();
		if (driver.toString().contains("firefox")) {
			Runtime.getRuntime().exec(new String[] { firefoxOnePath, mh2FilePath });
		} else if (driver.toString().contains("chrome") || driver.toString().contains("edge")) {
			Runtime.getRuntime().exec(new String[] { chromeOnePath, mh2FilePath });
		}
		sleepInSecond(5);

		driver.findElement(By.cssSelector("span.btn-success")).click();
		if (driver.toString().contains("firefox")) {
			Runtime.getRuntime().exec(new String[] { firefoxOnePath, mh3FilePath });
		} else if (driver.toString().contains("chrome") || driver.toString().contains("edge")) {
			Runtime.getRuntime().exec(new String[] { chromeOnePath, mh3FilePath });
		}
		sleepInSecond(2);

		// Verify file loaded success
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + mh1Name + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + mh2Name + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + mh3Name + "']")).isDisplayed());

		// Click to start button > Upload
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button[class*='start']"));

		for (WebElement start : startButtons) {
			start.click();
			sleepInSecond(2);
		}

		// Verify file uploaded success
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + mh1Name + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + mh2Name + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + mh3Name + "']")).isDisplayed());
	}

	@Test
	public void TC_02_AutoIT_MultipleFile() throws IOException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		// click to add file button > show open file Dialog
		driver.findElement(By.cssSelector("span.btn-success")).click();
		if (driver.toString().contains("firefox")) {
			Runtime.getRuntime().exec(new String[] { firefoxMultiPath, mh1FilePath, mh2FilePath, mh3FilePath });
		} else if (driver.toString().contains("chrome") || driver.toString().contains("edge")) {
			Runtime.getRuntime().exec(new String[] { chromeMultiPath, mh1FilePath, mh2FilePath, mh3FilePath });
		}
		// Open file dialog > use AutoIT de xu li doan load file len
		sleepInSecond(8);

		// Verify file loaded success
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + mh1Name + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + mh2Name + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + mh3Name + "']")).isDisplayed());

		// Click to start button > Upload
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button[class*='start']"));

		for (WebElement start : startButtons) {
			start.click();
			sleepInSecond(2);
		}

		// Verify file uploaded success
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + mh1Name + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + mh2Name + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + mh3Name + "']")).isDisplayed());
	}

	@Test
	public void TC_03_Java_Robot() throws AWTException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		//Specify the file location with extension
		StringSelection select = new StringSelection(mh1FilePath);
		
		//Copy path cua file save to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
		
		// click to add file button > show open file Dialog
		driver.findElement(By.cssSelector("span.btn-success")).click();
		
		Robot robot = new Robot();
		sleepInSecond(2);
		
		//nhan phim enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		//Nhan ctrl V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		sleepInSecond(5);
		
		//nha ctrl V
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		sleepInSecond(5);
		
		//Nhan enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		// Verify file loaded success
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + mh1Name + "']")).isDisplayed());
		//Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + mh2Name + "']")).isDisplayed());
		//Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + mh3Name + "']")).isDisplayed());

		// Click to start button > Upload
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button[class*='start']"));

		for (WebElement start : startButtons) {
			start.click();
			sleepInSecond(2);
		}

		// Verify file uploaded success
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + mh1Name + "']")).isDisplayed());
		//Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + mh2Name + "']")).isDisplayed());
		//Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + mh3Name + "']")).isDisplayed());

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}