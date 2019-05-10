package com.app.weatherapi.client;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.app.weatherapi.dto.WeatherDTO;

public class WeatherClient {
	
	private static WeatherClient weatherClient = new WeatherClient();
	
	private WeatherClient() {
	}
	
	public static WeatherClient getInstance() {
		return weatherClient;
	}
	
	public ResponseEntity<WeatherDTO> executeRequest(String cityId, String pathToApi, String patternReplace){
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders(); 
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON)); 
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(pathToApi.replace(patternReplace, cityId), HttpMethod.GET, entity, WeatherDTO.class);
	}
	
}
