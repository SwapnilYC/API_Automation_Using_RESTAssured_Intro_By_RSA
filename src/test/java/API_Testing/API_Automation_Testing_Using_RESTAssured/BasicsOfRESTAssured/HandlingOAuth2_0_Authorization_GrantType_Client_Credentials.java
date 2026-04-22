package API_Testing.API_Automation_Testing_Using_RESTAssured.BasicsOfRESTAssured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import API_Testing.API_Automation_Testing_Using_RESTAssured.ReusableParts.ReusableJsonPath;

public class HandlingOAuth2_0_Authorization_GrantType_Client_Credentials {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String clientID = "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com";
		String client_secret = "erZOWM9g3UtwNRj340YYaK_W";
		String grant_type = "client_credentials";
		String scope = "trust";
		getCourseDetails(clientID, client_secret, grant_type, scope);
	}

// Hit the POST call on Authorization server
	public static String getToken(String clientID, String client_secret, String grant_type, String scope) {
		String resource = "/oauthapi/oauth2/resourceOwner/token";
		String response = given().formParam("client_id", clientID).formParam("client_secret", client_secret)
				.formParam("grant_type", grant_type).formParam("scope", scope).when().post(resource).then()
				.assertThat().statusCode(200).extract().response().asString();

		JsonPath jpObj = ReusableJsonPath.rawToJson(response);
		String access_token = jpObj.getString("access_token");
		return access_token;
	}

// Hit api with token & get course details
	public static void getCourseDetails(String clientID, String client_secret, String grant_type, String scope) {
		String access_token = getToken(clientID, client_secret, grant_type, scope);
		String resource = "/oauthapi/getCourseDetails";
		String response = given().queryParam("access_token", access_token).when().get(resource).then()
				.assertThat().statusCode(401).extract().response().asString();
		
		JsonPath jpObj = ReusableJsonPath.rawToJson(response);
		int size = jpObj.getInt("courses.webAutomation.size()");
		for (int i = 0; i < size; i++) {
			String courseName = jpObj.getString("courses.webAutomation["+i+"].courseTitle");
			String coursePrice = jpObj.getString("courses.webAutomation["+i+"].price");
			System.out.println(
					"Course Name is: " + courseName + "\nCourse Price is: " + coursePrice + "\n----------------------");
		}
	}

}

/*
 * API Contract download
 ******************************************************************
 * - Authorization Server EndPoint:
 * https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token - HTTP
 * Method : POST - Form parameters : - client_id:
 * 692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com -
 * client_secret: erZOWM9g3UtwNRj340YYaK_W - grant_type: client_credentials -
 * scope: trust
 ******************************************************************
 * - GetCourseDetails EndPoint (Secured by OAuth) :
 * https://rahulshettyacademy.com/oauthapi/getCourseDetails - HTTP Method : GET
 * - Query Parameter : access_token
 */
