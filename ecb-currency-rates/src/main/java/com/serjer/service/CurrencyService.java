package com.serjer.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.serjer.domain.Currency;
import com.serjer.repo.CurrencyRepo;

@Service
public class CurrencyService {

	@Autowired
	private CurrencyRepo currencyRepo;

	public void getCurriencyCodeAndName() {
		try {
			String URL = "https://www.lb.lt//webservices/FxRates/FxRates.asmx/getCurrencyList";

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(URL);
			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName("CcyNtry");

			for (int i = 0; i < nodeList.getLength(); i++) {

				Node node = nodeList.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) node;
					String code = elem.getElementsByTagName("Ccy").item(0).getTextContent();
					String name = elem.getElementsByTagName("CcyNm").item(1).getTextContent();
					Currency currency = new Currency(code, name);
					currencyRepo.save(currency);
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	public String convertCurrency(String rate, String amount) {
		final DecimalFormat df = new DecimalFormat("#0.00");
		BigDecimal convertedAmount = new BigDecimal(rate).multiply(new BigDecimal(amount));
		return df.format(convertedAmount.doubleValue());
	}
}
