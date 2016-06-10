package com.pushraven;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.*;

/**
 * This method implements the "Builder Pattern" in order to construct Notifications
 * These notifications can be converted into a JSON Object in order to interface with Firebase Cloud Messaging
 * @author Raudius - 09/06/2016
 *
 */

@SuppressWarnings("unchecked")
public class Notification {
	private Collection<String> multicast;
	private HashMap<String, Object> requestAttributes;
	private HashMap<String, Object> notificationAttributes;
	

	
	public Notification(){
		clear();
	}
	
	
	/**
	 * Convert this object into JSON.
	 * @return JSON object adhering to the FCM format.
	 */
	public String toJSON(){
		
		JSONObject obj = new JSONObject(); // Parent object


		// create and add every notification attribute into its own json objects
		JSONObject not = new JSONObject();
		not.putAll(notificationAttributes);
		
		// add notification object to parent
		obj.put("notification", not);

		// add request attributes to parent
		obj.putAll(requestAttributes);
		
		if(!multicast.isEmpty()){
			// create and add all targets to the JSON array
			JSONArray ids = new JSONArray();
			ids.addAll(multicast); 
		
			// add targets to parent
			obj.put("registration_ids", ids);
		}
		
		return obj.toString();
	}
	
	
	/**
	 * Clears both attributes and targets from the Notification
	 * @see clearTargets() and clearAttributes()
	 */
	public Notification clear(){
		clearTargets();
		clearAttributes();
		
		return this;
	}
	
	/**
	 * Clear all targets from the Notification
	 * @see clear()
	 */
	public Notification clearTargets(){
		multicast = new ArrayList<String>();
		return this;
	}
	/**
	 * Clear all attributes from the Notification
	 * @see clear()
	 */
	public Notification clearAttributes(){
		notificationAttributes = new HashMap<String, Object>();
		requestAttributes = new HashMap<String, Object>();
		return this;
	}
	
	
	// Notification attributes
	/**
	 * Add a construction parameter to your Notification. 
	 * @see https://firebase.google.com/docs/cloud-messaging/http-server-ref
	 */
	public Notification addNotificationAttribute(String key, Object value){
		notificationAttributes.put(key, value);
		return this;
	}
	
	/**
	 * Add a construction parameter to your Request. 
	 * @see https://firebase.google.com/docs/cloud-messaging/http-server-ref
	 */
	public Notification addRequestAttribute(String key, Object value){
		requestAttributes.put(key, value);
		return this;
	}
	
	
	
	/* REQUEST ATTRIBUTES */

	// Notification target(s)
	/**
	 * This parameter specifies a list of devices (registration tokens, or IDs) receiving a multicast message. It must contain at least 1 and at most 1000 registration tokens.
	 * 
	 * Use this parameter only for multicast messaging, not for single recipients. Multicast messages (sending to more than 1 registration tokens) are allowed using HTTP JSON format only.
	 */
	public Notification registration_ids(Collection<String> targets){
		this.multicast = targets;
		return this;
	}
	/**
	 * Adds a collection of devices to the list receiving a multicast message.
	 * @see registration_ids(Collection<String> targets)
	 */
	public Notification addAllMulticasts(Collection<String> targets){
		this.multicast.addAll(targets);
		return this;
	}
	/**
	 * Adds a single device to the list receiving a multicast message.
	 * @see registration_ids(Collection<String> targets)
	 */
	public Notification addMulticast(String target){
		this.multicast.add(target);
		return this;
	}

