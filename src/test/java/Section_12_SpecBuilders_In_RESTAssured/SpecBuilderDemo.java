package Section_12_SpecBuilders_In_RESTAssured;

import static io.restassured.RestAssured.given;

import java.util.Arrays;

import Section_11_Serialization_Of_Input_payload.LocationSubJson;
import Section_11_Serialization_Of_Input_payload.POJO_For_Add_Place;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilderDemo {

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

		// Use spec builders to create RequestSpecification & ResponseSpecification
		RequestSpecification reqObj = ReqSpecBuilderClass.reqSpecBuilder(); // Create object of RequestSpecBuilder Class
																			// & use it to create req
		RequestSpecification req = given().spec(reqObj).body(addPlaceObj);
		ResponseSpecification resObj = ResSpecBuilderClass.resSpecBuilder();

		String response = req.when().post("maps/api/place/add/json").then().spec(resObj).extract().response()
				.asString();

		System.out.println(response);

	}

}
