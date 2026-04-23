package API_Testing.API_Automation_Testing_Using_RESTAssured.BasicsOfRESTAssured;

import static io.restassured.RestAssured.given;

import java.util.List;

import API_Testing.API_Automation_Testing_Using_RESTAssured.ReusableParts.ReusableJsonPath;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pojo.APISubJson;
import pojo.CoursesSubJson;
import pojo.MobileSubJson;
import pojo.POJO_class_For_GET_Course_Response;
import pojo.WebAutomationSubJson;

public class HandlingOAuth2_0_Using_POJO_classes_Exercise {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String clientID = "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com";
		String client_secret = "erZOWM9g3UtwNRj340YYaK_W";
		String grant_type = "client_credentials";
		String scope = "trust";
		String access_token = getToken(clientID, client_secret, grant_type, scope);
		getCourseDetails(access_token);
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

// Hit api with token & get course details(in class obj format i.e. serialization)
	public static void getCourseDetails(String access_token) {
		String resource = "/oauthapi/getCourseDetails";
		// Serialization
		POJO_class_For_GET_Course_Response getCourceObj = given().queryParam("access_token", access_token).when().get(resource).as(POJO_class_For_GET_Course_Response.class);  
		
		List<WebAutomationSubJson> webAutomationObj = getCourceObj.getCourses().getWebAutomation();
		
		// Print name of every course in Webautomation
		System.out.println("Courses Name in Web Automation are");
		int sizewebAutomationObj = webAutomationObj.size();
		for(int i = 0; i < sizewebAutomationObj; i++) {
			System.out.println((i+1)+". " + webAutomationObj.get(i).getCourseTitle());
		}
	}
}
