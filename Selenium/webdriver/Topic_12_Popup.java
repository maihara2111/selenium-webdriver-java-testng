package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Popup {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "//BrowserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();
		
		jsExecutor = (JavascriptExecutor) driver;
		
		explicitWait = new WebDriverWait(driver, 20);
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
	public void TC_01_Fixed_Popup() {
		driver.get("https://ngoaingu24h.vn/");

		driver.findElement(By.cssSelector("button.login_")).click();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div#modal-login-v1>div")).isDisplayed());
		
		driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1 div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
		
		driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();
		
		Assert.assertFalse(driver.findElement(By.cssSelector("div#modal-login-v1>div")).isDisplayed());

	}

	@Test
	public void TC_02_Random_In_DOM() {
		driver.get("https://blog.testproject.io/");
		sleepInSecond(50);
		
		WebElement popup = driver.findElement(By.cssSelector("div.mailch-wrap"));
		//Neu no hien thi thi phai close de thuc hien cac step sau
		if(popup.isDisplayed()) {
			//close popup
			System.out.println("Popup is displayed");
			driver.findElement(By.cssSelector("div.close-mailch")).click();
			sleepInSecond(2);
		} else {
			System.out.println("Popup is not displayed");
		}
		
		//Neu no hien thi thi phai close de thuc hien cac step sau
		driver.findElement(By.cssSelector("#search-2 input.search-field")).sendKeys("Selenium");
		driver.findElement(By.cssSelector("#search-2 span.glass")).click();
		sleepInSecond(5);
		
		//Verify title chua text = selenium
		List<WebElement> articleTitle = driver.findElements(By.cssSelector("h3.post-title>a"));
		
		for (WebElement article : articleTitle) {
			Assert.assertTrue(article.getText().contains("Selenium"));
		}
	}
	@Test
	public void TC_02_Random_In_DOM_Use_WaitFunction() {
		driver.get("https://blog.testproject.io/");
		Assert.assertTrue(isJQueryLoadedSuccess());
		
		WebElement popup = driver.findElement(By.cssSelector("div.mailch-wrap"));
		//Neu no hien thi thi phai close de thuc hien cac step sau
		if(popup.isDisplayed()) {
			//close popup
			System.out.println("Popup is displayed");
			driver.findElement(By.cssSelector("div.close-mailch")).click();
			sleepInSecond(2);
		} else {
			System.out.println("Popup is not displayed");
		}
		
		//Neu no hien thi thi phai close de thuc hien cac step sau
		driver.findElement(By.cssSelector("#search-2 input.search-field")).sendKeys("Selenium");
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#search-2 span.glass")));
		driver.findElement(By.cssSelector("#search-2 span.glass")).click();
		
		//Verify title chua text = selenium
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("h3.post-title>a")));
		List<WebElement> articleTitle = driver.findElements(By.cssSelector("h3.post-title>a"));
		
		for (WebElement article : articleTitle) {
			Assert.assertTrue(article.getText().contains("Selenium"));
		}
	}


	@Test
	public void TC_03_Random_Not_In_DOM() {
		driver.get("https://shopee.vn/");
		sleepInSecond(5);
	
		//if element ko co trong DOM thi ham Findelememt
		//wait het timeout cua implicit
		//Danh fail testcase ngay tai step do lun
		//Throw ra 1 exception: NoSuchElement
		//WebElement popup = driver.findElement(By.cssSelector("div.shopee-popup img"));
		
		//if element ko co trong DOM thi ham Findelememt
		//No se tra ve 1 list empty (size = 0)
		//Ko danh fail testcase
		//Ko throw exception
		List<WebElement> pop_up = driver.findElements(By.cssSelector("div.shopee-popup img"));
		
		//Neu no hien thi thi phai close de thuc hien cac step sau
		if(pop_up.size()>0 && pop_up.get(0).isDisplayed()) {
			//close popup
			System.out.println("Popup is displayed");
			driver.findElement(By.cssSelector("div.shopee-popup__close-btn")).click();
			sleepInSecond(2);
		} else {
			System.out.println("Popup is not displayed");
		}
		
		driver.findElement(By.cssSelector("input.shopee-searchbar-input__input")).sendKeys("iphone xs max");
		driver.findElement(By.cssSelector("div.shopee-searchbar>button")).click();
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	private boolean isJQueryLoadedSuccess() {
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.
						executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
		return explicitWait.until(jQueryLoad);
	}
}