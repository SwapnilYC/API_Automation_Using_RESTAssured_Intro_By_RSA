package API_Testing.API_Automation_Testing_Using_RESTAssured.BasicsOfRESTAssured;

// for given, when & then methods. Because these are static methods
import static io.restassured.RestAssured.given;
// for reponse matcher for body
import static org.hamcrest.Matchers.equalTo;

import API_Testing.API_Automation_Testing_Using_RESTAssured.ReusableParts.PayLoad;
import API_Testing.API_Automation_Testing_Using_RESTAssured.ReusableParts.ReusableJsonPath;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Automating_POST_Request {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		// Add Place
		String placeID = addPlace();
		System.out.println("----------------------------------\n");
		
		//Get Place's Details
		String originalAddressVal = getPlace(placeID);
		System.out.println("Original Address is: "+originalAddressVal);
		System.out.println("----------------------------------\n");
		
		// Update Place's address
		String newAddress = "Pangya Niwas, Pushpatai Hire Nagar, Ajonde BAba chauk, Malegaon camp";
		System.out.println("----------------------------------\n");
		updatePlace(placeID, newAddress);
		
		//Get Place's Details
		String updatedAddressVal = getPlace(placeID);
		System.out.println("Updated Address is: "+updatedAddressVal);
		System.out.println("----------------------------------\n");
		
	}
	
// 1. Add Place	(POST method)
	public static String addPlace() {
		String createPlacePayload = PayLoad.createPlace();
		String response =
				given().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(createPlacePayload)
				.when().post("maps/api/place/add/json")
				.then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)")
				.extract().response().asString();
	
		JsonPath jpObj = ReusableJsonPath.rawToJson(response);
		String placeID = jpObj.getString("place_id");
		return placeID;
	}
	
// 2. Update place's Address (PUT Method)
	public static void updatePlace(String placeID, String newAddress) {
		// integrate the placeID & new address in update body payload
		String updatePlacePayload = PayLoad.updatePlace(placeID, newAddress);  
		String updatePlaceResource = "maps/api/place/update/json";
		
		given().queryParam("key", "qaclick123").headers("Content-Type","application/json").body(updatePlacePayload)
		.when().put(updatePlaceResource)
		.then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
	}
	
// 3. Get Place Details (GET Method)
	public static String getPlace(String placeID) {
		String getPlaceResource = "maps/api/place/get/json";
		String response = 
				given().queryParam("key", "qaclick123").queryParam("place_id", placeID)
				.when().get(getPlaceResource)
				.then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath jpObj = ReusableJsonPath.rawToJson(response);
		String addressFieldVal = jpObj.getString("address");
		return addressFieldVal;
	}
	
}