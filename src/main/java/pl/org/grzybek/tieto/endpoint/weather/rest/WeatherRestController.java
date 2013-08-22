package pl.org.grzybek.tieto.endpoint.weather.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.org.grzybek.tieto.service.weather.Weather;
import pl.org.grzybek.tieto.service.weather.WeatherService;

/**
 * Controller exposing REST service to access weather information
 * 
 * @author zgrzybek
 * 
 */
@Controller
@RequestMapping("/weather")
public class WeatherRestController {

	@Resource
	WeatherService weatherService;

	/**
	 * Retrieve weather information for one specific city
	 * 
	 * @param city
	 * @return
	 */
	@RequestMapping(value = "/{city}", method = RequestMethod.GET)
	public @ResponseBody
	WeatherRestResponse getWeather(@PathVariable String city) {

		Weather weather = weatherService.getWeather(city);
		return new WeatherRestResponse(new RestWeather(weather));
	}

	/**
	 * Retrieve weather information for all supported cities
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	WeatherRestResponse getWeather() {

		List<RestWeather> results = new ArrayList<RestWeather>();
		for (Weather weather : weatherService.getWeather()) {
			results.add(new RestWeather(weather));
		}

		return new WeatherRestResponse(results);
	}

}