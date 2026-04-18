package API_Testing.API_Automation_Testing_Using_RESTAssured.BasicsOfRESTAssured;

// for given, when & then methods. Because these are static methods
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;    // for reponse matcher for body
import io.restassured.RestAssured;
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
		.then().log().all()
		// Apply assertion on status code
		.assertThat().statusCode(200)
		// Applying validations on response body & Header
		.body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.52 (Ubuntu)");         
		

	}

}

/*
 RESTAssured is based on 3 methods
 	1. given -> all input data goes here (queryParam -> header -> body)
 	2. when -> API is hit & resources & HTTP methods goes here
 	3. then -> response validation goes here 
 */
 