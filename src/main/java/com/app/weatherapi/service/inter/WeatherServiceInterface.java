package com.app.weatherapi.service.inter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.ResponseEntity;

import com.app.weatherapi.dto.ResponseDTO;
import com.app.weatherapi.dto.WeatherDTO;

public interface WeatherServiceInterface{
	
	
	public ResponseEntity<WeatherDTO> doRequestClient(String cityId);
	
	public ResponseDTO buildResponse(ResponseEntity<WeatherDTO> weatherResponse);
	
	default String parseTimeUnix(long unixTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("h:mm:ss a");
		sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
		return sdf.format(new Date(unixTime*1000L));
	}

}
