package hello;


public class Hello {
	
	private static void usage() {
		System.out.println("    Usage: hello.Hello");
	}
	
	public static void main(String[] args) throws Exception {
		// Check and get parameters
		if (args.length != 0) {
			usage();
			throw new Exception("[ERROR] Incorrect number of parameters");
		}
		
		// Hello World from main application
		System.out.println("Hello World! (from main application)");

		// Hello World from a task
		HelloImpl.sayHello();
	}
	
} 
