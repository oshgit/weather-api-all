package com.app.weatherapi.service;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import com.app.weatherapi.app.BaseTest;
import com.app.weatherapi.dto.ResponseDTO;
import com.app.weatherapi.dto.WeatherDTO;
import com.app.weatherapi.service.inter.WeatherServiceInterface;

@SpringBootTest(classes = { WeatherService.class})
public class WeatherServiceTest extends BaseTest{
	
	@Autowired
	public WeatherServiceInterface weatherService;
	
	@Test
	public void testOk() {
		stubResponseOk();
		ResponseEntity<WeatherDTO> weatherResponse = weatherService.doRequestClient(cityIdTest);
		assertEquals(HttpStatus.OK.value(), weatherResponse.getStatusCodeValue());
		assertNotNull(weatherResponse.getBody());
		assertEquals(cityExpectedPass,weatherResponse.getBody().getName());			
	}
	
	@Test(expected = HttpServerErrorException.InternalServerError.class)
	public void testFailInternalServerError(){
		stubFor(get(urlEqualTo(path))
			    .willReturn(aResponse()
			                        .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
			                        .withHeader("Content-Type", "application/json")
			                        .withBody(internalServerError)));
		weatherService.doRequestClient(cityIdTest);
	}
	
	@Test(expected = HttpClientErrorException.NotFound.class)
	public void testNotFoundException(){
		stubFor(get(urlEqualTo(path))
			    .willReturn(aResponse()
			                        .withStatus(HttpStatus.NOT_FOUND.value())
			                        .withHeader("Content-Type", "application/json")
			                        .withBody(notFound)));
		weatherService.doRequestClient(cityIdTest);
	}
	
	@Test
	public void buildResponse(){
		stubResponseOk();
		ResponseDTO responseDTO = weatherService.buildResponse(weatherService.doRequestClient(cityIdTest));
		assertNotNull(responseDTO);
		assertNotEquals(cityExpectedFail,responseDTO.getName());
	}
	
}
