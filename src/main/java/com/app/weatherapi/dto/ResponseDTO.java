package com.app.weatherapi.dto;

public class ResponseDTO {

    private String date;
    private String name;
    private String description;
    private String temperature;
    private String sunrise;
    private String sunset;
    
	public ResponseDTO(String date, String name, String description, String temperature, String sunrise,
			String sunset) {
		super();
		this.date = date;
		this.name = name;
		this.description = description;
		this.temperature = temperature;
		this.sunrise = sunrise;
		this.sunset = sunset;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getSunrise() {
		return sunrise;
	}
	public void setSunrise(String sunrise) {
		this.sunrise = sunrise;
	}
	public String getSunset() {
		return sunset;
	}
	public void setSunset(String sunset) {
		this.sunset = sunset;
	}
    
}
