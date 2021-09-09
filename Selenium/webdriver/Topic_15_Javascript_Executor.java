package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Javascript_Executor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	String emailAddress, loginPageUrl, userID, password, customerID;

	// Declare Data Test (New Customer)
	String name, dob, city, address, state, pin, phone;

	By nameTextboxBy = By.name("name");
	By genderTextboxBy = By.name("gender");
	By dobTextboxBy = By.name("dob");
	By addressTextAreaBy = By.name("addr");
	By cityTextboxBy = By.name("city");
	By stateTextboxBy = By.name("state");
	By pinTextboxBy = By.name("pinno");
	By phoneTextboxBy = By.name("telephoneno");
	By emailTextboxBy = By.name("emailid");
	By passwordTextboxBy = By.name("password");


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "//BrowserDrivers/geckodriver.exe");
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
	@Test
	public void TC_01_Live_Guru() {
		driver.get("http://live.demoguru99.com/");
		
		//action: tuong tac vs browser/ element
		//click/set value/ scroll
		
		//get gia tri
		/*
		 * jsExecutor.executeScript("return document.title");
		 * jsExecutor.executeScript("return document.URL");
		 * jsExecutor.executeScript("return document.domain");
		 */
		System.out.println(jsExecutor.executeScript("return document.title"));
		System.out.println(jsExecutor.executeScript("return document.URL"));
		System.out.println(jsExecutor.executeScript("return document.domain"));
		
		String homePageTitle = (String) jsExecutor.executeScript("return document.title");
		Assert.assertEquals(homePageTitle, "Home page");
		
		homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle, "Home page");

	}

	@Test
	public void TC_01_Live_Guru_01() {
		//If internet slowly run > test case will be fail
		
		navigateToUrlByJS("http://live.demoguru99.com/");
		
		
		String liveGuruDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(liveGuruDomain, "live.demoguru99.com");
		
		String liveGuruUrl = (String) executeForBrowser("return document.URL");
		Assert.assertEquals(liveGuruUrl, "http://live.demoguru99.com/");
		
		hightlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		
		hightlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");

		Assert.assertTrue(isExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));
		
		hightlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");
		
		String customerServiceTitle = (String) executeForBrowser("return document.title;");
		
		Assert.assertEquals(customerServiceTitle, "Customer Service");
		
		hightlightElement("//input[@id='newsletter']");
		scrollToElement("//input[@id='newsletter']");
		sendkeyToElementByJS("//input[@id='newsletter']", generateEmail());
		
		hightlightElement("//button[@title='Subscribe']");
		
		Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));

		navigateToUrlByJS("http://demo.guru99.com/v4/");
		
		String bankGuruDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(bankGuruDomain, "http://demo.guru99.com/v4/");
		
	}
	
	@Test
	public void TC_02_HTML5_ValidationMessage() {
		driver.get("https://sieuthimaymocthietbi.com/account/register");
		String validationMessage;
		
		driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
		
		validationMessage = getElementValidationMessage("//input[@id='lastName']");
		Assert.assertEquals(validationMessage, "Please fill out this field.");
		
		//LatName
		driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Mai");
		
		driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
		
		validationMessage = getElementValidationMessage("//input[@id='firstName']");
		Assert.assertEquals(validationMessage, "Please fill out this field.");
		
		//FirstName
		driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("Hara");
		
		driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
		
		validationMessage = getElementValidationMessage("//input[@id='email']");
		Assert.assertEquals(validationMessage, "Please fill out this field.");
		
		//Email
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("hara@");
		
		driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
		
		validationMessage = getElementValidationMessage("//input[@id='email']");
		Assert.assertEquals(validationMessage, "Please enter an email address.");
		
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("hara@1111");
		
		driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
		
		validationMessage = getElementValidationMessage("//input[@id='email']");
		Assert.assertEquals(validationMessage, "Please match the requested format.");
		
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("hara@1111.com");
		
		driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
		
		validationMessage = getElementValidationMessage("//input[@id='password']");
		Assert.assertEquals(validationMessage, "Please fill out this field.");
	}

	@Test
	public void TC_03_RemoveTypeDatetimePicker() {
		driver.get("http://demo.guru99.com/v4");
		
		emailAddress = "maihara" + generateEmail();
		
		name = "Mai Hara";
		dob = "1994-11-21";
		city = "Nauy";
		address = "1111 LKT";
		state = "Hawaii";
		pin = "222222";
		phone = "0777711112";
		
		loginPageUrl = driver.getCurrentUrl();

		driver.findElement(By.xpath("//a[text()='here']")).click();

		driver.findElement(By.name("emailid")).sendKeys(emailAddress);
		driver.findElement(By.name("btnLogin")).click();

		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

		driver.get(loginPageUrl);

		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);

		driver.findElement(By.name("btnLogin")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("marquee.heading3")).getText(),
				"Welcome To Manager's Page of Guru99 Bank");

		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		driver.findElement(nameTextboxBy).sendKeys(name);
		
		
		removeAttributeInDOM("//input[@id='dob']", "type");
		sleepInSecond(5);
		driver.findElement(dobTextboxBy).sendKeys(dob);
		
		driver.findElement(addressTextAreaBy).sendKeys(address);
		driver.findElement(cityTextboxBy).sendKeys(city);
		driver.findElement(stateTextboxBy).sendKeys(state);
		driver.findElement(pinTextboxBy).sendKeys(pin);
		driver.findElement(phoneTextboxBy).sendKeys(phone);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(By.name("sub")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector(".heading3")).getText(),
				"Customer Registered Successfully!!!");
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),
				dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
				address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),
				state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),
				phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),
				emailAddress);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public String generateEmail() {
		Random rand = new Random();
		return rand.nextInt(9999) + "@mail.net";
	}
	
	
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean isExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElement(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}

}