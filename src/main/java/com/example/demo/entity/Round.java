package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@RequiredArgsConstructor

@DynamicInsert
@DynamicUpdate

@Table(name = "tb_round")
@Entity
public class Round extends SuperEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "round_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NonNull
	@Column(name = "round_name")
	private String name;

	@NonNull
	@Column(name = "round_desc")
	private String desc;

	@NonNull
	@Column(name = "round_rank")
	private Integer rank;

//	@Formula("SELECT SUM(group_of_matches) from groups")
//	@Column(name = "round_no_of_matches")
//	private Integer noOfMatches;

}
