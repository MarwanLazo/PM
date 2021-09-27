package com.example.demo.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.NonNull;

import com.example.demo.entity.Group;
import com.example.demo.entity.Match;
import com.example.demo.entity.Participant;
import com.example.demo.entity.Round;
import com.example.demo.repository.MatchRepository;

@Service
public class MatchServiceImpl extends SuperServiceImpl<Match, Integer, MatchRepository> implements MatchService {

	private final GroupService groupService;
	private final ParticipantService participantService;
	private final RoundService roundService;

	public MatchServiceImpl(MatchRepository repo, GroupService groupService, RoundService roundService,
			ParticipantService participantService) {
		super(repo);
		this.groupService = groupService;
		this.participantService = participantService;
		this.roundService = roundService;
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
		generateNextRoundGroupsAndPaticipant();
		return update(match).getId();
	}

	/**
	 * check that all matches is played to generate next round group , participants
	 * group of next round and create match
	 * 
	 */
	@Async("executor")
	public void generateNextRoundGroupsAndPaticipant() {

		// load not played mathes count
		List<Match> matches = findAll();
		long count = matches.stream().filter(mt -> !mt.isPlayed()).count();
		if (count == 0l) {
			// get max round to generate next round group
			Optional<@NonNull Round> round = matches.stream().map(Match::getRound)
					.max(Comparator.comparing(Round::getId));
			if (round.isPresent()) {
				var nextRound = roundService.findById(round.get().getId() + 1);
				if (nextRound != null) {

					Map<Participant, Long> winners = matches.stream().map(Match::getWinner)
							.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

					List<Participant> groupHeadList = getParticipant(winners, true);
					List<Participant> notGroupHeadList = getParticipant(winners, false);

					List<Integer> groupedParticipantId = new ArrayList<>();

					for (Participant grHeadPart : groupHeadList) {
						if (groupedParticipantId.contains(grHeadPart.getId()))
							continue;
						for (Participant notGrHeadPart : notGroupHeadList) {

							if (groupedParticipantId.contains(notGrHeadPart.getId()))
								continue;
							if (notGrHeadPart.getPartGroup().getId().equals(grHeadPart.getPartGroup().getId()))
								continue;

							if (!notGrHeadPart.getPartGroup().getRound().getId()
									.equals(grHeadPart.getPartGroup().getRound().getId()))
								continue;

							// create group of next round
							var group = new Group("", "", 2);
							group.setRound(nextRound);
							// set group name ( group head group name and round Desc)
							group.setName(grHeadPart.getPartGroup().getName().concat(" ").concat(nextRound.getName()));
							// set palce of groupHead group place
							group.setPlace(grHeadPart.getPartGroup().getPlace());

							// create group
							group.setId(null);
							group = groupService.create(group);

							// update participant group name
							grHeadPart.setPartGroup(group);
							notGrHeadPart.setPartGroup(group);

							// update participant
							participantService.update(grHeadPart);
							participantService.update(notGrHeadPart);

							groupedParticipantId.add(notGrHeadPart.getId());
							groupedParticipantId.add(grHeadPart.getId());

							break;
						}
					}
					// create matches per round
					createRoundMatches(nextRound.getId());

				}
			}

		}

	}

	List<Participant> getParticipant(Map<Participant, Long> winners, boolean isHead) {
		Optional<Long> count;
		if (isHead)
			count = winners.values().stream().max(Comparator.comparing(Long::longValue));
		else
			count = winners.values().stream().min(Comparator.comparing(Long::longValue));

		List<Participant> parts = new ArrayList<>();
		if (count.isEmpty())
			return parts;

		for (Participant p : winners.keySet()) {
			if (winners.get(p).equals(count.get()))
				parts.add(p);
		}
		return parts;
	}

}
