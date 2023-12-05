package Day2;

public class Pojo_PostRequest {
	
	//these class contains variables getter and setter method  so what all variable we will required to send the body
	//name, location , phone, courses (couses variable is an array) so total 4 variables are required 3 are the 
	//primitive variable and one is the array variable we crate string varible 3 and 1 is string array
	
	String name;
	String location;
	String phone;
	String courses[];
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String[] getCourses() {
		return courses;
	}
	public void setCourses(String[] courses) {
		this.courses = courses;
	}
	
	
	//now to assigning a data and retrieving data for every variable , we can extract data for these two things
	//so we have to write getter and setter for every method

}
