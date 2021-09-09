package webdriver;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//Topic 29
public class Topic_17_Wait_Part_II_FindElement {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "//BrowserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();
		
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
	/*
	 * - Nó đều chiu anh huong boi timeout:implicit
	 * - 10s nay se la thgian de chờ cho element co trong DOM/HTML
	 * - If xuat hien ngay trong vòng ít hơn 10s(2-3s xuat hien thi 7s con lai ko can chờ nữa)
	 * - Trong thoi gian wait thi có cơ chế tìm lại sau mỗi 0.5s
	 * - If sau 10s van chưa xuất hiện thi tùy vao hàm đang sử dụng findElement/findElements
	 */
	@Test
	public void TC_01_Find_Element() {
		driver.get("https://www.facebook.com/");
		
		//Find single element
		//WebElement emailTextbox = driver.findElement(By.xpath(""));
		
		//1-Tim thay 1 matching node
		//not need chờ hết timeout nên run very fast (milisecond)
		//System.out.println("Start 1" + getDateTimeNow());
		//driver.findElement(By.id("email")).sendKeys("mai@gmail.com");
		//System.out.println("End 1" + getDateTimeNow());

		
		//2-Ko tim thay node nao het
		//Chờ hết timeout
		//Throw(ném ra) 1 ngoại lệ (exception): NoSuchElementException
		//ko run step tiep theo
		//System.out.println("Start 2" + getDateTimeNow());
		//try {
		//	driver.findElement(By.id("tiki")).isDisplayed();
		//}finally {
		//	System.out.println("End 2" + getDateTimeNow());
		//}
		
		//2.1 - Ko tim thay node nao het
		//Trong thoi gian chờ hết timeout thi tim lai sau mỗi 0.5s
		//Tim được thì như case 1
		System.out.println("Start 2.1" + getDateTimeNow());
		//5s đầu tiên chưa click Tao tai khoan: ko tim thay
		//Giây thứ 5 trở đi click Tao tai khoan > xuat hien
		driver.findElement(By.name("reg_email__")).sendKeys("mai@gmail.com");
		System.out.println("End 2.1" + getDateTimeNow());

		//3-Tim thay nhiu hon 1 node
		//- If nhìu hơn 1 node thi thao tac voi node dau tien
		//- Ko quan tam cac node sau
		//System.out.println("Start 3" + getDateTimeNow());
		//driver.findElement(By.cssSelector("input#pageFooter a"));
		//System.out.println("End 3" + getDateTimeNow());

		//4- 
		//driver.findElement(By.id("pass")).sendKeys("11112222");

	}

	@Test
	public void TC_02_Find_Elements() {
		driver.get("https://www.facebook.com/");

		//Find multiple elements
		List<WebElement> links = driver.findElements(By.tagName("a"));
		List<WebElement> elements;
		
		//1-Tim thay 1 matching node
		//- No sẽ trả về 1 list chứa 1 element (node) đó ->size = 1
		System.out.println("Start 1" + getDateTimeNow());
		elements = driver.findElements(By.cssSelector("input#email"));
		System.out.println("End 1" + getDateTimeNow());
		System.out.println("List size = " + elements.size());
		
		//2-Ko tim thay node nao het
		//- Cũng phải chờ hết timeout
		//- Trong thoi gian wait cơ chế tìm lại mỗi 0.5s/1lan
		//- Chờ hết timeout rồi thì ko đánh fail testcase
		//- Vẫn chạy các step tiếp theo
		System.out.println("Start 2" + getDateTimeNow());
		elements = driver.findElements(By.cssSelector("input#tiki"));
		System.out.println("End 2" + getDateTimeNow());
		System.out.println("List size = " + elements.size());
		
		//3-Tim thay nhiu hon 1 node
		//- Nó sẽ trả về 1 list chứa n element (node) đó > size = n
		System.out.println("Start 3" + getDateTimeNow());
		elements = driver.findElements(By.cssSelector("input#pageFooter a"));
		System.out.println("End 3" + getDateTimeNow());
		System.out.println("List size = " + elements.size());
	}

	@Test
	public void TC_03() {

	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}
}