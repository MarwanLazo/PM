package com.example.demo.mapping;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Round;
import com.example.demo.model.RoundModel;

@Service
public class RoundMappingServiceImpl extends SuperMappingServiceImpl<Round, RoundModel> implements RoundMappingService {

	public RoundMappingServiceImpl(ModelMapper mapper) {
		super(mapper, Round.class, RoundModel.class);
	}

}
