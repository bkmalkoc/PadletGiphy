package com.burak;

import java.util.Arrays;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) {
		Img img = new Img();
		String[] response = img.TopTenTrending();
		System.out.println(Arrays.toString(response));
	}
}