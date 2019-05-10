package com.app.weatherapi.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.weatherapi.client.WeatherClient;
import com.app.weatherapi.dto.ResponseDTO;
import com.app.weatherapi.dto.WeatherDTO;
import com.app.weatherapi.service.inter.WeatherServiceInterface;
import com.app.weatherapi.utils.Constants;
import com.app.weatherapi.utils.ResponseBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WeatherService extends Constants implements WeatherServiceInterface{
	
	private static final Logger logger = LogManager.getLogger(WeatherService.class);
	
	@Override
	public ResponseEntity<WeatherDTO> doRequestClient(String cityId){
		try {
	        ResponseEntity<WeatherDTO> weatherResponse = WeatherClient.getInstance().executeRequest(cityId, pathToApi, patternReplace);	        
	        logInfo(messageLogResponseWeather, weatherResponse.getBody());
	        return weatherResponse;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
		
	}

	@Override
	public ResponseDTO buildResponse(ResponseEntity<WeatherDTO> weatherResponse){
		try {
	        WeatherDTO weather = weatherResponse.getBody();
	        ResponseDTO response = new ResponseBuilder().setSunrise(parseTimeUnix(weather.getSys().getSunrise())).setSunset(parseTimeUnix(weather.getSys().getSunset()))
	        	.setName(weather.getName()).setDate(dateFormat).setDescription(weather.getWeather(),noDescAvailable)
	        		.setTemperature(weather.getMain().getTemp(),decimalFormat, celciusFormat, farenheithFormat).build();
		    logInfo(messageLogResponseToView,response);
		    return response;
		}catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}
	
	public void logInfo(String message, Object object) {
		try {
        	logger.info(message + " " + new ObjectMapper().writeValueAsString(object));
		} catch (Exception e) {
			logger.error(messageLogError + e.getMessage());
		}
	}

}
