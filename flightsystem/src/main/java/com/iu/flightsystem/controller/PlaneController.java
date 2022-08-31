package com.iu.flightsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.iu.flightsystem.model.Plane;
import com.iu.flightsystem.repository.PlaneRepo;
import com.iu.flightsystem.service.PlaneService;


@RestController
@RequestMapping(path = "plane")
public class PlaneController {

	@Autowired
	public PlaneService planeService;
	
	// localhost:8080/flightsystem/plane/getAll
	@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Plane> getAll() {
		return planeService.getAll();
	}

	// localhost:8080/flightsystem/plane/getById/1
	@GetMapping(value = "/getById/{plane_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Plane getById(@PathVariable(value = "plane_id") Long plane_id) {
		return planeService.getById(plane_id);
	}

	// localhost:8080/flightsystem/plane/save
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> save(@RequestBody Plane plane) {
		if (planeService.save(plane)) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Başarı ile kaydedildi");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Başarı ile kaydedilemedi");
		}
	}

	// localhost:8080/flightsystem/plane/deleteById/1
	@DeleteMapping(value = "/deleteById/{plane_id}")
	public ResponseEntity<String> deleteById(@PathVariable(value = "plane_id") Long plane_id) {
		if (planeService.deleteById(plane_id)) {
			return ResponseEntity.status(HttpStatus.IM_USED).body("Başarı ile silindi");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Başarı ile silinemedi");
		}
	}
}