package de.freerider.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Component;

import de.freerider.model.Customer;


@Component
class CustomerRepository implements CrudRepository<Customer, String> {
	
	private final IDGenerator idGen = new IDGenerator( "C", IDGenerator.IDTYPE.NUM, 6 );
	private final ArrayList<Customer> customerList = new ArrayList<>();

	@Override
	public <S extends Customer> S save(S entity) {
		if(entity.getId() == null) {
			String id = idGen.nextId();
			while(existsById(id)) {
				id = idGen.nextId();
			}
			entity.setId(id);
		}
		if(!existsById(entity.getId())) {
			customerList.add(entity);
		}
		return entity;
	}

	@Override
	public <S extends Customer> Iterable<S> saveAll(Iterable<S> entities) {
		for(S entity : entities ) {
			save(entity);
		}
		return entities;
	}

	@Override
	public Optional<Customer> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(String id) {
		if(customerList.contains(findById(id))){
			return true;
		} else {
		return false;
		}
	}

	@Override
	public Iterable<Customer> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Customer> findAllById(Iterable<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		return customerList.size();
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Customer entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllById(Iterable<? extends String> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Customer> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}


	
}
