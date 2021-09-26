package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.entity.Group;
import com.example.demo.repository.GroupRepository;
import com.example.demo.service.GroupService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class GroupTests {

	private Integer PARTICIPANT_NO;
	private Integer GROUP_COUNT;
	private @Autowired GroupService service;
	private @Autowired GroupRepository repository;

	private List<Group> groups;

	@BeforeEach
	void setUp() throws Exception {
		PARTICIPANT_NO = 12;
		GROUP_COUNT = PARTICIPANT_NO / 4;
		groups = Stream.<String>of("A", "B", "C", "D").map(g -> Group.newInstance(g, "place " + g, GROUP_COUNT))
				.collect(Collectors.toList());
	}

	@AfterEach
	void tearDown() throws Exception {
		repository.deleteAll();
	}

	@Test
	void test() {
		groups = groups.parallelStream().map(service::create).collect(Collectors.toList());
		groups.stream().forEach(System.out::println);
		int size = groups.size();
		assertThat(size).isEqualTo(4);
	}

}
