package com.apress.spring6recipes.weather;

import com.apress.spring6recipes.weather.schemas.GetTemperaturesRequest;
import com.apress.spring6recipes.weather.schemas.GetTemperaturesResponse;
import org.springframework.ws.client.core.WebServiceTemplate;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.time.LocalDate;
import java.util.List;

public class WeatherServiceProxy implements WeatherService {

	private final WebServiceTemplate webServiceTemplate;

	public WeatherServiceProxy(WebServiceTemplate webServiceTemplate) throws Exception {
		this.webServiceTemplate = webServiceTemplate;
	}

	public List<TemperatureInfo> getTemperatures(String city, List<LocalDate> dates) {
		var request = createRequest(city, dates);
		var response = (GetTemperaturesResponse) webServiceTemplate.marshalSendAndReceive(request);
		return response.getTemperatureInfo().stream().map( (ti) -> map(city, ti)).toList();
	}

	private TemperatureInfo map(String city, GetTemperaturesResponse.TemperatureInfo info) {
		var date = info.getDate();
		var min = info.getMin();
		var max = info.getMax();
		var average = info.getAverage();
		return new TemperatureInfo(city, LocalDate.of(date.getYear(), date.getMonth(), date.getDay()),
						min, max, average);
	}

	private GetTemperaturesRequest createRequest(String city, List<LocalDate> dates) {
		var request = new GetTemperaturesRequest();
		request.setCity(city);
		dates.forEach(ld -> {
			try {
				request.getDate().add(DatatypeFactory.newInstance().newXMLGregorianCalendar(ld.toString()));
			} catch (DatatypeConfigurationException e) {
				throw new IllegalStateException(e);
			}
		});
		return request;
	}
}