package de.freerider.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.freerider.datamodel.Customer;

@SpringBootTest
public class CustomerRepositoryTest {
	
	@Autowired
	CrudRepository<Customer,String> customerRepository;
	// two sample customers
	private Customer mats;
	private Customer thomas;
	
	private Customer axel;
	private Customer eric;
	
	private ArrayList<Customer> customers;
	private ArrayList<String> ids;
	

	@BeforeEach
	public void setUpEach() {
		customerRepository.deleteAll();
		mats = new Customer(null,null,null);
		thomas = new Customer(null,null,null);
		axel = new Customer("axel", null, null);
		eric = new Customer("Eric", null, null);
		customers = new ArrayList<>();
		ids = new ArrayList<>();
	}
	
	@Test
	void testConstructor(){
		thomas.setId("a5555");
		mats.setId("1");
		assertEquals(0, customerRepository.count());
		assertFalse(customerRepository.existsById("a5555"));
		
		assertEquals(Optional.empty(),customerRepository.findById(mats.getId()));
		
		List<Customer> c = (List) customerRepository.findAll();
		assertEquals(c.size(), 0);
		
		ids.add(thomas.getId());
		ids.add(mats.getId());
		c = (List) customerRepository.findAllById(ids);
		assertEquals(c.size(), 0);
		
		customerRepository.deleteById("1");
		assertEquals(0, customerRepository.count());
		
		customerRepository.delete(thomas);
		assertEquals(0, customerRepository.count());
		
		customerRepository.deleteAllById(ids);
		assertEquals(0, customerRepository.count());
		
		customers.add(thomas);
		customers.add(mats);
		assertEquals(0, customerRepository.count());
		customerRepository.deleteAll(customers);
		assertEquals(0, customerRepository.count());
		
		customerRepository.deleteAll();
		assertEquals(0, customerRepository.count());
		
	}
	
	
	@Test
	void testSave() {
		// regular cases
		customerRepository.save(thomas);
		mats.setId("a5755");
		customerRepository.save(mats);
		assertEquals(2, customerRepository.count());
		
		assertEquals(Optional.of(mats),customerRepository.findById(mats.getId()));
		
		assertEquals(Optional.of(thomas),customerRepository.findById(thomas.getId()));
		assertNotEquals("", thomas.getId());
		assertEquals("a5755", mats.getId());
		
		//special cases
		assertThrows(IllegalArgumentException.class,() -> {customerRepository.save(null);});
		assertEquals(2, customerRepository.count());
		
		customerRepository.save(mats);
		assertEquals(2, customerRepository.count());
		
		axel.setId("a5755");
		customerRepository.save(axel);
		assertEquals(2, customerRepository.count());
		assertEquals(Optional.of(axel),customerRepository.findById("a5755"));
		
		
	}

	@Test
	void testSaveAll() {
		//regular cases
		customers.add(mats);
		customers.add(thomas);
		
		customerRepository.saveAll(customers);
		assertEquals(2, customerRepository.count());
		assertEquals(customers,customerRepository.findAll());
		
		assertNotEquals("", thomas.getId());
		assertNotEquals("", mats.getId());
		
		customers.clear();
		axel.setId("a7777");
		eric.setId("cDDDD");
		customers.add(axel);
		customers.add(eric);
		
		customerRepository.saveAll(customers);
		assertEquals(4, customerRepository.count());
		assertEquals(Optional.of(axel),customerRepository.findById("a7777"));
		assertEquals(Optional.of(eric),customerRepository.findById("cDDDD"));
		
		//special cases
		assertThrows(IllegalArgumentException.class,() -> {customerRepository.saveAll(null);});
		assertEquals(4, customerRepository.count());
		
		customerRepository.saveAll(customers);
		assertEquals(4, customerRepository.count());
		
		customerRepository.deleteAll(customers);
		assertEquals(2, customerRepository.count());
		assertEquals(Optional.of(mats),customerRepository.findById(mats.getId()));
		assertEquals(Optional.of(thomas),customerRepository.findById(thomas.getId()));
		
		axel.setId(null);
		eric.setId(null);
		customers.clear();
		String mid = mats.getId();
		String tid = thomas.getId();
		
		axel.setId(mid);
		eric.setId(tid);
		customers.add(axel);
		customers.add(eric);
		
		customerRepository.saveAll(customers);
		assertEquals(2, customerRepository.count()); 
		assertEquals(Optional.of(axel),customerRepository.findById(mid));
		assertEquals(Optional.of(eric),customerRepository.findById(tid));
		
		
	}

	@Test
	void testFindById() {
		//regular cases
		mats.setId("a5555");
		customerRepository.save(thomas);
		customerRepository.save(mats);
		
		assertEquals(Optional.of(thomas), customerRepository.findById(thomas.getId()));
		assertEquals(Optional.of(mats), customerRepository.findById("a5555"));
		assertEquals(Optional.empty(),customerRepository.findById("aaaaa"));
		
		//special case
		assertThrows(IllegalArgumentException.class,() -> {customerRepository.findById(null);});
		
	}


