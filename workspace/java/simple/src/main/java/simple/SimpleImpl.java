package simple;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;


public class SimpleImpl {
	
	public static void increment(String counterFile) throws FileNotFoundException, IOException {
		// Read value
		FileInputStream fis = new FileInputStream(counterFile);
		int count = fis.read();
		fis.close();
		
		// Write new value
		FileOutputStream fos = new FileOutputStream(counterFile);
		fos.write(++count);
		fos.close();
	}
	
}
