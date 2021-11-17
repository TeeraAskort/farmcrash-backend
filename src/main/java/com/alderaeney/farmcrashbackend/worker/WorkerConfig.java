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
            Worker clark = new Worker("Clark", 18, "clark", Hired.NOT_HIRED, 1000);
            Worker david = new Worker("David", 22, "david", Hired.NOT_HIRED, 1000);
            Worker jonas = new Worker("Jonas", 30, "jonas", Hired.NOT_HIRED, 1000);
            Worker lindsay = new Worker("Lindsay", 20, "lindsay", Hired.NOT_HIRED, 1000);
            Worker mariam = new Worker("Mariam", 26, "mariam", Hired.NOT_HIRED, 1000);
            Worker michael = new Worker("Michael", 40, "michael", Hired.NOT_HIRED, 1000);
            Worker stefanie = new Worker("Stefanie", 18, "stefanie", Hired.NOT_HIRED, 1000);
            Worker steffan = new Worker("Steffan", 32, "steffan", Hired.NOT_HIRED, 1000);

            repository.saveAll(List.of(clark, david, jonas, lindsay, mariam, michael, stefanie, steffan));
        };
    }

}