package JavaTester;

public class Topic_02_And_Or {

	public static void main(String[] args) {
		boolean a = true;
		boolean b = false;
		boolean c = true;
		boolean d = false;
		
		//TRUE and TRUE = TRUE
		System.out.println(a & c);
		
		//TRUE and FALSE = FALSE
		System.out.println(a & b);
		
		//FALSE and TRUE = FALSE
		System.out.println(b & c);
		
		//FALSE and FALSE = FALSE
		System.out.println(b & d);
		
		
		//TRUE or TRUE = TRUE
		System.out.println(a | c);
		
		//TRUE or FALSE = TRUE
		System.out.println(a | b);
		
		//FALSE or TRUE = TRUE
		System.out.println(b | c);
		
		//FALSE or FALSE = FALSE
		System.out.println(b | d);
		
		
		
		
	}

}
