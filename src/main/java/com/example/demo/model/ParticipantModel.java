package com.example.demo.model;

import lombok.Data;

@Data
public class ParticipantModel implements AbstractModel {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String name;

	private Integer age;

	private String email;

	private String phone;

	private GroupModel partGroup;

}
