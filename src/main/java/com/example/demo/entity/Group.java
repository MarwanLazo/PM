package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@RequiredArgsConstructor

@Table(name = "tb_group")
@Entity
public class Group extends SuperEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "group_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NonNull
	@Column(name = "group_name")
	private String name;

	@NonNull
	@Column(name = "group_place")
	private String place;

	@NonNull
	@Column(name = "group_count")
	private Integer count;

	@Column(name = "group_of_matches")
	private Integer noOfMatches;

	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "group_round", referencedColumnName = "round_id")
	private Round round;

	public static Group newInstance(String name, String place, Integer count) {
		return new Group(name, place, count);
	}

	/**
	 * set No. of matches based on group count
	 */
	@PrePersist
	private void doPrePersist() {
		if (getCount() > 0) {
			setNoOfMatches((getCount() - 1) * getCount());
		}
	}
}
