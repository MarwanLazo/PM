package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@RequiredArgsConstructor

@Table(name = "tb_match", uniqueConstraints = @UniqueConstraint(
		columnNames = { "match_part_1", "match_part_2","match_round" }))
@Entity
public class Match extends SuperEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "match_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NonNull
	@Column(name = "match_desc")
	private String desc;

	@NonNull
	@ManyToOne
	@JoinColumn(name = "match_part_1", referencedColumnName = "part_id")
	private Participant partOne;

	@NonNull
	@ManyToOne
	@JoinColumn(name = "match_part_2", referencedColumnName = "part_id")
	private Participant partTwo;

	@ManyToOne
	@JoinColumn(name = "match_winner", referencedColumnName = "part_id")
	private Participant winner;

	@NonNull
	@ManyToOne
	@JoinColumn(name = "match_round", referencedColumnName = "round_id")
	private Round round;

	@Type(type = "true_false")
	@Column(name = "match_status")
	private boolean played;

//	@temp
	@Column(name = "match_date")
	private Date matchDate;

}
