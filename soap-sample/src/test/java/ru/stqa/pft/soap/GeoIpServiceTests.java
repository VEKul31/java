package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeoIpServiceTests {

	@Test
	public void testMyIp() {
		String ipLocation20 = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("5.18.145.36");
		Assert.assertTrue(ipLocation20.contains("RU"));
	}

	@Test
	public void testInvalidIp() {
		String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("5.18.145.xx");
		Assert.assertFalse(ipLocation.contains("RU"));
	}

}
