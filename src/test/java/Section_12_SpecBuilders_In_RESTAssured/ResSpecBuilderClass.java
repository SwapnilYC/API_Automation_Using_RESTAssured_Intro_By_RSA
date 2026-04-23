package Section_12_SpecBuilders_In_RESTAssured;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ResSpecBuilderClass {
	public static ResponseSpecification resSpecBuilder() {
		ResponseSpecification resObj =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		return resObj;
	}
}
