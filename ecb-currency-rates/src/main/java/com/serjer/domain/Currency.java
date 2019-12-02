package com.serjer.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Currency {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
		
	private String code;

	private String name;
	
	@OneToMany(mappedBy = "currency", cascade = CascadeType.ALL)
	private List<HistoryRate> rates;
	
	@OneToOne(mappedBy = "currency", cascade = CascadeType.ALL)
    private CurrentRate currentRate;


	public CurrentRate getCurrentRate() {
		return currentRate;
	}

	public void setCurrentRate(CurrentRate currentRate) {
		this.currentRate = currentRate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<HistoryRate> getRates() {
		return rates;
	}

	public void setRates(List<HistoryRate> rates) {
		this.rates = rates;
	}


	public Currency() {
		super();
	}
	

	public Currency(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
