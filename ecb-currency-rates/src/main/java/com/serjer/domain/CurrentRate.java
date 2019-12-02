package com.serjer.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class CurrentRate {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

	private String date;

    private String rate;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curremcy_id")
    private Currency currency;

        
	public CurrentRate() {
		super();
	}

	public CurrentRate(String date, String rate, Currency currency) {
		super();
		this.date = date;
		this.rate = rate;
		this.currency = currency;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}


}
