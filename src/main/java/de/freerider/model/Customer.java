package de.freerider.model;

enum Status {
	New,
	InRegistration,
	Active,
	Suspended,
	Deleted;
}

public class Customer {
	private String id;
	private String lastName;
	private String firstName;
	private String contact;
	private Status status;
	
	
	public Customer (String firstname, String lastname, String contact) {
		setLastName(lastname);
		setFirstName(firstname);
		setContact(contact);
		this.id = null;
		this.status = Status.New;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setStatus(Status status){
		this.status = status;
	}
		
	
	
	public void setFirstName(String firstname) {
		this.firstName = firstname;
		
	}
	
	public void setLastName(String lastname) {
		this.lastName = lastname;
		
	}
	
	public void setContact(String contact) {
		this.contact = contact;
		
	}
	
	public String getId() {
		return id;
	}
	
	public Status getStatus(){
		return status;
	}
	
	public String getFirstName() {
		return firstName;
		
	}
	
	public String getLastName() {
		return lastName;
		
	}
	
	public String getContact() {
		return contact;
		
	}




}