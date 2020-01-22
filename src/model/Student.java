package model;

public class Student {
	private int id;
	private String firstName;
	private String lastName;
	private String streetAddress;
	private String postcode;
	private String city;

	public Student(int id, String fN, String lN, String sA, String pC, String city) {
		this.id = id;
		firstName = fN;
		lastName = lN;
		streetAddress = sA;
		postcode = pC;
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return id + ": " + firstName + " " + lastName + ", " + streetAddress + ", " + postcode + " " + city;
	}
}