package us.raudi.pushraven;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

/**
 * Modular notification creation class.
 *
 * @author Raudius, equintana
 */
public class Pushraven {
	
	private final static String DEFAULT_API_URL = "https://fcm.googleapis.com/fcm/send";
	private final static String DEFAULT_REQUEST_METHOD = "POST";
	private final static String FIREBASE_SERVER_DEFAULT_AUTH_SYSTEM = "Authorization";
	private final static String FIREBASE_SERVER_DEFAULT_TOKEN_SYSTEM = "key=";
	private final static String FIREBASE_SERVER_DEFAULT_CONTENT_TYPE_KEY = "Content-Type";
	private final static String FIREBASE_SERVER_DEFAULT_CONTENT_TYPE_VALUE = "application/json;charset=UTF-8";
	private final static String FIREBASE_SERVER_DEFAULT_CODIFICATION = "UTF-8";
	private final static Boolean FIREBASE_SERVER_DEFAULT_ENCAPSULATE_REQUEST_IN_MESSAGE = false;
	
	private static String API_URL;
	private static String REQUEST_METHOD;
	private static String FIREBASE_SERVER_AUTH_SYSTEM;
	private static String FIREBASE_SERVER_TOKEN_SYSTEM;
	private static String FIREBASE_SERVER_KEY;
	private static String FIREBASE_SERVER_CONTENT_TYPE_KEY;
	private static String FIREBASE_SERVER_CONTENT_TYPE_VALUE;
	private static String FIREBASE_SERVER_CODIFICATION;
	private static Boolean FIREBASE_SERVER_ENCAPSULATE_REQUEST_IN_MESSAGE;
	
	public static Notification notification;

	// static initialization
	static {
		notification = new Notification();
	}

	/**
	 * Set the API URL
	 *
	 * @param apiUrl The URL of the API
	 */
	public static void setApiUrl(String apiUrl) {
		API_URL = apiUrl;
	}
	
	/**
	 * Set the Request method
	 *
	 * @param requestMethod The Request method
	 */
	public static void setRequestMethod(String requestMethod) {
		REQUEST_METHOD = requestMethod;
	}
	
	/**
	 * Set the API Server Auth System.
	 *
	 * @param key Firebase Server Auth System
	 */
	public static void setAuthSystem(String authSystem) {
		FIREBASE_SERVER_AUTH_SYSTEM = authSystem;
	}
	
	/**
	 * Set the API Server Token System.
	 *
	 * @param key Firebase Server Token System (can be "key=" or "Bearer " or whatever)
	 */
	public static void setTokenSystem(String tokenSystem) {
		FIREBASE_SERVER_TOKEN_SYSTEM = tokenSystem;
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
	 * Set the left part of the "Content type".
	 *
	 * @param contentTypeKey Content type key
	 */
	public static void setContentTypeKey(String contentTypeKey) {
		FIREBASE_SERVER_CONTENT_TYPE_KEY = contentTypeKey;
	}
	
	/**
	 * Set the right part of the "Content type".
	 *
	 * @param contentTypeValue Content type value
	 */
	public static void setContentTypeValue(String contentTypeValue) {
		FIREBASE_SERVER_CONTENT_TYPE_VALUE = contentTypeValue;
	}
	
	/**
	 * Set the codification for the BufferedWriter.
	 *
	 * @param codification BufferedWriter codification
	 */
	public static void setCodification(String codification) {
		FIREBASE_SERVER_CODIFICATION = codification;
	}
	
	/**
	 * Set the condition to encapsulate the JSON object for the BufferedWriter inside a "message" field.
	 *
	 * @param encapsulateInMessage Boolean
	 */
	public static void setEncapsulateRequestInMessage(Boolean encapsulateRequestInMessage) {
		FIREBASE_SERVER_ENCAPSULATE_REQUEST_IN_MESSAGE = encapsulateRequestInMessage;
	}
	
	/**
	 * Set new Notification object
	 *
	 * @param notification set the notification object for Pushraven
	 */
	public static void setNotification(Notification notification) {
		Pushraven.notification = notification;
	}

	/**
	 * Messages sent to targets.
	 * This class interfaces with the FCM server by sending the Notification over HTTP-POST JSON.
	 *
	 * @param n Defines the notification object to be pushed to FCM.
	 * @return FcmResponse object containing HTTP response info.
	 */
	public static FcmResponse push(Notification n) {
		if (FIREBASE_SERVER_KEY == null) {
			System.err.println("No Server-Key has been defined for Pushraven.");
			return null;
		}

		HttpsURLConnection con = null;
		try {
			if (API_URL == null){
				API_URL = DEFAULT_API_URL;
			}
			String url = API_URL;			
			URL obj = new URL(url);
			con = (HttpsURLConnection) obj.openConnection();

			if (REQUEST_METHOD == null){
				REQUEST_METHOD = DEFAULT_REQUEST_METHOD;
			}
			con.setRequestMethod(REQUEST_METHOD);
						
			setRequestMethodHeaders(con);									

			// Send POST body
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			if (FIREBASE_SERVER_CODIFICATION == null){
				FIREBASE_SERVER_CODIFICATION = FIREBASE_SERVER_DEFAULT_CODIFICATION;
			}
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, FIREBASE_SERVER_CODIFICATION));
			if (FIREBASE_SERVER_ENCAPSULATE_REQUEST_IN_MESSAGE == null){
				FIREBASE_SERVER_ENCAPSULATE_REQUEST_IN_MESSAGE = FIREBASE_SERVER_DEFAULT_ENCAPSULATE_REQUEST_IN_MESSAGE;
			}
			String jsonToWrite = n.toJSON(FIREBASE_SERVER_ENCAPSULATE_REQUEST_IN_MESSAGE);			
			writer.write(jsonToWrite);

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
	
	/**
	 * Set request method headers
	 */
	private static void setRequestMethodHeaders(HttpsURLConnection con){
		if (FIREBASE_SERVER_AUTH_SYSTEM == null){
			FIREBASE_SERVER_AUTH_SYSTEM = FIREBASE_SERVER_DEFAULT_AUTH_SYSTEM;
		}
		if (FIREBASE_SERVER_TOKEN_SYSTEM == null){
			FIREBASE_SERVER_TOKEN_SYSTEM = FIREBASE_SERVER_DEFAULT_TOKEN_SYSTEM;
		}		
		if (FIREBASE_SERVER_CONTENT_TYPE_KEY == null){
			FIREBASE_SERVER_CONTENT_TYPE_KEY = FIREBASE_SERVER_DEFAULT_CONTENT_TYPE_KEY;
		}
		if (FIREBASE_SERVER_CONTENT_TYPE_VALUE == null){
			FIREBASE_SERVER_CONTENT_TYPE_VALUE = FIREBASE_SERVER_DEFAULT_CONTENT_TYPE_VALUE;
		}		
		con.setRequestProperty(FIREBASE_SERVER_AUTH_SYSTEM, FIREBASE_SERVER_TOKEN_SYSTEM + FIREBASE_SERVER_KEY);	
		con.setRequestProperty(FIREBASE_SERVER_CONTENT_TYPE_KEY, FIREBASE_SERVER_CONTENT_TYPE_VALUE);			
	}
	
	/**
	 * Messages sent to targets.
	 * This class interfaces with the FCM server by sending the Notification over HTTP-POST JSON.
	 *
	 * @return FcmResponse object containing HTTP response info.
	 */
	public static FcmResponse push() {
		return push(notification);
	}
}
