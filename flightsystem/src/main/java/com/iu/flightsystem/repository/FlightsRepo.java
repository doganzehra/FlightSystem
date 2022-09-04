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
import com.iu.flightsystem.model.viewobject.CustomerFlightCityVO;
import com.iu.flightsystem.model.viewobject.CustomerFlightDateVO;
import com.iu.flightsystem.model.viewobject.CustomerFlightVO;
import com.iu.flightsystem.model.viewobject.PlaneFlightCityVO;
import com.iu.flightsystem.model.viewobject.PlaneFlightVO;

@Repository
public class FlightsRepo {
	@Autowired
	public JdbcTemplate jdbcTemplate;

	@Autowired
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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

	public boolean deleteById(Long id) {
		String sql = "DELETE FROM \"FLIGHTS\" where \"FLIGHT_ID\" = :FLIGHT_ID";
		HashMap<String, Object> params = new HashMap<>();
		params.put("FLIGHT_ID", id);
		return namedParameterJdbcTemplate.update(sql, params) > 0;
	}

	public boolean deleteByCustomerId(Long customerId) {
		String sql = "DELETE FROM \"FLIGHTS\" WHERE \"CUSTOMER_ID\" = :CUSTOMER_ID";
		HashMap<String, Object> params = new HashMap<>();
		params.put("CUSTOMER_ID", customerId);
		return namedParameterJdbcTemplate.update(sql, params) > 0;
	}

	public List<CustomerFlightVO> getFlightByCustomerName(String customerName) {
		String sql = "SELECT f.*, c.\"CUSTOMER_NAME\" FROM \"FLIGHTS\" AS f, \"CUSTOMER\" AS c WHERE c.\"CUSTOMER_ID\"=f.\"CUSTOMER_ID\" AND c.\"CUSTOMER_NAME\" = :CUSTOMER_NAME";
		RowMapper<CustomerFlightVO> rowMapper = new RowMapper<CustomerFlightVO>() {
			@Override
			public CustomerFlightVO mapRow(ResultSet result, int rowNum) throws SQLException {
				CustomerFlightVO customerVO = new CustomerFlightVO();
				customerVO.setFLIGHT_ID(result.getLong("FLIGHT_ID"));
				customerVO.setCUSTOMER_ID(result.getLong("CUSTOMER_ID"));
				customerVO.setPLANE_ID(result.getLong("PLANE_ID"));
				customerVO.setFLIGHT_DATE(result.getString("FLIGHT_DATE"));
				customerVO.setFLIGHT_PRICE(result.getLong("FLIGHT_PRICE"));
				customerVO.setFROM_WHERE(result.getLong("FROM_WHERE"));
				customerVO.setTO_WHERE(result.getLong("TO_WHERE"));
				customerVO.setCUSTOMER_NAME(result.getString("CUSTOMER_NAME"));
				return customerVO;
			}
		};
		HashMap<String, String> params = new HashMap<>();
		params.put("CUSTOMER_NAME", customerName);
		List<CustomerFlightVO> res = namedParameterJdbcTemplate.query(sql, params, rowMapper);
		return res;
	}

