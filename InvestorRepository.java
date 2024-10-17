package com.example;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestorRepository extends JpaRepository<Investor, Integer> {
	
	Investor findByEmail(String email);

}
