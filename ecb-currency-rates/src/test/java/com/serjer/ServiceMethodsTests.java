package com.serjer;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.serjer.service.CurrencyService;


@SpringBootTest
class ServiceMethodsTests {
		
	@Autowired
	private CurrencyService currencyService;

	    @Test
	    public void test1ConvertCurrency() {
	    	assertEquals("1.25", currencyService.convertCurrency("1.245699", "1"));  
	    }
	    
	    @Test
	    public void test2ConvertCurrency() {
	    	assertEquals("1.24", currencyService.convertCurrency("1.244999", "1"));  
	    }
	      
}
