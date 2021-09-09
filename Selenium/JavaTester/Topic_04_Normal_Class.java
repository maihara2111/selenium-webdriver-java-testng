package JavaTester;

import org.openqa.selenium.WebDriver;

public class Topic_04_Normal_Class {
	
	//Attribute
	String fullName = "Mai Hara";
	String address, street;
	//Method
	public void setFullName(String name) {
		fullName = name;		
	}
	
	public void setAddress(String addressName, String streetName) {
		address = addressName;
		street = streetName;				
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public static void main(String[] args) {
		WebDriver driver = null;
		driver.get("https://www.facebook.com/r.php");		
		
		String homePageUrl = driver.getCurrentUrl();
		
		
		Topic_04_Normal_Class topic = new Topic_04_Normal_Class();
		topic.setFullName("Mai Hara");
		
		//instance = dai dien cho kieu du lieu (class/interface)
		
	}

}
