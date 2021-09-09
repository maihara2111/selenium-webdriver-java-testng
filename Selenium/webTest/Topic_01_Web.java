package webTest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_01_Web {
	
	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		System.out.println("Run Before Class");
	}
	
	@Test(groups = { "web", "regression" })
	public void TC_01_Add_New_Language() {
	}

	@Test(groups = { "web", "regression" })
	public void TC_02_Update_Language() {
	}

	@Test(groups = { "web", "regression" })
	public void TC_03_Move_Language() {
	}
	
	//If before fail thì after still run if use alwaysRun = true
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		System.out.println("Run After Class");
	}
	
}
