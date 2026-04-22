package pojo;
// 1. grandParent
public class POJO_class_For_GET_Course_Response {
	private String instructor;
	private String url;
	private String services;
	private String expertise;
	private CoursesSubJson courses;
	private String linkedIn;

// instructor
	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

// url
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

// services
	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

// servexpertiseices
	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

// courses  -> This is nested Json -> Also create POJO for this nested jason
	public CoursesSubJson getCourses() {
		return courses;
	}

	public void setCourses(CoursesSubJson courses) {
		this.courses = courses;
	}

// linkedIn
	public String getLinkedIn() {
		return linkedIn;
	}

	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}

}

/*
 * Json response is: https://p.ip.fi/hvTB
 * 
 * GetCourcesResponse └──CoursesSubJackson └──WebautomationSubJackson,
 * ApiSubJackson, PriceSubJackson
 */
