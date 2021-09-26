package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {

	@Query("SELECT m FROM Match m WHERE m.partOne.id=?1 AND  m.partTwo.id=?2 AND m.round.id=?3 ")
	Match getMatchByPartOneIdAndPartTwoIdAndRound(Integer partOneId, Integer partTwoId, Integer roundId);

}
