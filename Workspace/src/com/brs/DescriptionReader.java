package com.brs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;

public interface DescriptionReader { //++++++++++++++++++abstract might be better as vars can be private!! 
	
    static String getDescription(String path) throws Exception {
    	
    	//URL url = new Object().getClass().getResource("/com/brs/airforce/TestDescription");
    	final URL url = new Object().getClass().getResource(path);
		
    	final File file = new File(url.toURI());
		
		final String description;
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

		String currentLine;

		while ((currentLine = bufferedReader.readLine()) != null) {
			System.out.println(currentLine);
		}
    	
		bufferedReader.close();
		return null; 
    } 

}


//https://funnelgarden.com/java_read_file/#2a_BufferedReader_Default_Encoding


/*

Loading file from path:
https://stackoverflow.com/questions/4359876/how-to-load-reference-a-file-as-a-file-instance-from-the-classpath

Calling getClass() from static method:
https://stackoverflow.com/questions/8275499/how-to-call-getclass-from-a-static-method-in-java

 */