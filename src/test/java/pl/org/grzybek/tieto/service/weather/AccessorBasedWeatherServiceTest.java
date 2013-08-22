package pl.org.grzybek.tieto.service.weather;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.org.grzybek.tieto.city.CityProvider;
import pl.org.grzybek.tieto.service.weather.accessor.WeatherAccessor;

@RunWith(MockitoJUnitRunner.class)
public class AccessorBasedWeatherServiceTest {

	private static final String LONDON = "London";

	private static final String PARIS = "Paris";

	@InjectMocks
	private WeatherService service = new AccessorBasedWeatherService();

	@Mock
	private WeatherAccessor weatherAccessor;

	@Mock
	private CityProvider cityProvider;

	@Mock
	private Weather ParisWeather;
	@Mock
	private Weather LondonWeather;

	@Before
	public void setUp() {
		when(weatherAccessor.getWeatherForCity(PARIS)).thenReturn(ParisWeather);
		when(weatherAccessor.getWeatherForCity(LONDON)).thenReturn(
				LondonWeather);

		when(cityProvider.getSupportedCities()).thenReturn(
				new HashSet<String>(Arrays.asList(PARIS, LONDON)));

	}

	@Test
	public void testGetWeatherForCityName() throws Exception {
		assertEquals(ParisWeather, service.getWeather(PARIS));
		assertEquals(LondonWeather, service.getWeather(LONDON));

	}

	@Test
	public void testGetWeatherForListOfCities() throws Exception {
		List<Weather> answer = service.getWeather(LONDON, PARIS);
		assertNotNull(answer);

		assertArrayEquals(new Weather[] { LondonWeather, ParisWeather },
				answer.toArray(new Weather[] {}));
	}

	@Test
	public void testGetWeatherForListOfCitiesWithNotNullAnwser()
			throws Exception {
		List<Weather> answer = service.getWeather(new String[] {});
		assertNotNull(answer);

		assertArrayEquals(new Weather[] { ParisWeather, LondonWeather },
				answer.toArray(new Weather[] {}));
	}

	@Test
	public void testGetWeather() throws Exception {
		List<Weather> answer = service.getWeather();
		assertArrayEquals(new Weather[] { ParisWeather, LondonWeather },
				answer.toArray(new Weather[] {}));
	}

}
