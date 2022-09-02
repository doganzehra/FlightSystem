package com.iu.flightsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iu.flightsystem.model.Flight;
import com.iu.flightsystem.model.viewobject.CustomerFlightVO;
import com.iu.flightsystem.repository.FlightsRepo;

@Service
public class FlightsService {
	private FlightsRepo repo;

	public FlightsService(FlightsRepo repo) {
		this.repo = repo;
	}

	public boolean deleteById(Long id) {
		return repo.deleteById(id);
	}

	public boolean save(Flight flight) {
		Long fromWhere = flight.getFROM_WHERE();
		Long toWhere = flight.getTO_WHERE();
		Long price = Math.abs(fromWhere - toWhere) * 100;
		flight.setFLIGHT_PRICE(price);
		return repo.save(flight);

	}

	public Flight getById(Long id) {
		return repo.getById(id);
	}

	public List<Flight> getAll() {
		return repo.getAll();
	}

	public List<CustomerFlightVO> getFlightByCustomerName(String customerName) {
		return repo.getFlightByCustomerName(customerName);
	}
}
