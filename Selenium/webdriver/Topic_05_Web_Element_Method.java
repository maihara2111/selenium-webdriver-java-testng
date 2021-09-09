package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.interactions.touch.SingleTapOnElement;
import org.testng.Assert;
//import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class Topic_05_Web_Element_Method {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");
	}

	@Test
	public void TC_01_Web_Element() {
		//Muon thao tac duoc vs element thi phai tim element truoc
		
		//Thao tac vs 1 element
		driver.findElement(By.id("")); //**
		
		//Tim nhieu element
		driver.findElements(By.id(""));//**
		
		//If chi thao tac vs element 1 lan thi ko can khai bao bien
		driver.findElement(By.id("small-searchterms")).sendKeys("Apple");//**
		
		//If can thao tac element nhieu lan thi nen khai bao bien
		WebElement searchTextbox = driver.findElement(By.id("small-searchterms"));
		searchTextbox.clear();//**
		searchTextbox.sendKeys("Apple");
		searchTextbox.getAttribute("value");//**
		
		//driver.findElement(By.id("small-searchterms")).clear();
		//driver.findElement(By.id("small-searchterms")).sendKeys("Apple");
		//driver.findElement(By.id("small-searchterms")).getAttribute("value");
		
		//Count co bao nhieu element thoa dieu kien
		//Verify so luong element tra ve nhu mong doi
		//Thao tac vs all cac loai element giong nhau trong 1 page (checkbox/textbox)
		List<WebElement> checkboxes = driver.findElements(By.xpath("//div[@class='inputs']/input[not(@type='checkbox')]"));
		
		//Lay ra so luong
		Assert.assertEquals(checkboxes.size(), 6);
		
		WebElement singleElement = driver.findElement(By.className(""));
		
		//Textbox TextArea/ Edittable dropdown
		//Du lieu duoc toan ven
		searchTextbox.clear();
		searchTextbox.sendKeys("");
		
		//Button/Link/radio/checkbox/custom dropdown/..
		singleElement.click();//**
		
		//Cac ham co tien to bat dau bang get lun lun tra ve du lieu 
		//getTitle/ getCurrentUrl/ getPageSource/ getAttribute/ getCssValue/ getText/ get...
		singleElement = driver.findElement(By.xpath("//input[@id='Firstname']"));
		singleElement.getAttribute("");
		
		//Automation
		singleElement = driver.findElement(By.xpath("//input[@id='small-searchterms']"));
		singleElement.getAttribute("placeholder");
		//Search store
		
		//Lay ra gia tri cua cac thuoc tinh css thuong dung de test GUI
		//Font/Size/Color/Background/...
		singleElement = driver.findElement(By.cssSelector("search-box-button"));
		singleElement.getCssValue("background-color");//*
		//#4ab2f1
		singleElement.getCssValue("text-transform");
		//uppercase
		
		//Lay ra toa do element so voi page hien tai (get gox ben ngoai)
		singleElement.getLocation();
		
		//lay ra kich thuoc cua element (rong x cao) -> get goc ben trong element
		singleElement.getSize();
		
		//Location + size
		singleElement.getRect();
		
		//Chup hinh loi => dua vao html export
		singleElement.getScreenshotAs(OutputType.FILE);//*
		
		
		//id/class/css/name...//tu 1 element ko bik tagname -> lay ra duoc tagname truyen vao cho 1 locator khac
		singleElement = driver.findElement(By.xpath("//div[@class='inputs']/input[not(@type='checkbox')]"));
		String searchButtonTagname = singleElement.getTagName();//*
		
		searchTextbox = driver.findElement(By.xpath("//" + searchButtonTagname + "[@class='inputs']/input[not(@type='checkbox')]"));
		//input
		
		//xpath
		//tagname[@attribute='value']
		
		
		//lay ra text element (header/link/message/...)
		singleElement.getText();//**
		
		//Cac ham co tien to la isXXX thi tra ve kieu Boolean (100%)
		//true/false
		
		//kiem tra xem 1 element la hien thi cho nguoi dung thao tac hay ko
		//true: dang hien thi
		//false: ko hien thi
		singleElement.isDisplayed();//**
		
		//kiem tra xem 1 element la disable hay ko
		//disable: user ko thao tac duoc
		//true: ko thao tac duoc
		//false: co the thao tac
		singleElement.isEnabled();//*
		
		//kiem tra xem 1 element da duoc chon roi hay chua
		//checkbox/ radio/ dropdown
		//true: da chon roi
		//false: chua duoc chon
		singleElement.isSelected();//*
		
		//no thay cho hanh vi ENTER vao textbox/button
		//chi dung duoc trong form (Login/search/register/...)
		singleElement.submit();
		
		singleElement = driver.findElement(By.id("small-searchterms"));
		singleElement.sendKeys("Apple");
		singleElement.submit();
		
		
		
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