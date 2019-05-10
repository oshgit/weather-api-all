package com.app.weatherapi.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {
	
	@Value("${service.constants.pathToApi}")
	public String pathToApi;
	@Value("${service.constants.dateFormat}")
	public String dateFormat;
	@Value("${service.constants.decimalFormat}")
	public String decimalFormat;
	@Value("${service.constants.noDescAvailable}")
	public String noDescAvailable;
	@Value("${service.constants.celcius}")
	public String celciusFormat;
	@Value("${service.constants.farenheith}")
	public String farenheithFormat;
	@Value("${service.constants.patternReplace}")
	public String patternReplace;
	@Value("${service.constants.messageLogResponseWeather}")
	public String messageLogResponseWeather;
	@Value("${service.constants.messageLogResponseToView}")
	public String messageLogResponseToView;
	@Value("${service.constants.messageLogError}")
	public String messageLogError;

}
