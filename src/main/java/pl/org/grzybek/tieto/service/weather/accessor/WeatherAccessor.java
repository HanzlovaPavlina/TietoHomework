package pl.org.grzybek.tieto.service.weather.accessor;

import pl.org.grzybek.tieto.service.weather.Weather;

/**
 * Interface providing possibility to retrieve weather information 
 * @author zgrzybek
 *
 */
public interface WeatherAccessor {

	/**
	 * Retrieves weather information for specific city
	 * @param cityName
	 * @return Weather or null, if no weather is available for specified city
	 */
	public Weather getWeatherForCity(String cityName);

}
