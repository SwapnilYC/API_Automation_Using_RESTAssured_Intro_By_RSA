package API_Testing.API_Automation_Testing_Using_RESTAssured.BasicsOfRESTAssured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

import API_Testing.API_Automation_Testing_Using_RESTAssured.ReusableParts.PayLoad;
import API_Testing.API_Automation_Testing_Using_RESTAssured.ReusableParts.ReusableJsonPath;

public class RESTAssuredAutomationToCreateBugInJIRA {
	public static void main(String[] args) {
		// Create bug
		RestAssured.baseURI = "https://swap95nil.atlassian.net";
		String resource = "/rest/api/3/issue";
		String spaceKey = "SCRUM"; // found in space settings
		String issueSummary = "Website Items are not working-Automation";
		String authKey = "Basic c3dhcDk1bmlsQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBwUVlQa0tudC1xVlNWOXpWb0FBM3RGRFg3aGpsMG9JcFZYUENVRjJlVUhobElIdlQ5b0t5UlNzUWIwZFV6MkVQNFdhNWZlWnNRWWlSVWhiU21aX3M0WHY1QktCWHBDWUdwUWJPS3d5WHpfUzRNcjhVbHBRakNGX3V2dWJqWWxieE13eUFVS1RDdDlyOC1iM1VmajFwZURZVktjV3cwaVQxaTBUa1ZubDh0Nm89QjhENzk5RDQ=";

		// Create issue & save the issue ID
		String issueID = createAnIssue(resource, spaceKey, authKey, issueSummary);

		// Upload screenshot to that issue
		attachFileToAnIssue(issueID, authKey);
	}

// 1. Create issue
	public static String createAnIssue(String resource, String spaceKey, String authKey, String issueSummary) {
		String response = given().log().all().header("Content-Type", "application/json")
				.header("Authorization", authKey).body(PayLoad.createIssueInJira(spaceKey, issueSummary)).when()
				.post(resource).then().log().all().assertThat().statusCode(201).extract().response().asString();

		JsonPath jpObj = ReusableJsonPath.rawToJson(response);
		String issueId = jpObj.getString("id");
		return issueId;
	}

// 2. Attach file to an issue
	public static void attachFileToAnIssue(String issueId, String authKey) {
		String response = given().header("X-Atlassian-Token", "no-check").header("Authorization", authKey)
				.pathParam("issueId", issueId) // Path parameter is denoted by '/' (query
											// param by ?)
				.multiPart("file", new File("C:/Users/Swpnil/Downloads/849339.png")).when()
				.post("/rest/api/3/issue/{issueId}/attachments") // insert path param in {}
				.then().assertThat().statusCode(200).extract().response().asString();

		JsonPath jpObj = ReusableJsonPath.rawToJson(response);

	}
}
