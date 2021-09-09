package webdriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Default_Dropdown {
	WebDriver driver;
	Select select;
	JavascriptExecutor jsExcutor;
	List<String> expectedItemText;
	String firstName, lastName, emailAddress, companyName, day, month, year;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jsExcutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		firstName = "Automation";
		lastName = "FC";
		emailAddress = "autofc" + generateEmail();
		companyName = "MH";
		day = "21";
		month = "November";
		year = "1994";
		expectedItemText = new ArrayList<String>(Arrays.asList("Month","January","February",
				"March","April","May","June",
				"July","August","September",
				"October","November","December"));
	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String generateEmail() {
		Random rand = new Random();
		return rand.nextInt(9999) + "@mail.net";
	}

	public void clickByJS(By by) {
		jsExcutor.executeScript("arguments[0].click();", driver.findElement(by));
	}

	@Test
	public void TC_01_NopCommerce() {
		driver.get("https://demo.nopcommerce.com/");

		driver.findElement(By.className("ico-register")).click();
		sleepInSecond(2);

		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);

		// select = new Select(driver.findElement(By.name("DateOfBirthDay")));

		// 3 cach khac nhau
		// select.selectByIndex(0);

		// select.selectByValue("");
		// Uu tien chon visible text
		// select.selectByVisibleText("Thanh pho Hai Phong");

		// ktra da chon dung item nay chua
		// Assert.assertEquals(select.getFirstSelectedOption().getText(), "10");

		// Use to verify xem trong dropdow co tong cong bao nhieu item
		// Assert.assertEquals(select.getOptions().size(), 32);

		// Verify dropdown nay ko chon nhiu item cung luc
		// Assert.assertFalse(select.isMultiple());

		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		select.selectByVisibleText("21");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
		
		/*chon nhiu item
		 * List<WebElement> itemsSelected = select.getAllSelectedOptions();
		 * bo chon item da chon
		 * select.deselectAll();
		 * Assert.assertEquals(itemsSelected.size(), 0);
		 * 
		 * for (WebElement item : itemsSelected) { actualItemsText.add(item.getText());
		 * }
		 */
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText("November");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);

		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText("1994");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);

		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Company")).sendKeys(companyName);

		driver.findElement(By.id("Password")).sendKeys("11112222");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("11112222");

		clickByJS(By.id("register-button"));
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),
				"Your registration completed");

		driver.findElement(By.xpath("//a[@class='ico-account']")).click();

		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), emailAddress);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), companyName);

		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);

		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);

		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);

	}

	@Test
	public void TC_02() {
		driver.get("https://demo.nopcommerce.com/");

		driver.findElement(By.className("ico-register")).click();

		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));

		List<WebElement> allItems = select.getOptions();
		List<String> actualItemsText = new ArrayList<>();

		// Duyet qua all item dang co trong list
		for (WebElement item : allItems) {
			actualItemsText.add(item.getText());
		}

		Assert.assertEquals(expectedItemText, actualItemsText);
	}

	@Test
	public void TC_03() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}