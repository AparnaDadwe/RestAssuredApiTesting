package Day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import  static org.hamcrest.Matchers.*;

public class LoggingDemo {
	@Test
	void testLogs() 
	{
		given()
		
		.when()
		.get("https://reqres.in/api/users?page=2")
		
		.then()
		//.log().all();// print entire response in console window
		
		//.log().body(); // it will print only body from response
		
		//.log().cookies(); // it will print only cookies from response
		
		.log().headers(); // it will print header only from response
		
		
	}

}
