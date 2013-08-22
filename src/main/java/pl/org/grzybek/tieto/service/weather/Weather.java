package pl.org.grzybek.tieto.service.weather;

/**
 * POJO representing weather informations
 * 
 * @author zgrzybek
 * 
 */
public class Weather {

	private String temperature;

	private String relativeHumidity;

	private String windDirection;

	private String weather;

	private String windString;

	private String city;

	private String observationTime;

	public Weather() {
	}

	public Weather(String city, String temperature, String relativeHumidity,
			String windDirection, String weather, String windString, String observationTime) {
		this.city = city;
		this.temperature = temperature;
		this.relativeHumidity = relativeHumidity;
		this.windDirection = windDirection;
		this.weather = weather;
		this.windString = windString;
		this.setObservationTime(observationTime);
	}

	@Override
	public String toString() {
		return "Weather [temperature=" + temperature + ", relativeHumidity="
				+ relativeHumidity + ", windDirection=" + windDirection
				+ ", weather=" + weather + ", windString=" + windString
				+ ", city=" + city + ", observationTime=" + observationTime + "]";
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setRelativeHumidity(String relativeHumidity) {
		this.relativeHumidity = relativeHumidity;
	}

	public String getRelativeHumidity() {
		return relativeHumidity;
	}

	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}

	public String getWindDirection() {
		return windDirection;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getWeather() {
		return weather;
	}

	public void setWindString(String windString) {
		this.windString = windString;
	}

	public String getWindString() {
		return windString;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getObservationTime() {
		return observationTime;
	}

	public void setObservationTime(String observationTime) {
		this.observationTime = observationTime;
	}

}
