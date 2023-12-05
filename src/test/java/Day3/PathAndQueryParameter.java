package Day3;
//How to specify the Path and Query Parameter in request
//https://reqres.in/api/users?page=2 (this api request) 
//https://reqres.in(domain url/base url)
// (/api/users)--this path parameter
//  ?page=2-- query parameter
//https://reqres.in/api/users?page=2&id=5 (in this there are two query parameter)
//sometime query parameter value keeps changing every time we cannot pass same qury parameter(pages/id)
//instead of hardcoading this value inside the url we can saperate them and specify the value
//at the time of srnding the request we can dynamically pass these two value instead of hardcoading we just saperate them into variable

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import  static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;


public class PathAndQueryParameter {
	
	@Test
	void testPathAndQueryParameter() {
		
		//https://reqres.in/api/users?page=2&id=5 (this our request - ?page=2&id=5(this our query parameter), users-path parameter)
		//we will specify that uery parameter in given() section as a prerequisite
		
		given()
		//here we define all query parameter
		//if we have any path and query parameter we can define in given section instead of direcly sending in the url
		//page and id is query parameter (should be exactly same as in request)we are not saving it another variable it will directly go to the request
		//we have to only users -- path parameter we have to save in another variable which is "myPath"
		//query parameter is goes along with the request in key value pair so can not change this 
		 .pathParam("myPath", "students")//  users is path parameter so using .pathParam() method we can specify path parameter
		                               //my path is the name given to our path(users)
		   // query parameter is specify the queryParam() method (page and id are two query parameter)
		 .queryParam("id", 4)    // second qyuery parameter
		 
		 .when()
		 .get("http://localhost:3000/{myPath}") // we save users path parameter in myPath variable with request in { myPath}
		 //.get("https://reqres.in/api/{myPath}")
		.then()
		.statusCode(200)
		.log().all();
		
		
	}

}
