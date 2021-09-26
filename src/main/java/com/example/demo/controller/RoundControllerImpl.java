package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Round;
import com.example.demo.mapping.RoundMappingService;
import com.example.demo.model.RoundModel;
import com.example.demo.service.RoundService;

@RestController
@RequestMapping({ "round" })
public class RoundControllerImpl extends
		SuperControllerImpl<RoundModel, Integer, Round, RoundService, RoundMappingService> implements RoundController {

	public RoundControllerImpl(RoundService service, RoundMappingService mapper) {
		super(service, mapper);
	}

}