package edu.dickerson.dillon;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class LabLogic {
	
	public static String getRequest(String url, List<String> params, List<String> values) {
		String line = null;
		HttpURLConnection conn;
		URL webUrl;
		InputStream iStream;
		BufferedReader rd;
		if (params.size() > 0 && values.size() > 0) {
			url += "?" + params.get(0) + "=" + values.get(0);
			if (params.size() > 1 && values.size() > 1) {
				url += "&" + params.get(1) + "=" + values.get(1);
			}
		}
		try {
			webUrl = new URL("http://" + url);
			conn = (HttpURLConnection)webUrl.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			iStream = conn.getInputStream();
			rd = new BufferedReader(new InputStreamReader(iStream));
			line = rd.readLine();
			rd.close();
			conn.disconnect();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return line;
	}
	
	public static List<String> genJSONList(String json) {
		System.out.println("json: " + json);
		List<String> list = new ArrayList<String>();
		JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON(json);
		for (int i = 0; i < jsonArray.size(); i++) {
			list.add(jsonArray.getJSONObject(i).getString("subject"));
		}
		return list;
	}
	
	public static String genJSONString(String json, String key) {
		String str = null;
		JSONObject jsonObj = (JSONObject) JSONSerializer.toJSON(json);
		str = jsonObj.getString(key);
		return str;
	}
	
	public static double getnJSONDouble(String json, String key) {
		double dbl = 0;
		JSONObject jsonObj = (JSONObject) JSONSerializer.toJSON(json);
		dbl = jsonObj.getDouble(key);
		return dbl;
	}
}
