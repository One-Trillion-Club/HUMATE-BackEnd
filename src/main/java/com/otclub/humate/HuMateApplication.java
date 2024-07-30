package com.otclub.humate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class HuMateApplication {

	public static void main(String[] args) {
		SpringApplication.run(HuMateApplication.class, args);
	}

}
