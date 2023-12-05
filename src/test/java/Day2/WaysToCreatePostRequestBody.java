package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import  static org.hamcrest.Matchers.*;

// there are different ways to create post request body in that we have pass response body content type auth etc 
// so that we have different ways 
//1)HashMap (Using HAshMap we can create post request)
//2)org.json (it is an library that we have to add in pom.xml file) and we have saperate class for that 
//3)POJO class(Plain Old java Object) it is the famous way to create body . We have to create saperate class 
//4)using some external json file (suppose we have some some json data in external file and we want to pass that json
// data as a request then we have to use tahrt external json data file )
public class WaysToCreatePostRequestBody {
	//post request body by HAshMap
	@Test(priority = 1,enabled = false)
	void createPostRequestUsingHashMap()
	{
		HashMap data=new HashMap();
		data.put("name", "Ajinkya");
		data.put("location", "Singapore");
		data.put("phone", "4567891230");
		
	    String	courseArray[]= {"Fishery","Artificial Intelligence"};
	
	    data.put("courses", courseArray);
	    
	    given()
	    .contentType("application/json")
	    .body(data)
	    
	    .when()
	    .post(" http://localhost:3000/students")
	    
	    .then()
	    .statusCode(201)
	    .body("name", equalTo("Ajinkya"))
	    .body("location", equalTo("Singapore"))
	    .body("phone",  equalTo("4567891230"))
	    .body("courses[0]", equalTo ("Fishery"))
	    .body("courses[1]", equalTo ("Artificial Intelligence"))
	    .header("Content-Type" , "application/json; charset=utf-8")
	    .log().all();
	
	}
	//post request body by org.json library
	@Test(enabled = false)
	void createPostUsingJsonLibrary() {
		// we have to create json object variable we have predefined class in org.json which is JSONObject
		//when we have to use org.json then we have to conver json object variable into string when we pass into the body always rember it
		//here we are using json library not hashmap when we use hashmap concept the data we directly passing into the body .body(data)
		//here we create obj using  org.json libarary so we can not pass these obj directly inside body so we have to convert 
		// obj into the string format and then we will able to send  obj in json format for converting onto string
		//.body(obj.toString())  we have to use
		JSONObject obj=new JSONObject();
		obj.put("name", "Aarushi");
		obj.put("location", "Netharland");
		obj.put("phone", "2254454545");
		
	    String	courseArray[]= {"MBBS","DMLT"};
	
	    obj.put("courses", courseArray);
	    
	    given()
	    .contentType("application/json")
	    .body(obj.toString())
	    
	    .when()
	    .post(" http://localhost:3000/students")
	    
	    .then()
	    .statusCode(201)
	    .body("name", equalTo("Aarushi"))
	    .body("location", equalTo("Netharland"))
	    .body("phone",  equalTo("2254454545"))
	    .body("courses[0]", equalTo ("MBBS"))
	    .body("courses[1]", equalTo ("DMLT"))
	    .header("Content-Type" , "application/json; charset=utf-8")
	    .log().all();
	    
		
	}
	//post request body by POJO class
	@Test(priority = 1, enabled = false)
	void createPostRequestByPOJO() {
		// first we have to create saperate pojo class(Day 2 - Pojo_PostRequest.java) and then we extract data from that POJO class
		//we ahve to create object of pojo class that we create in package Day-2 ojo_PostRequest.java
		//here we just setting the data so we use setName, setLocation, setphone, setCourses(we arenot retriving data we not using getName ettc)
		Pojo_PostRequest data=new Pojo_PostRequest();
		data.setName("Kajal");//this method is called from Pojo_PostRequest class Kajal(this value is assign to name)
	    data.setLocation("Keneya");  //(prepare data by pojo class and send the data to body)
	    data.setPhone("8956321475");
	    //we have to pass courses value which in String array
	    
	    String	courseArray[]= {"Fire Eng","Chemical Eng"};
	    data.setCourses(courseArray);
	    
	    given()
	    .contentType("application/json")
	    .body(data) //here we pass data from pojo class that we create in package day 2-Pojo_PostRequest
	    
	    .when()
	    .post(" http://localhost:3000/students")
	    
	    .then()
	    .statusCode(201)
	    .body("name", equalTo("Kajal"))
	    .body("location", equalTo("Keneya"))
	    .body("phone",  equalTo("8956321475"))
	    .body("courses[0]", equalTo ("Fire Eng"))
	    .body("courses[1]", equalTo ("Chemical Eng"))
	    .header("Content-Type" , "application/json; charset=utf-8")
	    .log().all();
		
	}
	//post request body by external json file
	@Test(priority = 1)
	void createPostRequestByExternalJsonFile() throws FileNotFoundException 
	{
		//we have to create json file in our project body.json file we have to keep json data in that file 
		//from that external json file we pass the data to body
		//first we have to open the file body.json  we have make object File type
		
		File file=new File(".\\body.json");
		
		//we have to read data from the file so we have to method FileReader 
		
		
	   FileReader fr=new FileReader(file);
		 
		// we have to split that file in different tokens means ultimately we have to get the ultimate format of data
		//we have another classs Json tokener
		JSONTokener jt=new JSONTokener(fr);
		
		JSONObject data=new JSONObject(jt);// after passing json tokener we get the jsonObject 
		
		//here data is in json format so we have to convert that json to sting by toString() method
		
		 given()
		    .contentType("application/json")
		    .body(data.toString()) //here we pass data from pojo class that we create in package day 2-Pojo_PostRequest
		    
		    .when()
		    .post(" http://localhost:3000/students")
		    
		    .then()
		    .statusCode(201)
		    .body("name", equalTo("Rahul"))
		    .body("location", equalTo("Uttarakhand"))
		    .body("phone",  equalTo("5896454515"))
		    .body("courses[0]", equalTo ("GK"))
		    .body("courses[1]", equalTo ("Ecomomics"))
		    .header("Content-Type" , "application/json; charset=utf-8")
		    .log().all();
		
	}
	@Test(priority = 2)
	void testDelete() {
		
		when()
		.delete("http://localhost:3000/students/13")
		
		.then()
		.statusCode(200);
	}
	
	// create POST request by using org.json library(we have to add org.json libray in pom.xml file)
	
	

}
