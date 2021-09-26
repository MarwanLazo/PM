package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.entity.Participant;
import com.example.demo.repository.ParticipantRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class ParticipanTest {

	private @Autowired ParticipantRepository repository;
	private @Autowired ParticipantService service;
	private Participant participant;

	@BeforeEach
	void setUp() throws Exception {
		participant = repository.save(new Participant("part A", 30, "parta@test.com", "0100 321 2525"));
	}

	@Test
	void testFindById() {
		Participant participnt = repository.findById(participant.getId()).get();
		assertThat(participant).isEqualTo(participnt);
	}

	@Test
	void testFindAll() {
		assertThat(repository.count()).isEqualTo(service.findAll().size());
	}

	@Test
	void testCreate() {
		participant = repository.save(new Participant("part B", 30, "parta@test.com", "0100 321 2525"));
		assertThat(participant.getName()).isEqualTo("part B");
	}

	@AfterEach
	void tearDown() throws Exception {
		repository.deleteAll();
	}

}
