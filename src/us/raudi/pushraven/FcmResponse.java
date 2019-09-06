package us.raudi.pushraven;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Holds response parameters from the FCM API request.
 *
 * @author Raudius
 */
public class FcmResponse {
	private int code = -1;
	private String message;
	HttpsURLConnection connection;

	public FcmResponse(HttpsURLConnection con) {
		readResponse(con);
	}

	/**
	 * Get connection response code.
	 *
	 * @return response code of the connection. (-1 if it fails to read from connection object).
	 */
	public int getResponseCode() {
		return code;
	}

	/**
	 * Get connection success/error response message.
	 *
	 * @return error response message of the connection. (Empty string if it fails to read from connection object).
	 */
	public String getMessage() {
		return message;
	}
	
	


	public String toString() {
		return String.format("Response Code: %d \nMessage: '%s'", getResponseCode(), getMessage());
	}
	
	
	


	private void readResponse(HttpsURLConnection con) {
		InputStream errorIs = con.getErrorStream();
		InputStream successIs = null;
		
		try {
			successIs = con.getInputStream();
		} catch (IOException e1) {
			// Connection failed
			// Error message will be read
		}
		
		try {
			code = con.getResponseCode();
			
			if(successIs != null) {
				message = readAllFromInputStream(successIs);
			}
			else {
				message = readAllFromInputStream(errorIs);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	private String readAllFromInputStream(InputStream in) {
		if (in == null)
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
	
	
	

	// TODO: Delete from next release
	
	@Deprecated
	/**
	 * Deprecated, please use getMessage() instead
	 * 
	 * @return always null
	 */
	public String getErrorMessage() {
		return null;
	}
	
	@Deprecated
	/**
	 * Deprecated, please use getMessage() instead
	 * 
	 * @return always null
	 */
	public String getSuccessResponseMessage() {
		return null;
	}
	

}
