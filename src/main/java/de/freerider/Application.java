package de.freerider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import de.freerider.model.Customer;
import de.freerider.repository.CrudRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class Application {
	
	
	
	@Autowired
	CrudRepository<Customer,String> customerManager;
	
	@EventListener(ApplicationReadyEvent.class)
	public void doWhenApplicationReady() {
		Customer cMax = new Customer("Max", "Mustermann", "mmustermann@web.de");
		Customer cLena = new Customer("Lena", "Müller", "lena@gmail.com");
		Customer cTim = new Customer("Tim", "Goldberg", "tim_goldberg@web.de");
		Customer cAnne = new Customer("Anne", "Smith", "asmith@gmail.com");
		Customer cKim = new Customer("Kim", "Ericsson", "kericsson@gmail.com");
		
		List<Customer> customers = new ArrayList<>();
		List<String> ids = new ArrayList<>();
		
		customers.add(cMax);
		customers.add(cLena);
		customers.add(cTim);
		customers.add(cAnne);
		customers.add(cKim);
		
		//save all test
		customerManager.saveAll(customers);
		System.out.println("Saved all given customers. Number of customers:  " + customerManager.count());
		
		
		//save existing customer
		customerManager.save(cMax);
		System.out.println("Customer Max already exists. Number of customers:  " + customerManager.count());
		
		//find by id
		System.out.println("Customer found: " + customerManager.findById(cMax.getId()));
		System.out.println("Customer for this id doesn't exist: "+ customerManager.findById("123"));
	
		
		//exists by id
		System.out.println("Customer with this id exists: " + customerManager.existsById(cMax.getId()));
		System.out.println("Customer with this id exists: " + customerManager.existsById("123"));
		
		
		//Find all
		System.out.println("All Customers: " + customerManager.findAll());
		
		//Find all by id
		ids.add(cLena.getId());
		ids.add(cAnne.getId());
		ids.add(cKim.getId());
		ids.add(cMax.getId());
		System.out.println("All Customers for given ids:" + customerManager.findAllById(ids));
		
		//delete by id
		customerManager.deleteById(cAnne.getId());
		customerManager.deleteById("123");
		System.out.println("Deleted customer Anne. Number of customers: " + customerManager.count());
		
		//delete
		customerManager.delete(cTim);
		System.out.println("Deleted Tim. Number of customers:  " + customerManager.count());
		
		// Delete All By id
		customerManager.deleteAllById(ids);
		System.out.println("Deleted everyone by id. Number of customers: " + customerManager.count());
		
		// delete All 
		customerManager.saveAll(customers);
		System.out.println("Saved all given customers. Number of customers: " + customerManager.count());
		customerManager.deleteAll(customers);
		System.out.println("Deleted all given customers. Number of customers:  " + customerManager.count());
		
		// delete all (no arguments)
		customerManager.saveAll(customers);
		System.out.println("Saved all given customers. Number of customers: " + customerManager.count());
		customerManager.deleteAll();
		System.out.println("Deleted all customers. Number of customers:  " + customerManager.count());
		
		
		
		long i = customerManager.count();
		System.err.print("i-->: " + i);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
