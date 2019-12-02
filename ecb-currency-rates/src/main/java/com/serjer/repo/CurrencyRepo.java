package com.serjer.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serjer.domain.Currency;

public interface CurrencyRepo extends JpaRepository<Currency, Long> {
	Currency findByCode(String code);
	Currency findOneById(Long id);
}
