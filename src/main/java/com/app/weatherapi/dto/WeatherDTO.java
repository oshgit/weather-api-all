package com.app.weatherapi.dto;

public class WeatherDTO {
	
	private String name;
	private InnerWeatherDTO[] weather;
	private MainDTO main;
	private SysDTO sys;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public InnerWeatherDTO[] getWeather() {
		return weather;
	}
	public void setWeather(InnerWeatherDTO[] weather) {
		this.weather = weather;
	}
	public MainDTO getMain() {
		return main;
	}
	public void setMain(MainDTO main) {
		this.main = main;
	}
	public SysDTO getSys() {
		return sys;
	}
	public void setSys(SysDTO sys) {
		this.sys = sys;
	}

}
