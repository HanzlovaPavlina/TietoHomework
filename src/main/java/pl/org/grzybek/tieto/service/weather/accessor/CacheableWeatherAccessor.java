package pl.org.grzybek.tieto.service.weather.accessor;

import org.springframework.cache.annotation.Cacheable;

import pl.org.grzybek.tieto.service.weather.Weather;
import pl.org.grzybek.tieto.service.weather.accessor.WeatherAccessor;

/**
 * This is a decorator providing cache (weatherAccessorCache) capabilities over
 * the WeatherAccessor
 * 
 * @author zgrzybek
 * 
 */
public class CacheableWeatherAccessor implements WeatherAccessor {

	private WeatherAccessor accessor;

	public CacheableWeatherAccessor(WeatherAccessor accessor) {
		this.accessor = accessor;
	}

	@Cacheable(value = "weatherAccessorCache", key = "#cityName", unless = "#result == null")
	@Override
	public Weather getWeatherForCity(String cityName) {
		return accessor.getWeatherForCity(cityName);
	}
}