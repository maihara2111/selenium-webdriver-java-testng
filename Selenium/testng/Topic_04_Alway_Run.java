package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_04_Alway_Run {
	
	@Test(description = "Create a new user with admin role")
	public void User_01_Create_User() {

	}

	//@Test(description = "Jara#12345")
	public void User_02_View_User() {

	}

	@Test(enabled = false)
	public void User_03_Edit_User() {
		//testcase này sẽ ko run
		//do enabled = false
	}

	@Test()
	public void User_04_Delete_User() {
	}
}
