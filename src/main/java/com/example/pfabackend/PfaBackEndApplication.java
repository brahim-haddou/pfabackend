package com.example.pfabackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PfaBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(PfaBackEndApplication.class, args);
	}

}
