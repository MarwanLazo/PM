package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Participant;
import com.example.demo.mapping.ParticipantMappingService;
import com.example.demo.model.ParticipantModel;
import com.example.demo.service.ParticipantService;

@RestController
@RequestMapping({ "participant" })
public class ParticipantControllerImpl extends
		SuperControllerImpl<ParticipantModel, Integer, Participant, ParticipantService, ParticipantMappingService>
		implements ParticipantController {

	public ParticipantControllerImpl(ParticipantService service, ParticipantMappingService mapper) {
		super(service, mapper);
	}

}
