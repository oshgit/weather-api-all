package com.app.weatherapi.utils;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.weatherapi.dto.InnerWeatherDTO;
import com.app.weatherapi.dto.ResponseDTO;

@RunWith(SpringRunner.class)
@EnableConfigurationProperties
@ActiveProfiles("test")
@SpringBootTest
public class ResponseBuilderTest {

	@Value("${service.constants.dateFormat}")
	public String dateFormat;
	@Value("${service.constants.decimalFormat}")
	public String decimalFormat;
	@Value("${service.constants.celcius}")
	public String celciusFormat;
	@Value("${service.constants.farenheith}")
	public String farenheithFormat;
	@Value("${service.constants.builder.name}")
	public String name;
	@Value("${service.constants.builder.temperature}")
	public double temperature;
	@Value("${service.constants.builder.sunrise}")
	public String sunrise;
	@Value("${service.constants.builder.sunset}")
	public String sunset;
	@Value("${service.constants.noDescAvailable}")
	public String noDescAvailable;
	@Value("${service.constants.builder.weather.description}")
	public String description;

	@Test
	public void testValidResponse() {
		ResponseDTO response = new ResponseBuilder().setSunrise(sunrise).setSunset(sunset)
	        	.setName(name).setDate(dateFormat).setDescription(new InnerWeatherDTO[] {new InnerWeatherDTO(description)},noDescAvailable)
	        		.setTemperature(temperature,decimalFormat, celciusFormat, farenheithFormat).build();
		assertNotNull(response);
		assertEquals(name, response.getName());
		assertEquals(description, response.getDescription());
	}
	
	@Test
	public void testNullValues() {
		ResponseDTO response = new ResponseBuilder().setSunrise(sunrise).setSunset(sunset)
	        	.setName(name).setDate(null).setDescription(new InnerWeatherDTO[] {new InnerWeatherDTO(description)},null)
	        		.setTemperature(temperature,decimalFormat, celciusFormat,null).build();
		assertNotNull(response);
		assertNull(null,response.getDate());
		assertNull(null,response.getDescription());
		assertNull(null,response.getTemperature());
	}



	
}
