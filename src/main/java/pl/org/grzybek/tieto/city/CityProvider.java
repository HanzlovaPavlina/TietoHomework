package pl.org.grzybek.tieto.city;

import java.util.Set;

/**
 * Interface providing access to the information about supported cities
 * @author zgrzybek
 *
 */
public interface CityProvider {
	
	/**
	 * Checks whether city is supported
	 * @param cityName
	 * @return true if city is supported, false otherwise
	 */
	public boolean isCitySupported(String cityName);
	
	/**
	 * Retrieves key for the specific city name
	 * @param cityName
	 * @return key value or null if city is not supported
	 */
	public String getCityKey(String cityName);

	/**
	 * Retrieves names of all supported cities
	 * @return return not null set of city names
	 */
	public Set<String> getSupportedCities();

}
