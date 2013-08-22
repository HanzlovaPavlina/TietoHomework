package pl.org.grzybek.tieto.endpoint.weather.rest;

import org.codehaus.jackson.annotate.JsonProperty;

import pl.org.grzybek.tieto.service.weather.Weather;

/**
 * POJO representing answer from the REST service
 * 
 * @author zgrzybek
 * 
 */
public class RestWeather {

	private String location;

	private double tempC;

	private String relativeHumidity;

	private String windDir;

	private String weather;

	private String windString;

	private String observationTime;

	public RestWeather(Weather weather) {
		this.location = weather.getCity();
		this.tempC = Double.parseDouble(weather.getTemperature());
		this.relativeHumidity = weather.getRelativeHumidity();
		this.windDir = weather.getWindDirection();
		this.weather = weather.getWeather();
		this.windString = weather.getWindString();
		this.observationTime = weather.getObservationTime();
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@JsonProperty(value = "location")
	public String getLocation() {
		return location;
	}

	public void setTempC(double tempC) {
		this.tempC = tempC;
	}

	@JsonProperty(value = "temp_c")
	public double getTempC() {
		return tempC;
	}

	public void setRelativeHumidity(String relativeHumidity) {
		this.relativeHumidity = relativeHumidity;
	}

	@JsonProperty(value = "relative_humidity")
	public String getRelativeHumidity() {
		return relativeHumidity;
	}

	public void setWindDir(String windDir) {
		this.windDir = windDir;
	}

	@JsonProperty(value = "wind_dir")
	public String getWindDir() {
		return windDir;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	@JsonProperty(value = "weather")
	public String getWeather() {
		return weather;
	}

	public void setWindString(String windString) {
		this.windString = windString;
	}

	@JsonProperty(value = "wind_string")
	public String getWindString() {
		return windString;
	}

	@JsonProperty(value = "observation_time")
	public String getObservationTime() {
		return observationTime;
	}

	public void setObservationTime(String observationTime) {
		this.observationTime = observationTime;
	}

}
