package com.alderaeney.farmcrashbackend.worker;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkerConfig {

    @Bean
    CommandLineRunner workerConfiguration(WorkerRepository repository) {
        return args -> {
            Worker clark = new Worker("Clark", 18, "clark");
            Worker david = new Worker("David", 22, "david");
            Worker jonas = new Worker("Jonas", 30, "jonas");
            Worker lindsay = new Worker("Lindsay", 20, "lindsay");
            Worker mariam = new Worker("Mariam", 26, "mariam");
            Worker michael = new Worker("Michael", 40, "michael");
            Worker stefanie = new Worker("Stefanie", 18, "stefanie");
            Worker steffan = new Worker("Steffan", 32, "steffan");

            repository.saveAll(List.of(clark, david, jonas, lindsay, mariam, michael, stefanie, steffan));
        };
    }

}