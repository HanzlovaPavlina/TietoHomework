package pl.org.grzybek.tieto.service.weather.accessor.wunderground;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * POJO representing wunderground's answer
 * 
 * @author zgrzybek
 * 
 */
@XmlRootElement(name = "current_observation")
class CurrentObservation {

	private String temperature;

	private String relativeHumidity;

	private String windDirection;

	private String weather;

	private String windString;

	private String observationTime;

	@Override
	public String toString() {
		return "CurrentObservation [temperature=" + temperature
				+ ", relativeHumidity=" + relativeHumidity + ", windDirection="
				+ windDirection + ", weather=" + weather + ", windString="
				+ windString + ", observationTime=" + observationTime + "]";
	}

	@XmlElement(name = "temp_c")
	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	@XmlElement(name = "relative_humidity")
	public String getRelativeHumidity() {
		return relativeHumidity;
	}

	public void setRelativeHumidity(String relativeHumidity) {
		this.relativeHumidity = relativeHumidity;
	}

	@XmlElement(name = "wind_dir")
	public String getWindDirection() {
		return windDirection;
	}

	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}

	@XmlElement(name = "weather")
	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getWindString() {
		return windString;
	}
	
	@XmlElement(name = "wind_string")
	public void setWindString(String windString) {
		this.windString = windString;
	}

	@XmlElement(name = "observation_time")
	public String getObservationTime() {
		return observationTime;
	}

	public void setObservationTime(String observationTime) {
		this.observationTime = observationTime;
	}
}
