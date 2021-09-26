package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Match;

public interface MatchService extends SuperService<Match, Integer> {

	List<Match> createRoundMatches(Integer roundId);

}
