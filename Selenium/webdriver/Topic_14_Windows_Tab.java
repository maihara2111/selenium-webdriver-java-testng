package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javafx.scene.Parent;

public class Topic_14_Windows_Tab {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	String childID;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "//BrowserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();
		expliciWait = new WebDriverWait(driver, 15);
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
	public void TC_01_Windows() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Get ra ID cua active tab/windows (driver đang đứng)
		String parentID = driver.getWindowHandle();
		System.out.println("Home ID = " + parentID);

		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();

		switchToWindowByID(parentID);

		String childID = driver.getWindowHandle();

		driver.findElement(By.cssSelector("#email")).sendKeys("child@gmail.com");

		switchToWindowByID(childID);

		driver.findElement(By.cssSelector("#email")).sendKeys("parent@gmail.com");

	}

	@Test
	public void TC_02_Morethan2TabOrWindows() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Get ra ID cua active tab/windows (driver đang đứng)
		String parentID = driver.getWindowHandle();
		System.out.println("Home ID = " + parentID);

		// click vao facebook link > hanh vi cua app auto nhay qa tab do lun
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(5);
		// expliciWait.until(ExpectedConditions.numberOfWindowsToBe(2));

		// Switch vao facebook tab = title
		switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");

		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("maihara@gmail.com");

		// Switch vao parent page lai (github.io)
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");

		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Mai Hara");

		// click vao tiki link > hanh vi cua app auto nhay qa tab do lun
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(5);
		// expliciWait.until(ExpectedConditions.numberOfWindowsToBe(3));

		// Switch vao tiki tab lai = title
		switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

		driver.findElement(By.xpath("//input[@data-view-id='main_search_form_input']"))
				.sendKeys("Samsung Note Ultra 20");
		driver.findElement(By.xpath("//button[@data-view-id='main_search_form_button']")).click();
		sleepInSecond(5);

		// Switch vao parent page lai (github.io)
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");

		// click vao lazada link > hanh vi cua app auto nhay qa tab do lun
		driver.findElement(By.xpath("//a[text()='LAZADA']")).click();
		sleepInSecond(5);
		// expliciWait.until(ExpectedConditions.numberOfWindowsToBe(4));

		// Switch vao lazada tab lai = title
		switchToWindowByTitle("Shopping online - Buy online on Lazada.vn");
		
		closeAllwindowsWithoutParent(parentID);
		
		driver.findElement(By.cssSelector("#email")).sendKeys("parent@gmail.com");

	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

	// Dung cho dung 2 windows/tab
	// Kiem tra id truoc
	// khac vs parent
	// thi moi switch
	public void switchToWindowByID(String parentID) {
		// Get ra all tab/window đang có
		Set<String> allWindows = driver.getWindowHandles();

		// Dung vong lap de duyet qua tung Window
		for (String id : allWindows) {
			// Neu id nao # parent id
			if (!id.equals(parentID)) {
				// Switch vao id đó
				driver.switchTo().window(id);
			}
		}
	}

	// Dung cho 2 hoac nhiu hon 2 windows/ tab
	// Switch vao tung window truoc
	// get ra title cua window do
	// kiem tra vs title mong muon
	// Neu nhu ma = thi stop ko kiem tra tiep nua
	public void switchToWindowByTitle(String expectedTitle) {
		// Get ra all tab/window dang co
		Set<String> allWindows = driver.getWindowHandles();

		// Dung vong lap de duyet qua tung window
		for (String id : allWindows) {
			driver.switchTo().window(id);
			String windowTitle = driver.getTitle();
			// Neu nhu ma = thi stop ko kiem tra tiep nua
			if (windowTitle.equals(expectedTitle)) {
				break;
			}
		}

	}

	public void closeAllwindowsWithoutParent(String parentID) {
		// Get ra all tab/window dang co
		Set<String> allWindows = driver.getWindowHandles();

		// Dung vong lap de duyet qua tung window
		for (String id : allWindows) {
			if(!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		//switch ve parent
		driver.switchTo().window(parentID);
	}

}