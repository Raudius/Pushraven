package com.pushraven;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.net.ssl.HttpsURLConnection;

public class FcmResponse {
	HttpsURLConnection connection;
	
	
	public FcmResponse(HttpsURLConnection con){
		connection = con;
	}
	
	
	
	public String toString(){
		return String.format("Response: %d\nMessage: '%s'", getResponseCode(), getResponseMessage());
	}
	
	public int getResponseCode(){
		try {
			return connection.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public String getResponseMessage(){
		InputStream in = connection.getErrorStream();
		
		if(in == null)
			return "";
		
		BufferedReader r = new BufferedReader(new InputStreamReader(in));

		StringBuilder total = new StringBuilder();

		String line = null;

		try {
			while ((line = r.readLine()) != null) {
			   total.append(line);
			}

			r.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return total.toString();
	}
}
