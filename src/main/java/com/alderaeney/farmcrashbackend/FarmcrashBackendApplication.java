package com.alderaeney.farmcrashbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FarmcrashBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmcrashBackendApplication.class, args);
	}

}
