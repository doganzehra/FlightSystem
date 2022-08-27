package com.iu.flightsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iu.flightsystem.model.Plane;
import com.iu.flightsystem.repository.PlaneRepo;

@Service
public class PlaneService {

	private PlaneRepo repo;

	public PlaneService(PlaneRepo repo) {
		this.repo = repo;
	}

	public boolean deleteById(Long id) {
		return repo.deleteById(id);
	}

	public boolean save(Plane plane) {
		return repo.save(plane);
	}

	public Plane getById(Long id) {
		return repo.getById(id);
	}

	public List<Plane> getAll() {
		return repo.getAll();
	}
}