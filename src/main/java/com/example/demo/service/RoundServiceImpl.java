package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Round;
import com.example.demo.repository.RoundRepository;

@Service
public class RoundServiceImpl extends SuperServiceImpl<Round, Integer, RoundRepository> implements RoundService {

	public RoundServiceImpl(RoundRepository repo) {
		super(repo);
	}

}
