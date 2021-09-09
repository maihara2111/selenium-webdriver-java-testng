package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//Topic 28
public class Topic_17_Wait_Part_I_Element_Status {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	By confirmEmailTextbox = By.xpath("//input[@name='reg_email_confirmation__']");
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "//BrowserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();
		
		explicitWait = new WebDriverWait(driver, 15);
		
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
	public void TC_01_Visible() {
		driver.get("https://www.facebook.com/");

		//Case 1: element hien thi tren UI va co xuat hien trong DOM/HTML
		
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
		
		driver.findElement(By.name("reg_email__")).sendKeys("mai@gmail.com");
		
		//wait for element is displayed
		//Hien thi tren UI
		//Co trong DOM
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(confirmEmailTextbox));
		
		driver.findElement(confirmEmailTextbox).sendKeys("mai@gmail.com");
		
	}

	@Test
	public void TC_02_Invisible_01() {
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
		
		//Case 2: element ko co tren UI va van co trong HTML
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(confirmEmailTextbox));
		
	}
	
	@Test
	public void TC_02_Invisible_02() {
		driver.get("https://www.facebook.com/");
		driver.navigate().refresh();
		
		//Case 3: Element ko co tren UI va ko co trong HTML
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(confirmEmailTextbox));
	}

	@Test
	public void TC_03_Presence() {
		driver.get("https://www.facebook.com/");
		//Dieu kien case 1 & 2
		//Dieu kien bat buoc la phai co trong DOM/HTML
		//Ko quan tam co tren UI hay ko
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
		
		//Wait presence(In DOM - ko co tren UI)
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(confirmEmailTextbox));
		
		driver.findElement(By.name("reg_email__")).sendKeys("mai@gmail.com");
		
		//Wait presence(In DOM - co tren UI)
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(confirmEmailTextbox));
	}
	
	@Test
	public void TC_04_Staleness() {
		/*
		 * //Case 3: ko co tren UI & ko xuat hien trong DOM/HTML
		 * driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
		 * 
		 * WebElement emailTextbox = driver.findElement(By.name("reg_email__"));
		 * 
		 * driver.findElement(By.
		 * xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();
		 * 
		 * //Wait email textbox is staleness
		 * explicitWait.until(ExpectedConditions.stalenessOf(emailTextbox));
		 */
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		driver.findElement(By.id("SubmitCreate")).click();
		
		//1
		WebElement accountErrorMessage = driver.findElement(By.xpath("//div[@id='create_account_error']"));
		
		//2-element tai buoc so 1 bi update lai - no longer attach to the DOM
		driver.navigate().refresh();
		
		//3- wait element staleness - wait cho 1 element ko con trang thai cu nua
		explicitWait.until(ExpectedConditions.stalenessOf(accountErrorMessage));
		
		//If thao tac tiep vs element tren thi se bi error
		//StaleElementException: elememt da bi thay doi trang thai roi ma minh van loi ra de thao tac
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}