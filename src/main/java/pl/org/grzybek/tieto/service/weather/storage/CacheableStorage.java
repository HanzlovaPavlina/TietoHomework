package pl.org.grzybek.tieto.service.weather.storage;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import pl.org.grzybek.tieto.service.weather.Weather;
import pl.org.grzybek.tieto.storage.KeyValueStorage;

/**
 * Class representing key value storage based on Cacheable
 * 
 * @author zgrzybek
 * 
 */
public class CacheableStorage implements KeyValueStorage<String, Weather> {

	@CachePut(value = "cacheableStorage", key = "#cityName")
	@Override
	public Weather saveOrUpdate(String cityName, Weather weather) {
		return weather;
	}

	@Cacheable(value = "cacheableStorage", key = "#cityName", unless = "#result == null")
	@Override
	public Weather get(String cityName) {
		return null;
	}
}