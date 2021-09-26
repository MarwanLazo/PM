package com.example.demo.service;

import java.io.Serializable;
import java.util.List;

import com.example.demo.entity.SuperEntity;

public interface SuperService<EN extends SuperEntity, PK extends Serializable> {

	EN findById(PK id);

	List<EN> findAll();

	EN update(PK id, EN t);

	EN create(EN e);

	void delete(EN e);

	void delete(PK id);

 

}
