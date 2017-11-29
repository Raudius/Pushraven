package us.raudi.pushraven;

import java.io.File;

import us.raudi.pushraven.configs.AndroidConfig;
import us.raudi.pushraven.configs.AndroidConfig.Priority;
import us.raudi.pushraven.notifications.AndroidNotification;

public class Sample {
	public static void main(String[] args) {
		String CLIENT_ID = "cA7gOth0X1Q:APA91bERuP4lNAw_oOe9huC27Eao6TDFLEgBmDGnln0IpJDgXyBttxCMV6u1VtegzbfFAI4b3TwAWOceg2oB2A2UuVzpYcxyrZHEVuEiZBF3dSnsWnZds-pdwMxefQDojBj6JvIqQEyd";

		Pushraven.setAccountFile(new File("service_account.json"));
		Pushraven.setProjectId("fcmtest-f57d4");
		
		// create Notification object
		Notification not = new Notification()
				.title("Hello World")
				.body("This is a notification");
		
		// Create android specific configuration
		AndroidConfig droidCfg = new AndroidConfig()
				.priority(Priority.HIGH)
				.notification(
						new AndroidNotification()
							.color("#ff0000")
						)
				.priority(Priority.HIGH);;
				
		// Create Message and add Notitifacion and Configurations to it
		Message raven = new Message()
				.name("id")
				.notification(not)
				.token(CLIENT_ID)
				.android(droidCfg);
		
		
		// Push the Message to FCM
		FcmResponse response = Pushraven.push(raven);


		// Print API server response
		System.out.println(response);
	}
}
