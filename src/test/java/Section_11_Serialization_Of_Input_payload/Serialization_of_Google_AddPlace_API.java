package Section_11_Serialization_Of_Input_payload;

import java.util.Arrays;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class Serialization_of_Google_AddPlace_API {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String request = "maps/api/place/add/json";

		// Fill info in POJO class
		POJO_For_Add_Place addPlaceObj = new POJO_For_Add_Place();
		addPlaceObj.setName("Aditya Niwas");
		addPlaceObj.setAccuracy(50);
		addPlaceObj.setPhone_number("9420126945");
		addPlaceObj.setWebsite("www.pangyaShethakaHarryPotterOfMalegaon.com");
		addPlaceObj.setLanguage("Marathi");
		addPlaceObj.setAddress("AjondeBabaChauk_Malegaon");
		String[] types = { "Type_01", "Type_02" };
		addPlaceObj.setTypes(Arrays.asList(types));
		LocationSubJson latLongObj = new LocationSubJson();
		latLongObj.setLat(-38.383494);
		latLongObj.setLng(33.427362);
		addPlaceObj.setLocation(latLongObj);

		// Now serialize the POJO class
		String response = given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(addPlaceObj).when().post(request).then().assertThat().statusCode(200).extract().response()
				.asString();

		System.out.println(response);
	}

}
