package com.iu.flightsystem.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.iu.flightsystem.model.Flight;
import com.iu.flightsystem.model.viewobject.CustomerFlightVO;

@Repository
public class FlightsRepo {
	@Autowired
	public JdbcTemplate jdbcTemplate;

	@Autowired
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public boolean save(Flight flight) {
		String sql = "INSERT INTO \"FLIGHTS\"( \"CUSTOMER_ID\", \"PLANE_ID\", \"FLIGHT_DATE\", \"FLIGHT_PRICE\", \"FROM_WHERE\", \"TO_WHERE\") VALUES (:CUSTOMER_ID, :PLANE_ID, :FLIGHT_DATE, :FLIGHT_PRICE, :FROM_WHERE, :TO_WHERE)";
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
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

	public List<CustomerFlightVO> getFlightByCustomerName(String customerName) {
		String sql = "SELECT f.*, c.\"CUSTOMER_NAME\" FROM \"FLIGHTS\" AS f, \"CUSTOMER\" AS c WHERE c.\"CUSTOMER_ID\"=f.\"CUSTOMER_ID\" AND c.\"CUSTOMER_NAME\" = :CUSTOMER_NAME";
		RowMapper<CustomerFlightVO> rowMapper = new RowMapper<CustomerFlightVO>() {
			@Override
			public CustomerFlightVO mapRow(ResultSet result, int rowNum) throws SQLException {
				CustomerFlightVO customerFlightVO = new CustomerFlightVO();
				customerFlightVO.setFLIGHT_ID(result.getLong("FLIGHT_ID"));
				customerFlightVO.setCUSTOMER_ID(result.getLong("CUSTOMER_ID"));
				customerFlightVO.setPLANE_ID(result.getLong("PLANE_ID"));
				customerFlightVO.setFLIGHT_DATE(result.getString("FLIGHT_DATE"));
				customerFlightVO.setFLIGHT_PRICE(result.getLong("FLIGHT_PRICE"));
				customerFlightVO.setFROM_WHERE(result.getLong("FROM_WHERE"));
				customerFlightVO.setTO_WHERE(result.getLong("TO_WHERE"));
				customerFlightVO.setCUSTOMER_NAME(result.getString("CUSTOMER_NAME"));
				return customerFlightVO;
			}
		};
		HashMap<String, String> params = new HashMap<>();
		params.put("CUSTOMER_NAME", customerName);
		List<CustomerFlightVO> res = namedParameterJdbcTemplate.query(sql, params, rowMapper);
		res.removeAll(Collections.singletonList(null));
		return res;
	}

	public List<CustomerFlightVO> getOutcomingFlightsToCityByCustomerName(String customerName, String city) {
		String sql = "SELECT f.*, c.\"CUSTOMER_NAME\", ci.\"CITY_NAME\" FROM \"FLIGHTS\" AS f, \"CUSTOMER\" AS c, \"CITIES\" AS ci WHERE c.\"CUSTOMER_ID\"=f.\"CUSTOMER_ID\" AND ci.\"CITY_ID\"=f.\"FROM_WHERE\" AND c.\"CUSTOMER_NAME\"= (:CUSTOMER_NAME) AND ci.\"CITY_NAME\" = (:CITY_NAME)";
		RowMapper<CustomerFlightVO> rowMapper = new RowMapper<CustomerFlightVO>() {
			@Override
			public CustomerFlightVO mapRow(ResultSet result, int rowNum) throws SQLException {
				CustomerFlightVO customerFlightVO = new CustomerFlightVO();
				customerFlightVO.setFLIGHT_ID(result.getLong("FLIGHT_ID"));
				customerFlightVO.setCUSTOMER_ID(result.getLong("CUSTOMER_ID"));
				customerFlightVO.setPLANE_ID(result.getLong("PLANE_ID"));
				customerFlightVO.setFLIGHT_DATE(result.getString("FLIGHT_DATE"));
				customerFlightVO.setFLIGHT_PRICE(result.getLong("FLIGHT_PRICE"));
				customerFlightVO.setFROM_WHERE(result.getLong("FROM_WHERE"));
				customerFlightVO.setTO_WHERE(result.getLong("TO_WHERE"));
				customerFlightVO.setCUSTOMER_NAME(result.getString("CUSTOMER_NAME"));
				customerFlightVO.setCITY_NAME(result.getString("CITY_NAME"));
				return customerFlightVO;
			}
		};
		HashMap<String, String> params = new HashMap<>();
		params.put("CUSTOMER_NAME", customerName);
		params.put("CITY_NAME", city);
		List<CustomerFlightVO> res = namedParameterJdbcTemplate.query(sql, params, rowMapper);
		res.removeAll(Collections.singletonList(null));
		return res;
	}

	public List<CustomerFlightVO> getIncomingFlightsToCityByCustomerName(String customerName, String city) {
		String sql = "SELECT f.*, c.\"CUSTOMER_NAME\", ci.\"CITY_NAME\" FROM \"FLIGHTS\" AS f, \"CUSTOMER\" AS c, \"CITIES\" AS ci WHERE c.\"CUSTOMER_ID\"=f.\"CUSTOMER_ID\" AND ci.\"CITY_ID\"=f.\"TO_WHERE\" AND c.\"CUSTOMER_NAME\"= :CUSTOMER_NAME AND ci.\"CITY_NAME\"= :CITY_NAME";
		RowMapper<CustomerFlightVO> rowMapper = new RowMapper<CustomerFlightVO>() {
			@Override
			public CustomerFlightVO mapRow(ResultSet result, int rowNum) throws SQLException {
				CustomerFlightVO customerFlightVO = new CustomerFlightVO();
				customerFlightVO.setFLIGHT_ID(result.getLong("FLIGHT_ID"));
				customerFlightVO.setCUSTOMER_ID(result.getLong("CUSTOMER_ID"));
				customerFlightVO.setPLANE_ID(result.getLong("PLANE_ID"));
				customerFlightVO.setFLIGHT_DATE(result.getString("FLIGHT_DATE"));
				customerFlightVO.setFLIGHT_PRICE(result.getLong("FLIGHT_PRICE"));
				customerFlightVO.setFROM_WHERE(result.getLong("FROM_WHERE"));
				customerFlightVO.setTO_WHERE(result.getLong("TO_WHERE"));
				customerFlightVO.setCUSTOMER_NAME(result.getString("CUSTOMER_NAME"));
				customerFlightVO.setCITY_NAME(result.getString("CITY_NAME"));
				return customerFlightVO;
			}
		};
		HashMap<String, String> params = new HashMap<>();
		params.put("CUSTOMER_NAME", customerName);
		params.put("CITY_NAME", city);
		List<CustomerFlightVO> res = namedParameterJdbcTemplate.query(sql, params, rowMapper);
		res.removeAll(Collections.singletonList(null));
		return res;
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
