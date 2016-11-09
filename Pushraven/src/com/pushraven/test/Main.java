package com.pushraven.test;

import java.util.HashMap;

import com.pushraven.*;

public class Main {
	
	private static String SERVER_KEY = "";
	
	public static void main(String[] args) {
		SERVER_KEY = args[0];
		
		Pushraven.setKey(SERVER_KEY);
		
		
		// create Notification object 
		Notification raven = new Notification();

		
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("Hello", "World!");
		data.put("Marco", "Polo");
		data.put("Foo", "Bar");
		
		// build raven message using the builder pattern
		raven.to("news")
			.collapse_key("a_collapse_key")
			.priority(1)
			.delay_while_idle(true)
			.time_to_live(100)
			.restricted_package_name("com.revihx.pushnot")
			.dry_run(true)
			.data(data)
			.title("Testing")
			.body("Hello World!")
			.color("#ff0000");
		
		
		

		// push the raven message
		FcmResponse response = Pushraven.push(raven);
		
		// alternatively set static notification first.
		Pushraven.setNotification(raven);
		response = Pushraven.push();
		
		// prints response code and message
		System.out.println(response);
	}
}
