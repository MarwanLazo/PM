package com.example.demo.model;

import java.util.Date;

import lombok.Data;

@Data
public class MatchModel implements AbstractModel {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String desc;

	private ParticipantModel partOne;

	private ParticipantModel partTwo;

	private ParticipantModel winner;

	private RoundModel round;

	private boolean played;

	private Date matchDate;

}
