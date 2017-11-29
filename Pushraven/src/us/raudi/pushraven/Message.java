package us.raudi.pushraven;

import java.util.Map;

import us.raudi.pushraven.Target.TargetType;
import us.raudi.pushraven.configs.AndroidConfig;
import us.raudi.pushraven.configs.ApnsConfig;
import us.raudi.pushraven.configs.WebpushConfig;

public class Message extends Payload {
	public Message name(String name) {
		return (Message) addAttribute("name", name);
	}
	
	public Message data(Map<String, String> data) {
		return (Message) addAttributeMap("data", data);
	}
	
	public Message notification(Notification not) {
		return (Message) addAttributePayload("notification", not);
	}
	
	public Message android(AndroidConfig config) {
		return (Message) addAttributePayload("android", config);
	}

	public Message webpush(WebpushConfig config) {
		return (Message) addAttributePayload("webpush", config);
	}
	
	public Message apns(ApnsConfig config) {
		return (Message) addAttributePayload("apns", config);
	}
	
	public Message target(Target target) {
		setTarget(target);
		return this;
	}
	
	private void setTarget(Target target) {
		removeAttribute("token");
		removeAttribute("topic");
		removeAttribute("condition");
		
		addAttribute(target.getType(), target.getTarget());
	}

	public Message token(String tok) {
		return target(new Target(TargetType.TOKEN, tok));
	}
	
	public Message topic(String top) {
		return target(new Target(TargetType.TOPIC, top));
	}
	
	public Message condition(String cond) {
		return target(new Target(TargetType.CONDITION, cond));
	}
}

