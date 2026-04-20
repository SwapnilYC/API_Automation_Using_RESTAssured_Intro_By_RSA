package Section_07_HandlingDynamicJson;

import org.testng.annotations.Test;

import API_Testing.API_Automation_Testing_Using_RESTAssured.ReusableParts.PayLoad;
import API_Testing.API_Automation_Testing_Using_RESTAssured.ReusableParts.ReusableJsonPath;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {

	public String addBook() {
		RestAssured.baseURI = "http://216.10.245.166";
		String resource = "/Library/Addbook.php";
		String bookToBeAdded = PayLoad.addBook("SnehalCH002","Mumbai425413");
		String response = given().header("Content-Type","application/json").body(bookToBeAdded)
		.when().post(resource)
		.then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath JPObj = ReusableJsonPath.rawToJson(response);
		System.out.println(JPObj.getString("Msg"));
		String ID = JPObj.getString("ID");
		System.out.println(ID);
		return ID;
	}
	
	@Test
	public void deleteBook() {
		RestAssured.baseURI = "http://216.10.245.166";
		String resource = "/Library/DeleteBook.php";
//		String IDOfBookToBeDeleted = addBook();
		String IDOfBookToBeDeleted = "bcd227";
		System.out.println("ID Of Book To Be Deleted: " + IDOfBookToBeDeleted);
		
		String response = given().header("Content-Type","application/json").body("{\r\n"
				+ "\"ID\" : \""+IDOfBookToBeDeleted+"\"}")
		.when().post(resource)
		.then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath JPObj = ReusableJsonPath.rawToJson(response);
//		System.out.println(JPObj.getString("Msg"));
//		String ID = JPObj.getString("ID");
		System.out.println(JPObj.getString("msg"));
	}
}
