package com.apress.spring6recipes.weather;

import com.apress.spring6recipes.weather.schemas.GetTemperaturesRequest;
import com.apress.spring6recipes.weather.schemas.GetTemperaturesResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.time.LocalDate;

@Endpoint
public class TemperatureEndpoint {

	private static final String namespaceUri = "http://spring6recipes.apress.com/weather/schemas";
	private final WeatherService weatherService;

	public TemperatureEndpoint(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

	@PayloadRoot(localPart = "GetTemperaturesRequest", namespace = namespaceUri)
	@ResponsePayload
	public GetTemperaturesResponse getTemperature(@RequestPayload GetTemperaturesRequest request) {
		// Extract the service parameters from the request message
		var city = request.getCity();
		var dates = request.getDate().stream()
						.map(ds -> LocalDate.of(ds.getYear(), ds.getMonth(), ds.getDay()))
						.toList();

		// Invoke the back-end service to handle the request
		var temperatures =
						weatherService.getTemperatures(city, dates);

		// Build the response message from the result of back-end service
		var response = new GetTemperaturesResponse();
		temperatures.forEach(temp -> response.getTemperatureInfo().add(map(temp)));
		return response;
	}

	private GetTemperaturesResponse.TemperatureInfo map(TemperatureInfo temperature) {
		var temperatureInfo = new GetTemperaturesResponse.TemperatureInfo();
		temperatureInfo.setCity(temperature.city());
		temperatureInfo.setMax(temperature.max());
		temperatureInfo.setMin(temperature.min());
		temperatureInfo.setAverage(temperature.average());
		try {
			temperatureInfo.setDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(temperature.date().toString()));
		} catch (DatatypeConfigurationException e) {
			throw new IllegalStateException(e);
		}
		return temperatureInfo;
	}
}
