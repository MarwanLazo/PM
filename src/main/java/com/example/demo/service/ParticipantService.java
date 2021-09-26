package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Participant;

public interface ParticipantService extends SuperService<Participant, Integer> {

	List<Participant> findAllByGroupId(Integer groupId);

}
