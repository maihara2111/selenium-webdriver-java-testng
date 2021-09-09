package JavaTester;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Topic_01_Data_Type {
	//char: unique 1 character
	char a = 'b';
	//interger
	byte byteNumber = 127;
	short shortNumber = 6333;
	int intNumber = 5555;
	long longNumber = 522222;
	//real number
	float floatNumber = 3.2F;
	double doubleNumber = 1111.1111D;
	//boolean
	boolean status = true;
	//string
	String name = "Mai Hara";
	String password = "P@ssword2111";
	String address = "1111/2222 LTK";
	//Class
	Topic_01_Data_Type topic01;
	//Object
	
	//Array
	String[] students = {name, address, password};
	int[] Salary = {1555, 1222, 1111};
	//Interface
	WebDriver driver;
	RemoteWebDriver remotewebDriver;
	//Collection (List/ Set/ Queue: Interface)
	List<String> studentName = new ArrayList<>();
	
}
