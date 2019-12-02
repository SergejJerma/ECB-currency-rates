package com.serjer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.serjer.domain.HistoryRate;

public interface HistoryRateRepo extends JpaRepository<HistoryRate, Long> {
	List<HistoryRate> findAllByCurrencyId(Long id);
	void deleteAllByCurrencyId(Long id);
}
