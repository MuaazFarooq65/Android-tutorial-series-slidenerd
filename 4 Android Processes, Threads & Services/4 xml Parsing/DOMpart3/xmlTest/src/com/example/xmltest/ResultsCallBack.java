package com.example.xmltest;

import java.util.ArrayList;
import java.util.HashMap;

public interface ResultsCallBack {
	public void onPreExecute();
	public void onPostExecute(ArrayList<HashMap<String, String>> results);
}