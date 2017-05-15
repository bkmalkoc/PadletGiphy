package com.burak;

import com.burak.model.TrendResponse;
import com.google.gson.Gson;

public class Img {

	private String URL;
	private String Width;
	private String Height;
	private String Size;

	private TrendResponse trendResponse;

	private String GetTrends() {
		Giphy giphy = new Giphy();
		String response = giphy.GetTrending();
		return response != null ? response : null;
	}

	private String[] Trends() {

		String trendingResults = GetTrends();
		String[] trendingImages = null;

		Gson gson = new Gson();
		try {
			trendResponse = gson.fromJson(trendingResults, TrendResponse.class);
			trendingImages = new String[trendResponse.getData().size()];

			for (int i = 0; i < trendResponse.getData().size(); i++) {
				URL = trendResponse.getData().get(i).getUrl();
				trendingImages[i] = URL;
			}
		} catch (Exception e) {
			System.out.println("Null trends");
		}
		return trendingImages;
	}

	public String[] TopTenTrending() {
		String[] topTen = new String[10];
		String[] trends = Trends();
		if (trends == null) {
			return null;
		}

		for (int i = 0; i < 10; i++) {
			topTen[i] = trends[i];
		}
		return topTen;
	}
}
