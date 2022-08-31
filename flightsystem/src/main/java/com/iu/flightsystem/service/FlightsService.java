package com.iu.flightsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iu.flightsystem.model.Flight;
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
		return repo.save(flight);
	}

	public Flight getById(Long id) {
		return repo.getById(id);
	}

	public List<Flight> getAll() {
		return repo.getAll();
	}
}
