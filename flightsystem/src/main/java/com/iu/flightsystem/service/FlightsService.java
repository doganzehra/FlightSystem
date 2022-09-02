package com.iu.flightsystem.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.iu.flightsystem.model.Flight;
import com.iu.flightsystem.model.viewobject.CustomerFlightCityVO;
import com.iu.flightsystem.model.viewobject.CustomerFlightDateVO;
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
		if (Math.abs(fromWhere - toWhere) == 0) {
			throw new IllegalStateException("Bir şehirden aynı şehire uçuş gerçekleştirilemez");
		}
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

	public List<CustomerFlightCityVO> getOutcomingFlightsToCityByCustomerName(String customerName, String city) {
		return repo.getOutcomingFlightsToCityByCustomerName(customerName, city);
	}

	public List<CustomerFlightCityVO> getIncomingFlightsToCityByCustomerName(String customerName, String city) {
		return repo.getIncomingFlightsToCityByCustomerName(customerName, city);
	}

	public List<Flight> getPastFlights() {
		List<Flight> allFlights = repo.getAll();
		List<Flight> pastFlights = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String currentDate = LocalDate.now().format(formatter);

		for (Flight f1 : allFlights) {
			if (f1.getFLIGHT_DATE().compareTo(currentDate) < 0) {
				pastFlights.add(f1);
			}
		}
		return pastFlights;
	}

	public List<Flight> getFutureFlights() {
		List<Flight> allFlights = repo.getAll();
		List<Flight> futureFlights = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String currentDate = LocalDate.now().format(formatter);

		for (Flight f1 : allFlights) {
			if (f1.getFLIGHT_DATE().compareTo(currentDate) > 0) {
				futureFlights.add(f1);
			}
		}
		return futureFlights;
	}

	public List<Flight> getTodayFlights() {
		List<Flight> allFlights = repo.getAll();
		List<Flight> todayFlights = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String currentDate = LocalDate.now().format(formatter);

		for (Flight f1 : allFlights) {
			if (f1.getFLIGHT_DATE().compareTo(currentDate) == 0) {
				todayFlights.add(f1);
			}
		}
		return todayFlights;
	}

	public List<CustomerFlightDateVO> getCustomersByGivenDate(String date) {
		return repo.getCustomersByGivenDate(date);
	}
}
