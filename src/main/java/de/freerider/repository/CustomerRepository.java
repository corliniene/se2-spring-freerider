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
	public <S extends Customer> S save(S entity)  {
		if(entity != null) {
			if(entity.getId() == null || entity.getId() == "") {
				String id = idGen.nextId();
				while(existsById(id)) {
					id = idGen.nextId();
				}
				entity.setId(id);
			}
			if(!existsById(entity.getId())) {
				customerList.add(entity);
			} else {
			Optional <Customer> customer1 = findById(entity.getId());
				if(customer1.isPresent()) {
					Customer customer = customer1.get();
	
					customerList.remove(customer);
					customerList.add(entity);
					entity = (S) customer;
				}
			}
					
		} else {
			throw new IllegalArgumentException("Argument entity is null");
		}
		return entity;
	}

	@Override
	public <S extends Customer> Iterable<S> saveAll(Iterable<S> entities) {
		if(entities != null) {
			for(S entity : entities ) {
				save(entity);
			}
		} else {
			throw new IllegalArgumentException("Argument entities is null");
		}
		return entities;
	}

	@Override
	public Optional<Customer> findById(String id) {
		if (id != null) {
			for (Customer customer : customerList) {
				if (existsById(id) && id.equals(customer.getId())) {
					return Optional.of(customer);
				}
			}
			return Optional.empty();
		} else {
			throw new IllegalArgumentException("Argument id is null");
		}
		
	}

	@Override
	public boolean existsById(String id) {
		if(id != null) {
			for (Customer customer : customerList) {
				if (customer.getId().equals(id)) {
					return true;
				}
				
			}
			return false;
		} else {
			throw new IllegalArgumentException("Argument id is null");
		}
	}

	@Override
	public Iterable<Customer> findAll() {
		return customerList;
	}

	@Override
	public Iterable<Customer> findAllById(Iterable<String> ids) {
		ArrayList<Customer> returnList = new ArrayList<>();
		if(ids != null) {
			for(String id : ids) {
				findById(id).ifPresent(c -> {
					returnList.add(c);
				});
				
			}
			return returnList;
		} else {
			throw new IllegalArgumentException("Argument ids is null");
		}
	}

	@Override
	public long count() {
		return customerList.size();
	}

	@Override
	public void deleteById(String id) {
		if(id != null) {
			if(existsById(id)) {
				customerList.remove(findById(id).get());
			}
		} else {
			throw new IllegalArgumentException("Argument id is null");
		}
	}

	@Override
	public void delete(Customer entity) {
		if(entity != null && entity.getId() != null) {
			if( existsById(entity.getId())) {
					customerList.remove(entity);
				}
		} else {
			throw new IllegalArgumentException("Argument entity or its id is null");
		}
	}

	@Override
	public void deleteAllById(Iterable<? extends String> ids) {
		if(ids != null) {
			for(String id : ids) {
				findById(id).ifPresent(c ->customerList.remove(c));
			}	
		} else {
			throw new IllegalArgumentException("Argument ids is null");
		}
	}

	@Override
	public void deleteAll(Iterable<? extends Customer> entities) {
		if(entities != null) {
			for(Customer entity : entities) {
				delete(entity);
				}
		} else {
			throw new IllegalArgumentException("Argument entities is null");
		}
		
	}

	@Override
	public void deleteAll() {
		customerList.clear();
	}


	
}
