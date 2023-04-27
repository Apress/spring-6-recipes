package com.apress.spring6recipes.weather;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.dom4j.xpath.DefaultXPath;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Endpoint
public class TemperatureEndpoint {

	private static final String namespaceUri = "http://spring6recipes.apress.com/weather/schemas";
	private final WeatherService weatherService;
	private final XPath cityPath;
	private final XPath datePath;

	public TemperatureEndpoint(WeatherService weatherService) {
		this.weatherService = weatherService;
		// Create the XPath objects, including the namespace
		var namespaceUris = Map.of("weather", namespaceUri);
		cityPath = new DefaultXPath("/weather:GetTemperaturesRequest/weather:city");
		cityPath.setNamespaceURIs(namespaceUris);
		datePath = new DefaultXPath("/weather:GetTemperaturesRequest/weather:date");
		datePath.setNamespaceURIs(namespaceUris);
	}

	@PayloadRoot(localPart = "GetTemperaturesRequest", namespace = namespaceUri)
	@ResponsePayload
	public Element getTemperature(@RequestPayload Element requestElement) {
		// Extract the service parameters from the request message
		var city = cityPath.valueOf(requestElement);
		var dates = datePath.selectNodes(requestElement).stream()
						.map(Node::getText)
						.map(ds -> LocalDate.parse(ds, DateTimeFormatter.ISO_DATE))
						.toList();

		// Invoke the back-end service to handle the request
		var temperatures =
						weatherService.getTemperatures(city, dates);

		// Build the response message from the result of back-end service
		var responseDocument = DocumentHelper.createDocument();
		var responseElement = responseDocument.addElement(
						"GetTemperaturesResponse", namespaceUri);
		temperatures.forEach(temp -> map(responseElement, temp));
		return responseElement;
	}

	private Element map(Element root, TemperatureInfo temperature) {
		var temperatureElement = root.addElement("TemperatureInfo");
		temperatureElement.addAttribute("city", temperature.city());
		temperatureElement.addAttribute("date", temperature.date().format(DateTimeFormatter.ISO_DATE));
		temperatureElement.addElement("min").setText(Double.toString(temperature.min()));
		temperatureElement.addElement("max").setText(Double.toString(temperature.max()));
		temperatureElement.addElement("average").setText(Double.toString(temperature.average()));
		return temperatureElement;
	}

}
