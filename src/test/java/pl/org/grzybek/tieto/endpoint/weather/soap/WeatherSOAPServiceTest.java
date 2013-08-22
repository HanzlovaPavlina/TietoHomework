package pl.org.grzybek.tieto.endpoint.weather.soap;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.xml.transform.Source;

import static org.springframework.ws.test.server.RequestCreators.*;
import static org.springframework.ws.test.server.ResponseMatchers.*;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

import pl.org.grzybek.tieto.service.weather.Weather;
import pl.org.grzybek.tieto.service.weather.WeatherService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-ws.xml")
@WebAppConfiguration
public class WeatherSOAPServiceTest {

	private static final String RQUEST_END = "</mod:WeatherDetailsRequest>";

	private static final String REQUEST_BEGINNING = "<mod:WeatherDetailsRequest xmlns:mod=\"http://pl/org/grzybek/tieto/endpoint/weather/soap/model\">";

	@Autowired
	private ApplicationContext applicationContext;

	private MockWebServiceClient mockClient;

	@Before
	public void createClient() {
		mockClient = MockWebServiceClient.createClient(applicationContext);
	}

	@Test
	public void testAllCities() throws Exception {

		Source requestPayload = new StringSource(REQUEST_BEGINNING
		// No city names provided in the request
				+ RQUEST_END);

		Source responsePayload = getExpectedResponse("soapAllCititesResponse.xml");

		testSoapCall(requestPayload, responsePayload);

	}

	@Test
	public void tesSpecificCities() throws Exception {
		Source requestPayload = new StringSource(REQUEST_BEGINNING
				+ "<mod:cityName>Paris</mod:cityName>"
				+ "<mod:cityName>London</mod:cityName>" + RQUEST_END);

		Source responsePayload = getExpectedResponse("soapSpecificCititesResponse.xml");	
		testSoapCall(requestPayload, responsePayload);

	}

	/**
	 * Read file from disk
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	private Source getExpectedResponse(String fileName) throws IOException {
		Source responsePayload = new StringSource(
				IOUtils.toString(new ClassPathResource(fileName)
						.getInputStream()));
		return responsePayload;
	}

	/**
	 * Make actual SOAP call
	 * 
	 * @param requestPayload
	 * @param responsePayload
	 * @throws IOException
	 */
	private void testSoapCall(Source requestPayload, Source responsePayload)
			throws IOException {
		Resource schema = new ClassPathResource(
				"WeatherDetailsServiceOperations.xsd");
		mockClient.sendRequest(withPayload(requestPayload))
				.andExpect(payload(responsePayload))
				.andExpect(validPayload(schema));
	}

	@Configuration
	@EnableWebMvc
	public static class TestConfiguration {

		@Bean
		public WeatherService weatherService() {
			return new WeatherService() {

				@Override
				public Weather getWeather(String cityName) {
					throw new IllegalStateException();
				}

				@Override
				public List<Weather> getWeather() {
					return Arrays.asList(new Weather("Vilnius", "20.9", "69%",
							"NNW", "Clear", "Calm", "Time"), new Weather("Praha",
							"20.92", "692%", "NNW2", "Clear2", "Calm2", "Time2"));
				}

				@Override
				public List<Weather> getWeather(String... cityNames) {
					throw new IllegalStateException();
				}

				@Override
				public List<Weather> getWeather(List<String> cityNames) {
					return Arrays.asList(new Weather("Paris", "20.9", "69%",
							"NNW", "Clear", "Calm", "Time"), new Weather("London",
							"20.91", "691%", "NNW1", "Clear1", "Calm1", "Time1"));
				}
			};
		}
	}
}