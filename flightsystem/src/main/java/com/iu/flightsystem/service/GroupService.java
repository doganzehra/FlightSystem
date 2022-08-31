package com.iu.flightsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iu.flightsystem.model.Group;
import com.iu.flightsystem.repository.GroupRepo;

@Service
public class GroupService {

	@Autowired
	public GroupRepo groupRepo;

	public List<Group> getAll() {
		return groupRepo.getAll();
	}

	public Group getById(Long id) {
		return groupRepo.getById(id);
	}

	public boolean save(Group group) {
		return groupRepo.save(group);
	}

	public boolean deleteById(Long id) {
		return groupRepo.deleteById(id);
	}

}
