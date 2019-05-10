package com.app.weatherapi.client;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import com.app.weatherapi.app.BaseTest;
import com.app.weatherapi.dto.WeatherDTO;

@SpringBootTest
public class WeatherClientTest extends BaseTest{
	
	@Override
	@Test
	public void testOk() {
		stubResponseOk();
		ResponseEntity<WeatherDTO> weatherResponse = WeatherClient.getInstance().executeRequest(cityIdTest, pathToApi, patternReplace);
		assertEquals(HttpStatus.OK.value(), weatherResponse.getStatusCodeValue());
		assertNotNull(weatherResponse.getBody());
		assertEquals(cityExpectedPass,weatherResponse.getBody().getName());			
	}

	@Override
	@Test(expected = HttpClientErrorException.NotFound.class)
	public void testNotFoundException() {
		stubFor(get(urlEqualTo(path + "error"))
			    .willReturn(aResponse()
			                        .withStatus(HttpStatus.NOT_FOUND.value())
			                        .withHeader("Content-Type", "application/json")
			                        .withBody(notFound)));
		WeatherClient.getInstance().executeRequest(cityIdTest, pathToApi + "error", patternReplace);
	}
	
	@Override
	@Test(expected = HttpServerErrorException.InternalServerError.class)
	public void testFailInternalServerError() {
		stubFor(get(urlEqualTo(path))
			    .willReturn(aResponse()
			                        .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
			                        .withHeader("Content-Type", "application/json")
			                        .withBody(internalServerError)));
		WeatherClient.getInstance().executeRequest(cityIdTest, pathToApi, patternReplace);
			
	}

}
