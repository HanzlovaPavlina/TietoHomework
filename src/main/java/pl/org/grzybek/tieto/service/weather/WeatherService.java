package pl.org.grzybek.tieto.service.weather;

import java.util.List;

/**
 * Class implementing this interface acts as WeatherService - provides access to
 * weather information
 * 
 * @author zgrzybek
 * 
 */
public interface WeatherService {

	/**
	 * Retrieve weather for specific city name
	 * 
	 * @param cityName
	 * @return weather
	 */
	public Weather getWeather(String cityName);

	/**
	 * Retrieve all available weather informations
	 * 
	 * @return not null weather list
	 */
	public List<Weather> getWeather();

	/**
	 * Retrieve weather information for specified city names
	 * 
	 * @param cityNames
	 * @return not null weather list
	 */
	public List<Weather> getWeather(String... cityNames);
	
	/**
	 * Retrieve weather information for specified city names
	 * 
	 * @param cityNames
	 * @return not null weather list
	 */
	public List<Weather> getWeather(List<String> cityNames);
}
