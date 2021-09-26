package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

	@Query("SELECT g FROM Group g where g.count>(select count(p.id) from Participant p where p.partGroup.id=g.id ) ORDER BY g.id ASC ")
	List<Group> selectGroupOrderedByIdASC();

	@Query("SELECT g FROM Group g where g.round.id=?1")
	List<Group> findAllByRoundId(Integer roundId);

}
