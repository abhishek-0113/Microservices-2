package com.example;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Long> {
	
	List<Trade> findByTicker(String ticker);

}
