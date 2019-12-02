package com.serjer.service;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.serjer.domain.CurrentRate;
import com.serjer.domain.HistoryRate;
import com.serjer.repo.CurrencyRepo;
import com.serjer.repo.CurrentRateRepo;
import com.serjer.repo.HistoryRateRepo;

@Service
public class RateService {
	
	@Autowired
	private HistoryRateRepo historyRateRepo;
	
	@Autowired
	private CurrentRateRepo currentRateRepo;
	
	@Autowired
	private CurrencyRepo currencyRepo;
	
	@Scheduled(cron = "0 0 19 * * *")
	public void getCurrentRates() {
		if(!currentRateRepo.findAll().isEmpty()) currentRateRepo.deleteAll();
	
		try {
			String URL = "https://www.lb.lt/webservices/FxRates/FxRates.asmx/getCurrentFxRates?tp=EU";

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(URL);

			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName("FxRate");

			for (int i = 0; i < nodeList.getLength(); i++) {

				Node node = nodeList.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) node;
					String date = elem.getElementsByTagName("Dt").item(0).getTextContent().replace('.', '-');
					String code = elem.getElementsByTagName("Ccy").item(1).getTextContent();
					String rate = elem.getElementsByTagName("Amt").item(1).getTextContent();
					CurrentRate currentRate = new CurrentRate(date, rate, currencyRepo.findByCode(code));
					currentRateRepo.save(currentRate);
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}
	
	public void getHistoryRates(String currencyCode,  String dateFrom, String dateTo){			
		  try {
	            String URL = "https://www.lb.lt/webservices/FxRates/FxRates.asmx/getFxRatesForCurrency?tp=EU"
	            		+ "&ccy=" + currencyCode
	            		+ "&dtFrom=" + dateFrom
	            		+ "&dtTo=" + dateTo;
	  
	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder builder = factory.newDocumentBuilder();
	            Document doc = builder.parse(URL);
	            doc.getDocumentElement().normalize();
	            
	            NodeList nodeList = doc.getElementsByTagName("FxRate");
	            
	            for (int i = 0; i < nodeList.getLength(); i++) {

	                Node node = nodeList.item(i);

	                if(node.getNodeType() == Node.ELEMENT_NODE) {
	                    Element elem = (Element) node;
						String date = elem.getElementsByTagName("Dt").item(0).getTextContent().replace('.', '-');
						String code = elem.getElementsByTagName("Ccy").item(1).getTextContent();
						String rate = elem.getElementsByTagName("Amt").item(1).getTextContent();
						HistoryRate historyRate = new HistoryRate(date, rate, currencyRepo.findByCode(code));
	                    historyRateRepo.save(historyRate);
	                }
	            }
	        } catch (Exception ex) {
	          System.out.println(ex.getMessage());
	    }
	}
}
