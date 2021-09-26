package com.example.demo.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.SuperEntity;
import com.example.demo.mapping.SuperMappingService;
import com.example.demo.model.AbstractModel;
import com.example.demo.service.SuperService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class SuperControllerImpl<Model extends AbstractModel, PK extends Serializable, EN extends SuperEntity, Service extends SuperService<EN, PK>, Mapper extends SuperMappingService<EN, Model>>
		implements SuperController<Model, PK> {

	protected final Service service;
	protected final Mapper mapper;

	@Override
	public ResponseEntity<Model> findById(@PathVariable PK id) {
		EN en = service.findById(id);
		Model model = mapper.mapToModel(en);
		return new ResponseEntity<>(model, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<Model>> findAll() {
		List<EN> en = service.findAll();
		List<Model> model = mapper.mapToModel(en);
		return new ResponseEntity<>(model, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Model> save(Model model) {
		EN en = mapper.mapToEntity(model);
		en = service.create(en);
		model = mapper.mapToModel(en);
		return new ResponseEntity<>(model, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Model> update(PK id, Model model) {
		EN en = mapper.mapToEntity(model);
		en = service.update(id, en);
		model = mapper.mapToModel(en);
		return new ResponseEntity<>(model, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> delete(PK id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}