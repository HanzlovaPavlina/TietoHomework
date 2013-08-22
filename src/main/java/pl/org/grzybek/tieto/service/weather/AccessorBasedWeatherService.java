package pl.org.grzybek.tieto.service.weather;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import pl.org.grzybek.tieto.city.CityProvider;
import pl.org.grzybek.tieto.service.weather.accessor.WeatherAccessor;

/**
 * This is an implementation of WeatherService bases on accessor, which acts as
 * an entry point, providing weather information
 * 
 * @author zgrzybek
 * 
 */
public class AccessorBasedWeatherService implements WeatherService {

	@Resource
	private WeatherAccessor weatherAccessor;

	@Resource
	private CityProvider cityProvider;

	@Override
	public Weather getWeather(String cityName) {
		return weatherAccessor.getWeatherForCity(cityName);
	}

	@Override
	public List<Weather> getWeather() {
		List<Weather> weathers = new ArrayList<Weather>();
		for (String cityName : cityProvider.getSupportedCities()) {
			weathers.add(getWeather(cityName));
		}
		return weathers;
	}

	@Override
	public List<Weather> getWeather(String... cityNames) {
		if (cityNames.length == 0) {
			return getWeather();
		}
		List<Weather> list = new ArrayList<Weather>();
		for (String cityName : cityNames) {
			list.add(getWeather(cityName));
		}
		return list;
	}

	@Override
	public List<Weather> getWeather(List<String> cityNames) {
		return getWeather(cityNames.toArray(new String[] {}));
	}
}
