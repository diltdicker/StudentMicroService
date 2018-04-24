package edu.dickerson.dillon;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

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
			webUrl = new URL(url);
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
}
