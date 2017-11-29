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
	HttpsURLConnection connection;

	public FcmResponse(HttpsURLConnection con) {
		connection = con;
	}

	/**
	 * Get connection response code.
	 *
	 * @return response code of the connection. (-1 if it fails to read from connection object).
	 */
	public int getResponseCode() {
		try {
			return connection.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return -1;
	}

	/**
	 * Get connection error response message.
	 *
	 * @return error response message of the connection. (Empty string if it fails to read from connection object).
	 */
	public String getErrorMessage() {
		InputStream in = connection.getErrorStream();

		return readAllFromInputStream(in);
	}

	/**
	 * Get connection success response message.
	 *
	 * @return success response message of the connection. (Empty string if it fails to read from connection object).
	 */
	public String getSuccessResponseMessage() {
		InputStream in = null;
		try {
			in = connection.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return readAllFromInputStream(in);

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

	public String toString() {
		return String.format("Response Code: %d\nSuccess Message: '%s'\nError Message: '%s'", getResponseCode(), getSuccessResponseMessage(), getErrorMessage());
	}
}
