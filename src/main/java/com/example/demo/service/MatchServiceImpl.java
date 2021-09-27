package com.example.demo.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Group;
import com.example.demo.entity.Match;
import com.example.demo.entity.Participant;
import com.example.demo.repository.MatchRepository;

@Service
public class MatchServiceImpl extends SuperServiceImpl<Match, Integer, MatchRepository> implements MatchService {

	private final GroupService groupService;
	private final ParticipantService participantService;

	public MatchServiceImpl(MatchRepository repo, GroupService groupService, ParticipantService participantService) {
		super(repo);
		this.groupService = groupService;
		this.participantService = participantService;
	}

	@Override
	public List<Match> createRoundMatches(Integer roundId) {
		List<Match> matches = new ArrayList<>();
		List<Group> groups = groupService.findAllByRoundId(roundId);
		Date matchDate;
		for (Group gp : groups) {
			matchDate = new Date();
			List<Participant> p = participantService.findAllByGroupId(gp.getId());
			for (int i = 0; i < p.size(); i++) {
				for (int j = i + 1; j < p.size(); j++) {
					Participant p1 = p.get(i);
					Participant p2 = p.get(j);

					Match match = repo.getMatchByPartOneIdAndPartTwoIdAndRound(p1.getId(), p2.getId(),
							gp.getRound().getId());
					if (match == null) {
						match = new Match(p1.getName() + " VS " + p2.getName(), p1, p2, gp.getRound());
						match.setMatchDate(matchDate);
						matchDate = getMatchDate(matchDate);
					}
					matches.add(repo.save(match));
				}
			}
		}
		return matches;
	}

	private Date getMatchDate(Date date) {

		// today
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, 3);
		return calendar.getTime();

	}

	@Override
	public Integer setMastchWinner(Integer matchId, Integer winnerId) {
		Match match = findById(matchId);
		if (match.getPartOne().getId().equals(winnerId)) {
			match.setWinner(match.getPartOne());
		} else {
			match.setWinner(match.getPartTwo());
		}
		match.setPlayed(true);
		return update(match).getId();
	}

}
