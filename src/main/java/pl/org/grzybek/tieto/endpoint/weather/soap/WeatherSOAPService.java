package pl.org.grzybek.tieto.endpoint.weather.soap;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import pl.org.grzybek.tieto.endpoint.weather.soap.model.WeatherDetails;
import pl.org.grzybek.tieto.endpoint.weather.soap.model.WeatherDetailsRequest;
import pl.org.grzybek.tieto.endpoint.weather.soap.model.WeatherDetailsResponse;
import pl.org.grzybek.tieto.service.weather.Weather;
import pl.org.grzybek.tieto.service.weather.WeatherService;

/**
 * This class is an endpoint exposing weather soap web service
 */
@Endpoint
public class WeatherSOAPService {
	private static final String TARGET_NAMESPACE = "http://pl/org/grzybek/tieto/endpoint/weather/soap/model";

	@Resource
	private WeatherService weatherService;

	/**
	 * Retrieve weather details for all specified cities, or for all supported
	 * cities if no city was specified
	 * 
	 * @param weatherNumber
	 *            the weather number
	 * @return the weather details
	 */
	@PayloadRoot(localPart = "WeatherDetailsRequest", namespace = TARGET_NAMESPACE)
	public @ResponsePayload
	WeatherDetailsResponse getWeatherDetails(
			@RequestPayload WeatherDetailsRequest request) {

		List<String> cityNames = request.getCityName();

		List<Weather> weathers;
		if (cityNames.size() == 0) {
			weathers = weatherService.getWeather();
		} else {
			weathers = weatherService.getWeather(cityNames);
		}

		return createResponse(weathers);
	}

	private WeatherDetailsResponse createResponse(List<Weather> weathers) {
		WeatherDetailsResponse response = new WeatherDetailsResponse();
		List<WeatherDetails> detailsList = response.getWeatherDetails();
		for (Weather weather : weathers) {
			WeatherDetails details = createWeatherDetails(weather);
			detailsList.add(details);
		}
		return response;
	}

	private WeatherDetails createWeatherDetails(Weather weather) {
		WeatherDetails details = new WeatherDetails();
		details.setCityName(weather.getCity());
		details.setRelativeHumidity(weather.getRelativeHumidity());
		details.setTemperature(weather.getTemperature());
		details.setWeather(weather.getWeather());
		details.setWindDescription(weather.getWindString());
		details.setWindDirection(weather.getWindDirection());
		details.setObservationTime(weather.getObservationTime());
		return details;
	}
}