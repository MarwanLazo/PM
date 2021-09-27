package com.example.demo.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.SuperEntity;

public class SuperServiceImpl<EN extends SuperEntity, PK extends Serializable, JPA extends JpaRepository<EN, PK>>
		implements SuperService<EN, PK> {

	protected final JPA repo;

	public SuperServiceImpl(JPA repo) {
		this.repo = repo;
	}

	@Override
	public EN findById(PK id) {
		return repo.findById(id).orElseThrow();
	}

	@Override
	public List<EN> findAll() {
		return repo.findAll();
	}

	@Override
	public EN update(PK id, EN e) {
		return repo.save(e);
	}

	@Override
	public EN update(EN e) {
		return update(null, e);
	}

	@Override
	public EN create(EN e) {
		return repo.save(e);
	}

	@Override
	public void delete(EN e) {
		repo.delete(e);
	}

	@Override
	public void delete(PK id) {
		repo.deleteById(id);
	}

}
