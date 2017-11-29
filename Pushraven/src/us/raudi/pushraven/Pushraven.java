package us.raudi.pushraven;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONObject;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.Arrays;

/**
 * Modular notificatiopn creation class.
 *
 * @author Raudius
 */
public class Pushraven {
	private final static String API_URL = "https://fcm.googleapis.com/v1/projects/";
	private static String FIREBASE_SERVER_KEY;
	public static Message message;

	// static initialization
	static {
		message = new Message();
	}

	/**
	 * Set the API Server Key.
	 *
	 * @param key Firebase Server Key (NOT the Web API Key!!!)
	 */
	public static void setKey(String key) {
		FIREBASE_SERVER_KEY = key;
	}

	/**
	 * Set new Notification object
	 *
	 * @param notification set the notification object for Pushraven
	 */
	public static void setNotification(Message notification) {
		Pushraven.message = notification;
	}

	/**
	 * Send parameter Message to targets.
	 * This class interfaces with the FCM server by sending the Notification over HTTP-POST JSON.
	 *
	 * @param n Defines the notification object to be pushed to FCM.
	 * @return FcmResponse object containing HTTP response info.
	 */
	@SuppressWarnings("unchecked")
	public static FcmResponse push(Message n) {
		
		try {
			System.out.println("OAuth: "+ getAccessToken());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (FIREBASE_SERVER_KEY == null) {
			System.err.println("No Server-Key has been defined for Pushraven.");
			return null;
		}

		HttpsURLConnection con = null;
		try {
			String url = API_URL+"fcmtest-f57d4/messages:send";

			URL fcm = new URL(url);
			con = (HttpsURLConnection) fcm.openConnection();

			con.setRequestMethod("POST");

			// Set POST headers
			con.setRequestProperty("Authorization", "Bearer " + getAccessToken());
			con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

			// Send POST body
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));
			
			JSONObject obj = new JSONObject();
			obj.put("message", n.toJson());
			obj.put("validate_only", false);
			
			System.out.println(obj);
			
			writer.write(obj.toString());

			// close output stream
			writer.close();
			wr.close();

			// send request
			con.connect();
			con.getResponseCode();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new FcmResponse(con);
	}

	private static String getAccessToken() throws IOException {
		String[] SCOPES = {"https://www.googleapis.com/auth/firebase.messaging"};
		GoogleCredential googleCredential = GoogleCredential
				.fromStream(new FileInputStream("service_account.json"))
				.createScoped(Arrays.asList(SCOPES));
		googleCredential.refreshToken();
		return googleCredential.getAccessToken();
	}
	
	
	/**
	 * Send staticly defined Pushraven.Message to targets.
	 * This class interfaces with the FCM server by sending the Notification over HTTP-POST JSON.
	 *
	 * @return FcmResponse object containing HTTP response info.
	 */
	public static FcmResponse push() {
		return push(Pushraven.message);
	}
}