	/**
	 * This parameter specifies the recipient of a message.
	 * The value must be a registration token, notification key, or topic. Do not set this field when sending to multiple topics. 
	 * @see condition(String condition) & registration_ids(Collection<String> targets)
	 */
	public Notification to(String to){
		return addRequestAttribute("to", to);
	}
	/**
	 * This parameter specifies a logical expression of conditions that determine the message target.
	 * Supported condition: Topic, formatted as "'yourTopic' in topics". This value is case-insensitive.
	 * Supported operators: &&, ||. Maximum two operators per topic message supported.
	 */
	public Notification condition(String cond){
		return addRequestAttribute("condition", cond);
	}
	/**
	 * This parameter identifies a group of messages (e.g., with collapse_key: "Updates Available") that can be collapsed, so that only the last message gets sent when delivery can be resumed. 
	 * This is intended to avoid sending too many of the same messages when the device comes back online or becomes active (see delay_while_idle).
	 * 
	 * Note that there is no guarantee of the order in which messages get sent.
	 * Note: A maximum of 4 different collapse keys is allowed at any given time. This means a FCM connection server can simultaneously store 4 different send-to-sync messages per client app. 
	 * If you exceed this number, there is no guarantee which 4 collapse keys the FCM connection server will keep.
	 * 
	 */
	public Notification collapse_key(String key){
		return addRequestAttribute("collapse_key", key);
	}
	/**
	 * Sets the priority of the message. Valid values are "normal" and "high." On iOS, these correspond to APNs priorities 5 and 10.
	 * By default, messages are sent with normal priority. Normal priority optimizes the client app's battery consumption and should be used unless immediate delivery is required. 
	 * For messages with normal priority, the app may receive the message with unspecified delay.
	 * When a message is sent with high priority, it is sent immediately, and the app can wake a sleeping device and open a network connection to your server.
	 * @see https://firebase.google.com/docs/cloud-messaging/concept-options#setting-the-priority-of-a-message
	 */
	public Notification priority(Integer p){
		p = Math.min(p, 10);
		p = Math.max(p, 0);
		
		return addRequestAttribute("priority", p);
	}
	/**
	 * When this parameter is set to true, it indicates that the message should not be sent until the device becomes active.
	 * 
	 * The default value is 'false'
	 */
	public Notification delay_while_idle(Boolean b){
		return addRequestAttribute("delay_while_idle", b);
	}
	/**
	 * This parameter specifies how long (in seconds) the message should be kept in FCM storage if the device is offline. 
	 * The maximum time to live supported is 4 weeks, and the default value is 4 weeks.
	 * @see https://firebase.google.com/docs/cloud-messaging/concept-options#ttl
	 */
	public Notification time_to_live(Integer n){
		return addRequestAttribute("time_to_live", n);
	}
	/**
	 * This parameter specifies the package name of the application where the registration tokens must match in order to receive the message.
	 */
	public Notification restricted_package_name(String name){
		return addRequestAttribute("restricted_package_name", name);
	}
	/**
	 * This parameter, when set to true, allows developers to test a request without actually sending a message.
	 * 
	 * The default value is false.
	 */
	public Notification dry_run(Boolean b){
		return addRequestAttribute("dry_run", b);		
	}
	/**
	 * This parameter specifies the custom key-value pairs of the message's payload.
	 * For example, with data:{"score":"3x1"}:
	 * 
	 * On iOS, if the message is sent via APNS, it represents the custom data fields. If it is sent via FCM connection server, it would be represented as key value dictionary in AppDelegate application:didReceiveRemoteNotification:.
	 * 
	 * On Android, this would result in an intent extra named score with the string value 3x1.
	 * The key should not be a reserved word ("from" or any word starting with "google" or "gcm"). Do not use any of the words defined in this table (such as collapse_key).
	 * 
	 * Values in string types are recommended. You have to convert values in objects or other non-string data types (e.g., integers or booleans) to string.
	 */
	public Notification data(Map<String, Object> data){
		JSONObject obj = new JSONObject();
		obj.putAll(data);
		
		return addRequestAttribute("data", obj);
	}
	/**
	 * This parameter specifies the predefined, user-visible key-value pairs of the notification payload. See Notification payload support for detail.
	 *  
	 * @see https://firebase.google.com/docs/cloud-messaging/concept-options#notifications_and_data_messages
	 */
	public Notification notification(Map<String, Object> map){
		JSONObject obj = new JSONObject();
		obj.putAll(map);
		
		return addRequestAttribute("notification", obj);
	}
	
	  /* *********************** */
	 /* NOTIFICATION ATTRIBUTES */
	/* *********************** */
	
	/**
	 * Indicates notification title.
	 */
	public Notification title(String title){
		return addNotificationAttribute("title", title);
	}
	/**
	 * Indicates notification body text.
	 */
	public Notification body(String body){
		return addNotificationAttribute("text", body);
	}
	/**
	 * Indicates notification body text. 
	 * @see body(String body)
	 */
	public Notification text(String text){
		return body(text);
	}

	/**
	 * Indicates notification icon. Sets value to myicon for drawable resource myicon.
	 */
	public Notification icon(String ic){
		return addNotificationAttribute("icon", ic);
	}

	/**
	 * Indicates a sound to play when the device receives a notification. Supports default or the filename of a sound resource bundled in the app. Sound files must reside in /res/raw/.
	 */
	public Notification sound(String sound){
		return addNotificationAttribute("sound", sound);
	}

	/**
	 * Indicates whether each notification results in a new entry in the notification drawer on Android. 
	 * If not set, each request creates a new notification. 
	 * If set, and a notification with the same tag is already being shown, the new notification replaces the existing one in the notification drawer.
	 */
	public Notification tag(String tag){
		return addNotificationAttribute("tag", tag);
	}
	/**
	 * Indicates color of the icon, expressed in #rrggbb format
	 */
	public Notification color(String rgb){
		return addNotificationAttribute("color", rgb);
	}
	/**
	 * Indicates the action associated with a user click on the notification. When this is set, an activity with a matching intent filter is launched when user clicks the notification.
	 */
	public Notification click_action(String intent_filter){
		return addNotificationAttribute("click_action", intent_filter);
	}
	/**
	 * Indicates the key to the body string for localization. Use the key in the app's string resources when populating this value.
	 */
	public Notification body_loc_key(String key){
		return addNotificationAttribute("body_loc_key", key);
	}
	/**
	 * Indicates the string value to replace format specifiers in the body string for localization. For more information
	 * @see https://developer.android.com/guide/topics/resources/string-resource.html#FormattingAndStyling
	 */
	public Notification body_loc_args(Collection<String> args){
		JSONArray arr = new JSONArray();
		arr.addAll(args);
		
		return addNotificationAttribute("body_loc_key", arr);
	}
	/**
	 * Indicates the key to the title string for localization. Use the key in the app's string resources when populating this value.
	 */
	public Notification title_loc_key(String key){
		return addNotificationAttribute("title_loc_key", key);
	}
	/**
	 * Indicates the string value to replace format specifiers in the title string for localization. For more information
	 * @see https://developer.android.com/guide/topics/resources/string-resource.html#FormattingAndStyling
	 */
	public Notification title_loc_args(Collection<String> args){
		JSONArray arr = new JSONArray();
		arr.addAll(args);
		
		return addNotificationAttribute("title_loc_key", arr);
	}
	
	
	
}
