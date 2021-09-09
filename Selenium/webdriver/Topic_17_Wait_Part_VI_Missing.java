package webdriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//Topic 31
public class Topic_17_Wait_Part_VI_Missing {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	WebElement element;


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
	
	//Trộn cả 2 loại wait vào với nhau thì nó sinh ra bo nhiêu trường hợp
	@Test
	public void TC_01_Element_Found_Implicit_Explicit() {
		driver.get("https://www.facebook.com/");
		
		//1. Tìm thấy element
		//=> output: ko cần phải chờ hết timeout - cũng ko có exception nào hết - ko fail testcase
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 15);

		System.out.println("Start implicit" + getDateTimeNow());
		driver.findElement(By.cssSelector("input#email"));
		System.out.println("Start implicit" + getDateTimeNow());
		
		System.out.println("Start explicit" + getDateTimeNow());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
		System.out.println("Start explicit" + getDateTimeNow());
	}

	@Test
	public void TC_02_1_Element_Not_Found_Only_Implicit() {
		driver.get("https://www.facebook.com/");
		
		//2.1. Only use implicit - not use explicit
		//=> output: chờ hết timeout/ tìm lại sau mỗi 0.5s/
		// > hết timeout đánh fail testcase/ throw NoSuchElementException
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		System.out.println("Start implicit" + getDateTimeNow());
		try {
			driver.findElement(By.cssSelector("input#maihara"));
		} finally {
			System.out.println("Start implicit" + getDateTimeNow());
		}
	}
	
	@Test
	public void TC_02_2_Element_Not_Found_Only_Explicit() {
		driver.get("https://www.facebook.com/");
		
		//2.1. Only use explicit - not use explicit
		//=> output: chờ hết timeout/ tìm lại sau mỗi 0.5s/
		// > hết timeout đánh fail testcase/ throw TimeoutException
		
		explicitWait = new WebDriverWait(driver, 5);

		System.out.println("Start explicit" + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#maihara")));
		} finally {
			System.out.println("Start explicit" + getDateTimeNow());
		}
	}

	@Test
	public void TC_02_3_1_Element_Not_Found_Implicit_Explicit() {
		driver.get("https://www.facebook.com/");
		
		//2.3.1. implicit > Explicit
		//TimeoutException: Expected condition failed: waiting for visibility of element located by By.cssSelector: input#tiki
		//(tried for 3 second(s) with 500 milliseconds interval)
		//=> output: timeout tổng cộng tổng cộng lớn I = timeout implicit
		//org.openqa.selenium.NoSuchElementException: Unable to locate element: input#tiki
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 3);

		//Implicit ảnh hưởng cho findElement
		//Explicit wait ko bao giờ ảnh hường cho findelement được
		//Explicit chỉ ảnh hưởng cho những trạng thai của element thôi
		
		System.out.println("Start implicit" + getDateTimeNow());
		try {
			//Nhận time out của implicit
			driver.findElement(By.cssSelector("input#tiki"));
		} catch (Exception e1) {
			// TODO: handle exception
		}
		System.out.println("End implicit" + getDateTimeNow());
		
		System.out.println("Start explicit" + getDateTimeNow());
		//Nhận timeout của cả 2
		//Implicit lun lun đc kích hoạt trước
		// Vi trong hàm ExpectedConditions.visibilityOfElementLocated có hàm findElement
		// driver.findElement(locator) => bị ảnh hưởng timeout của implicit: 5s
		// elementIfVisible > bị ảnh hưởng timeout của explicit" 3s
		
		try {
			element = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#tiki")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End explicit" + getDateTimeNow());
	
		Assert.assertNull(element);
	}
	
	@Test
	public void TC_02_3_2_Element_Not_Found_Implicit_Explicit() {
		driver.get("https://www.facebook.com/");
		
		//2.3.2. implicit = Explicit
		//TimeoutException: Expected condition failed: waiting for visibility of element located by By.cssSelector: input#tiki
		//(tried for 3 second(s) with 500 milliseconds interval)
		//=> output: timeout tổng cộng tổng cộng lớn I = timeout implicit
		//org.openqa.selenium.NoSuchElementException: Unable to locate element: input#tiki
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 5);

		//Implicit ảnh hưởng cho findElement
		//Explicit wait ko bao giờ ảnh hường cho findelement được
		//Explicit chỉ ảnh hưởng cho những trạng thai của element thôi
		
		System.out.println("Start implicit" + getDateTimeNow());
		try {
			//Nhận time out của implicit
			driver.findElement(By.cssSelector("input#tiki"));
		} catch (Exception e1) {
			// TODO: handle exception
		}
		System.out.println("End implicit" + getDateTimeNow());
		
		System.out.println("Start explicit" + getDateTimeNow());
		//Nhận timeout của cả 2
		//Implicit lun lun đc kích hoạt trước
		// Vi trong hàm ExpectedConditions.visibilityOfElementLocated có hàm findElement
		// driver.findElement(locator) => bị ảnh hưởng timeout của implicit: 5s
		// elementIfVisible > bị ảnh hưởng timeout của explicit" 3s
		
		try {
			element = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#tiki")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End explicit" + getDateTimeNow());
	
		Assert.assertNull(element);
	}

	@Test
	public void TC_02_3_3_Element_Not_Found_Implicit_Explicit() {
		driver.get("https://www.facebook.com/");
		
		//2.3.2. implicit < Explicit
		//TimeoutException: Expected condition failed: waiting for visibility of element located by By.cssSelector: input#tiki
		//(tried for 3 second(s) with 500 milliseconds interval)
		//=> output: timeout tổng cộng lớn I = 10-11s
		//org.openqa.selenium.NoSuchElementException: Unable to locate element: input#tiki
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 8);

		//Implicit ảnh hưởng cho findElement
		//Explicit wait ko bao giờ ảnh hường cho findelement được
		//Explicit chỉ ảnh hưởng cho những trạng thai của element thôi
		
		System.out.println("Start implicit" + getDateTimeNow());
		try {
			//Nhận time out của implicit
			driver.findElement(By.cssSelector("input#tiki"));
		} catch (Exception e1) {
			// TODO: handle exception
		}
		System.out.println("End implicit" + getDateTimeNow());
		
		System.out.println("Start explicit" + getDateTimeNow());
		//Nhận timeout của cả 2
		//Implicit lun lun đc kích hoạt trước
		// Vi trong hàm ExpectedConditions.visibilityOfElementLocated có hàm findElement
		// driver.findElement(locator) => bị ảnh hưởng timeout của implicit: 5s
		// elementIfVisible > bị ảnh hưởng timeout của explicit" 3s
		
		try {
			element = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#tiki")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End explicit" + getDateTimeNow());
	
		Assert.assertNull(element);
	}
	
	@Test
	public void TC_02_4_Element_Not_Found_Implicit_Explicit() {
		driver.get("https://www.facebook.com/");
		
		//2.3.2. implicit < Explicit
		//TimeoutException: Expected condition failed: waiting for visibility of element located by By.cssSelector: input#tiki
		//(tried for 3 second(s) with 500 milliseconds interval)
		//=> output: timeout tổng cộng lớn I = 10-11s
		//org.openqa.selenium.NoSuchElementException: Unable to locate element: input#tiki
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 15);

		System.out.println("Start implicit" + getDateTimeNow());
		try {
			//Nhận tham số là By
			//Nhận timeout của cả 2 trong hàm visibilityOfElementLocated
			// driver.findElement(locator) => bị ảnh hưởng timeout của implicit: 5s
			// elementIfVisible > bị ảnh hưởng timeout của explicit" 5s
			//Throw ra exception: TimeoutException (Explicit)
			//element = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#tiki")));
			
			//Nhận tham số là WebElement
			//Chạy từ trong ra: findElement > nhận timeout của implicit
			//Chờ hết timeout 5s
			//Đánh fail
			//Throw ra exception: NoSuchElement (Implicit)
			element = explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#tiki"))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End implicit" + getDateTimeNow());
	
		Assert.assertNull(element);
	}
	
	@Test
	public void TC_03_Element_Not_Found_Implicit_Explicit() {
		driver.get("https://www.facebook.com/");
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 15);

		System.out.println("Start implicit" + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#tiki"))));
		} finally {
			System.out.println("End implicit" + getDateTimeNow());
		}
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}
}