package com.apress.spring6recipes.weather;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class WeatherServiceProxy implements WeatherService {

	private static final String uri = "http://spring6recipes.apress.com/weather/schemas";

	private final WebServiceTemplate webServiceTemplate;

	public WeatherServiceProxy(WebServiceTemplate webServiceTemplate) {
		this.webServiceTemplate = webServiceTemplate;
	}

	@Override
	public List<TemperatureInfo> getTemperatures(String city, List<LocalDate> dates) {

		// Build the request document from the method arguments
		var doc = DocumentHelper.createDocument();
		var el = doc.addElement("GetTemperaturesRequest", uri);
		el.addElement("city").setText(city);
		dates.forEach(date -> el.addElement("date")
						.setText(date.format(DateTimeFormatter.ISO_DATE)));

		// Invoke the remote web service
		var source = new DocumentSource(doc);
		var result = new DocumentResult();
		webServiceTemplate.sendSourceAndReceiveToResult(source, result);

		// Extract the result from the response document
		var responseDocument = result.getDocument();
		var responseElement = responseDocument.getRootElement();
		return responseElement.elements("TemperatureInfo")
						.stream().map( (e) -> this.map(city, e))
						.toList();
	}

	private TemperatureInfo map(String city, Element element) {
		var date = LocalDate.parse(element.attributeValue("date"), DateTimeFormatter.ISO_DATE);
		var min = Double.parseDouble(element.elementText("min"));
		var max = Double.parseDouble(element.elementText("max"));
		var average = Double.parseDouble(element.elementText("average"));
		return new TemperatureInfo(city, date, min, max, average);
	}
}