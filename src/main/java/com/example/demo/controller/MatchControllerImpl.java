package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Match;
import com.example.demo.mapping.MatchMappingService;
import com.example.demo.model.MatchModel;
import com.example.demo.service.MatchService;

@RestController
@RequestMapping({ "match" })
public class MatchControllerImpl extends
		SuperControllerImpl<MatchModel, Integer, Match, MatchService, MatchMappingService> implements MatchController {

	public MatchControllerImpl(MatchService service, MatchMappingService mapper) {
		super(service, mapper);
	}

	@Override
	public ResponseEntity<List<MatchModel>> createRoundMatches(Integer roundId) {

		List<Match> match = service.createRoundMatches(roundId);
		List<MatchModel> model = mapper.mapToModel(match);
		return new ResponseEntity<>(model, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Integer> setMatchWinner(Integer matchId, Integer winnerId) {
		winnerId = service.setMastchWinner(matchId, winnerId);
		return new ResponseEntity<>(winnerId, HttpStatus.OK);
	}

}
