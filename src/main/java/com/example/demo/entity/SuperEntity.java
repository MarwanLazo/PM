package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@EntityListeners(AuditingEntityListener.class)

@Data
@MappedSuperclass
public abstract class SuperEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@CreatedBy
	@Column(name = "create_by")
	private String createBy;

	@CreatedDate
	@Column(name = "create_date")
	private Date createDate;

	@LastModifiedBy
	@Column(name = "modified_by")
	private String modifiedBy;

	@LastModifiedDate
	@Column(name = "modified_date")
	private Date modifiedDate;

}