	public List<CustomerFlightCityVO> getIncomingFlightsToCityByCustomerName(String customerName, String city) {
		String sql = "SELECT f.*, c.\"CUSTOMER_NAME\", ci.\"CITY_NAME\" FROM \"FLIGHTS\" AS f, \"CUSTOMER\" AS c, \"CITIES\" AS ci WHERE c.\"CUSTOMER_ID\"=f.\"CUSTOMER_ID\" AND ci.\"CITY_ID\"=f.\"TO_WHERE\" AND c.\"CUSTOMER_NAME\"= :CUSTOMER_NAME AND ci.\"CITY_NAME\"= :CITY_NAME";
		RowMapper<CustomerFlightCityVO> rowMapper = new RowMapper<CustomerFlightCityVO>() {
			@Override
			public CustomerFlightCityVO mapRow(ResultSet result, int rowNum) throws SQLException {
				CustomerFlightCityVO customerFlightVO = new CustomerFlightCityVO();
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
		List<CustomerFlightCityVO> res = namedParameterJdbcTemplate.query(sql, params, rowMapper);
		res.removeAll(Collections.singletonList(null));
		return res;
	}

	public List<CustomerFlightCityVO> getOutcomingFlightsToCityByCustomerName(String customerName, String city) {
		String sql = "SELECT f.*, c.\"CUSTOMER_NAME\", ci.\"CITY_NAME\" FROM \"FLIGHTS\" AS f, \"CUSTOMER\" AS c, \"CITIES\" AS ci WHERE c.\"CUSTOMER_ID\"=f.\"CUSTOMER_ID\" AND ci.\"CITY_ID\"=f.\"FROM_WHERE\" AND c.\"CUSTOMER_NAME\"= (:CUSTOMER_NAME) AND ci.\"CITY_NAME\" = (:CITY_NAME)";
		RowMapper<CustomerFlightCityVO> rowMapper = new RowMapper<CustomerFlightCityVO>() {
			@Override
			public CustomerFlightCityVO mapRow(ResultSet result, int rowNum) throws SQLException {
				CustomerFlightCityVO customerFlightVO = new CustomerFlightCityVO();
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
		List<CustomerFlightCityVO> res = namedParameterJdbcTemplate.query(sql, params, rowMapper);
		res.removeAll(Collections.singletonList(null));
		return res;
	}

	public List<CustomerFlightDateVO> getCustomersByGivenDate(String date) {
		String sql = "SELECT c.*, f.\"FLIGHT_DATE\" FROM \"FLIGHTS\" AS f, \"CUSTOMER\" AS c WHERE f.\"CUSTOMER_ID\"=c.\"CUSTOMER_ID\" AND f.\"FLIGHT_DATE\"= :FLIGHT_DATE";
		RowMapper<CustomerFlightDateVO> rowMapper = new RowMapper<CustomerFlightDateVO>() {
			@Override
			public CustomerFlightDateVO mapRow(ResultSet result, int rowNum) throws SQLException {
				CustomerFlightDateVO customerFlightDateVO = new CustomerFlightDateVO();
				customerFlightDateVO.setCUSTOMER_ID(result.getLong("CUSTOMER_ID"));
				customerFlightDateVO.setCUSTOMER_NAME(result.getString("CUSTOMER_NAME"));
				customerFlightDateVO.setFLIGHT_DATE(result.getString("FLIGHT_DATE"));
				return customerFlightDateVO;
			}
		};
		HashMap<String, String> params = new HashMap<>();
		params.put("FLIGHT_DATE", date);
		List<CustomerFlightDateVO> res = namedParameterJdbcTemplate.query(sql, params, rowMapper);
		return res;
	}

	public List<PlaneFlightVO> getFlightByPlaneBrand(String planeBrand) {
		String sql = "SELECT f.*, p.\"PLANE_NAME\", p.\"PLANE_BRAND\" FROM \"FLIGHTS\" f, \"PLANE\" p WHERE f.\"PLANE_ID\" = p.\"PLANE_ID\" AND p.\"PLANE_BRAND\" = :PLANE_BRAND";
		RowMapper<PlaneFlightVO> rowMapper = new RowMapper<PlaneFlightVO>() {
			@Override
			public PlaneFlightVO mapRow(ResultSet result, int rowNum) throws SQLException {
				PlaneFlightVO planeFlightVO = new PlaneFlightVO();
				planeFlightVO.setFLIGHT_ID(result.getLong("FLIGHT_ID"));
				planeFlightVO.setCUSTOMER_ID(result.getLong("CUSTOMER_ID"));
				planeFlightVO.setPLANE_ID(result.getLong("PLANE_ID"));
				planeFlightVO.setFLIGHT_DATE(result.getString("FLIGHT_DATE"));
				planeFlightVO.setFLIGHT_PRICE(result.getLong("FLIGHT_PRICE"));
				planeFlightVO.setFROM_WHERE(result.getLong("FROM_WHERE"));
				planeFlightVO.setTO_WHERE(result.getLong("TO_WHERE"));
				planeFlightVO.setPLANE_NAME(result.getString("PLANE_NAME"));
				planeFlightVO.setPLANE_BRAND(result.getString("PLANE_BRAND"));
				return planeFlightVO;
			}
		};
		HashMap<String, String> params = new HashMap<>();
		params.put("PLANE_BRAND", planeBrand);
		List<PlaneFlightVO> res = namedParameterJdbcTemplate.query(sql, params, rowMapper);
		res.removeAll(Collections.singletonList(null));
		return res;
	}

	public List<PlaneFlightCityVO> getIncomingFlightsToCityByPlaneBrand(String planeBrand, String city) {
		String sql = "SELECT f.*, p.\"PLANE_NAME\", p.\"PLANE_BRAND\", c.\"CITY_NAME\" FROM \"FLIGHTS\" f, \"PLANE\" p, \"CITIES\" c WHERE f.\"PLANE_ID\" = p.\"PLANE_ID\" AND f.\"TO_WHERE\"=c.\"CITY_ID\" AND p.\"PLANE_BRAND\" = :PLANE_BRAND AND c.\"CITY_NAME\" = :CITY_NAME";
		RowMapper<PlaneFlightCityVO> rowMapper = new RowMapper<PlaneFlightCityVO>() {
			@Override
			public PlaneFlightCityVO mapRow(ResultSet result, int rowNum) throws SQLException {
				PlaneFlightCityVO planeFlightCityVO = new PlaneFlightCityVO();
				planeFlightCityVO.setFLIGHT_ID(result.getLong("FLIGHT_ID"));
				planeFlightCityVO.setCUSTOMER_ID(result.getLong("CUSTOMER_ID"));
				planeFlightCityVO.setPLANE_ID(result.getLong("PLANE_ID"));
				planeFlightCityVO.setFLIGHT_DATE(result.getString("FLIGHT_DATE"));
				planeFlightCityVO.setFLIGHT_PRICE(result.getLong("FLIGHT_PRICE"));
				planeFlightCityVO.setFROM_WHERE(result.getLong("FROM_WHERE"));
				planeFlightCityVO.setTO_WHERE(result.getLong("TO_WHERE"));
				planeFlightCityVO.setPLANE_NAME(result.getString("PLANE_NAME"));
				planeFlightCityVO.setPLANE_BRAND(result.getString("PLANE_BRAND"));
				planeFlightCityVO.setCITY_NAME(result.getString("CITY_NAME"));
				return planeFlightCityVO;
			}
		};
		HashMap<String, String> params = new HashMap<>();
		params.put("PLANE_BRAND", planeBrand);
		params.put("CITY_NAME", city);
		List<PlaneFlightCityVO> res = namedParameterJdbcTemplate.query(sql, params, rowMapper);
		res.removeAll(Collections.singletonList(null));
		return res;
	}

	public List<PlaneFlightCityVO> getOutcomingFlightsToCityByPlaneBrand(String planeBrand, String city) {
		String sql = "SELECT f.*, p.\"PLANE_NAME\", p.\"PLANE_BRAND\", c.\"CITY_NAME\" FROM \"FLIGHTS\" f, \"PLANE\" p, \"CITIES\" c WHERE f.\"PLANE_ID\" = p.\"PLANE_ID\" AND f.\"FROM_WHERE\"=c.\"CITY_ID\" AND p.\"PLANE_BRAND\" = :PLANE_BRAND AND c.\"CITY_NAME\" = :CITY_NAME";
		RowMapper<PlaneFlightCityVO> rowMapper = new RowMapper<PlaneFlightCityVO>() {
			@Override
			public PlaneFlightCityVO mapRow(ResultSet result, int rowNum) throws SQLException {
				PlaneFlightCityVO planeFlightCityVO = new PlaneFlightCityVO();
				planeFlightCityVO.setFLIGHT_ID(result.getLong("FLIGHT_ID"));
				planeFlightCityVO.setCUSTOMER_ID(result.getLong("CUSTOMER_ID"));
				planeFlightCityVO.setPLANE_ID(result.getLong("PLANE_ID"));
				planeFlightCityVO.setFLIGHT_DATE(result.getString("FLIGHT_DATE"));
				planeFlightCityVO.setFLIGHT_PRICE(result.getLong("FLIGHT_PRICE"));
				planeFlightCityVO.setFROM_WHERE(result.getLong("FROM_WHERE"));
				planeFlightCityVO.setTO_WHERE(result.getLong("TO_WHERE"));
				planeFlightCityVO.setPLANE_NAME(result.getString("PLANE_NAME"));
				planeFlightCityVO.setPLANE_BRAND(result.getString("PLANE_BRAND"));
				planeFlightCityVO.setCITY_NAME(result.getString("CITY_NAME"));
				return planeFlightCityVO;
			}
		};
		HashMap<String, String> params = new HashMap<>();
		params.put("PLANE_BRAND", planeBrand);
		params.put("CITY_NAME", city);
		List<PlaneFlightCityVO> res = namedParameterJdbcTemplate.query(sql, params, rowMapper);
		res.removeAll(Collections.singletonList(null));
		return res;
	}
}
