package com.iu.flightsystem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iu.flightsystem.model.Customer;
import com.iu.flightsystem.model.viewobject.CustomerPlaneFlightVO;
import com.iu.flightsystem.service.CustomerService;

@RestController
@RequestMapping(path = "customer")
public class CustomerController {

	private CustomerService service;

	public CustomerController(CustomerService service) {
		this.service = service;
		// TODO Auto-generated constructor stub
	}

	// localhost:8080/flightsystem/customer/getAll
	@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Customer> getAll() {
		return service.getAll();
	}

	// localhost:8080/flightsystem/customer/getById/1
	@GetMapping(value = "/getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer getById(@PathVariable(value = "id") Long id) {
		return service.getById(id);
	}

	// localhost:8080/flightsystem/customer/save
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> save(@RequestBody Customer customer) {
		if (service.save(customer)) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Basari ile kaydedildi");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Basari ile kaydedilemedi");
		}
	}

	// localhost:8080/flightsystem/customer/deleteById/1
	@DeleteMapping(value = "/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable(value = "id") Long id) {
		if (service.deleteById(id)) {
			return ResponseEntity.status(HttpStatus.IM_USED).body("Basari ile silindi");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Basari ile silinemedi");
		}
	}

	// localhost:8080/flightsystem/customer/getAllCustomersOnAllFlights
	@GetMapping(value = "/getAllCustomersOnAllFlights", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CustomerPlaneFlightVO> getAllCustomersOnAllFlights() {
		return service.getAllCustomersOnAllFlights();
	}
}
