package us.raudi.pushraven.notifications;

import java.util.Collection;

import us.raudi.pushraven.Notification;
/**
 * Notification to send to android devices.
 * @see https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages#androidnotification
 * @author Revihx
 *
 */
public class AndroidNotification extends Notification {
	/**
	 * The notification's icon. Sets the notification icon to myicon for drawable resource myicon. 
	 * If you don't send this key in the request, FCM displays the launcher icon specified in your app manifest.
	 * @param myicon
	 * @return
	 */
	public AndroidNotification icon(String myicon) {
		return (AndroidNotification) addAttribute("icon", myicon);
	}
	
	/**
	 * The notification's icon color, expressed in #rrggbb format.
	 * @param rgb as a string. Example: "#ff01a0"
	 * @return
	 */
	public AndroidNotification color(String rgb) {
		return (AndroidNotification) addAttribute("color", rgb);
	}
	
	/**
	 * The sound to play when the device receives the notification. 
	 * Supports "default" or the filename of a sound resource bundled in the app. 
	 * Sound files must reside in /res/raw/.
	 * @param mysound
	 * @return
	 */
	public AndroidNotification sound(String mysound) {
		return (AndroidNotification) addAttribute("sound", mysound);
	}
	
	/**
	 * Identifier used to replace existing notifications in the notification drawer. If not specified, each request creates a new notification. 
	 * If specified and a notification with the same tag is already being shown, the new notification replaces the existing one in the notification drawer.
	 * @param mytag
	 * @return
	 */
	public AndroidNotification tag(String mytag) {
		return (AndroidNotification) addAttribute("sound", mytag);
	}
	
	/**
	 * The action associated with a user click on the notification. 
	 * If specified, an activity with a matching intent filter is launched when a user clicks on the notification.
	 * @param action
	 * @return
	 */
	public AndroidNotification clickAction(String action) {
		return (AndroidNotification) addAttribute("clickAction", action);
	}
	
	/**
	 * The key to the body string in the app's string resources to use to localize the body text to the user's current localization. 
	 * @see https://developer.android.com/guide/topics/resources/string-resource.html
	 * @param key
	 * @return
	 */
	public AndroidNotification body_loc_key(String key) {
		return (AndroidNotification) addAttribute("body_loc_key", key);
	}
	
	/**
	 * Variable string values to be used in place of the format specifiers in body_loc_key to use to localize the body text to the user's current localization.
	 * @see https://developer.android.com/guide/topics/resources/string-resource.html#FormattingAndStyling
	 * @param args
	 * @return
	 */
	public AndroidNotification body_loc_args(Collection<String> args){
		return (AndroidNotification) addAttributeArray("body_loc_args", args);
	}
	
	/**
	 * The key to the title string in the app's string resources to use to localize the title text to the user's current localization. 
	 * @see https://developer.android.com/guide/topics/resources/string-resource.html
	 * @param key
	 * @return
	 */
	public AndroidNotification title_loc_key(String key) {
		return (AndroidNotification) addAttribute("title_loc_key", key);
	}
	
	/**
	 * Variable string values to be used in place of the format specifiers in title_loc_key to use to localize the title text to the user's current localization.
	 * @see https://developer.android.com/guide/topics/resources/string-resource.html#FormattingAndStyling
	 * @param args
	 * @return
	 */
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
