package pl.org.grzybek.tieto.service.weather.accessor;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheFactoryBean;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.org.grzybek.tieto.service.weather.Weather;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class CacheableWeatherAccessorTest {

	@Resource(name = "cacheableWeatherAccessor")
	WeatherAccessor accessor;

	@Test
	public void testCache() {
		// make sure entries are cached accros calls
		Weather first = accessor.getWeatherForCity("Paris");
		Weather second = accessor.getWeatherForCity("Paris");
		assertSame(first, second);
	}

	@Configuration
	@EnableCaching
	@ComponentScan("pl.org.grzybek.tieto.service.weather.accessor")
	public static class TestConfiguration {

		@Bean
		public SimpleCacheManager cacheManager() {
			SimpleCacheManager cacheManager = new SimpleCacheManager();
			List<Cache> caches = new ArrayList<Cache>();
			caches.add(cacheBean().getObject());
			cacheManager.setCaches(caches);
			return cacheManager;
		}

		@Bean
		public ConcurrentMapCacheFactoryBean cacheBean() {
			ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
			cacheFactoryBean.setName("weatherAccessorCache");
			return cacheFactoryBean;
		}

		@Bean
		public CacheableWeatherAccessor cacheableWeatherAccessor() {
			WeatherAccessor mockAccessor = mock(WeatherAccessor.class);
			when(mockAccessor.getWeatherForCity("Paris")).thenReturn(
					new Weather(), new Weather());
			return new CacheableWeatherAccessor(mockAccessor);
		}

	}

}
