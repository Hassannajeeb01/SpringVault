package com.example.springvault;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.springvault.repository")
public class SpringVault {

	public static void main(String[] args) {
		SpringApplication.run(SpringVault.class, args);
	}

}