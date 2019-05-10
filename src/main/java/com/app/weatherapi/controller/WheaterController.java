package com.app.weatherapi.controller;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.weatherapi.controller.inter.WheaterControllerInterface;
import com.app.weatherapi.dto.ExceptionDTO;
import com.app.weatherapi.dto.WeatherDTO;
import com.app.weatherapi.service.inter.WeatherServiceInterface;

@RestController
@RequestMapping("/weather")
public class WheaterController implements WheaterControllerInterface{
	
	@Autowired
	public WeatherServiceInterface weatherService;
	@Value("${service.constants.errorAtServer}")
	public String errorAtServer;
	
	@GetMapping(value = "/{cityId}",produces = "application/json")
	public ResponseEntity<Object> findWheaterByCity(@PathVariable("cityId") String cityId){
		try {
			ResponseEntity<WeatherDTO> weatherResponse = weatherService.doRequestClient(cityId);
			return new ResponseEntity<>(weatherService.buildResponse(weatherResponse), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<>(new ExceptionDTO(1,errorAtServer + " " +  ex.getMessage(),ExceptionUtils.getStackTrace(ex)), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

}