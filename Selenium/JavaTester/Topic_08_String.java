package JavaTester;

public class Topic_08_String {

	public static void main(String[] args) {
		String actual = "                First Option             ";
		System.out.println(actual);
		System.out.println(actual.trim());
		
		String user = "admin";
		String pass = "admin";
		String href = "http://the-internet.herokuapp.com/basic_auth";
		
		String[] hrefValue = href.split("//");

		href = hrefValue[0] + "//" + user + ":" + pass + "@" + hrefValue[1];
		System.out.println(href);
	}

}
