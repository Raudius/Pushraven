package us.raudi.pushraven;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Modular notificatiopn creation class.
 * @author Raudius
 *
 */
public class Pushraven {
	private final static String API_URL = "https://fcm.googleapis.com/fcm/send";
	private static String FIREBASE_SERVER_KEY;
	public static Notification notification;
	
	// static initialization
	static {
		notification = new Notification();
	}
	
	
	/**
	* Set the API Server Key.
	* @param key Firebase Server Key (NOT the Web API Key!!!)
	*/
	public static void setKey(String key){
		FIREBASE_SERVER_KEY = key;
	}
	
	
	/** 
	 * Set new Notification object
	 * @param notification set the notification object for Pushraven
	 */
	public static void setNotification(Notification notification){
		Pushraven.notification = notification;
	}
	
	
	/**
	 * Messages sent to targets.
	 * This class interfaces with the FCM server by sending the Notification over HTTP-POST JSON.
	 * @param n Defines the notification object to be pushed to FCM.
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
			con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
	
			
			// Send POST body
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));
			writer.write(n.toJSON());
			
			// close output stream
			writer.close();
			wr.close();

			// send request
			con.connect();
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