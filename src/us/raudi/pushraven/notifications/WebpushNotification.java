package us.raudi.pushraven.notifications;

import us.raudi.pushraven.Notification;

/**
 * Notification to send to android devices.
 * Web notification to send via webpush protocol.
 * @author Raudius
 *
 */
public class WebpushNotification extends Notification {
	/**
	 * The URL to use for the notification's icon.
	 * @param url URL to the icon resource
	 * @return Returns the modified payload
	 */
	public WebpushNotification icon(String url) {
		return (WebpushNotification) addAttribute("icon", url);
	}
	
	
	@Override
	public WebpushNotification title(String title) {
		return (WebpushNotification) super.title(title);
	}
	@Override
	public WebpushNotification body(String body) {
		return (WebpushNotification) super.body(body);
	}
}
