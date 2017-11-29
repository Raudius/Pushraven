package us.raudi.pushraven;

public class Notification extends Payload {
	public Notification title(String title) {
		return (Notification) addAttribute("title",title);
	}
	
	public Notification body(String body) {
		return (Notification) addAttribute("body", body);
	}
}
