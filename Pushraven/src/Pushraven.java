import java.io.DataOutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Pushraven extends Notification {
	private final String API_URL = "https://fcm.googleapis.com/fcm/send";
	private String FIREBASE_SERVER_KEY;
	
	public Pushraven(String key){
		super();
		
		FIREBASE_SERVER_KEY = key;
	}
	
	
	public void push() {
		try{
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
			wr.writeBytes(this.toJSON());
			wr.flush();
			wr.close();
			
			
			int responseCode = con.getResponseCode();
			System.out.println("Response code : " + responseCode);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
