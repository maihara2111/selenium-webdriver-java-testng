package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
//import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Element_Execise {
	// Khai bao bien (declare)
	WebDriver driver;
	String firstName, lastName, emailAddress, password, confirmation, fullName;
	By emailTextbox = By.id("mail");
	By educationTextArea = By.id("edu");
	By under18Radio = By.id("under_18");
	By javaCheckbox = By.id("java");
	By passwordTextbox = By.id("password");
	By disableCheckbox = By.id("check-disbaled");
	By disableButton = By.id("button-disabled");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// Khoi tao data test
		firstName = "Osama";
		lastName = "Bin Laden 1111";
		fullName = firstName + " " + lastName;
		emailAddress = "osama1111" + generateEmail();
		password = "11112222";
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
		return rand.nextInt(9999) + "@mail.vn";
	}

	@Test
	public void TC_01_Create_New_Account() {

		driver.get("http://live.demoguru99.com/");

		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);

		driver.findElement(By.xpath("//button[@title='Register']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),
				"Thank you for registering with Main Website Store.");

		// Dung ham isDisplayed de kiem tra
		Assert.assertTrue(driver.findElement(
				By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p[contains(string(),'"
						+ fullName + "')]"))
				.isDisplayed());
		Assert.assertTrue(driver.findElement(
				By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p[contains(string(),'"
						+ emailAddress + "')]"))
				.isDisplayed());

		// Dung ham getText va verify contains (fullname/ email)
		String contactInformation = driver
				.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p"))
				.getText();
		System.out.println(contactInformation);

		Assert.assertTrue(contactInformation.contains(fullName));
		Assert.assertTrue(contactInformation.contains(emailAddress));

		driver.findElement(By.cssSelector(".skip-account")).click();
		driver.findElement(By.cssSelector("a[title='Log Out']")).click();

	}

	@Test
	public void TC_02_Login() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		driver.findElement(By.cssSelector("#email")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("#pass")).sendKeys(password);

		driver.findElement(By.cssSelector("button[title='Login']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(),
				"Hello, " + fullName + "!");
	}

	@Test
	public void TC_03_Displayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		if (isElementDisplayed(emailTextbox)) {
			sendkeyToElement(emailTextbox, "Automation FC");
		}

		if (isElementDisplayed(educationTextArea)) {
			sendkeyToElement(educationTextArea, "Automation FC");
		}

		if (isElementDisplayed(under18Radio)) {
			clickToElement(under18Radio);
		}

		// Ham kiem tra dieu kien
		// if dieu kien true thi moi vao ben trong ham if
		// Sai thi ko vao

		/*
		 * if(driver.findElement(By.id("mail")).isDisplayed()) {
		 * driver.findElement(By.id("mail")).sendKeys("Automation FC");
		 * System.out.println("Mail textbox is displayed"); }else {
		 * System.out.println("Mail textbox is not displayed (undisplayed)"); }
		 * 
		 * if(driver.findElement(By.id("under_18")).isDisplayed()) {
		 * driver.findElement(By.id("under_18")).click();
		 * System.out.println("Radio button 'under_18' is displayed"); }else {
		 * System.out.println("Radio button 'under_18' is not displayed (undisplayed)");
		 * }
		 * 
		 * if(driver.findElement(By.id("edu")).isDisplayed()) {
		 * driver.findElement(By.id("edu")).sendKeys("Automation FC");
		 * System.out.println("edu textbox is displayed"); }else {
		 * System.out.println("edu textbox is not displayed (undisplayed)"); }
		 */
	}

	@Test
	public void TC_04_Selected_Function() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		clickToElement(under18Radio);
		clickToElement(javaCheckbox);

		// Verify checkbox/radio are selected
		Assert.assertTrue(isElementSelected(under18Radio));
		Assert.assertTrue(isElementSelected(javaCheckbox));

		clickToElement(javaCheckbox);

		// Verify checkbox is de-Selected
		Assert.assertFalse(isElementSelected(javaCheckbox));

		// Verify radio is selected
		Assert.assertTrue(isElementSelected(under18Radio));
	}

	@Test
	public void TC_05_Enabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Expected it enabled
		Assert.assertTrue(isElementEnabled(emailTextbox));
		Assert.assertTrue(isElementEnabled(educationTextArea));
		Assert.assertTrue(isElementEnabled(under18Radio));
		Assert.assertTrue(isElementEnabled(javaCheckbox));
		
		//Expected it disabled (readonly)
		Assert.assertFalse(isElementEnabled(passwordTextbox));
		Assert.assertFalse(isElementEnabled(disableCheckbox));
		Assert.assertFalse(isElementEnabled(disableButton));
		
	}
	
	@Test
	public void TC_06_Register_Validate() {
		driver.get("https://login.mailchimp.com/signup/");

		By passwordTextbox = By.id("new_password");
		By signUpButton = By.cssSelector("#create-account");
		By newsletterCheckbox = By.id("marketing_newsletter");
		By upperCaseCompleted = By.cssSelector(".uppercase-char.completed");
		By lowerCaseCompleted = By.cssSelector(".lowercase-char.completed");
		By numberCompleted = By.cssSelector(".number-char.completed");
		By specialCharCompleted = By.cssSelector(".special-char.completed");
		By greaterthan8charCompleted = By.cssSelector("li[class='8-char completed']");
		
		driver.findElement(By.id("email")).sendKeys("automationfc@gmail.com");
		driver.findElement(By.id("new_username")).sendKeys("automationfc");
		
		//Uppercase
		driver.findElement(passwordTextbox).sendKeys("P");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(upperCaseCompleted));
		Assert.assertFalse(isElementEnabled(signUpButton));
		
		//Lowercase
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("s");
		Assert.assertTrue(isElementDisplayed(lowerCaseCompleted));
		Assert.assertFalse(isElementEnabled(signUpButton));
		
		//Number
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("2111");
		Assert.assertTrue(isElementDisplayed(numberCompleted));
		Assert.assertFalse(isElementEnabled(signUpButton));
		
		//Special char
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("@");
		Assert.assertTrue(isElementDisplayed(specialCharCompleted));
		Assert.assertFalse(isElementEnabled(signUpButton));
		
		//Greater than or equal to 8 char
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("P@ssword");
		Assert.assertTrue(isElementDisplayed(greaterthan8charCompleted));
		Assert.assertFalse(isElementEnabled(signUpButton));
		
		//All Criteria
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("P@ssword2111");
		sleepInSecond(2);

		Assert.assertFalse(isElementDisplayed(upperCaseCompleted));
		Assert.assertFalse(isElementDisplayed(lowerCaseCompleted));
		Assert.assertFalse(isElementDisplayed(numberCompleted));
		Assert.assertFalse(isElementDisplayed(specialCharCompleted));
		Assert.assertFalse(isElementDisplayed(greaterthan8charCompleted));
		Assert.assertTrue(isElementEnabled(signUpButton));
		
		clickToElement(newsletterCheckbox);
		Assert.assertTrue(isElementSelected(newsletterCheckbox));

	}

	public boolean isElementDisplayed(By by) {
		if (driver.findElement(by).isDisplayed()) {
			System.out.println(by + " is displayed");
			return true;
		} else {
			System.out.println(by + " is not displayed");
			return false;
		}
	}

	public boolean isElementSelected(By by) {
		if (driver.findElement(by).isSelected()) {
			System.out.println(by + " is Selected");
			return true;
		} else {
			System.out.println(by + " is not Selected");
			return false;
		}
	}

	public boolean isElementEnabled(By by) {
		if (driver.findElement(by).isEnabled()) {
			System.out.println(by + " is Enabled");
			return true;
		} else {
			System.out.println(by + " is Disabled");
			return false;
		}
	}

	public void sendkeyToElement(By by, String value) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(value);
	}

	public void clickToElement(By by) {
		driver.findElement(by).click();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}