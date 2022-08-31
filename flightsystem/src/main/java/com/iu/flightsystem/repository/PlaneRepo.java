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

import com.iu.flightsystem.model.Plane;

@Repository
public class PlaneRepo {

	@Autowired
	public JdbcTemplate jdbcTemplate;

	@Autowired
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public boolean save(Plane plane) {
		String sql = "INSERT INTO public.\"PLANE\"(\"PLANE_NAME\", \"PLANE_BRAND\") VALUES (:PLANE_NAME, :PLANE_BRAND)";
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("PLANE_NAME", plane.getPLANE_NAME());
		paramMap.put("PLANE_BRAND", plane.getPLANE_BRAND());
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}

	public List<Plane> getAll() {
		String sql = "SELECT * FROM \"PLANE\"";
		RowMapper<Plane> rowMapper = new RowMapper<Plane>() {
			@Override
			public Plane mapRow(ResultSet result, int rowNum) throws SQLException {
				return new Plane(result.getLong("PLANE_ID"), result.getString("PLANE_NAME"),
						result.getString("PLANE_BRAND"));
			}
		};
		return jdbcTemplate.query(sql, rowMapper);
	}

	public Plane getById(Long plane_id) {
		String sql = "SELECT * FROM \"PLANE\" WHERE \"PLANE_ID\" = :PLANE_ID";
		RowMapper<Plane> rowMapper = new RowMapper<Plane>() {
			@Override
			public Plane mapRow(ResultSet result, int rowNum) throws SQLException {
				return new Plane(result.getLong("PLANE_ID"), result.getString("PLANE_NAME"),
						result.getString("PLANE_BRAND"));
			}
		};
		HashMap<String, Object> params = new HashMap<>();
		params.put("PLANE_ID", plane_id);
		return namedParameterJdbcTemplate.queryForObject(sql, params, rowMapper);
	}

	public boolean deleteById(Long plane_id) {
		String sql = "DELETE FROM \"PLANE\" where \"PLANE_ID\" = :PLANE_ID";
		HashMap<String, Object> params = new HashMap<>();
		params.put("PLANE_ID", plane_id);
		return namedParameterJdbcTemplate.update(sql, params) > 0;
	}

}
