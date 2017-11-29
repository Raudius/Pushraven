package us.raudi.pushraven;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONObject;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.Arrays;

/**
 * This class is used to statically send Messages to FCM
 *
 * @author Raudius
 */
public class Pushraven {
	private final static String API_URL = "https://fcm.googleapis.com/v1/projects/";
	public static File ACCOUNT_FILE;
	private static String PROJECT_ID;
	public static boolean validate_only = false;

	
	/**
	 * Defines the account authenticator file
	 * @param file Json file downloaded from FirebaseConsole >> Settings >> Service Accounts
	 */
	public static void setAccountFile(File file) {
		ACCOUNT_FILE = file;
	}
	
	/**
	 * Define the ID for the project. 
	 * Can be found in the settings of the Firebase Console
	 * @param id
	 */
	public static void setProjectId(String id) {
		PROJECT_ID = id;
	}
	
	
	/**
	 * Flag for testing the request without actually delivering the message.
	 * @param validate_only If set to true the message wont be delivered.
	 */
	public static void setValidateOnly(boolean validate_only) {
		Pushraven.validate_only = validate_only;
	}


	/**
	 * Send Message 'm' to targets.
	 * This class interfaces with the FCM server as per the HTTP v1 REST API
	 *
	 * @param m Defines the Message object to be pushed to FCM.
	 * @return FcmResponse object containing HTTP response info.
	 */
	@SuppressWarnings("unchecked")
	public static FcmResponse push(Message m) {
		// check ProjectID and Account-File have been given and that File exists
		if(!checkFileAndId()) return null;
		
		HttpsURLConnection con = null;
		try {
			String url = API_URL+PROJECT_ID+"/messages:send";

			URL fcm = new URL(url);
			con = (HttpsURLConnection) fcm.openConnection();

			// Set POST headers for authorization and content-type
			con.setRequestMethod("POST");
			con.setRequestProperty("Authorization", "Bearer " + getAccessToken());
			con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));
			
			// create request object (https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages/send)
			JSONObject obj = new JSONObject();
			obj.put("message", m.toJson());
			obj.put("validate_only", validate_only);
			
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

	/*
	 * Performs check prior to sending the message. 
	 * If returns false the Message wont be sent
	 */
	private static boolean checkFileAndId() {
		if (PROJECT_ID == null) {
			System.err.println("Error: No Project ID has been defined for Pushraven.");
			return false;
		}
		if(ACCOUNT_FILE == null) {
			System.err.println("Error: No Account File has been defined for Pushraven.");
			return false;
		}
		else if(!ACCOUNT_FILE.exists()) {
			System.err.println("Error: Could not find the given Account File.");
			return false;
		}
		
		return true;
	}


	private static String getAccessToken() throws IOException {
		String[] SCOPES = {"https://www.googleapis.com/auth/firebase.messaging"};

		GoogleCredential googleCredential = GoogleCredential
				.fromStream(new FileInputStream(ACCOUNT_FILE))
				.createScoped(Arrays.asList(SCOPES));
		googleCredential.refreshToken();
		return googleCredential.getAccessToken();
	}
}