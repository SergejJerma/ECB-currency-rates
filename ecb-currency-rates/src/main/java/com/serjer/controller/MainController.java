package com.serjer.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.serjer.domain.Currency;
import com.serjer.repo.CurrencyRepo;
import com.serjer.repo.CurrentRateRepo;
import com.serjer.repo.HistoryRateRepo;
import com.serjer.service.CurrencyService;
import com.serjer.service.RateService;

@Controller
public class MainController {

	@Autowired
	private RateService rateService;

	@Autowired
	private CurrencyService currencyService;

	@Autowired
	private HistoryRateRepo historyRateRepo;
	
	@Autowired
	private CurrentRateRepo currentRateRepo;

	@Autowired
	private CurrencyRepo currencyRepo;

	private static boolean executed;

	@GetMapping("/")
	public String showActualRates(Model model) {
		if (!executed) {
			currencyService.getCurriencyCodeAndName();
			rateService.getCurrentRates();
			executed = true;
		}
			
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		model.addAttribute("date", dateTime.format(formatter));
		
		model.addAttribute("currentRates", currentRateRepo.findAll());
		return "rates";
	}

	@GetMapping("/history")
	public String show1CurrencyRatesHistory(Model model, 
						@RequestParam Long id) {

		model.addAttribute("currency", currencyRepo.findOneById(id));
		model.addAttribute("historyRates", historyRateRepo.findAllByCurrencyId(id));
		historyRateRepo.deleteAll();
		return "history";
	}
	
	@PostMapping("/history")
	public String show2CurrencyRatesHistory(Model model,
						@RequestParam(value = "range", required = false) String range,
						@RequestParam Long id) {
		
		LocalDate dateFrom;
		LocalDate dateTo = LocalDate.now();
		
		if (range.equals("YTD")) dateFrom = dateTo.with(TemporalAdjusters.firstDayOfYear());
		else if (range.equals("all")) dateFrom = LocalDate.of(2014, 9, 30);
		else dateFrom = dateTo.minusMonths(Integer.valueOf(range));
	
		rateService.getHistoryRates( currencyRepo.findOneById(id).getCode(), 
									dateFrom.toString(), 
									dateTo.toString());
		return "redirect:/history?id=" + id;
	}

	@GetMapping("/converter")
	public String currencyConvertor(Model model) {
		
		List<Currency> currenciesList = currencyRepo
				.findAll().stream()
				.filter(e -> e.getCurrentRate()!=null)
				.sorted(Comparator.comparing(Currency::getName))
				.collect(Collectors.toList());
		model.addAttribute("currenciesList", currenciesList);
		return "converter";
	}

	@PostMapping("/converter")
	public String convertCurrency(Model model, 
						@RequestParam String amount,
						@RequestParam(value = "code", required = false) String code) {
		
		model.addAttribute("enteredAmount", amount);
		model.addAttribute("convertedAmount", 
				currencyService.convertCurrency(currentRateRepo.findByCurrencyCode(code).getRate(), 
												amount));
		model.addAttribute("currentRate", currentRateRepo.findByCurrencyCode(code));
		return "converter";
	}

}
