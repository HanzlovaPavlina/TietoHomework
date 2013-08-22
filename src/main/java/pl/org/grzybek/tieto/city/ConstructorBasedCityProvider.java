package pl.org.grzybek.tieto.city;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Implementation of CityProvider where user can submit map of supported cities
 * and its keys through the constructor
 * 
 * @author zgrzybek
 * 
 */
public class ConstructorBasedCityProvider implements CityProvider {

	private Map<String, String> cities;

	/**
	 * 
	 * @param supportedCities
	 *            map of supported cities and its keys
	 */
	public ConstructorBasedCityProvider(
			Map<String, String> supportedCities) {
		this.cities = Collections.unmodifiableMap(new HashMap<String, String>(
				supportedCities));
	}

	@Override
	public boolean isCitySupported(String cityName) {
		return cities.containsKey(cityName);
	}

	@Override
	public String getCityKey(String cityName) {
		return cities.get(cityName);
	}

	@Override
	public Set<String> getSupportedCities() {
		return cities.keySet();
	}

}
