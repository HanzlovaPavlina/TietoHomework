package pl.org.grzybek.tieto.service.weather.accessor;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import pl.org.grzybek.tieto.service.weather.Weather;
import pl.org.grzybek.tieto.storage.KeyValueStorage;

@RunWith(MockitoJUnitRunner.class)
public class StorageBasedCachedWeatherAccessorTest {

	private static final String PARIS = "Paris";

	@Mock
	private WeatherAccessor mockAccessor;

	@Mock
	private KeyValueStorage<String, Weather> storage;

	@InjectMocks
	private WeatherAccessor accessor = new StorageBasedCachedWeatherAccessor(
			mockAccessor);

	@Test(expected = IllegalStateException.class)
	public void testNullAnwser() {
		when(mockAccessor.getWeatherForCity(PARIS)).thenReturn(null);
		accessor.getWeatherForCity(PARIS);
	}

	@Test
	public void testReturnPreviousValueOnNull() {
		Weather initial = new Weather();
		when(mockAccessor.getWeatherForCity(PARIS)).thenReturn(initial)
				.thenReturn(null).thenReturn(new Weather());

		// Make first call where we get the actual value
		assertSame(initial, accessor.getWeatherForCity(PARIS));
		// Make the second call, where we get null, but we retrieve it from
		// cache
		assertSame(initial, accessor.getWeatherForCity(PARIS));
		// Make third call where we get new object which is then saved and
		// retrieved
		assertNotSame(initial, accessor.getWeatherForCity(PARIS));
	}

	private Weather lastSaved;

	@Before
	public void setUp() {
		when(storage.get(PARIS)).thenReturn(lastSaved);
		when(
				storage.saveOrUpdate(Matchers.eq(PARIS),
						Matchers.any(Weather.class))).thenAnswer(
				new Answer<Weather>() {

					@Override
					public Weather answer(InvocationOnMock invocation)
							throws Throwable {
						lastSaved = (Weather) invocation.getArguments()[1];
						return lastSaved;
					}
				});
		when(storage.get(PARIS)).thenAnswer(new Answer<Weather>() {

			@Override
			public Weather answer(InvocationOnMock invocation) throws Throwable {
				return lastSaved;
			}
		});
		;
	}

}
