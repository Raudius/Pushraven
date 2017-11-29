package us.raudi.pushraven.configs;

import java.util.Map;

import us.raudi.pushraven.Payload;

public class ApnsConfig extends Payload {
	public ApnsConfig headers(Map<String, String> values) {
		return (ApnsConfig) addAttributeMap("headers", values);
	}
	
	
	//TODO: Implement this thing: https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages#ApnsConfig
	public ApnsConfig payload(/*?????*/) {
		return this;
	}
}
