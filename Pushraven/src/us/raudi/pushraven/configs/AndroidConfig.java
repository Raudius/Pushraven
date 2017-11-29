package us.raudi.pushraven.configs;

import java.util.Map;

import us.raudi.pushraven.Payload;
import us.raudi.pushraven.notifications.AndroidNotification;

public class AndroidConfig extends Payload {
	public enum Priority {
		NORMAL, HIGH;
		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
	}
	
	
	public AndroidConfig collapse_key(String key) {
		return (AndroidConfig) addAttribute("collapse_key", key);
	}
	
	public AndroidConfig priority(Priority p) {
		return (AndroidConfig) addAttribute("priority", p.toString());
	}
	
	public AndroidConfig ttl(int seconds) {
		String duration = seconds + "s";
		return (AndroidConfig) addAttribute("ttl", duration);
	}
	
	public AndroidConfig restricted_package_name(String name) {
		return (AndroidConfig) addAttribute("restricted_package_name", name);
	}
	
	public AndroidConfig data(Map<String, String> data) {
		return (AndroidConfig) addAttributeMap("data", data);
	}
	
	public AndroidConfig notification(AndroidNotification not) {
		return (AndroidConfig) addAttributePayload("notification", not);
	}

	
	
}
