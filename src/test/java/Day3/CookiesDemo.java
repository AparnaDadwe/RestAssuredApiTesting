package Day3;
//sometimes we send the request we get the some cookies and header as part of the response
//so we want to see whatare the cookies are created what are the headers are created by the request 
//want to capture some data from cookies and headers
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import  static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;


public class CookiesDemo {
	
	@Test(priority = 1,enabled = false)
	void testCookies() {
		//we have check wheather cookies genearted or not everytime cookies keeps changing dynamically so wecan expect 
		//exact value of cookies 
		//single cookies check in this test
		
		given()
		
		.when()
		.get("https://www.google.com/")
		
		.then()
		.cookie("AEC" , "Ackid1SCpO-1CP6R-5qQ0eUs1mIw5ph7RcqmBDUQMWtlWNq0CchK9QFeVg") //we have make some validation
		                                              // we have cookie method in which have to pass key value pair
		                                              //"AEC" - cookie name "Ackid1SCpO"-- cookie value
		// if our test got failed it means our cookie is generated everytime new cookie genrated it will not shoew u exact value 
		//whichever we  provide(means our test case is passed if the cookies genrated)
		.log().all();
		
		
	}

	@Test(priority = 2)
	void getMultipleCookiesInfo() {
		
       Response res= given()
		
		.when()
		.get("https://www.google.com/");  
       //here we want entire response into a variable
       // .get("https://www.google.com/") -- it will return response object that response will store in a variable we 
       //sprcify that variable res (res is Response type variable Response import from librabry io.restassured.response.Response)
       //Response res= given()
       //once we get the response it willl store in res variable which is Response type variable
       //to get single cookie info
       //res is containg all response body header cookie everything we want to extract only cookie from that response
       
       //single cookie information we have  getCookie() method 
       
         // String cookie_value=res.getCookie("AEC");//here we have to specify the name of the cookie frome that 
                                                //cookie_value variable we extract the cookie value 
          
         // System.out.println("Value of cookie is ======>"+cookie_value);//normal java code here we are not making any validation
                                                               // we are not write then() 
		
          //get all cookies from response we have getCookies() method which return in key value pair like hashMap we have to use
          
        Map<String,String> all_cookies_values=res.getCookies(); //all_cookies_values these variable having multiple keys and values
        
        //if we want ro read all the values and keys from the hasMap we have keySet() method
        
        System.out.println(all_cookies_values.keySet());// only keys information will return(keys value-[1P_JAR, AEC, NID])
        
        //once we know the key we extract the value of the key using for loop
        
        for(String k:all_cookies_values.keySet()) {
        	String cookie_value=res.getCookie(k);
        	System.out.println(k+  cookie_value  );
        	
        }
        
        
          
		
	}
}
