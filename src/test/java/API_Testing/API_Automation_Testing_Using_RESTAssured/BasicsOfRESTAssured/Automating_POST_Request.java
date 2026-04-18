package API_Testing.API_Automation_Testing_Using_RESTAssured.BasicsOfRESTAssured;

// for given, when & then methods. Because these are static methods
import static io.restassured.RestAssured.given;
// for reponse matcher for body
import static org.hamcrest.Matchers.equalTo;

import API_Testing.API_Automation_Testing_Using_RESTAssured.payLoads.PayLoad;
import io.restassured.RestAssured;

public class Automating_POST_Request {

	public static void main(String[] args) {
		// Storing base url
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(PayLoad.createPlace()).when().post("maps/api/place/add/json").then().log().all().assertThat()
				.statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)");
	}

}

/*
 * RESTAssured is based on 3 methods 1. given -> all input data goes here
 * (queryParam -> header -> body) 2. when -> API is hit & resources & HTTP
 * methods goes here 3. then -> response validation goes here
 */
