package API_Testing.API_Automation_Testing_Using_RESTAssured.BasicsOfRESTAssured;

// for given, when & then methods. Because these are static methods
import static io.restassured.RestAssured.given;
// for reponse matcher for body
import static org.hamcrest.Matchers.equalTo;

import java.time.chrono.JapaneseChronology;

import API_Testing.API_Automation_Testing_Using_RESTAssured.payLoads.PayLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Automating_POST_Request {

	public static void main(String[] args) {
		// Storing base url
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String response =
				given().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(PayLoad.createPlace())
				.when().post("maps/api/place/add/json")
				.then().log().all().assertThat()
				.statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)")
				// Extract all the response (which is in json format) as String & save it in a String variable.
				.extract().response().asString();
		
		// Now use JsonPath object to convert your API response into a readable/queryable object
		// JsonPath is used to parse JSON response and extract specific values using key-based paths.
		JsonPath jpObj = new JsonPath(response);
		
		// Using JsonPath to navigate inside JSON and extract values
		String placeID = jpObj.getString("place_id");
		System.out.println("Place ID: "+placeID);
	}

}

/*
 * RESTAssured is based on 3 methods 1. given -> all input data goes here
 * (queryParam -> header -> body) 2. when -> API is hit & resources & HTTP
 * methods goes here 3. then -> response validation goes here
 */
