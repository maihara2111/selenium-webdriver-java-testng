package JavaTester;

import java.util.ArrayList;
import java.util.List;

public class Topic_09_Loop {
	public static void main(String[] args) {
		List<String> students = new ArrayList<>();
		students.add("Boyy K");
		students.add("Boyy H");
		students.add("Boyy L");

		for (String student : students) {
			System.out.println(student);
		}

		int number = 20;
		for (int i = 1; i <= number; i++) {
			System.out.println(i);
			// lan lap dau tien thi i=1
			// kiem tra i < number hay ko: i=1 <= 20
			// > thoa dieu kien > chua thoat khoi for
			// In ra i = 1
			// tang i len 1 don vi: i++ > i = 2

			// lan lap 2 thi i=2
			// kiem tra i < number hay ko: i=2 <= 20
			// > thoa dieu kien > chua thoat khoi for
			// In ra i = 2
			// tang i len 1 don vi: i++ > i = 3
			//...
			//lan lap 20 thi i=20 
			//kiem tra i < number hay ko: i=20 <= 20 
			//> thoa dieu kien > chua thoat khoi for
			//In ra i = 20
			//tang i len 1 don vi: i++ > i = 21
			
			//lan lap 21 thi i=21 
			//kiem tra i < number hay ko: i=21 <= 20 
			//> ko thoa dieu kien > thoat khoi for
			
			if (i==10) {
				break;
			}
			
			int a=0, b=0;
			a++; //a=1
			++b; //b=1
			System.out.println(a);
			System.out.println(b);

			//tang truoc gan sau
			b = ++a; 
			//++a = a = 1+1 = 2
			//b = a = 2
			System.out.println(b);
			
			//gan truoc tang sau
			a = b++; //a = 2, b = 3
			//a=b -> a=2
			//b++ -> b=2
			System.out.println(a);
			System.out.println(b);
		}

	}

}
