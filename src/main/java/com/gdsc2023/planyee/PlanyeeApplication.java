package com.gdsc2023.planyee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PlanyeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanyeeApplication.class, args);
	}

}
