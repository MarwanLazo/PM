package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Group;
import com.example.demo.mapping.GroupMappingService;
import com.example.demo.model.GroupModel;
import com.example.demo.service.GroupService;

@RestController
@RequestMapping({ "group" })
public class GroupControllerImpl extends
		SuperControllerImpl<GroupModel, Integer, Group, GroupService, GroupMappingService> implements GroupController {

	public GroupControllerImpl(GroupService service, GroupMappingService mapper) {
		super(service, mapper);
	}

}