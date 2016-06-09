public class Main {
	
	private static String SERVER_KEY = "";
	
	public static void main(String[] args) {
		String a_client_key = "dCpaPGYIxgI:APA91bE0BryxFEQFKBokRmrkT-0pR5mgbXu7SqVWkBffcp4r_jvOPjNQ17AVJ-ki-b4Sti2DZprLWLI0IZPPyKDao3NGcq0aR2LsGGXj5Rp6vSJhWiVuMYv0TV1w-g0lZRzSn0LruxVN";
		
		SERVER_KEY = args[0];
		
		Pushraven raven = new Pushraven(SERVER_KEY);
		
		raven.title("Title")
			.text("Hello World!")
			.addTarget(a_client_key);
		
		raven.send();
		
	}
}
