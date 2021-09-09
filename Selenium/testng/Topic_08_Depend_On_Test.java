package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

//@Listeners(testng.TestListener.class)
public class Topic_08_Depend_On_Test {
	Object studentAddress = null;
	
  @Test()
  public void TC_01_Create_User() {
	  Assert.assertTrue(false);
  }
  
  @Test(dependsOnMethods = "TC_01_Create_User")
  public void TC_02_View_User() {
	  
  }
  
  @Test(dependsOnMethods = "TC_02_View_User")
  public void TC_03_Edit_User() {
	  
  }
  
  @Test(dependsOnMethods = "TC_03_Edit_User")
  public void TC_04_Delete_User() {
	  
  }
}
