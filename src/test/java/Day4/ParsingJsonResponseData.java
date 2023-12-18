package Day4;

import static org.testng.Assert.assertEquals;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import  static org.hamcrest.Matchers.*;

//how can we validate response body we will send request we get some response  in json and sometime will get xml format
//Parsing the response body 
//in this we have store.json file in this book in which have json array inside that array we have json object
//if we want to validate specific field from that json arrray then we have to give proper path (book[4].title)
//in body section 

public class ParsingJsonResponseData {
	@Test(priority = 1, enabled = false)
	void testJsonResponse()
	
	{
		//Approach 1 to validate response body
		//if json data is less then we can use Approach 1 for validation
		given()
		//here we write prerequisite
		
		.contentType(ContentType.JSON)
		
		.when()
		.get("http://localhost:3000/store")// in this we have store.json file
		
		.then()
		.statusCode(200)
		.header("Content-Type" , "application/json; charset=utf-8")
		.body("book[4].title", equalTo("To Kill a Mockingbird")); // here we have to give proper json path and its value to validate 
		                                                         // for specific field in book in whic json array which have json object
		                                                         //on the index of book[4] we have verify title
//		.log().all();
		
	}
	
	@Test(priority = 2,enabled = false)
	void testJsonResponse2()
	
	{
		//Approach 2 to validate response body
		//some time we have so much xml data in file we have to validate multiple things you have to read data sometimes
		//you have to traverse each and every field in the response  sometimes we have to verify that our data in json iile or not
		//so in that case we have to capture the entire response data into variable and then we do some additional validation
		// we are putting validation at the top of the response varibles res we haave to dont use then() section
		Response res; // here res is variable of Response type in which we store entire json response
		                   //once we get the entire response then we put validation means instead of doing validation in then()
		                   // section we can directly capture the entire response in one variable which is Response type then validate
		// if we want to do assertion/verification on the basis of this res variable then we have to use  testng assertion
		res=given()
		//here we write prerequisite
		
		.contentType(ContentType.JSON)
		
		.when()
		.get("http://localhost:3000/store");// in this we have store.json filef
		
		//here we are doing validation using res variable  and testng assertion
		
		 assertEquals(res.getStatusCode(), 200);// this is one validation
		 assertEquals(res.getHeader("Content-Type"), "application/json; charset=utf-8");
		 
		 //additional assertion we can add from the response
		 //we have to capture speicific from response body we have method jsonPath() in which we have get() in we have to add our
		 //jsonPath of specific filed that we have ro validate
		 
	String bookName=res.jsonPath().get("book[4].title").toString(); // whenever we use get() method it will return response i object (integer, value )
		                                     // we have to convert into String format using toString method 
		                                     // it will give a string value we have to store in string type of variable
	                                         // we converting data into string format
       assertEquals(bookName, "To Kill a Mockingbird");
		 
		}
	
	@Test(priority = 3,enabled = false)
	void testJsonResponseData()
	
	{
		 // here res is variable of Response type in which we store entire json response
                      //once we get the entire response then we put validation means instead of doing validation in then()
                      // section we can directly capture the entire response in one variable which is Response type then validate
                      // if we want to do assertion/verification on the basis of this res variable then we have to use  testng assertion
		Response res=given()
              //here we write prerequisite
				 .contentType(ContentType.JSON)
         
                  .when()
                  .get("http://localhost:3000/store");// in this we have store.json file it will return response in Response format

    //here we are doing validation using res variable  and testng assertion

  //suppoose my requirement is we have multiple books is here in my store.json file 
  // i want to capture all book , In book json array different types of json object
  //from each book object i want to capture title i want tocapture title of each book
       
       //To traverse entire json response we have  special class JSONObject class(or.json dependency we added in pom.xml file)
      
      JSONObject jo=new JSONObject(res.asString()); // converting entire reponse into jsonObject type 
                                                    // whenever u create oject of JSONObject class it will take one parameter res 
                                                    //whic capture entire response inthe form of object we have to convert in String 
       // i want to print book title of each book(we use for loop) 
       //we are reading this value from json , array index start from 0 how many times we have to repeat 
      //it depends on no of objects in book array it may increase or decrease we have to get length of array dynamically
      //we have gstJSONArray we have book array (jo.getJSONArray("book") it will give entire json array elements)
      
     for(int i=0;i<jo.getJSONArray("book").length();i++) 
     {
    	 //here we have to extract title of each book in an array(i is repersenting an index)
    	 //book is an json array and inside that json array we have json object(by index manner we representing the json object ian array)
    	 // getJSONObject method
    	 
  String bookTitle=jo.getJSONArray("book").getJSONObject(i).get("title").toString();//getJSONObject(i) it will give 0th index and and we have to get field as title in an array
        
    	//JSONObject from this jsonObject we get JSONArray and in the jsonArray 
    	//we have multiple object capture oth index and title and convert into string
    	//get("title") will return type object we have to convert to string
  
  System.out.println(bookTitle);
     }
  }
	@Test
	void testJsonResponseDataPriceFromBokArray() {
		//validate total price of book with expected 
		Response res=given()
			 	
			.contentType(ContentType.JSON)	
			
			.when()
			.get("http://localhost:3000/store");
		
		JSONObject jo=new JSONObject(res.asString());
	// here we are extracting field price from an json array
		double totalPrice=0;
		for(int i=0;i<jo.getJSONArray("book").length();i++)
		{
			String price=jo.getJSONArray("book").getJSONObject(i).get("price").toString();
			//expression for calculation
			totalPrice=totalPrice+Double.parseDouble(price);//here price in string format we have to convert into double format
			                                                // we wrapper class Double in which parseDouble method 
		}
		System.out.println("Total price is:"+ totalPrice);
		assertEquals(totalPrice, 928.7);
		
			
			
	}
	

}
