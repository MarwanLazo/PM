package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Group;

public interface GroupService extends SuperService<Group, Integer> {

	List<Group> selectGroupOrderedByIdASC();

	List<Group> findAllByRoundId(Integer roundId);

}
