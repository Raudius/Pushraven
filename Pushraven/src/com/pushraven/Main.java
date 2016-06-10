package com.pushraven;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Main {
	
	private static String SERVER_KEY = "";
	
	public static void main(String[] args) {
		String a_client_key = "dCpaPGYIxgI:APA91bE0BryxFEQFKBokRmrkT-0pR5mgbXu7SqVWkBffcp4r_jvOPjNQ17AVJ-ki-b4Sti2DZprLWLI0IZPPyKDao3NGcq0aR2LsGGXj5Rp6vSJhWiVuMYv0TV1w-g0lZRzSn0LruxVN";
		
		SERVER_KEY = args[0];
		
		
		// create raven object using your firebase messaging key
		Pushraven raven = new Pushraven(SERVER_KEY);

		Collection<String> c = new ArrayList<String>();
		c.add("hello");
		c.add("world");
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("Hello", "World!");
		data.put("Marco", "Polo");
		data.put("Foo", "Bar");
		
		// build raven message using the builder pattern
		raven.addMulticast(a_client_key)
			.collapse_key("a_collapse_key")
			.priority(1)
			.delay_while_idle(true)
			.time_to_live(100)
			.restricted_package_name("com.revihx.pushnot")
			.dry_run(false)
			.data(data)
			.title("Testing")
			.body("Hello World!")
			.color("#ff0000");
		
		
		

		// push the raven message
		FcmResponse r = raven.push();
		
		// prints response code and message
		System.out.println(r);
	}
}
