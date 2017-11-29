package us.raudi.pushraven.configs;

import java.util.Map;

import us.raudi.pushraven.Payload;
import us.raudi.pushraven.notifications.WebpushNotification;

public class WebpushConfig extends Payload {
	public WebpushConfig headers(Map<String, String> values) {
		return (WebpushConfig) addAttributeMap("headers", values);
	}

	public WebpushConfig data(Map<String, String> data) {
		return (WebpushConfig) addAttributeMap("data", data);
	}
	
	public WebpushConfig notification(WebpushNotification not) {
		return (WebpushConfig) addAttributePayload("notification", not);
	}
}
