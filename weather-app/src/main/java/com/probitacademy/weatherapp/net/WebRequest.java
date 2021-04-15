package com.probitacademy.weatherapp.net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import com.probitacademy.weatherapp.model.WeatherData;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WebRequest implements WeatherDataProvider {
	private final String API_KEY = "324c69522d0c4ae3b4a8b78ed1cbb04a";// vendosni APIKEY tuaj ketu
	private String baseUrl = "http://api.openweathermap.org/data/2.5/weather";

	public WeatherData getCurrentWeather(String qyteti) throws Exception {
		WeatherData currentW = null;
		String jsonPergjigja = invokeOWM(qyteti);
// OBjectMapper eshte klase në jackson
		ObjectMapper mapper = new ObjectMapper();
// metoda readValue(String,Class) e klases ObjectMapper
// në menyre automatike e nderlidh pergjigjen json me atributet
// e objektit nga klasa e caktuar
		currentW = mapper.readValue(jsonPergjigja, WeatherData.class);
		return currentW;
	}

	private String invokeOWM(String qyteti) throws Exception {
		String responseBody = null;
		try {
			qyteti = URLEncoder.encode(qyteti, "UTF-8");
//HttpGet - klase e Appache HTTP Components
			HttpGet httpget = new HttpGet(baseUrl + "/?q=" + qyteti + "&APPID=" + API_KEY + "&units=metric");
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			CloseableHttpResponse response = httpClient.execute(httpget);
			int code = response.getCode();
			if (code < 200 || code > 300) {
				throw new Exception("Deshtim : HTTP error code : " + code);
			}
			InputStream is = response.getEntity().getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			StringBuilder pergjigja = new StringBuilder();
			String output;
			while ((output = br.readLine()) != null) {
				pergjigja.append(output);
			}
			responseBody = pergjigja.toString();
			httpClient.close();
		} catch (Exception e) {
			throw e;
		}
		return responseBody;
	}
}