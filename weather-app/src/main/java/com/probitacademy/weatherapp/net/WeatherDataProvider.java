package com.probitacademy.weatherapp.net;

import com.probitacademy.weatherapp.model.WeatherData;

public interface WeatherDataProvider {
	public WeatherData getCurrentWeather(String qyteti) throws Exception;
}