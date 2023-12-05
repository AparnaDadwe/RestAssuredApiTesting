package Day3;

//how to get  header or capture information from header from request that we send

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import  static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;


public class HeadersDemo {
	//when we send reuest in response we get the resopnse header information always same so we can do validation in when() part
	//content type , Content-Encoding , server (on which server request in running) we have to validate in header
	//date and expire value changes continuosly like cookies in header when we get the information
	@Test(enabled = false, priority = 1)
	void testHeaders()                         
	
	
	{
		given()
		
		.when()
		.get("https://www.google.com/")    
		
		//in then section verify some header informastion
		.then()
		.header("Content-Type", "text/html; charset=ISO-8859-1")
		.and()
		.header("Content-Encoding", "gzip")
		.and()
		.header("Server", "gws");
	}
	
	//capture the information from header
	@Test
void Headgeters()                         
	
	
	{
		Response res=given()
		
		.when()
		.get("https://www.google.com/"); 
		
		//if we want header information from request then we dont hav to then() for validation 
		
		//single header information
		
	String singleHeaderValue=	res.getHeader("Content-Type");
	
	//System.out.println("The value of Conten-type is ===========>" + singleHeaderValue);
	
	                //get all headers information 
	  Headers myHeaders= res.getHeaders();// it will return its return type is Header
	  
	  // we have to store key value pair we will use for loop(we have to capture each and evry header which stored in variable 'myHeaders')
	  
	  //There are two types : Header -- Header name:value
	  //Headers - Multiple headers and value 
	  //Example==Header name:value, Header name:value, Header name:value
		
		for(Header hd:myHeaders)  //type of the variable is a Header (using hd can extract the name and value of the header)
		{
			System.out.println(hd.getName()+"                    "+hd.getValue());// getName() give the name of the header
			                                                                     // getValue() give the value of the header
		}
	}

}
