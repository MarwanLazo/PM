package com.example.demo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Group;
import com.example.demo.entity.Participant;
import com.example.demo.exceptions.GroupNotFoundException;
import com.example.demo.repository.ParticipantRepository;

@Service
public class ParticipantServiceImpl extends SuperServiceImpl<Participant, Integer, ParticipantRepository>
		implements ParticipantService {

	private final ModelMapper mapper;
	private final GroupService groupService;

	public ParticipantServiceImpl(final ParticipantRepository repo, final ModelMapper mapper,
			final GroupService groupService) {
		super(repo);
		this.mapper = mapper;
		this.groupService = groupService;
	}

	@Override
	public Participant create(Participant e) {

		List<Group> group = groupService.selectGroupOrderedByIdASC();
		if (group == null || group.isEmpty())
			throw new GroupNotFoundException(" NO Group valid or all groups Completed ");
		e.setPartGroup(group.stream().findFirst().get());
		return super.create(e);
	}

	@Override
	public Participant update(Integer id, Participant e) {
		Participant saved = findById(id);
		mapper.map(e, saved);
		return super.update(id, saved);
	}

	@Override
	public List<Participant> findAllByGroupId(Integer groupId) {
		return repo.findAllByGroupId(groupId);
	}

}
