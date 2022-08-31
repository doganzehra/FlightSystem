package com.iu.flightsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iu.flightsystem.model.Cities;
import com.iu.flightsystem.repository.CitiesRepo;

@Service
public class CitiesService {
	private CitiesRepo repo;

	public CitiesService(CitiesRepo repo) {
		this.repo = repo;
	}

	public boolean deleteById(Long id) {
		return repo.deleteById(id);
	}

	public boolean save(Cities cities) {
		return repo.save(cities);
	}

	public Cities getById(Long id) {
		return repo.getById(id);
	}

	public List<Cities> getAll() {
		return repo.getAll();
	}
}
