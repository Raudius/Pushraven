package us.raudi.pushraven.configs;

import java.util.Map;

import us.raudi.pushraven.Payload;
import us.raudi.pushraven.notifications.AndroidNotification;

/**
 * Android specific options for messages sent through FCM connection server.
 * https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages#androidconfig
 * @author Raudius
 *
 */
public class AndroidConfig extends Payload {
	/**
	 * Priority of a message to send to Android devices.
	 * @author Revihx
	 */
	public enum Priority {
		NORMAL, HIGH;
		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
	}
	
	/**
	 * An identifier of a group of messages that can be collapsed, so that only the last message gets sent when delivery can be resumed. 
	 * A maximum of 4 different collapse keys is allowed at any given time.
	 * @param key (see: https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages)
	 * @return Returns the modified payload
	 */
	public AndroidConfig collapse_key(String key) {
		return (AndroidConfig) addAttribute("collapse_key", key);
	}
	
	/**
	 * Message priority. Can take "normal" and "high" values. For more information, see Setting the priority of a message.
	 * @param p (see: https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages)
	 * @return Returns the modified payload
	 */
	public AndroidConfig priority(Priority p) {
		return (AndroidConfig) addAttribute("priority", p.toString());
	}
	
	/**
	 * How long (in seconds) the message should be kept in FCM storage if the device is offline. 
	 * The maximum time to live supported is 4 weeks, and the default value is 4 weeks if not set. 
	 * Set it to 0 if want to send the message immediately.
	 * @param seconds (see: https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages)
	 * @return Returns the modified payload
	 */
	public AndroidConfig ttl(long seconds) {
		String duration = seconds + "s";
		return (AndroidConfig) addAttribute("ttl", duration);
	}
	
	/**
	 * Package name of the application where the registration tokens must match in order to receive the message.
	 * @param name (see: https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages)
	 * @return Returns the modified payload
	 */
	public AndroidConfig restricted_package_name(String name) {
		return (AndroidConfig) addAttribute("restricted_package_name", name);
	}
	
	/**
	 * Arbitrary key/value payload. If present, it will override google.firebase.fcm.v1.Message.data.
	 * 
	 * An object containing a list of "key": value pairs. Example: { "name": "wrench", "mass": "1.3kg", "count": "3" }.
	 * @param data (see: https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages)
	 * @return Returns the modified payload
	 */
	public AndroidConfig data(Map<String, String> data) {
		return (AndroidConfig) addAttributeMap("data", data);
	}
	
	
	/**
	 * Notification to send to android devices.
	 * https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages#androidnotification
	 * @param not (see: https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages)
	 * @return Returns the modified payload
	 */
	public AndroidConfig notification(AndroidNotification not) {
		return (AndroidConfig) addAttributePayload("notification", not);
	}

	
	
}
