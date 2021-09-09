package JavaTester;

public abstract class Topic_04_Abtract_Class {
	//Atribute
	String fullName = "Mai Hara";
	
	String address, street;
	
	//method
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
	public abstract void setAddress();
	
}	
