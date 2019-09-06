package us.raudi.pushraven;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

import us.raudi.pushraven.configs.AndroidConfig;
import us.raudi.pushraven.configs.AndroidConfig.Priority;
import us.raudi.pushraven.notifications.AndroidNotification;

public class Sample {
	public static void main(String[] args) throws IOException {
		// The folliwng three lines will be different for every implementation
		// 1. Client id, used to send messages to specific client
		// 2. Json file downloaded from the Firebase console, containing details for authentication to google
		// 3. Your project ID. Can also be found in the Firebase console.
		String CLIENT_ID = "cA7gOth0X1Q:APA91bERuP4lNAw_oOe9huC27Eao6TDFLEgBmDGnln0IpJDgXyBttxCMV6u1VtegzbfFAI4b3TwAWOceg2oB2A2UuVzpYcxyrZHEVuEiZBF3dSnsWnZds-pdwMxefQDojBj6JvIqQEyd";
		
		File serviceAccount = new File("service_account.json");
		GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(serviceAccount) );
		Pushraven.setCredential(credential);		
		Pushraven.setProjectId("fcmtest-f57d4");
		
		// Create Notification object
		Notification not = new Notification()
				.title("Hello World")
				.body("This is a notification");
		
		
		// Create OPTIONAL target configuration (in this case Android)
		AndroidConfig droidCfg = new AndroidConfig()
				.priority(Priority.HIGH)
				.notification(
						new AndroidNotification()
							.color("#ff0000")
						)
				.priority(Priority.HIGH);
			
		// Create Message and add Notification and Configurations to it
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
