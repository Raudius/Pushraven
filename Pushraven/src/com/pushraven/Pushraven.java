package com.pushraven;
import java.io.DataOutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * This method allows for simple and modular Notification creation. Notifications can then be pushed to clients
 * over FCM using the push() method.
 * @author Raudius
 *
 */
public class Pushraven extends Notification {
	private final static String API_URL = "https://fcm.googleapis.com/fcm/send";
	private static String FIREBASE_SERVER_KEY;
	public static Notification notification;
	
	// static initialization
	static {
		notification = new Notification();
	}
	
	
	/**
	* Set the API Server Key.
	*/
	public static void setKey(String key){
		FIREBASE_SERVER_KEY = key;
	}
	
	
	/** 
	 * Set new Notification object
	 */
	public static void setNotification(Notification notification){
		Pushraven.notification = notification;
	}
	
	
	/**
	 * Messages sent to targets.
	 * This class interfaces with the FCM server by sending the Notification over HTTP-POST JSON.
	 * @return FcmResponse object containing HTTP response info.
	 */
	public static FcmResponse push(Notification n) {	
		if(FIREBASE_SERVER_KEY == null){
			System.err.println("No Server-Key has been defined for Pushraven.");
			return null;
		}
		
		HttpsURLConnection con = null;
		try{
			String url = API_URL;
			
			URL obj = new URL(url);
			con = (HttpsURLConnection) obj.openConnection();
	
			con.setRequestMethod("POST");
	
			// Set POST headers
			con.setRequestProperty("Authorization", "key="+FIREBASE_SERVER_KEY);
			con.setRequestProperty("Content-Type", "application/json");
	
			
			// Send POST body
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			
			wr.writeBytes( n.toJSON() );
			
			wr.flush();
			wr.close();
			
			con.getResponseCode();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return new FcmResponse(con);
	}


	/**
	 * Messages sent to targets.
	 * This class interfaces with the FCM server by sending the Notification over HTTP-POST JSON.
	 * @return FcmResponse object containing HTTP response info.
	 */
	public static FcmResponse push() {
		return push(notification);
	}
	
}