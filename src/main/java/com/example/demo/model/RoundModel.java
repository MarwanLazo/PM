package com.example.demo.model;

import lombok.Data;

@Data
public class RoundModel implements AbstractModel {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private Integer rank;
}
