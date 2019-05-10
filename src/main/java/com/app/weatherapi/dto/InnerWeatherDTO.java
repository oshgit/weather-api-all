package com.app.weatherapi.dto;

public class InnerWeatherDTO {
	
	private String description;

	public InnerWeatherDTO() {
		super();
	}

	public InnerWeatherDTO(String description) {
		super();
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
