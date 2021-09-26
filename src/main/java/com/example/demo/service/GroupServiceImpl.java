package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Group;
import com.example.demo.repository.GroupRepository;

@Service
public class GroupServiceImpl extends SuperServiceImpl<Group, Integer, GroupRepository> implements GroupService {

	public GroupServiceImpl(GroupRepository repo) {
		super(repo);
	}

	@Override
	public List<Group> selectGroupOrderedByIdASC() {
		return repo.selectGroupOrderedByIdASC();
	}

	@Override
	public List<Group> findAllByRoundId(Integer roundId) {
		return repo.findAllByRoundId(roundId);
	}

}
