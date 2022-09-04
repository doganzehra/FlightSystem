package com.iu.flightsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iu.flightsystem.model.Plane;
import com.iu.flightsystem.repository.PlaneRepo;

@Service
public class PlaneService {

	@Autowired
	public PlaneRepo repo;

	public PlaneService(PlaneRepo repo) {
		this.repo = repo;
		// TODO Auto-generated constructor stub
	}

	public List<Plane> getAll() {
		return repo.getAll();
	}

	public Plane getById(Long id) {
		return repo.getById(id);
	}

	public boolean save(Plane plane) {
		return repo.save(plane);
	}

	public boolean deleteById(Long id) {
		return repo.deleteById(id);
	}
}