package pl.org.grzybek.tieto.city;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class ConstructorBasedCityProviderTest {

	private static final String CIESZYN_KEY = "Ckey";
	private static final String VILNIUS_KEY = "Vkey";
	private static final String CIESZYN = "Cieszyn";
	private static final String VILNIUS = "Vilnius";
	private CityProvider emptyProvider;
	private CityProvider fullProvider;

	@Before
	public void setUp() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(VILNIUS, VILNIUS_KEY);
		map.put(CIESZYN, CIESZYN_KEY);
		fullProvider = new ConstructorBasedCityProvider(map);

		emptyProvider = new ConstructorBasedCityProvider(
				new LinkedHashMap<String, String>());
	}

	@Test
	public void testIsCitySupported() {
		assertTrue(fullProvider.isCitySupported(VILNIUS));
		assertTrue(fullProvider.isCitySupported(CIESZYN));
		assertFalse(fullProvider.isCitySupported("Paris"));
		assertFalse(emptyProvider.isCitySupported(CIESZYN));
	}

	@Test
	public void testGetCityKey() {
		assertEquals(VILNIUS_KEY, fullProvider.getCityKey(VILNIUS));
		assertEquals(CIESZYN_KEY, fullProvider.getCityKey(CIESZYN));
		assertNull(fullProvider.getCityKey("Paris"));
	}

	@Test
	public void testGetSupportedCities() {
		assertArrayEquals(new String[] { VILNIUS, CIESZYN }, fullProvider
				.getSupportedCities().toArray(new String[] {}));
		assertEquals(0, emptyProvider.getSupportedCities().size());
	}

}