	@Test
	void testFindAll() {
		//regular cases
		customerRepository.save(thomas);
		customerRepository.save(mats);
		List<Customer> c = (List) customerRepository.findAll();
		assertEquals(c.get(0), thomas);
		assertEquals(c.get(1), mats);
		assertEquals(c.size(), 2);
	}

	@Test
	void testFindAllById() {
		//regular cases
		customers.add(mats);
		customers.add(thomas);
		customerRepository.saveAll(customers);
		ids.add(mats.getId());
		ids.add(thomas.getId());
		
		List<Customer> c = (List) customerRepository.findAllById(ids);
		assertEquals(c.get(1), thomas);
		assertEquals(c.get(0), mats);
		assertEquals(c.size(), 2);
		
		
		ids.clear();
		ids.add("aaaa");
		ids.add("0000");
		c = (List) customerRepository.findAllById(ids);
		assertEquals(c.size(),0);
		
		//special case
		assertThrows(IllegalArgumentException.class,() -> {customerRepository.findAllById(null);});
		
	}

	@Test
	void testCount() {
		//regular cases
		assertEquals(0, customerRepository.count());
		
		customerRepository.save(thomas);
		customerRepository.save(mats);
		assertEquals(2, customerRepository.count());
		
		
	}

	@Test
	void testDeleteById() {
		//regular cases
		customerRepository.save(thomas);
		customerRepository.save(mats);
		String id = mats.getId();
		customerRepository.deleteById(id);
		assertEquals(1, customerRepository.count());
		assertEquals(Optional.empty(),customerRepository.findById(id));
		
		customerRepository.deleteById("11111");
		assertEquals(1, customerRepository.count());
		
		assertThrows(IllegalArgumentException.class,() -> {customerRepository.deleteById(null);});
		assertEquals(1, customerRepository.count());
	}

	@Test
	void testDelete() {
		//regular cases
		customerRepository.save(thomas);
		customerRepository.save(mats);
		String id = thomas.getId();
		customerRepository.delete(thomas);
		assertEquals(1, customerRepository.count());
		assertEquals(Optional.empty(),customerRepository.findById(id));
		axel.setId("1");
		customerRepository.delete(axel);
		assertEquals(1, customerRepository.count());
		
		//special cases
		assertThrows(IllegalArgumentException.class,() -> {customerRepository.delete(null);});
		assertEquals(1, customerRepository.count());
		
	}

	@Test
	void testDeleteAllById() {
		//regular cases
		customers.add(mats);
		customers.add(thomas);
		customerRepository.saveAll(customers);
		ids.add(mats.getId());
		ids.add(thomas.getId());
		
		customerRepository.deleteAllById(ids);
		assertEquals(0, customerRepository.count());
		
		ids.clear();
		customerRepository.saveAll(customers);
		ids.add("2222");
		ids.add("llll");
		customerRepository.deleteAllById(ids);
		assertEquals(2, customerRepository.count());
		
		//special cases
		assertThrows(IllegalArgumentException.class,() -> {customerRepository.deleteAllById(null);});
		assertEquals(2, customerRepository.count());
		
		ids.clear();
		customerRepository.deleteAllById(ids);
		assertEquals(2, customerRepository.count());
		
	}

	@Test
	void testDeleteAllwithParameter() {
		//regular cases
		customers.add(mats);
		customers.add(thomas);
		customerRepository.saveAll(customers);
		customerRepository.deleteAll(customers);
		assertEquals(0, customerRepository.count());
		
		customerRepository.saveAll(customers);
		customers.clear();
		axel.setId("1");
		eric.setId("2");
		customers.add(axel);
		customers.add(eric);
		customerRepository.deleteAll(customers);
		assertEquals(2, customerRepository.count());
		
		//special cases
		assertThrows(IllegalArgumentException.class,() -> {customerRepository.deleteAll(null);});
		assertEquals(2, customerRepository.count());
		
		customers.clear();
		customerRepository.deleteAll(customers);
		assertEquals(2, customerRepository.count());
	}

	@Test
	void testDeleteAll() {
		//regular cases
		customerRepository.save(thomas);
		customerRepository.save(mats);
		customerRepository.save(eric);
		customerRepository.save(axel);
		String thomas_id = thomas.getId();
		String mats_id = mats.getId();
		String eric_id = eric.getId();
		String axel_id = thomas.getId();
		customerRepository.deleteAll();
		
		assertEquals(0, customerRepository.count());
		assertEquals(Optional.empty(),customerRepository.findById(axel_id));
		assertEquals(Optional.empty(),customerRepository.findById(thomas_id));
		assertEquals(Optional.empty(),customerRepository.findById(mats_id));
		assertEquals(Optional.empty(),customerRepository.findById(eric_id));
	}

}
