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

import com.iu.flightsystem.model.Cities;
import com.iu.flightsystem.service.CitiesService;

@RestController
@RequestMapping(path = "cities")
public class CitiesController {
	private CitiesService service;

	public CitiesController(CitiesService service) {
		this.service = service;
	}

	// localhost:8080/flightsystem/cities/getAll
	@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cities> getAll() {
		return service.getAll();
	}

	// localhost:8080/flightsystem/cities/getById/1
	@GetMapping(value = "/getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Cities getById(@PathVariable(value = "id") Long id) {
		return service.getById(id);
	}

	// localhost:8080/flightsystem/cities/save
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> save(@RequestBody Cities cities) {
		if (service.save(cities)) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Başarı ile kaydedildi");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Başarı ile kaydedilemedi");
		}
	}

	// localhost:8080/flightsystem/cities/deleteById/1
	@DeleteMapping(value = "/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable(value = "id") Long id) {
		if (service.deleteById(id)) {
			return ResponseEntity.status(HttpStatus.IM_USED).body("Başarı ile silindi");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Başarı ile silinemedi");
		}
	}
}
