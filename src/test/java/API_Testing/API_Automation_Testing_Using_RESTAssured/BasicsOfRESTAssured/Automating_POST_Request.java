package API_Testing.API_Automation_Testing_Using_RESTAssured.BasicsOfRESTAssured;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;  // for given, when & then methods. Because these are static methods

public class Automating_POST_Request {

	public static void main(String[] args) {
		// Storing base url
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		// GIVEN
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body("{\r\n"  
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Aditya Niwas\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}")
		// WHEN
		.when().post("maps/api/place/add/json")  
		// THEN
		.then().log().all().assertThat().statusCode(200);         
		

	}

}

/*
 RESTAssured is based on 3 methods
 	1. given -> all input data goes here (queryParam -> header -> body)
 	2. when -> API is hit & resources & HTTP methods goes here
 	3. then -> response validation goes here 
 */
 