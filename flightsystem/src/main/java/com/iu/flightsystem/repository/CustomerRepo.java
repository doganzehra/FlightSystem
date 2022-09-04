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

import com.iu.flightsystem.model.Customer;
import com.iu.flightsystem.model.viewobject.CustomerPlaneFlightVO;

@Repository
public class CustomerRepo {

	@Autowired
	public JdbcTemplate jdbcTemplate;

	@Autowired
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<Customer> getAll() {
		String sql = "SELECT * FROM \"CUSTOMER\"";
		RowMapper<Customer> rowMapper = new RowMapper<Customer>() {
			@Override
			public Customer mapRow(ResultSet result, int rowNum) throws SQLException {
				return new Customer(result.getLong("CUSTOMER_ID"), result.getString("CUSTOMER_NAME"));
			}
		};
		return jdbcTemplate.query(sql, rowMapper);
	}

	public Customer getById(Long id) {
		String sql = "SELECT * FROM \"CUSTOMER\" where \"CUSTOMER_ID\" = :CUSTOMER_ID";
		RowMapper<Customer> rowMapper = new RowMapper<Customer>() {
			@Override
			public Customer mapRow(ResultSet result, int rowNum) throws SQLException {
				return new Customer(result.getLong("CUSTOMER_ID"), result.getString("CUSTOMER_NAME"));
			}
		};
		HashMap<String, Object> params = new HashMap<>();
		params.put("CUSTOMER_ID", id);
		return namedParameterJdbcTemplate.queryForObject(sql, params, rowMapper);
	}

	public boolean save(Customer customer) {
		String sql = "INSERT INTO \"CUSTOMER\"( \"CUSTOMER_NAME\") VALUES (:CUSTOMER_NAME)";
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("CUSTOMER_NAME", customer.getCUSTOMER_NAME());
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}

	public boolean deleteById(Long id) {
		String sql = "DELETE FROM \"CUSTOMER\" where \"CUSTOMER_ID\" = :CUSTOMER_ID";
		HashMap<String, Object> params = new HashMap<>();
		params.put("CUSTOMER_ID", id);
		return namedParameterJdbcTemplate.update(sql, params) > 0;
	}

	public List<CustomerPlaneFlightVO> getAllCustomersOnAllFlights() {
		String sql = "SELECT c.*, p.*, f.\"FLIGHT_DATE\",f.\"FLIGHT_PRICE\" FROM \"CUSTOMER\" c, \"PLANE\" p, \"FLIGHTS\" f WHERE p.\"PLANE_ID\" = f.\"PLANE_ID\" AND c.\"CUSTOMER_ID\"=f.\"CUSTOMER_ID\"";
		RowMapper<CustomerPlaneFlightVO> rowMapper = new RowMapper<CustomerPlaneFlightVO>() {
			@Override
			public CustomerPlaneFlightVO mapRow(ResultSet result, int rowNum) throws SQLException {
				CustomerPlaneFlightVO customerPlaneFlightVO = new CustomerPlaneFlightVO();
				customerPlaneFlightVO.setCUSTOMER_ID(result.getLong("CUSTOMER_ID"));
				customerPlaneFlightVO.setCUSTOMER_NAME(result.getString("CUSTOMER_NAME"));
				customerPlaneFlightVO.setPLANE_ID(result.getLong("PLANE_ID"));
				customerPlaneFlightVO.setPLANE_NAME(result.getString("PLANE_NAME"));
				customerPlaneFlightVO.setPLANE_BRAND(result.getString("PLANE_BRAND"));
				customerPlaneFlightVO.setFLIGHT_DATE(result.getString("FLIGHT_DATE"));
				customerPlaneFlightVO.setFLIGHT_PRICE(result.getLong("FLIGHT_PRICE"));
				return customerPlaneFlightVO;
			}
		};
		List<CustomerPlaneFlightVO> res = jdbcTemplate.query(sql, rowMapper);
		res.removeAll(Collections.singletonList(null));
		return res;
	}
}
