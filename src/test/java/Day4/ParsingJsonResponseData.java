package Day4;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import  static org.hamcrest.Matchers.*;

//how can we validate response body we will send request we get some response  in json and sometime will get xml format
//Parsing the response body
public class ParsingJsonResponseData {
	@Test
	void testJsonResponse()
	
	{
		//Approach 1
		given()
		//here we write prerequisite
		
		.contentType("ContentType.JSON")
		
		.when()
		.get(" http://localhost:3000/store")
		
		.then();
		
	}

}
