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

import com.iu.flightsystem.model.Customer;

@Repository
public class CustomerRepo {

	@Autowired
	public JdbcTemplate jdbcTemplate;

	@Autowired
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public boolean save(Customer customer) {
		String sql = "INSERT INTO \"CUSTOMER\"( \"CUSTOMER_ID\", \"CUSTOMER_NAME\") VALUES (:CUSTOMER_ID, :CUSTOMER_NAME)";
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("NAME", customer.getCUSTOMER_ID());
		paramMap.put("OGR_NUMBER", customer.getCUSTOMER_NAME());
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}

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

	public boolean deleteById(Long id) {
		String sql = "DELETE FROM \"CUSTOMER\" where \"CUSTOMER_ID\" = :CUSTOMER_ID";
		HashMap<String, Object> params = new HashMap<>();
		params.put("CUSTOMER_ID", id);
		return namedParameterJdbcTemplate.update(sql, params) > 0;
	}
}
