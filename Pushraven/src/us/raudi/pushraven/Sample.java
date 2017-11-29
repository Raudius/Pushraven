package us.raudi.pushraven;

import us.raudi.pushraven.configs.AndroidConfig;
import us.raudi.pushraven.configs.AndroidConfig.Priority;

public class Sample {
	public static void main(String[] args) {
		final String SERVER_KEY = "AAAAyhD613w:APA91bEXYcWtpQoFXJdbOXugYQkbxy5uu45rcWFTrRnjQXw9J92NS4sG7qg6eX3a5KwLvQz_ubmIrPqwDauZ2bXiilAcVmbF2hWDHXVHmYP0fB4aDwxtJmi2OQ2ZGjpJnKTzadCs6wZc ";
		String id = "cA7gOth0X1Q:APA91bERuP4lNAw_oOe9huC27Eao6TDFLEgBmDGnln0IpJDgXyBttxCMV6u1VtegzbfFAI4b3TwAWOceg2oB2A2UuVzpYcxyrZHEVuEiZBF3dSnsWnZds-pdwMxefQDojBj6JvIqQEyd";

		Pushraven.setKey(SERVER_KEY);

		// create Notification object
		Message raven = new Message();
		Notification not = new Notification();
		AndroidConfig droidCfg = new AndroidConfig();
		
		
		
		not.title("Hello World")
			.body("This is a notification");
		
		
		droidCfg.priority(Priority.HIGH);
		

		// build raven message using the builder pattern
		raven.name("id")
			.notification(not)
			.token(id)
			.android(droidCfg);
		
		System.out.println(raven.toJson());


		// push the raven message
		//FcmResponse response = Pushraven.push(raven);

		// alternatively set static notification first.
		Pushraven.setNotification(raven);
		//response = Pushraven.push();

		// prints response code and message
		//System.out.println(response);
	}
}
