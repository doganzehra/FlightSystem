package com.iu.flightsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iu.flightsystem.model.Customer;
import com.iu.flightsystem.model.viewobject.CustomerPlaneFlightVO;
import com.iu.flightsystem.repository.CustomerRepo;

@Service
public class CustomerService {
	private CustomerRepo repo;

	public CustomerService(CustomerRepo repo) {
		this.repo = repo;
	}

	public boolean deleteById(Long id) {
		return repo.deleteById(id);
	}

	public boolean save(Customer customer) {
		return repo.save(customer);
	}

	public Customer getById(Long id) {
		return repo.getById(id);
	}

	public List<Customer> getAll() {
		return repo.getAll();
	}

	public List<CustomerPlaneFlightVO> getAllCustomersOnAllFlights() {
		return repo.getAllCustomersOnAllFlights();
	}
}
