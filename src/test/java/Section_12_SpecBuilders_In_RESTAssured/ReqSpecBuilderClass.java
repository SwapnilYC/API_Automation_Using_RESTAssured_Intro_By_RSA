package Section_12_SpecBuilders_In_RESTAssured;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ReqSpecBuilderClass {
	public static RequestSpecification reqSpecBuilder() {
		RequestSpecification reqObj	= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();  // when build() -> then reurn type is RequestSpecification
		return reqObj;
	}
}
