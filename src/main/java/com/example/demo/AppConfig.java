package com.example.demo;

import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.example.demo.entity.Round;
import com.example.demo.service.RoundService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableAsync
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
class AppConfig {
    @Bean
    AuditorAware<String> auditorAware() {
        return () -> Optional.<String>of("LoggedInUser");
    }

    @Bean
    ModelMapper getModelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper;
    }

    @Bean
    CommandLineRunner run(final RoundService service) {
        return ar -> {
            /**
             * set up default and first round
             */
            Stream.<Round>of(new Round("Groups", "League Start , it is 1st Round ", 1),
                    new Round("Round 8", "Round has 8 PARTICIPANT", 2),
                    new Round("Round 4", "Round has 4 PARTICIPANT", 3),
                    new Round("Round 2", "Round has 2 PARTICIPANT", 4),
                    new Round("Final Round", "Final Round :: THE CHAMPION ", 5)).map(service::create)
                    .forEach(rnd -> log.info("{},{},{}", rnd, rnd.getCreateBy(), rnd.getCreateDate()));
        };
    }

    @Bean(name = "executor")
    public Executor executor() {
        return new ThreadPoolTaskExecutor();
    }

}