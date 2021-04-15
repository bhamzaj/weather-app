package com.probitacademy.weatherapp;

import com.probitacademy.weatherapp.net.WeatherDataProvider;
import com.probitacademy.weatherapp.net.WebRequest;
import com.probitacademy.weatherapp.ui.CurrentWeatherFrame;

public class App {
	public static void main(String[] args) {
		try {
			WeatherDataProvider dataProvider = new WebRequest();
			CurrentWeatherFrame frm = new CurrentWeatherFrame(dataProvider);
			frm.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}