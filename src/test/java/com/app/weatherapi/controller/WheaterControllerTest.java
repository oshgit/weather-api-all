package com.app.weatherapi.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;

import com.app.weatherapi.dto.ExceptionDTO;
import com.app.weatherapi.dto.ResponseDTO;
import com.app.weatherapi.dto.WeatherDTO;
import com.app.weatherapi.service.WeatherService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class WheaterControllerTest{
	
	@InjectMocks
	public WheaterController wheaterController;
	@Mock
	public WeatherService weatherService;
	
	public String cityId = "2643743";
	public String bodyTest = "{\"name\":\"London\",\"weather\":[{\"description\":\"light rain\"}],\"main\":{\"temp\":286.28},\"sys\":{\"sunrise\":1557461825,\"sunset\":1557517010}}";
	public String cityExpected = "London";
	
	@Test
	public void testFindWheaterByCity() throws Exception{
		ResponseEntity<WeatherDTO> weatherResponse = new ResponseEntity<WeatherDTO>(new ObjectMapper().readValue(bodyTest, WeatherDTO.class),HttpStatus.OK); 
		Mockito.when(weatherService.doRequestClient(cityId)).thenReturn(weatherResponse);
		Mockito.when(weatherService.buildResponse(weatherResponse)).thenReturn(new ResponseDTO("2019-05-10","London","light rain","13.13 °C, 55.63 °F","4:17:05 AM","7:36:50 PM"));
		ResponseEntity<Object> response = wheaterController.findWheaterByCity(cityId);		
		assertNotNull(response.getBody());
		assertEquals(ResponseDTO.class,response.getBody().getClass());
		assertEquals(cityExpected, ((ResponseDTO) response.getBody()).getName());
	}
	
	@Test
	public void testFindWheaterByCityErrorCase() throws Exception{
		Mockito.when(weatherService.doRequestClient(cityId)).thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
		ResponseEntity<Object> response = wheaterController.findWheaterByCity(cityId);
		assertNotNull(response.getBody());
		assertEquals(ExceptionDTO.class,response.getBody().getClass());
	}


}
