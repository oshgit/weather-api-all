package com.app.weatherapi.utils;

import java.text.DecimalFormat;

public class Utils {
	
	private Utils() {}
	
	private static Utils utils= new Utils();
	
	private static final double CONVERSION_FACTOR = 273.15;
	
	public static Utils getInstance() {
		return utils;
	}
	
	public String kelvinToCelcius(double kelvin,String format) {
		if(format != null) {
			return new DecimalFormat(format).format(kelvin - CONVERSION_FACTOR) + "";
		}else {
			return null;
		}
		
	}
	
	public String kelvinToFarenheith(double kelvin,String format) {
		if(format != null) {
			return new DecimalFormat(format).format((kelvin - CONVERSION_FACTOR) * 9/5 + 32) + "";
		}else {
			return null;
		}
	}

}
