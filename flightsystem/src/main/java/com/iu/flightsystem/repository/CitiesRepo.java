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

import com.iu.flightsystem.model.Cities;

@Repository
public class CitiesRepo {
	@Autowired
	public JdbcTemplate jdbcTemplate;

	@Autowired
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public boolean save(Cities cities) {
		String sql = "INSERT INTO \"CITIES\"( \"CITY_ID\", \"CITY_NAME\", \"CITY_GROUP_ID\") VALUES (:CITY_ID, :CITY_NAME, :CITY_GROUP_ID)";
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("CITY_ID", cities.getCITY_ID());
		paramMap.put("CITY_NAME", cities.getCITY_NAME());
		paramMap.put("CITY_GROUP_ID", cities.getCITY_GROUP_ID());
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}

	public List<Cities> getAll() {
		String sql = "SELECT * FROM \"CITIES\"";
		RowMapper<Cities> rowMapper = new RowMapper<Cities>() {
			@Override
			public Cities mapRow(ResultSet result, int rowNum) throws SQLException {
				return new Cities(result.getLong("CITY_ID"), result.getString("CITY_NAME"),
						result.getLong("CITY_GROUP_ID"));
			}
		};
		return jdbcTemplate.query(sql, rowMapper);
	}

	public Cities getById(Long id) {
		String sql = "SELECT * FROM \"CITIES\" where \"CITY_ID\" = :CITY_ID";
		RowMapper<Cities> rowMapper = new RowMapper<Cities>() {
			@Override
			public Cities mapRow(ResultSet result, int rowNum) throws SQLException {
				return new Cities(result.getLong("CITY_ID"), result.getString("CITY_NAME"),
						result.getLong("CITY_GROUP_ID"));
			}
		};
		HashMap<String, Object> params = new HashMap<>();
		params.put("CITY_ID", id);
		return namedParameterJdbcTemplate.queryForObject(sql, params, rowMapper);
	}

	public boolean deleteById(Long id) {
		String sql = "DELETE FROM \"CITIES\" where \"CITY_ID\" = :CITY_ID";
		HashMap<String, Object> params = new HashMap<>();
		params.put("CITY_ID", id);
		return namedParameterJdbcTemplate.update(sql, params) > 0;
	}

}
