package com.iu.flightsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iu.flightsystem.model.Plane;
import com.iu.flightsystem.repository.PlaneRepo;

@Service
public class PlaneService {

	@Autowired
	public PlaneRepo planeRepo;

	public boolean deleteById(Long id) {
		return planeRepo.deleteById(id);
	}

	public boolean save(Plane plane) {
		return planeRepo.save(plane);
	}

	public Plane getById(Long id) {
		return planeRepo.getById(id);
	}

	public List<Plane> getAll() {
		return planeRepo.getAll();
	}
}