package webdriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_17_Wait_Part_VII_Fluent {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;

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
	public void TC_01() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		waitForElementAndClick(By.cssSelector("div#start button"));
		//clickToElement(By.cssSelector("div#start button"));
		
		Assert.assertTrue(waitForElementAndDisplayed(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")));
	}

	@Test
	public void TC_02_Implicit() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);

		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='javascript_countdown_time' and text()='01:01:00']")).isDisplayed());
	}

	@Test
	public void TC_03_Explicit() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		
		explicitWait = new WebDriverWait(driver, 13);
		
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='javascript_countdown_time' and text()='01:01:00']")));
	}

	@Test
	public void TC_03_Fluent() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		WebElement countDownTime = driver.findElement(By.id("javascript_countdown_time"));
		//Implicit ko ảnh hưởng đến Fluent wait
		FluentWait<WebElement> Fluentwait = new FluentWait<WebElement>(countDownTime);

		Fluentwait.withTimeout(Duration.ofSeconds(13))
		.pollingEvery(Duration.ofMillis(100))
		.ignoring(NoSuchElementException.class)
		.until(new Function<WebElement, Boolean>(){
			public Boolean apply(WebElement countdown) {
				System.out.println(countdown.getText());
				return countdown.getText().endsWith("00");
			}
		});
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public WebElement getWebElement(By locator) {
		//Khai bao va khoi tao fluent wait
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				//Tong thoi gian chờ là bao nhiu s
				.withTimeout(Duration.ofSeconds(15))
				//Thoi gian để lặp lại là bao nhiu s
				.pollingEvery(Duration.ofSeconds(1))
				//If sau mỗi lần lặp lại mà gặp exception thì sẽ ignore
				.ignoring(NoSuchElementException.class);
		
		WebElement element = wait.until(new Function<WebDriver, WebElement>(){
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		return element;
	}
	
	public void clickToElement(By locator) {
		getWebElement(locator).click();
	}

	
	public void waitForElementAndClick(By locator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(15))
				.pollingEvery(Duration.ofSeconds(1))
				.ignoring(NoSuchElementException.class);
		
		WebElement element = wait.until(new Function<WebDriver, WebElement>(){
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		element.click();;
	}
	
	public boolean waitForElementAndDisplayed(By locator) {
		WebElement element = getWebElement(locator);
		FluentWait<WebElement> wait = new FluentWait<WebElement>(element)
				.withTimeout(Duration.ofSeconds(15))
				.pollingEvery(Duration.ofSeconds(1))
				.ignoring(NoSuchElementException.class);
		
		boolean isDisplayed = wait.until(new Function<WebElement, Boolean>() {
			public Boolean apply(WebElement element) {
				boolean flag = element.isDisplayed();
				return flag;
			}
		});
		return isDisplayed;
	}
}