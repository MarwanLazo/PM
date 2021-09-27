package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.MatchModel;

public interface MatchController extends SuperController<MatchModel, Integer> {

	@GetMapping({ "createRoundMatches/{roundId}" })
	ResponseEntity<List<MatchModel>> createRoundMatches(@PathVariable Integer roundId);

	@GetMapping({ "setMatchWinner/{matchId}/{winnerId}" })
	ResponseEntity<Integer> setMatchWinner(@PathVariable("matchId") Integer matchId,
			@PathVariable("winnerId") Integer winnerId);
}
