package com.iu.flightsystem.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.iu.flightsystem.model.Flight;

@Repository
public class FlightsRepo {
	@Autowired
	public JdbcTemplate jdbcTemplate;

	@Autowired
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public boolean save(Flight flight) {
		String sql = "INSERT INTO \"FLIGHTS\"( \"FLIGHT_ID\", \"CUSTOMER_ID\", \"PLANE_ID\", \"FLIGHT_DATE\", \"FLIGHT_PRICE\", \"FROM_WHERE\", \"TO_WHERE\") VALUES (:FLIGHT_ID, :CUSTOMER_ID, :PLANE_ID, :FLIGHT_DATE, :FLIGHT_PRICE, :FROM_WHERE, :TO_WHERE)";
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("FLIGHT_ID", flight.getFLIGHT_ID());
		paramMap.put("CUSTOMER_ID", flight.getCUSTOMER_ID());
		paramMap.put("PLANE_ID", flight.getPLANE_ID());
		paramMap.put("FLIGHT_DATE", flight.getFLIGHT_DATE());
		paramMap.put("FLIGHT_PRICE", flight.getFLIGHT_PRICE());
		paramMap.put("FROM_WHERE", flight.getFROM_WHERE());
		paramMap.put("TO_WHERE", flight.getTO_WHERE());
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}

	public List<Flight> getAll() {
		String sql = "SELECT * FROM \"FLIGHTS\" ORDER BY \"FLIGHT_ID\" DESC";
		RowMapper<Flight> rowMapper = new RowMapper<Flight>() {
			@Override
			public Flight mapRow(ResultSet result, int rowNum) throws SQLException {
				return new Flight(result.getLong("FLIGHT_ID"), result.getLong("CUSTOMER_ID"),
						result.getLong("PLANE_ID"), result.getString("FLIGHT_DATE"), result.getLong("FLIGHT_PRICE"),
						result.getLong("FROM_WHERE"), result.getLong("TO_WHERE"));
			}
		};
		return jdbcTemplate.query(sql, rowMapper);
	}

	public Flight getById(Long id) {
		String sql = "SELECT * FROM \"FLIGHTS\" where \"FLIGHT_ID\" = :FLIGHT_ID";
		RowMapper<Flight> rowMapper = new RowMapper<Flight>() {
			@Override
			public Flight mapRow(ResultSet result, int rowNum) throws SQLException {
				return new Flight(result.getLong("FLIGHT_ID"), result.getLong("CUSTOMER_ID"),
						result.getLong("PLANE_ID"), result.getString("FLIGHT_DATE"), result.getLong("FLIGHT_PRICE"),
						result.getLong("FROM_WHERE"), result.getLong("TO_WHERE"));
			}
		};
		HashMap<String, Object> params = new HashMap<>();
		params.put("FLIGHT_ID", id);
		return namedParameterJdbcTemplate.queryForObject(sql, params, rowMapper);
	}

	public boolean deleteById(Long id) {
		String sql = "DELETE FROM \"FLIGHTS\" where \"FLIGHT_ID\" = :FLIGHT_ID";
		HashMap<String, Object> params = new HashMap<>();
		params.put("FLIGHT_ID", id);
		return namedParameterJdbcTemplate.update(sql, params) > 0;
	}
}
