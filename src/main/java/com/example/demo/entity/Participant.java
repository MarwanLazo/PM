package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

@Table(name = "tb_participant")
@Entity
public class Participant extends SuperEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "part_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NonNull
	@Column(name = "part_name")
	private String name;

	@NonNull
	@Column(name = "part_age")
	private Integer age;

	@NonNull
	@Column(name = "part_email")
	private String email;

	@NonNull
	@Column(name = "part_phone")
	private String phone;

	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "part_group", referencedColumnName = "group_id")
	private Group partGroup;
}
