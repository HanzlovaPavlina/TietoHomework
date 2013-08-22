package pl.org.grzybek.tieto.service.weather.accessor.wunderground;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withNoContent;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.ResponseActions;
import org.springframework.web.client.RestTemplate;

import pl.org.grzybek.tieto.city.CityProvider;
import pl.org.grzybek.tieto.service.weather.Weather;
import pl.org.grzybek.tieto.service.weather.accessor.WeatherAccessor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:accessor-config.xml" })
public class WundergroundWeatherAccessorTest {

	private MockRestServiceServer mockServer;

	@Resource
	private RestTemplate restTemplate;

	@Resource
	private CityProvider cityProvider;

	@Resource
	private WeatherAccessor accessor;

	@Before
	public void setup() {
		when(cityProvider.getCityKey("Vilnius")).thenReturn("VilniusKey");
		this.mockServer = MockRestServiceServer.createServer(this.restTemplate);

	}

	@Test
	public void testGet() throws Exception {

		mockServer().andRespond(
				withSuccess(new ClassPathResource("WundergroundResponse.xml"),
						MediaType.APPLICATION_XML));

		Weather weather = accessor.getWeatherForCity("Vilnius");
		assertEquals("18", weather.getTemperature());
		assertEquals("64%", weather.getRelativeHumidity());
		assertEquals("SSE", weather.getWindDirection());
		assertEquals("Clear", weather.getWeather());
		assertEquals("From the SSE at 8 MPH", weather.getWindString());
		assertEquals("Vilnius", weather.getCity());
		assertEquals("Last Updated on August 18, 11:20 PM EEST",
				weather.getObservationTime());

		this.mockServer.verify();
	}

	@Test(expected = IllegalStateException.class)
	public void testNotSupportedCity() throws Exception {

		when(cityProvider.getCityKey("Vilnius")).thenReturn(null);

		accessor.getWeatherForCity("Vilnius");
	}

	private ResponseActions mockServer() {
		return this.mockServer
				.expect(requestTo("http://api.wunderground.com/api/someKey/conditions/q/VilniusKey.xml"))
				.andExpect(method(HttpMethod.GET));
	}

	@Test
	public void noContent() throws Exception {

		mockServer().andRespond(withNoContent());

		Weather weather = accessor.getWeatherForCity("Vilnius");
		assertNull(weather);

		this.mockServer.verify();
	}

	@Test
	public void serverError() throws Exception {

		mockServer().andRespond(withServerError());

		Weather weather = accessor.getWeatherForCity("Vilnius");
		assertNull(weather);

		this.mockServer.verify();
	}

}