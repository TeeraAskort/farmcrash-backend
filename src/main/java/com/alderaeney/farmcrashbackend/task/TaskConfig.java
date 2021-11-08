package com.alderaeney.farmcrashbackend.task;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskConfig {

    @Bean
    CommandLineRunner taskConfiguration(TaskRepository repository) {
        return args -> {
            Task fishing = new Task(TaskType.FISHING, 3, 100);
            repository.saveAll(List.of(fishing));
        };
    }

}
