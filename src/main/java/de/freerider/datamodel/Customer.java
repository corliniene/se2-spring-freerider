package de.freerider.datamodel;

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
	
			if(id == null || this.id == null) {
				this.id = id;
			}
		
	}
	
	public void setStatus(Status status){
		this.status = status;
		
	}
		
	
	
	public void setFirstName(String firstname) {
		if(firstname != null){
			this.firstName = firstname;
		} else {
			this.firstName = "";
		}
	}
	
	public void setLastName(String lastname) {
		if(lastname != null) {
			this.lastName = lastname;
		} else {
			this.lastName = "";
		}
		
	}
	
	public void setContact(String contact) {
		if(contact != null) {
			this.contact = contact;
		} else {
			this.contact = "";
		}
		
	}
	
	public String getId() {
		return this.id;
	}
	
	public Status getStatus(){
		return this.status;
	}
	
	public String getFirstName() {
		return this.firstName;
		
	}
	
	public String getLastName() {
		return this.lastName;
		
	}
	
	public String getContact() {
		return this.contact;
		
	}




}
