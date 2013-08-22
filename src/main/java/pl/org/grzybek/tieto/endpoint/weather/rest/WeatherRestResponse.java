package pl.org.grzybek.tieto.endpoint.weather.rest;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * POJO representing response from REST service
 * service
 * 
 * @author zgrzybek
 * 
 */
public class WeatherRestResponse {

	private Object result;

	public WeatherRestResponse(Object result) {
		this.result = result;
	}

	@JsonProperty(value = "result")
	public Object getResult() {
		return result;
	}

}
