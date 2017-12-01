package us.raudi.pushraven.configs;

import java.util.Map;

import us.raudi.pushraven.Payload;

/**
 * Apple Push Notification Service specific options.
 * https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages#apnsconfig
 * @author Raudius
 *
 */
public class ApnsConfig extends Payload {
	/**
	 * HTTP request headers defined in Apple Push Notification Service. Refer to APNs request headers for supported headers, e.g. "apns-priority": "10".
	 * An object containing a list of "key": value pairs. Example: { "name": "wrench", "mass": "1.3kg", "count": "3" }.
	 * @param values
	 * @return
	 */
	public ApnsConfig headers(Map<String, String> values) {
		return (ApnsConfig) addAttributeMap("headers", values);
	}
	
	/**
	 * APNs payload as a JSON object, including both aps dictionary and custom payload. 
	 * If present, it overrides google.firebase.fcm.v1.Notification.title and google.firebase.fcm.v1.Notification.body
	 * 
	 * You must implement the payload yourself using the Payload class and the documentation from apple.
	 * us.raudi.pushraven.Payload.java
	 * https://developer.apple.com/library/content/documentation/NetworkingInternet/Conceptual/RemoteNotificationsPG/PayloadKeyReference.html#//apple_ref/doc/uid/TP40008194-CH17-SW1
	 * @return
	 */
	public ApnsConfig payload(Payload p) {
		return (ApnsConfig) addAttributePayload("payload", p);
	}
}
