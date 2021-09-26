package com.example.demo.mapping;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Match;
import com.example.demo.model.MatchModel;

@Service
public class MatchMappingServiceImpl extends SuperMappingServiceImpl<Match, MatchModel> implements MatchMappingService {

	public MatchMappingServiceImpl(ModelMapper mapper) {
		super(mapper, Match.class, MatchModel.class);
	}

}
