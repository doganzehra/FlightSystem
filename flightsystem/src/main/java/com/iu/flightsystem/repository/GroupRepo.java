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

import com.iu.flightsystem.model.Group;

@Repository
public class GroupRepo {

	@Autowired
	public JdbcTemplate jdbcTemplate;

	@Autowired
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<Group> getAll() {
		String sql = "SELECT * FROM \"GROUP\"";
		RowMapper<Group> rowMapper = new RowMapper<Group>() {
			@Override
			public Group mapRow(ResultSet result, int rowNum) throws SQLException {
				return new Group(result.getLong("GROUP_ID"), result.getLong("GROUP_NO"));
			}
		};
		return jdbcTemplate.query(sql, rowMapper);
	}

	public Group getById(Long group_id) {
		String sql = "SELECT * FROM \"GROUP\" WHERE \"GROUP_ID\" = :GROUP_ID";
		RowMapper<Group> rowMapper = new RowMapper<Group>() {
			@Override
			public Group mapRow(ResultSet result, int rowNum) throws SQLException {
				return new Group(result.getLong("GROUP_ID"), result.getLong("GROUP_NO"));
			}
		};
		HashMap<String, Object> params = new HashMap<>();
		params.put("GROUP_ID", group_id);
		return namedParameterJdbcTemplate.queryForObject(sql, params, rowMapper);
	}

	public boolean save(Group group) {
		String sql = "INSERT INTO public.\"GROUPS\"(\"GROUP_NO\")VALUES (:GROUP_NO)";
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("GROUP_NO", group.getGROUP_NO());
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}

	public boolean deleteById(Long group_id) {
		String sql = "DELETE FROM \"GROUP\" where \"GROUP_ID\" = :GROUP_ID";
		HashMap<String, Object> params = new HashMap<>();
		params.put("GROUP_ID", group_id);
		return namedParameterJdbcTemplate.update(sql, params) > 0;
	}

}
