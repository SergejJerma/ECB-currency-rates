package com.serjer.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serjer.domain.CurrentRate;

public interface CurrentRateRepo extends JpaRepository<CurrentRate, Long> {
	CurrentRate findByCurrencyCode(String code);
	
}
