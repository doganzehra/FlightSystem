package com.iu.flightsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iu.flightsystem.model.Group;
import com.iu.flightsystem.repository.GroupRepo;

@Service
public class GroupService {

	@Autowired
	public GroupRepo repo;

	public GroupService(GroupRepo repo) {
		this.repo = repo;
		// TODO Auto-generated constructor stub
	}

	public List<Group> getAll() {
		return repo.getAll();
	}

	public Group getById(Long id) {
		return repo.getById(id);
	}

	public boolean save(Group group) {
		return repo.save(group);
	}

	public boolean deleteById(Long id) {
		return repo.deleteById(id);
	}

}
