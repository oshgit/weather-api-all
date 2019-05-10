package com.app.weatherapi.controller.inter;

import org.springframework.http.ResponseEntity;

public interface WheaterControllerInterface {

	public ResponseEntity<Object> findWheaterByCity(String cityId);
	
}
