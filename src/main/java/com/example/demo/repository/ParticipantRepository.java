package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Participant;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Integer> {

	@Query("SELECT p from Participant p WHERE p.partGroup.id=?1")
	List<Participant> findAllByGroupId(Integer groupId);

}
