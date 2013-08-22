package pl.org.grzybek.tieto.service.weather.accessor;

import javax.annotation.Resource;

import pl.org.grzybek.tieto.service.weather.Weather;
import pl.org.grzybek.tieto.storage.KeyValueStorage;

/**
 * WeatherAccessor decorator providing possibility to retrieve previously stored
 * value if value provided by accessor is null
 * 
 * @author zgrzybek
 * 
 */
public class StorageBasedCachedWeatherAccessor implements WeatherAccessor {

	@Resource
	private KeyValueStorage<String, Weather> storage;
	private WeatherAccessor accessor;

	public StorageBasedCachedWeatherAccessor(WeatherAccessor accessor) {
		this.accessor = accessor;
	}

	/**
	 * Retrieves previously cached value if accessor returns null
	 * 
	 * @return Weather not null weather
	 * @throws IllegalStateException
	 *             if weather can not be retrieved both from accessor and cache
	 */
	public Weather getWeatherForCity(String cityName) {
		Weather weather = accessor.getWeatherForCity(cityName);

		if (weather != null) {
			weather = storage.saveOrUpdate(cityName, weather);
			return weather;
		}
		weather = storage.get(cityName);

		if (weather == null) {
			throw new IllegalStateException(
					"Can not retrieve weather from accessor");
		}
		return weather;
	}
}
