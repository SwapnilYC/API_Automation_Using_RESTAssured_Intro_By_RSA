package Section_07_HandlingDynamicJson;

import org.testng.annotations.Test;

import API_Testing.API_Automation_Testing_Using_RESTAssured.ReusableParts.PayLoad;
import API_Testing.API_Automation_Testing_Using_RESTAssured.ReusableParts.ReusableJsonPath;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	@Test
	public void addBook() {
		RestAssured.baseURI = "http://216.10.245.166";
		String resource = "/Library/Addbook.php";
		String bookToBeAdded = PayLoad.addBook("Snehal","Mumbai425413");
		String response = given().header("Content-Type","application/json").body(bookToBeAdded)
		.when().post(resource)
		.then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath JPObj = ReusableJsonPath.rawToJson(response);
		System.out.println(JPObj.getString("Msg"));
		System.out.println(JPObj.getString("ID"));
	}
}


/*
Practical Problems:
	1. Dynamically build json payload with external data inputs
		- Now body payload is hardcoded in code(payload class)
	2. Parameterize the API Tests with multiple data sets
		- Currently if we want to change data we have to manually change in payload class
		- We cant do it on the fly
	3. How to send static Json files(payload) directly into Post Method of Rest Assured
 */