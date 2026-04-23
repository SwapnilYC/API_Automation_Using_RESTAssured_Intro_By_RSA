package Section_11_Serialization_Of_Input_payload;

import java.util.List;

public class POJO_For_Add_Place {
	private int accuracy;
	private String name;
	private String phone_number;
	private String address;
	private String website;
	private String language;
	private LocationSubJson location;
	private List<String> types;

// 1. Location-------------------------------------------------------------------------
	public LocationSubJson getLocation() {
		return location;
	}

	public void setLocation(LocationSubJson location) {
		this.location = location;
	}

// 2. Accuracy-------------------------------------------------------------------------
	public int getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}

// 3. Name-------------------------------------------------------------------------
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

// 4. PhoneNumber-------------------------------------------------------------------------
	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

// 5. Address-------------------------------------------------------------------------
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

// 6. Types-------------------------------------------------------------------------
	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

// 7. Website-------------------------------------------------------------------------
	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

// 8. Language-------------------------------------------------------------------------
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
