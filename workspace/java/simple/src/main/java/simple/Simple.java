package simple;

import java.io.FileInputStream;
import java.io.FileOutputStream;


public class Simple {
	
	private static final String fileName = "counter";
	
	private static void usage() {
		System.out.println("    Usage: simple <counterValue>");
	}
	
	public static void main(String[] args) throws Exception {
		// Check and get parameters
		if (args.length != 1) {
			usage();
			throw new Exception("[ERROR] Incorrect number of parameters");
		}
		int initialValue = Integer.parseInt(args[0]);

		// Write value
		FileOutputStream fos = new FileOutputStream(fileName);
		fos.write(initialValue);
		fos.close();
		System.out.println("Initial counter value is " + initialValue);

		//Execute increment
		SimpleImpl.increment(fileName);

		// Write new value
		FileInputStream fis = new FileInputStream(fileName);
		int finalValue = fis.read();
		fis.close();
		System.out.println("Final counter value is " + finalValue);
	}
	
} 
