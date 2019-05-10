package com.app.weatherapi.utils;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@EnableConfigurationProperties
@ActiveProfiles("test")
@SpringBootTest
public class UtilsTest {

	@Value("${service.constants.builder.temperature}")
	public double temperature;
	@Value("${service.constants.decimalFormat}")
	public String decimalFormat;
	@Value("${service.constants.utils.celciusExpected}")
	public String celciusExpected;
	@Value("${service.constants.utils.farenheithExpected}")
	public String farenheithExpected;
	
	Utils utils = Utils.getInstance();
	
	@Test
	public void testPass() {
		assertEquals(celciusExpected, utils.kelvinToCelcius(temperature, decimalFormat));
		assertEquals(farenheithExpected, utils.kelvinToFarenheith(temperature, decimalFormat));
	}
	
	@Test
	public void testNullValues() {
		assertNull(utils.kelvinToCelcius(temperature, null));
		assertNull(utils.kelvinToFarenheith(temperature, null));
	}

}
