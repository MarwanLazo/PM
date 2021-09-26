package com.example.demo.model;

import lombok.Data;

@Data
public class GroupModel implements AbstractModel {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private String place;

	private Integer count;

	private Integer noOfMatches;

	private RoundModel round;
}
