package pl.org.grzybek.tieto.service.weather.accessor.wunderground;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * POJO representing wunderground's answer
 * 
 * @author zgrzybek
 * 
 */
@XmlRootElement(name = "response")
class WundergroundResponse {

	@Override
	public String toString() {
		return "WundergroundResponse [currentObservation=" + currentObservation
				+ "]";
	}

	private CurrentObservation currentObservation;

	@XmlElement(name = "current_observation")
	public CurrentObservation getCurrentObservation() {
		return currentObservation;
	}

	public void setCurrentObservation(CurrentObservation currentObservation) {
		this.currentObservation = currentObservation;
	}
}
