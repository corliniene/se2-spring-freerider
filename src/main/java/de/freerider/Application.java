package de.freerider;

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
		long i = customerManager.count();
		System.err.print("i-->: " + i);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
