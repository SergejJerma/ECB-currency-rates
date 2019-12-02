package com.serjer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EcbCurrencyRatesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcbCurrencyRatesApplication.class, args);
	}

}
