package Day1;
//Rest assured test cases
//while writing rest assured test cases we used gherkin word which we used in bdd framworke like given when then but in rest assured they are method
//by default rest assured supprted gherkin languages we dot need to add that dependency 
/*given()  (prerequisite)
 *   inside the given section--when we send api request there are some prerequisite required 
 *   what type of content we are sending , we can specify the authentication, we can send the query parameter
 *   we can add the query parameter and path parameter , cookies we can send with our request 
 *   specially when we send the post request 
 *   all this thing we have to keep in given section
 * content type, set cookies, add auth , add parameter, set header info
 * */

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import  static org.hamcrest.Matchers.*;

import java.util.HashMap;


/* when()-section(request)
  * In when section we keep get, put ,post, delete (url request)
  * */

/*then()-section (validation)
 * all validation code we can do in then section like status code ,extract header content, extract response , extract cookies , response body all
 * thing we are validate
 * */
//we have write test  rest assured test cases in test ng style
//for using givn when then () method we have to import some packages they are called static packages we have to find out and manually we have to added

/*  io.restassured.RestAssured.*   (these 3 packages we have to import in every test case)
    io.restassured.matcher.RestAssuredMatchers.*
    org.hamcrest.Matchers.*/
// . is given when(), then() method because we accessing from given() method
// sometime we no need to use given() method so we can directly use when() method that we do not need given .before when() method and
//. is to then() method
 

public class HttpRequest {
	int id;  // we just create id as global variable and we can acess in evry method
	@Test(priority = 1)
	void getUser() {
		
		given()
		
		//we have to specify url which type of request we send 
		.when()
		  .get("https://reqres.in/api/users?page=2") // for multiple user information
		.then()
		 .statusCode(200)
		 .body("page",equalTo(2))
	     .log().all();  //this method will display all information in console window
	}
	@Test(priority=2)
	void createUser() {
		//when we sending a post request we have some data we have to pass the user information
		//we have content type in json format what type of data we sending (json type)
		//we have to prepare test data 
		//to generate body of post request we have different method in retsassured
		HashMap data=new HashMap();
		data.put("name", "Aparna");
		data.put("job", "trainer");
		
	id=	given()
		.contentType("application/json")
		.body(data)
		
		.when()
		
		.post("https://reqres.in/api/users") // after sending the request this will send some request 
		                                      //i dont want to do validation i want to capture response
		                                      // in a variable response in json format so we have to use some traditional method 
         .jsonPath().getInt("id");   //which response we have to get like id(id in integer value id=2) we get so we use getInt() method
		                             //and getString() method is used to get string value like name="Aparna"
		                             //we have to id store in variable which we declare in globally id=given()
	                                 //so whatever id we retrived we used for another request like for update , delete
		
		                            
		
//		.then()
//		.statusCode(201)
//		.log().all();
		
		//for updating means put request we have to capture the id of that specific user so id is capture from above method which give 
		// the response (get the id from response)
	}
	//if the create user is succesful then we update the user so updateUser is depends on createUser method so in testng we have 
	//dependency or feature means if the createUser is succesfully paased then updateUser is passed so and if crateUser is failed
	//then we have to skipped the updateUser by using 
	
	@Test(priority = 3, dependsOnMethods = {"createUser"})
	void updateUser() {
		
		HashMap data=new HashMap();
		data.put("name", "Abhijit");
		data.put("job", "Lecturer");
		
	given()
		.contentType("application/json")
		.body(data)
		
		.when()
		
		.put("https://reqres.in/api/users/"+id) // https://reqres.in/api/users/2 this is the url for put request (udate)
		                                        //  but we have to send request url like that (https://reqres.in/api/users/"+id) 
		                                        // here id is the parameter we get after createUser(so we have pass the +id)
	    .then()
	    .statusCode(200)
	    .log().all();
	}
	@Test(priority = 4)
	void deleteUser() {
		
		given() //for delete request we dont have any prerequisite so we given() method we should empty
		
		.when()
		.delete("https://reqres.in/api/users/"+id)//here we also provide id of that perticular user we want to delete
		                                          // this id extract from the createUser
		
		.then()
		.statusCode(204)
		.log().all();
		
		
		
	}

}
