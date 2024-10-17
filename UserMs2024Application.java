package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class UserMs2024Application {

	public static void main(String[] args) {
		SpringApplication.run(UserMs2024Application.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(InvestorRepository repository) {
		return (args)->{
			
			repository.save(new Investor("abhi13@gmail.com","abhi3",100000.0));
			repository.save(new Investor("test@gmail.com","test",100000.0));
			
			repository.findAll().forEach(data ->{
				System.out.println(data);
			});
		};
	}

}
