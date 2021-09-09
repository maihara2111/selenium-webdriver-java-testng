package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_08_Custom_Dropdown {
	String projectPath = System.getProperty("user.dir");
	
	String[] firstMonth = {"February","May","October"};
	String[] secondMonth = {"February","May","November","October"};
	
	WebDriver driver;
	// Wait
	WebDriverWait expliciWait;

	// Inject 1 javascript code
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		
		driver = new FirefoxDriver();
		
		//System.setProperty("webdriver.chrome.driver", projectPath + "/BrowserDrivers/chromedriver.exe");
		//driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		expliciWait = new WebDriverWait(driver, 15);

		/*
		 * //ep kieu ngam dinh //nho => lon int price = 111777; float size = price;
		 * 
		 * //lon -> nho short sPrice = (short) price;
		 */

		// ep kieu tuong minh
		jsExecutor = (JavascriptExecutor) driver;

	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public void selectItemInCustomDropdown(String parentXpath, String childXpath, String expectItem) {
		// - Click vào 1 element để cho xổ hết tất cả các item trong dropdown ra
		driver.findElement(By.xpath(parentXpath)).click();
		sleepInSecond(1);

		// - Chờ cho tất cả các item được load ra thành công -> Child element
		// Lay het tat ca cac item nay luu vao list element
		List<WebElement> allItems = expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		// 19 Items

		// Tim item can chon
		// Duyet qua tung item
		for (WebElement item : allItems) {

			// Get text cua item do ra va kiem tra xem no co bang vs item text minh mong
			// muon hay ko
			if (item.getText().trim().equals(expectItem)) {
				/*
				 * // Item cần chọn nó hiển thị -> Click vào item đó luôn if
				 * (item.isDisplayed()) { item.click(); } // Item cần chọn ko hiển thị (ẩn bên
				 * dưới) else { // Scroll đến item đó -> Click vào item
				 * jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				 * sleepInSecond(1); item.click(); }
				 */

				//if (!item.isDisplayed()) {
					//System.out.println("------Scroll to element --------");
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
					sleepInSecond(1);
				//}
				item.click();
				break;
			}

		}

	}

	public void enterAndSelectItemInCustomDropdown(String parentXpath, String textboxXpath, String childXpath, String expectItem) {
		// - sendkey in dropdown
		driver.findElement(By.xpath(parentXpath)).click();
		sleepInSecond(1);
		
		driver.findElement(By.xpath(textboxXpath)).sendKeys(expectItem);
		sleepInSecond(1);

		List<WebElement> allItems = expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectItem)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				break;
			}

		}

	}
	
	public void enterAndTabToItemInCustomDropdown(String textboxXpath, String expectItem) {
		driver.findElement(By.xpath(textboxXpath)).sendKeys(expectItem);
		sleepInSecond(1);

		driver.findElement(By.xpath(textboxXpath)).sendKeys(Keys.TAB);
		sleepInSecond(1);

	}
	
	public void selectMultiItemInDropdown(String parentXpath, String childXpath, String[] expectedValueItem) {
		// 1: click on dropdown for it loads all values
		driver.findElement(By.xpath(parentXpath)).click();
		
		// 2: waiting for all values in dropdown load sucess
		List<WebElement> allItems = expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		
		// Duyet qua het all phan tu cho den khi thoa man dieu kien
		for (WebElement childElement : allItems) {
			//"January", "April", "July"
			for (String item : expectedValueItem) {
				if (childElement.getText().equals(item)) {
					//3: scroll to item can chon (if item can chon co the nhin thay dc thi ko can scroll)
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
					sleepInSecond(1);
					
					// 4: click on item can chon
					childElement.click();
					sleepInSecond(1);
					
					List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
					System.out.println("Item Selected = " + itemSelected.size());
					if (expectedValueItem.length == itemSelected.size()) {
						break;
					}
					
				}
			}
		}
		
	}
	
	public boolean areItemSelected(String[] months) {
		List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class=''selected]//input"));
		int numberItemSelected = itemSelected.size();
		
		String allItemSelectedText = driver.findElement(By.xpath("(//button[@class='ms-choice']/span)[1]")).getText();
		System.out.println("Text da chon = " + allItemSelectedText);
		
		if (numberItemSelected <=3 && numberItemSelected > 0) {
			System.out.println(">0 & <=3 = " + numberItemSelected);
			boolean status = true;
			for (String item : months) {
				if (!allItemSelectedText.contains(item)) {
					status = false;
					return status;
				}
			}
			return status;
		} else if (numberItemSelected == 12) {
			System.out.println("==12 = " + numberItemSelected);
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='All selected']")).isDisplayed();
		} else if (numberItemSelected > 3 && numberItemSelected < 12) {
			System.out.println(">3 & <12 = " + numberItemSelected);
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='" + numberItemSelected + " of 12 selected']")).isDisplayed();

		} else {
			return false;
		}
		
	}
	
	@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		selectItemInCustomDropdown("//span[@id='number-button']/span[contains(@class,'ui-selectmenu-icon')]", "//ul[@id='number-menu']//div", "5");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='5']")).isDisplayed());
		
		selectItemInCustomDropdown("//span[@id='number-button']/span[contains(@class,'ui-selectmenu-icon')]", "//ul[@id='number-menu']//div", "11");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='11']")).isDisplayed());

		
		selectItemInCustomDropdown("//span[@id='number-button']/span[contains(@class,'ui-selectmenu-icon')]", "//ul[@id='number-menu']//div", "13");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='13']")).isDisplayed());


	}

	@Test
	public void TC_02_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		selectItemInCustomDropdown("//i[@class='dropdown icon']", "//div[@role='option']/span", "Stevie Feliciano");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and text()='Stevie Feliciano']")).isDisplayed());
		
		selectItemInCustomDropdown("//i[@class='dropdown icon']", "//div[@role='option']/span", "Jenny Hess");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and text()='Jenny Hess']")).isDisplayed());

		selectItemInCustomDropdown("//i[@class='dropdown icon']", "//div[@role='option']/span", "Christian");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and text()='Christian']")).isDisplayed());

		
		
	}

	@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		selectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Second Option");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Second Option')]")).isDisplayed());
		
		selectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "First Option");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'First Option')]")).isDisplayed());
		
		selectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Third Option");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Third Option')]")).isDisplayed());

	}
	
	@Test
	public void TC_04_Angular() {
		driver.get("https://valor-software.com/ng2-select/");
		
		selectItemInCustomDropdown("//tab[@heading='Single']//i[@class='caret pull-right']", "//tab[@heading='Single']//a[@class='dropdown-item']/div", "Cologne");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//h3[text()='Select a single city']/following-sibling::ng-select//span[contains(@class,'ui-select-allow-clear')]")).getText(), "Cologne");
		
		/*
		 * driver.get(
		 * "https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding"
		 * );
		 * 
		 * driver.findElement(By.xpath("//span[@aria-owns='games_options']")).click();
		 * sleepInSecond(5);
		 * Assert.assertTrue(driver.findElement(By.xpath("")).isDisplayed());
		 */
		/*
		 * selectItemInCustomDropdown("//span[@aria-owns='games_options']",
		 * "//li[@class='e-list-item ']", "Football"); sleepInSecond(3);
		 * 
		 * selectItemInCustomDropdown("//span[@aria-owns='games_options']",
		 * "//li[@class='e-list-item ']", "Tennis"); sleepInSecond(3);
		 */
		
	}
	
	@Test
	public void TC_05_Edittable_01() {
		driver.get("https://valor-software.com/ng2-select/");
		
		enterAndSelectItemInCustomDropdown("//tab[@heading='Single']//i[@class='caret pull-right']", "//tab[@heading='Single']//input", "//tab[@heading='Single']//a[@class='dropdown-item']/div", "Cologne");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//h3[text()='Select a single city']/following-sibling::ng-select//span[contains(@class,'ui-select-allow-clear')]")).getText(), "Cologne");

		enterAndSelectItemInCustomDropdown("//tab[@heading='Single']//i[@class='caret pull-right']", "//tab[@heading='Single']//input", "//tab[@heading='Single']//a[@class='dropdown-item']/div", "Birmingham");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//h3[text()='Select a single city']/following-sibling::ng-select//span[contains(@class,'ui-select-allow-clear')]")).getText(), "Birmingham");

	}

	@Test
	public void TC_05_Edittable_02() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

		enterAndTabToItemInCustomDropdown("//input[@class='search']", "Algeria");
		
		enterAndTabToItemInCustomDropdown("//input[@class='search']", "Andorra");

	}
	
	@Test
	public void TC_06_Multiple_Select_Advance() {
		driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");

		selectMultiItemInDropdown("(//button[@class='ms-choice'])[1]", "//div[@class='form-group row'][2]//div[@class='ms-drop bottom']//li//span", firstMonth);
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}