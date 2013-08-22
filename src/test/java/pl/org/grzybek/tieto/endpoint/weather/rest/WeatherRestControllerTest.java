package pl.org.grzybek.tieto.endpoint.weather.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import pl.org.grzybek.tieto.service.weather.Weather;
import pl.org.grzybek.tieto.service.weather.WeatherService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class WeatherRestControllerTest {

	@Resource
	private WebApplicationContext ctx;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

	@Test
	public void testGetCity() throws Exception {
		MvcResult result = mockMvc
				.perform(
						get("/weather/{city}", "Vilnius").accept(
								MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(
						content().contentType("application/json;charset=UTF-8"))
				.andReturn();

		asserEquals(result, "singleCityJsonResponse.json");

	}

	@Test
	public void testGetForAllCities() throws Exception {
		MvcResult result = mockMvc
				.perform(get("/weather").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(
						content().contentType("application/json;charset=UTF-8"))
				.andReturn();

		asserEquals(result, "multipleCitiesJsonResponse.json");

	}

	private void asserEquals(MvcResult result, String fileName)
			throws IOException, UnsupportedEncodingException, JSONException {
		String expected = IOUtils.toString(new ClassPathResource(fileName)
				.getInputStream());
		String actual = result.getResponse().getContentAsString();
		JSONAssert.assertEquals(expected, actual, true);
	}

	@Configuration
	@EnableWebMvc
	public static class TestConfiguration {

		@Bean
		public WeatherRestController weatherRestController() {
			return new WeatherRestController();
		}

		@Bean
		public WeatherService weatherService() {
			return new WeatherService() {

				@Override
				public Weather getWeather(String cityName) {
					return new Weather("Vilnius", "20.9", "69%", "NNW",
							"Clear", "Calm", "Time");
				}

				@Override
				public List<Weather> getWeather() {
					return Arrays.asList(new Weather("Vilnius", "20.9", "69%",
							"NNW", "Clear", "Calm", "Time"), new Weather("Riga",
							"20.91", "691%", "NNW1", "Clear1", "Calm1", "Time1"));
				}

				@Override
				public List<Weather> getWeather(String... cityNames) {
					throw new IllegalStateException();
				}

				@Override
				public List<Weather> getWeather(List<String> cityNames) {
					throw new IllegalStateException();
				}
			};
		}

	}

}
