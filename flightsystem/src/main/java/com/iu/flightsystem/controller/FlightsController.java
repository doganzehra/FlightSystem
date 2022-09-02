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

import com.iu.flightsystem.model.Flight;
import com.iu.flightsystem.model.viewobject.CustomerFlightVO;
import com.iu.flightsystem.service.FlightsService;

@RestController
@RequestMapping(path = "flight")
public class FlightsController {
	private FlightsService service;

	public FlightsController(FlightsService service) {
		this.service = service;
	}

//localhost:8080/flightsystem/flight/getAll
	@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Flight> getAll() {
		return service.getAll();
	}

	// localhost:8080/flightsystem/flight/getById/1
	@GetMapping(value = "/getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flight getById(@PathVariable(value = "id") Long id) {
		return service.getById(id);
	}

	// localhost:8080/flightsystem/flight/save
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> save(@RequestBody Flight flight) {
		if (service.save(flight)) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Başarı ile kaydedildi");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Başarı ile kaydedilemedi");
		}
	}

	// localhost:8080/flightsystem/flight/deleteById/1
	@DeleteMapping(value = "/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable(value = "id") Long id) {
		if (service.deleteById(id)) {
			return ResponseEntity.status(HttpStatus.IM_USED).body("Başarı ile silindi");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Başarı ile silinemedi");
		}
	}

	// localhost:8080/flightsystem/flight/getFlightByCustomerName/ZEHRA DOGAN
	@GetMapping(value = "/getFlightByCustomerName/{customer_name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CustomerFlightVO> getFlightByCustomerName(@PathVariable(value = "customer_name") String customerName) {
		return service.getFlightByCustomerName(customerName);
	}

	// localhost:8080/flightsystem/flight/getOutcomingFlightsToCityByCustomerName/ZEHRA
	// DOGAN/ankara
	@GetMapping(value = "/getOutcomingFlightsToCityByCustomerName/{customer_name}/{city}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CustomerFlightVO> getOutcomingFlightsToCityByCustomerName(
			@PathVariable(value = "customer_name") String customerName, @PathVariable(value = "city") String city) {
		return service.getOutcomingFlightsToCityByCustomerName(customerName, city);
	}

	// localhost:8080/flightsystem/flight/getIncomingFlightsToCityByCustomerName/MEHMET
	// SIMSEK/MANISA
	@GetMapping(value = "/getIncomingFlightsToCityByCustomerName/{customer_name}/{city}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CustomerFlightVO> getIncomingFlightsToCityByCustomerName(
			@PathVariable(value = "customer_name") String customerName, @PathVariable(value = "city") String city) {
		return service.getIncomingFlightsToCityByCustomerName(customerName, city);
	}

}
