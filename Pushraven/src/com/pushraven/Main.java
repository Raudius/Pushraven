package com.pushraven;
public class Main {
	
	private static String SERVER_KEY = "";
	
	public static void main(String[] args) {
		String a_client_key = "dCpaPGYIxgI:APA91bE0BryxFEQFKBokRmrkT-0pR5mgbXu7SqVWkBffcp4r_jvOPjNQ17AVJ-ki-b4Sti2DZprLWLI0IZPPyKDao3NGcq0aR2LsGGXj5Rp6vSJhWiVuMYv0TV1w-g0lZRzSn0LruxVN";
		
		SERVER_KEY = args[0];
		
		
		// create raven object using your firebase messaging key
		Pushraven raven = new Pushraven(SERVER_KEY);

		
		
		// build raven message using the builder pattern
		raven.title("MyTitle")
		  .text("Hello World!")
		  .color("#ff0f0f")
		  .tag("test_message")
		  .addTarget(a_client_key);
		
		// push the raven message
		raven.push();
	
		// clear the Notification and send a different notification using the same object.
		raven.clear();
		
		raven.title("HELLO")
			.text("WORLD")
			.addTarget(a_client_key);
		
		raven.push();
	}
}
