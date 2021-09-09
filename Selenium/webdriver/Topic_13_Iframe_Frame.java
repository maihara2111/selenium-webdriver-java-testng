package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
//import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Iframe_Frame {
	WebDriver driver;
	JavascriptExecutor jsExcutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "//BrowserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();

		jsExcutor = (JavascriptExecutor) driver;
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
	public void TC_01_Iframe() {
		driver.get("https://kyna.vn/");
		sleepInSecond(5);

		scrollToButtomPage();
		
		// Index (If CRUD 1 iframe/frame thì index thay đổi)
		// driver.switchTo().frame(0);

		// Name or ID: rat ít có iframe/frame use name or id

		// Switch vao facebook fanpage iframe
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.fanpage iframe")));
		sleepInSecond(2);
		
		// Verify facebook page = 168k luot thich
		Assert.assertEquals(driver.findElement(
				By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText(), "168K lượt thích");

		// Ve parent page truoc
		driver.switchTo().defaultContent();

		// Switch vao chat iframe
		driver.switchTo().frame(driver.findElement(By.cssSelector("#cs_chat_iframe")));
		sleepInSecond(2);
		
		//click vao chat iframe
		driver.findElement(By.cssSelector("div.button_bar")).click();;
		sleepInSecond(5);

		//click to button gui tin nhan
		driver.findElement(By.cssSelector("input.submit")).click();
		
		//Verify text
		Assert.assertEquals(driver.findElement(By.cssSelector("input.input_name+div.error_message")).getText(), "Tên của bạn chưa được nhập");
		Assert.assertEquals(driver.findElement(By.cssSelector("select#serviceSelect+div.error_message")).getText(), "Bạn chưa chọn dịch vụ hỗ trợ");
		
		// Ve parent page truoc
		driver.switchTo().defaultContent();

		//enter keywork to search box
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("excel");
		
		driver.findElement(By.cssSelector("button.search-button")).click();
		
		List<WebElement> courseName = driver.findElements(By.cssSelector("section div.content h4"));
		
		//Verify course number = 10
		Assert.assertEquals(courseName.size(), 10);
		
		//Verify course name contains Excel
		//for each
		for (WebElement course : courseName) {
			System.out.println(course.getText());
			Assert.assertTrue(course.getText().toLowerCase().contains("excel"));
			Assert.assertTrue(course.getText().toLowerCase().matches("(.*)excel(.*)"));
		}
		
		//for interate
		for (int i = 0; i < courseName.size(); i++) {
			Assert.assertTrue(courseName.get(i).getText().toLowerCase().contains("excel"));
			Assert.assertTrue(courseName.get(i).getText().toLowerCase().matches("(.*)excel(.*)"));
		}
	}

	@Test
	public void TC_02_frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		sleepInSecond(5);
		
		//Switch vao frame login
		driver.switchTo().frame("login_page");
		
		//Enter text vao login form
		driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("automationfc");
		
		//click vao continue button
		driver.findElement(By.cssSelector("div.loginData a.login-btn")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("input[name='fldPassword']")).isDisplayed());
		
		//back to parent page
		driver.switchTo().defaultContent();
		
		//Switch vao frame hearder
		driver.switchTo().frame("login_page");
	
		//click vao tern and condition at footer
		driver.findElement(By.xpath("//a[text()='Terms and Conditions']")).click();
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void scrollToButtomPage() {
		jsExcutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	
}