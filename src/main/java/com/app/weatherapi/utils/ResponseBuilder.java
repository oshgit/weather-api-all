package com.app.weatherapi.utils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import com.app.weatherapi.dto.InnerWeatherDTO;
import com.app.weatherapi.dto.ResponseDTO;

public class ResponseBuilder {
	
    private String date;
    private String name;
    private String description;
    private String temperatureBuild;
    private String sunrise;
    private String sunset;

	public ResponseBuilder setDate(String dateFormat) {
		if(dateFormat != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			this.date = sdf.format(new Date());
		}
		return this;
	}
	public ResponseBuilder setName(String name) {
		this.name = name;
		return this;
	}
	public ResponseBuilder setDescription(InnerWeatherDTO[] innerWeatherDTOs,String noDescAvailable) {
		if(innerWeatherDTOs != null && noDescAvailable != null) {
			Optional<String> optionalDescription = Arrays.asList(innerWeatherDTOs).stream().map(input -> input.getDescription()).reduce((st1,st2)->
		 		st1 + ", " + st2
			);
			this.description = optionalDescription.isPresent()?optionalDescription.get():noDescAvailable;
		} 
		return this;
	}
	public ResponseBuilder setTemperature(double temperatureInput,String format, String celciusFormat, String farenheithFormat) {
		if(temperatureInput != 0 && format != null && celciusFormat != null && farenheithFormat != null) {
			Utils utils = Utils.getInstance();
			this.temperatureBuild = new StringBuilder().append(utils.kelvinToCelcius(temperatureInput, format)).append(celciusFormat).append(utils.kelvinToFarenheith(temperatureInput, format)).append(farenheithFormat).toString();
		}
		return this;
	}
	public ResponseBuilder setSunrise(String sunrise) {
		this.sunrise = sunrise;
		return this;
	}
	public ResponseBuilder setSunset(String sunset) {
		this.sunset = sunset;
		return this;
	}
	public ResponseDTO build() {
		return new ResponseDTO(date, name, description, temperatureBuild, sunrise, sunset);
	}

}
