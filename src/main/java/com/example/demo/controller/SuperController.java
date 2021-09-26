package com.example.demo.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.AbstractModel;

public interface SuperController<Model extends AbstractModel, PK extends Serializable> {

	@GetMapping({ "/{id}" })
	ResponseEntity<Model> findById(@PathVariable PK id);

	@GetMapping
	ResponseEntity<List<Model>> findAll();

	@PostMapping
	ResponseEntity<Model> save(@RequestBody Model t);

	@PutMapping({ "/{id}" })
	ResponseEntity<Model> update(@PathVariable PK id, @RequestBody Model t);

	@DeleteMapping({ "/{id}" })
	ResponseEntity<Void> delete(@PathVariable PK id);

}
