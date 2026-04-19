package API_Testing.API_Automation_Testing_Using_RESTAssured.ReusableParts;

import io.restassured.path.json.JsonPath;

public class ReusableJsonPath {
	public static JsonPath rawToJson(String response) {
		JsonPath jpObj = new JsonPath(response);
		return jpObj;
	}

}
