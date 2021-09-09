package webdriver;

import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.SetScriptTimeout;
//import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Web_Browser_Method {
	//Interface
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver(); //Class
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");
	}

	@Test
	public void TC_01_Browser() {
		//Bien driver tuong tac voi browser
		
		//Open 1 page (Url) -> **
		driver.get("https://www.facebook.com/r.php");		

		//Lay ra duong dan cua current page -> **
		String localPageUrl = driver.getCurrentUrl();
		
		//lay ra title page hien tai -> **
		driver.getTitle();
		
		//lay ra HTML code cua current page
		driver.getPageSource();
		
		//Xu li tab/window -> **
		driver.getWindowHandle();
		driver.getWindowHandles();
		
		
		driver.manage().window().getSize();
		driver.manage().window().getPosition();
		
		//framework (Share class state)
		//driver.manage().addCookie(cookie);
		
		//Thoi gian xxx wait cho element duoc tim thay -> **
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//SetScriptTimeout
		//pageLoadTimeout
		
		//Back ve page truoc
		//Fordward toi page truoc
		//Refresh page hien tai
		//OPen 1 url
		driver.navigate().back();
		//History (navigate().to(https://www.facebook.com/r.php));
		
		//Browser chi co 1 tab duy nhat thi deu dong browser

		//do not care bao nhieu tab -> **
		driver.quit();
		
		//close tab dang active
		//Xu li switch tab/windows
		driver.close();
		
		//Window/ tab -> **
		//Alert -> **
		//Frame/ Iframe -> **
		driver.switchTo().alert();
		
		driver.switchTo().frame(1);
		
		driver.switchTo().window("");
		
		driver.manage().window().fullscreen();
		
		driver.manage().window().maximize(); //-> **

		//lay ra vi tri browser so voi do phan gian man hinh hien tai
		driver.manage().window().getPosition();
		//driver.manage().window().setPosition(targetPosition);
		
		driver.manage().window().getSize();
		//driver.manage().window().setSize(targetSize);		
		
		//Bien... tuong tac element (textbox,checboc, dropdown,...)
	
		
		//open 1 page (url)
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_02() {

	}

	@Test
	public void TC_03() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}