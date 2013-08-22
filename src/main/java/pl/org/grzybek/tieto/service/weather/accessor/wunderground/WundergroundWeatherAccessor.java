package pl.org.grzybek.tieto.service.weather.accessor.wunderground;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import pl.org.grzybek.tieto.city.CityProvider;
import pl.org.grzybek.tieto.service.weather.AccessorBasedWeatherService;
import pl.org.grzybek.tieto.service.weather.Weather;
import pl.org.grzybek.tieto.service.weather.accessor.WeatherAccessor;

/**
 * Accessor accessing wunderground xml rest service to retrieve weather details
 * 
 * @author zgrzybek
 * 
 */
public class WundergroundWeatherAccessor implements WeatherAccessor {

	private static final Logger LOGGER = Logger
			.getLogger(AccessorBasedWeatherService.class);

	@Resource
	private RestTemplate restTemplate;

	@Resource
	private CityProvider cityProvider;

	private String key;

	public WundergroundWeatherAccessor(String key) {
		this.key = key;
	}

	@Override
	public Weather getWeatherForCity(String cityName) {

		String cityId = cityProvider.getCityKey(cityName);
		if (cityId == null) {
			throw new IllegalStateException(cityName + " is not supported");
		}

		String url = String.format(
				"http://api.wunderground.com/api/%s/conditions/q/%s.xml", key,
				cityId);

		WundergroundResponse response = callForObject(url);
		if (response == null) {
			return null;
		}

		return convertAnwser(cityName, response.getCurrentObservation());
	}

	private Weather convertAnwser(String cityName,
			CurrentObservation currentObservation) {
		Weather weather = new Weather();
		weather.setCity(cityName);
		weather.setRelativeHumidity(currentObservation.getRelativeHumidity());
		weather.setTemperature(currentObservation.getTemperature());
		weather.setWeather(currentObservation.getWeather());
		weather.setWindDirection(currentObservation.getWindDirection());
		weather.setWindString(currentObservation.getWindString());
		weather.setObservationTime(currentObservation.getObservationTime());

		return weather;
	}

	private WundergroundResponse callForObject(String url) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Calling rest service: " + url);
		}
		WundergroundResponse response;
		try {
			response = restTemplate.getForObject(url,
					WundergroundResponse.class);
		} catch (RestClientException e) {
			LOGGER.error("Server didn't respond properly", e);
			return null;
		}

		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Retreived weather : " + response);
		}
		return response;
	}
}
