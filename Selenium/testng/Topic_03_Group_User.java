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

public class Topic_03_Group_User {
	Object studentAddress = null;
	
  @Test(groups = {"user"})
  public void TC_01_Create_User() {
	  
  }
  
  @Test(groups = {"user", "admin"})
  public void TC_02_View_User() {
	  
  }
  
  @Test(groups = {"user", "admin"})
  public void TC_03_Edit_User() {
	  
  }
  
  @Test(groups = {"user", "admin"})
  public void TC_04_Delete_User() {
	  
  }
}
