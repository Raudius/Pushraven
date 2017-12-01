package us.raudi.pushraven;

/**
 * Basic notification template to use across all platforms.
 * https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages#notification
 * @author Raudius
 *
 */
public class Notification extends Payload {
	/**
	 * The notification's title.
	 * @param title
	 * @return
	 */
	public Notification title(String title) {
		return (Notification) addAttribute("title",title);
	}
	
	/**
	 * The notification's body text.
	 * @param body
	 * @return
	 */
	public Notification body(String body) {
		return (Notification) addAttribute("body", body);
	}
}
