package de.freerider.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;



import org.junit.jupiter.api.Test;

public class CustomerTest {
	
	private Customer mats;
	private Customer thomas;
	
	public CustomerTest() {
		mats = new Customer(null,null,null);
		thomas = new Customer(null,null,null);
	
	}


	@Test
	void testIdNull() {
		assertNull(mats.getId());
		assertNull(thomas.getId());
	}

	@Test
	void testSetId() {
		mats.setId("55555");
		thomas.setId("blubblub");
		assertEquals("55555",mats.getId());
		assertEquals("blubblub", thomas.getId());
	}

	@Test
	void testSetIdOnlyOnce() {
		mats.setId("55555");
		thomas.setId("blubblub");
		mats.setId("1111");
		thomas.setId("abcd");
		assertEquals("55555", mats.getId());
		assertEquals("blubblub", thomas.getId());
	}
	@Test
	void testResetId() {
		mats.setId("55555");
		mats.setId(null);
		assertNull(mats.getId());
	}

	@Test
	void testNamesInitial() {
		assertEquals("", thomas.getFirstName());
		assertEquals("", thomas.getLastName());
		
	}

	@Test
	void testNamesSetNull() {
		thomas.setFirstName(null);
		thomas.setLastName(null);
		assertEquals("", thomas.getFirstName());
		assertEquals("", thomas.getLastName());
	}

	@Test
	void testSetNames() {
		thomas.setFirstName("Thomas");
		thomas.setLastName("Berg");
		assertEquals("Thomas", thomas.getFirstName());
		assertEquals("Berg", thomas.getLastName());
	}

	
	@Test
	void testContactsInitial() {
		assertEquals("", mats.getContact());
		
	}

	@Test
	void testContactsSetNull() {
		mats.setContact(null);
		assertEquals("", mats.getContact());
	
	}

	@Test
	void testSetContact() {
		mats.setContact("mats@gmail.com");
		assertEquals("mats@gmail.com", mats.getContact());
	}

	@Test
	void testStatusInitial() {
		assertEquals(Status.New, thomas.getStatus());
	}
	
	@Test
	void testSetStatus() {
		thomas.setStatus(Status.InRegistration);
		assertEquals(Status.InRegistration, thomas.getStatus());
		mats.setStatus(Status.Active);
		assertEquals(Status.Active, mats.getStatus());
		thomas.setStatus(Status.Suspended);
		assertEquals(Status.Suspended, thomas.getStatus());
		mats.setStatus(Status.Deleted);
		assertEquals(Status.Deleted, mats.getStatus());
		thomas.setStatus(Status.New);
		assertEquals(Status.New, thomas.getStatus());
	}
	
	
	

	
	

}
