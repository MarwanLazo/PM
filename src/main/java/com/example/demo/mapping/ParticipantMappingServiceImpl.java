package com.example.demo.mapping;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Participant;
import com.example.demo.model.ParticipantModel;

@Service
public class ParticipantMappingServiceImpl extends SuperMappingServiceImpl<Participant, ParticipantModel>
		implements ParticipantMappingService {

	public ParticipantMappingServiceImpl(ModelMapper mapper) {
		super(mapper, Participant.class, ParticipantModel.class);
	}

}
