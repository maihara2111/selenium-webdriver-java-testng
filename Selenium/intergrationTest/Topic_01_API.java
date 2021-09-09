package intergrationTest;

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

public class Topic_01_API {
  @Test(groups = {"intergration", "regression"})
  public void TC_01_Get_Student_Name() {
	  System.out.println("Run TC 01");
  }
  
  @Test(groups = {"intergration", "regression"})
  public void TC_02_Update_Student_By_Id() {
	  System.out.println("Run TC 02");
  }
  
  @Test(groups = {"intergration", "regression"})
  public void TC_03_Delete_Student_By_Name() {
	  System.out.println("Run TC 03");
  }
}
