package us.raudi.pushraven.configs;

import java.util.Map;

import us.raudi.pushraven.Payload;
import us.raudi.pushraven.notifications.WebpushNotification;

/**
 * Webpush protocol options.
 * https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages#webpushconfig
 * @author Raudius
 *
 */
public class WebpushConfig extends Payload {
	/**
	 * HTTP headers defined in webpush protocol. Refer to Webpush protocol for supported headers, e.g. "TTL": "15".
	 * An object containing a list of "key": value pairs. Example: { "name": "wrench", "mass": "1.3kg", "count": "3" }.
	 * @param values (see: https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages)
	 * @return Returns the modified payload
	 */
	public WebpushConfig headers(Map<String, String> values) {
		return (WebpushConfig) addAttributeMap("headers", values);
	}

	/**
	 * Arbitrary key/value payload. If present, it will override google.firebase.fcm.v1.Message.data
	 * An object containing a list of "key": value pairs. Example: { "name": "wrench", "mass": "1.3kg", "count": "3" }.
	 * @param data (see: https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages)
	 * @return Returns the modified payload
	 */
	public WebpushConfig data(Map<String, String> data) {
		return (WebpushConfig) addAttributeMap("data", data);
	}
	
	
	/**
	 * A web notification to send.
	 * https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages#WebpushNotification
	 * @param not (see: https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages)
	 * @return Returns the modified payload
	 */
	public WebpushConfig notification(WebpushNotification not) {
		return (WebpushConfig) addAttributePayload("notification", not);
	}
}
