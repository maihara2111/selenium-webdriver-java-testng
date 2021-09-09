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

public class Topic_02_Assert {
	Object studentAddress = null;
	
  @Test()
  public void TC_01_Assert() {
	  String studentName = "Mai Hara";
	  
	  //IsDisplayed/ isEnabled/ isSelected/ isMultiple
	  
	  //Verify 1 điều kiện trả về là đúng
	  Assert.assertTrue(studentName.contains("Mai"));
	  
	  //Verify 1 điều kiện trả về là sai
	  Assert.assertFalse(studentName.contains("Boyy"));
	  
	  //Verify 2 điều kiện = nhau
	  Assert.assertEquals(studentName, "Mai Hara");
	  
	  //Verify 2 điều kiện ko = nhau
	  Assert.assertNotEquals(studentName, "Boyy");
	  
	  Assert.assertNull(studentAddress);
	  
	  studentAddress = "Mai Hara";
	  
	  Assert.assertNotNull("studentAddress");
  }
}
