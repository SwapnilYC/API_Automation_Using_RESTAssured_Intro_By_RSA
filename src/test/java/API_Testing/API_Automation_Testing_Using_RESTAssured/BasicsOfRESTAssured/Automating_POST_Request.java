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
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		// Add Place
		String placeID = addPlace();
		updatePlace(placeID);
	}
	
// 1. Add Place	(POST method)
	public static String addPlace() {
		String response =
				given().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(PayLoad.createPlace())
				.when().post("maps/api/place/add/json")
				.then().log().all().assertThat()
				.statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)")
				.extract().response().asString();
	
		JsonPath jpObj = new JsonPath(response);
		String placeID = jpObj.getString("place_id");
		return placeID;
	}
	
// 2. Update place's Address (Put Method)
	public static void updatePlace(String placeID) {
		String newAddress = "Pangya Niwas, Pushpatai Hire Nagar, Ajinde BAba chauk, Malegaon camp";
		// integrate the placeID & new address in update body payload
		String updatePlacePayload = PayLoad.updatePlace(placeID, newAddress);  
		String updatePlaceResource = "maps/api/place/update/json";
		
		given().log().all().queryParam("key", "qaclick123").headers("Content-Type","application/json").body(updatePlacePayload)
		.when().put(updatePlaceResource)
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
	}
}