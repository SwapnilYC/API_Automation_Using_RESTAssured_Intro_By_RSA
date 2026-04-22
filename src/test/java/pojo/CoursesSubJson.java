package pojo;

import java.util.List;

// 2. Parent
public class CoursesSubJson {
	private List<WebAutomationSubJson> webAutomation;
	private List<APISubJson> api;
	private List<MobileSubJson> mobile;

// webAutomation
	public List<WebAutomationSubJson> getWebAutomation() {
		return webAutomation;
	}

	public void setWebAutomation(List<WebAutomationSubJson> webAutomation) {
		this.webAutomation = webAutomation;
	}

// webAutomation
	public List<APISubJson> getApi() {
		return api;
	}

	public void setApi(List<APISubJson> api) {
		this.api = api;
	}

// webAutomation
	public List<MobileSubJson> getMobile() {
		return mobile;
	}

	public void setMobile(List<MobileSubJson> mobile) {
		this.mobile = mobile;
	}

}
