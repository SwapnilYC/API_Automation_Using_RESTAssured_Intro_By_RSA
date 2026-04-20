package API_Testing.API_Automation_Testing_Using_RESTAssured.BasicsOfRESTAssured;

import API_Testing.API_Automation_Testing_Using_RESTAssured.ReusableParts.PayLoad;
import API_Testing.API_Automation_Testing_Using_RESTAssured.ReusableParts.ReusableJsonPath;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonPath jpObj = ReusableJsonPath.rawToJson(PayLoad.coursePrice());
		
		//1. Print No of courses returned by API
		int count = jpObj.getInt("courses.size()"); // courses is an array
		System.out.println("Course Count: "+count);
		
		// 2. Print Purchase Amount
		System.out.println("Total Purchase Amount is: "+jpObj.getString("dashboard.purchaseAmount"));
		
		// 3. Print Title of the first course
		System.out.println("Title of first course: " + jpObj.getString("courses[0].title"));
		
		// 4. Print All course titles and their respective Prices
		for(int i = 0; i < count; i++) {
			int courseNo = i+1;
			System.out.println("Tilte of course "+courseNo+": " + jpObj.getString("courses["+i+"].title") + " & Price is = "+ jpObj.getInt("courses["+i+"].price"));
		}
		
		// 5. Print no of copies sold by RPA Course
		for(int i = 0; i < count; i++) {
			if(jpObj.getString("courses["+i+"].title").equalsIgnoreCase("RPA")) {
				System.out.println("No of Copies Sold By RPA course is " + jpObj.getInt("courses["+i+"].copies"));
				break;
			}
		}
		System.out.println("No of Copies Sold By RPA course is " + jpObj.getInt("courses[2].copies"));
		
		// 6. Verify if Sum of all Course prices matches with Purchase Amount
		int totalPrice = 0;
		for(int i = 0; i < count; i++) {
			totalPrice+=(jpObj.getInt("courses["+i+"].price")*jpObj.getInt("courses["+i+"].copies"));
		}
		int purchaseAmount = jpObj.getInt("dashboard.purchaseAmount");
		
		if(purchaseAmount-totalPrice > 0) {
			System.out.println("Purchase amount is greater than totalPrice");
		}else if (purchaseAmount-totalPrice < 0) {
			System.out.println("Purchase amount is less than totalPrice");
		}else {
			System.out.println("Purchase amount is same as totalPrice");
		}
		System.out.println("Purchase Amount: "+ purchaseAmount+"\nTotal Price: "+totalPrice);
		
		
	}
}


/*
1. Print No of courses returned by API

2. Print Purchase Amount

3. Print Title of the first course

4. Print All course titles and their respective Prices

5. Print no of copies sold by RPA Course

6. Verify if Sum of all Course prices matches with Purchase Amount
 */