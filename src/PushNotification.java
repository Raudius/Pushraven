import java.io.DataOutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
/**
 * Deprecated: Use Pushraven class for sending notifications
 * @author Raudius
 *
 */
public class PushNotification {
	private final static String API_URL = "https://fcm.googleapis.com/fcm/send";
	private final static String FIREBASE_SERVER_KEY = "AIzaSyBF6mk_UWnRZpKmTXTCAIE-SKgTdZ_53ck";
	
	
	static void send(Notification notification) throws Exception {
		String url = API_URL;
		
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		con.setRequestMethod("POST");

		//Set POST headers
		con.setRequestProperty("Authorization", "key="+FIREBASE_SERVER_KEY);
		con.setRequestProperty("Content-Type", "application/json");

		
		// Send POST body
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(notification.toJSON());
		wr.flush();
		wr.close();
		
		
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + notification);
		System.out.println("Response Code : " + responseCode);
	}
}
