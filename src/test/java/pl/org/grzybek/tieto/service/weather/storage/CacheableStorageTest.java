package pl.org.grzybek.tieto.service.weather.storage;

import static org.junit.Assert.assertNull;
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
import pl.org.grzybek.tieto.service.weather.accessor.WeatherAccessor;
import pl.org.grzybek.tieto.service.weather.storage.CacheableStorage;
import pl.org.grzybek.tieto.storage.KeyValueStorage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class CacheableStorageTest {

	private static final String PARIS = "Paris";
	@Resource(name = "cacheableStorage")
	KeyValueStorage<String, Weather> storage;

	@Test
	public void testStorage() {
		assertNull(storage.get(PARIS));
		Weather initial = new Weather();
		storage.saveOrUpdate(PARIS, initial);

		assertSame(initial, storage.get(PARIS));
		assertSame(initial, storage.get(PARIS));

		Weather second = new Weather();
		storage.saveOrUpdate(PARIS, second);
		assertSame(second, storage.get(PARIS));

		storage.saveOrUpdate(PARIS, null);
		assertNull(storage.get(PARIS));

	}

	@Configuration
	@EnableCaching
	@ComponentScan("pl.org.grzybek.tieto.service.weather.storage")
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
			cacheFactoryBean.setName("cacheableStorage");
			return cacheFactoryBean;
		}

		@Bean
		public KeyValueStorage<String, Weather> cacheableStorage() {
			WeatherAccessor mockAccessor = mock(WeatherAccessor.class);
			when(mockAccessor.getWeatherForCity(PARIS)).thenReturn(
					new Weather(), new Weather());
			return new CacheableStorage();
		}

	}

}