package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.entity.Group;
import com.example.demo.entity.Match;
import com.example.demo.entity.Participant;
import com.example.demo.entity.Round;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.MatchRepository;
import com.example.demo.repository.ParticipantRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class MatchTests {

	private final Integer ROUND_ID = 1; // Groups

	private @Autowired ParticipantRepository partRepo;
	private @Autowired RoundService roundService;

	private @Autowired MatchRepository matchRepo;
	private @Autowired MatchService matchService;

	private @Autowired GroupRepository groupRepo;
	private @Autowired GroupService groupService;

	private Participant part1;
	private Participant part2;
	private Round round;

	@BeforeEach
	void setUp() throws Exception {
		part1 = partRepo.save(new Participant("part A", 31, "parta@test.com", "0100 321 2525"));
		part2 = partRepo.save(new Participant("part B", 32, "partb@test.com", "0100 321 2524"));
		partRepo.save(new Participant("part C", 33, "partc@test.com", "0100 321 2523"));
		round = roundService.findById(ROUND_ID);
		groupService.create(new Group("Groups", "City 1", 3));
	}

//	@Test
	void test_createRoundMatches() {
		List<Match> matches = matchService.createRoundMatches(round.getId());
		assertThat(matches.size()).isEqualTo(3);
	}

//	@Test
	void test_checkMatchWithTwoParticipantAndRoundIsExist() {
		Match match = matchRepo.getMatchByPartOneIdAndPartTwoIdAndRound(part1.getId(), part2.getId(), round.getId());
		assertNotNull(match);
	}

	@AfterEach
	void tearDown() throws Exception {
		partRepo.deleteAll();
		groupRepo.deleteAll();
		matchRepo.deleteAll();
	}

}
