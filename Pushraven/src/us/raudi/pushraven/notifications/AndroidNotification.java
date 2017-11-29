package us.raudi.pushraven.notifications;

import java.util.Collection;

import us.raudi.pushraven.Notification;

public class AndroidNotification extends Notification {
	public AndroidNotification icon(String myicon) {
		return (AndroidNotification) addAttribute("icon", myicon);
	}
	
	public AndroidNotification color(String rgb) {
		return (AndroidNotification) addAttribute("color", rgb);
	}
	
	public AndroidNotification sound(String mysound) {
		return (AndroidNotification) addAttribute("sound", mysound);
	}
	
	public AndroidNotification tag(String mytag) {
		return (AndroidNotification) addAttribute("sound", mytag);
	}
	
	public AndroidNotification clickAction(String action) {
		return (AndroidNotification) addAttribute("clickAction", action);
	}
	
	public AndroidNotification body_loc_key(String key) {
		return (AndroidNotification) addAttribute("body_loc_key", key);
	}
	
	public AndroidNotification body_loc_args(Collection<String> args){
		return (AndroidNotification) addAttributeArray("body_loc_args", args);
	}
	
	public AndroidNotification title_loc_key(String key) {
		return (AndroidNotification) addAttribute("title_loc_key", key);
	}
	
	public AndroidNotification title_loc_args(Collection<String> args){
		return (AndroidNotification) addAttributeArray("title_loc_args", args);
	}
	
	@Override
	public AndroidNotification title(String title) {
		return (AndroidNotification) super.title(title);
	}
	@Override
	public AndroidNotification body(String body) {
		return (AndroidNotification) super.body(body);
	}
}
