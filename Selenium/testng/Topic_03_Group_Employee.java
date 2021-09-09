package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_03_Group_Employee {

	@BeforeClass(groups = { "user" })
	public void beforeClass() {
		System.out.println("Run Before Class");
	}

	@Test(groups = { "user" })
	public void TC_01_Create_User() {

	}

	@Test(groups = { "employee", "admin" })
	public void TC_02_View_User() {

	}

	@Test(groups = { "employee", "admin" })
	public void TC_03_Edit_User() {

	}

	@Test(groups = { "employee", "admin" })
	public void TC_04_Delete_User() {
	}

	// If before fail thì after still run if use alwaysRun = true
	@AfterClass(groups = { "user" })
	public void afterClass() {
		System.out.println("Run After Class");
	}
}
