package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//import junit.framework.Assert;

public class Topic_09_Button_Radio_Checkbox {
	WebDriver driver;
	boolean status;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;

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

	public void checkToCheckboxOrRadioButton(By by) {
		WebElement checkbox = driver.findElement(by);
		if (!checkbox.isSelected()) {
			checkbox.click();
		}
	}

	public void uncheckToCheckbox(By by) {
		WebElement checkbox = driver.findElement(by);
		if (checkbox.isSelected()) {
			checkbox.click();
		}
	}
	
	public void clickToElementByJS(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].click();", element);
	}
	
	public void scrollToElement(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
		sleepInSecond(1);
	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");

		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();

		// Verify button is disabled
		status = driver.findElement(By.cssSelector(".fhs-btn-login")).isEnabled();
		System.out.println("Boolean status = " + status);
		Assert.assertFalse(status);

		driver.findElement(By.cssSelector("#login_username")).sendKeys("0987654321");
		driver.findElement(By.cssSelector("#login_password")).sendKeys("123456");

		// Verify button is enabled
		status = driver.findElement(By.cssSelector(".fhs-btn-login")).isEnabled();
		System.out.println("Boolean status = " + status);
		Assert.assertTrue(status);

		driver.navigate().refresh();

		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();

		// trick
		// Remove disabled attribute of login button
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')",
				driver.findElement(By.cssSelector(".fhs-btn-login")));
		sleepInSecond(5);

		// Verify button is enabled
		status = driver.findElement(By.cssSelector(".fhs-btn-login")).isEnabled();
		System.out.println("Boolean status = " + status);
		Assert.assertTrue(status);

		driver.findElement(By.cssSelector(".fhs-btn-login")).click();

		Assert.assertEquals(driver.findElement(By.xpath(
				"//div[@class='popup-login-content']//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath(
				"//div[@class='popup-login-content']//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");

		driver.navigate().refresh();

		// Remove disabled attribute of login button
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')",
				driver.findElement(By.cssSelector(".fhs-btn-login")));

		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector(".fhs-btn-login")));
		sleepInSecond(2);

		// Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Số
		// điện
		// thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(),
		// "Thông tin này không thể để trống");
		// Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Mật
		// khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông
		// tin này không thể để trống");
	}

	@Test
	public void TC_02_Radio_Checkbox() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

		By rearSideCheckbox = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input");
		scrollToElement(rearSideCheckbox);
		// click vao checkbox de chon
		checkToCheckboxOrRadioButton(rearSideCheckbox);
		sleepInSecond(2);

		// verify checkbox is selected
		Assert.assertTrue(driver.findElement(rearSideCheckbox).isSelected());

		// click vao checkbox de bo chon
		uncheckToCheckbox(rearSideCheckbox);
		sleepInSecond(2);

		// verify checkbox is Deselected
		Assert.assertFalse(driver.findElement(rearSideCheckbox).isSelected());
		sleepInSecond(2);

		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");

		By oneDotFourRadioButton = By.xpath("//label[text()='1.4 Petrol, 92kW']/preceding-sibling::input");
		// click vao checkbox de chon
		checkToCheckboxOrRadioButton(oneDotFourRadioButton);
		sleepInSecond(2);
		
		// verify radio is selected
		Assert.assertTrue(driver.findElement(oneDotFourRadioButton).isSelected());
	}

	@Test
	public void TC_03_Checkbox_Select_All() {
		driver.get("https://automationfc.github.io/multiple-fields/");

		//Select all checboxes
		List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
		for (WebElement checkbox : checkboxes) {
			if (!checkbox.isSelected()) {
				checkbox.click();
				sleepInSecond(1);
			}
		}
		
		for (WebElement checkbox : checkboxes) {
			Assert.assertTrue(checkbox.isSelected());
		}
	}

	@Test
	public void TC_04_Radio_Checkbox_Custom() {
		driver.get("https://material.angular.io/components/radio/examples");

		//By winterRadio = By.xpath("//input[@value='Winter']");
		
		
		//1 - The input is hide, can not click but can verify
		//checkToCheckboxOrRadioButton(winterRadio);
		//sleepInSecond(2);
		//Assert.assertTrue(driver.findElement(winterRadio).isSelected());
		
		//2 - The span de click (dang hien thi)
		//By winterSpan = By.xpath("//span[contains(text(),'Winter')]");
		//driver.findElement(winterSpan).click();
		//Assert.assertTrue(driver.findElement(winterSpan).isSelected());
		
		//3 - The span de click + the input de verify
		//By winterSpan = By.xpath("//span[contains(text(),'Winter')]");
		//driver.findElement(winterSpan).click();
		
		//4 - The input dung js de click + verify
		By winterRadio = By.xpath("//input[@value='Winter']");
		clickToElementByJS(winterRadio);
		sleepInSecond(2);		
		
		Assert.assertTrue(driver.findElement(winterRadio).isSelected());
		
		//define 2 locator cho 1 element
		//Maintain -> bao tri nhieu cho	
	}
	
	@Test
	public void TC_05_Radio_Checkbox_Custom() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		sleepInSecond(2);
		
		//Verify checkbox is deselected
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Quảng Nam' and @aria-checked='false']")).isDisplayed());
		
		clickToElementByJS(By.xpath("//div[@aria-label='Quảng Nam']/div[contains(@class,'exportInnerBox')]"));
		sleepInSecond(2);
		
		String quangNamCheckboxChecked = driver.findElement(By.xpath("//div[@aria-label='Quảng Nam']")).getAttribute("aria-checked");
		System.out.println(quangNamCheckboxChecked);
				
		//Verify checkbox is selected
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Quảng Nam' and @aria-checked='true']")).isDisplayed());

	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}