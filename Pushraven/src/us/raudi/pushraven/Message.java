package us.raudi.pushraven;

import java.util.Map;

import us.raudi.pushraven.Target;
import us.raudi.pushraven.Target.TargetType;
import us.raudi.pushraven.configs.AndroidConfig;
import us.raudi.pushraven.configs.ApnsConfig;
import us.raudi.pushraven.configs.WebpushConfig;

/**
 * Message to send by Firebase Cloud Messaging Service.
 * https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages#resource-message
 * @author Raudius
 *
 */
public class Message extends Payload {
	/**
	 * Output Only. The identifier of the message sent, in the format of: "projects/{PROJECT_ID}/messages/{message_id}."
	 * @param name
	 * @return
	 */
	public Message name(String name) {
		return (Message) addAttribute("name", name);
	}
	
	
	/**
	 * Input only. Arbitrary key/value payload.
	 * An object containing a list of "key": value pairs. Example: { "name": "wrench", "mass": "1.3kg", "count": "3" }.
	 * @param data
	 * @return
	 */
	public Message data(Map<String, String> data) {
		return (Message) addAttributeMap("data", data);
	}
	
	
	/**
	 * Input only. Basic notification template to use across all platforms.
	 * https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages#notification
	 * @param not
	 * @return
	 */
	public Message notification(Notification not) {
		return (Message) addAttributePayload("notification", not);
	}
	
	
	/**
	 * Input only. Android specific options for messages sent through FCM connection server.
	 * https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages#androidconfig
	 * @param config
	 * @return
	 */
	public Message android(AndroidConfig config) {
		return (Message) addAttributePayload("android", config);
	}

	
	/**
	 * Input only. Webpush protocol options.
	 * https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages#webpushconfig
	 * @param config
	 * @return
	 */
	public Message webpush(WebpushConfig config) {
		return (Message) addAttributePayload("webpush", config);
	}
	
	
	/**
	 * Input only. Apple Push Notification Service specific options.
	 * https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages#apnsconfig
	 * @param config
	 * @return
	 */
	public Message apns(ApnsConfig config) {
		return (Message) addAttributePayload("apns", config);
	}
	
	/**
	 * Target to send a message to. target can be only one of the following: TOKEN, TOPIC, CONDITION.
	 * Setting a new target will remove all previously set targets.
	 * @param target
	 * @return
	 */
	public Message target(Target target) {
		setTarget(target);
		return this;
	}
	
	/**
	 * Registration token to send a message to.
	 * Note: setting this target will remove all previous targets (token, topic an condition).
	 * @param tok
	 * @return
	 */
	public Message token(String tok) {
		return target(new Target(TargetType.TOKEN, tok));
	}
	
	/**
	 * Topic name to send a message to, e.g. "weather". Note: "/topics/" prefix should not be provided.
	 * Note: setting this target will remove all previous targets (token, topic an condition).
	 * @param top
	 * @return
	 */
	public Message topic(String top) {
		return target(new Target(TargetType.TOPIC, top));
	}
	
	/**
	 * Condition to send a message to, e.g. "'foo' in topics &amp;&amp; 'bar' in topics".
	 * Note: setting this target will remove all previous targets (token, topic an condition).
	 * @param cond
	 * @return
	 */
	public Message condition(String cond) {
		return target(new Target(TargetType.CONDITION, cond));
	}
	

	/*
	 * Private implementation for setting a target.
	 * For public setters see: target(Target), token(String), topic(String), condition(String).
	 */
	private void setTarget(Target target) {
		removeAttribute("token");
		removeAttribute("topic");
		removeAttribute("condition");
		
		addAttribute(target.getType(), target.getTarget());
	}
}