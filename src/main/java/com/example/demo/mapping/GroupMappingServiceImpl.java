package com.example.demo.mapping;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Group;
import com.example.demo.model.GroupModel;

@Service
public class GroupMappingServiceImpl extends SuperMappingServiceImpl<Group, GroupModel> implements GroupMappingService {

	public GroupMappingServiceImpl(ModelMapper mapper) {
		super(mapper, Group.class, GroupModel.class);
	}

}
