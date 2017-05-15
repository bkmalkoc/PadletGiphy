package com.burak;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Giphy {
	public static final String API_KEY = "dc6zaTOxFJmzC";
	public static final String BASE_URL = "http://api.giphy.com/";
	public static final String TRENDING_PATH = "v1/gifs/trending?";

	private static String SignUrl(String url) {
		return url + "api_key=" + API_KEY;
	}

	private static String GetTrendingUrl() {
		return SignUrl(BASE_URL + TRENDING_PATH);
	}

	public String GetTrending() {
		String trendingUrl = GetTrendingUrl();
		String response = Connect(trendingUrl);
		return response != null || response != "" ? response : null;
	}

	public String Connect(String url) {
		URL urlBuild = null;
		HttpURLConnection urlConnection = null;
		BufferedReader bufferedReader = null;
		String line = null;
		InputStream inputStream = null;
		StringBuffer response = new StringBuffer();
		int responseCode = 0;

		try {
			urlBuild = new URL(url);
			urlConnection = (HttpURLConnection) urlBuild.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setRequestProperty("Content-Type", "application/json");
			urlConnection.connect();

			inputStream = urlConnection.getInputStream();
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			responseCode = urlConnection.getResponseCode();
			if (responseCode == 200) {
				while ((line = bufferedReader.readLine()) != null) {
					response.append(line);
				}
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response.toString();
	}
}