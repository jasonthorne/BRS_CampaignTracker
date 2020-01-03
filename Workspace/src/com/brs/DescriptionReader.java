package com.brs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.Files;

public interface DescriptionReader {
	
    static String getDescription(String path) throws Exception { 
    	
    	//URL url = new Object().getClass().getResource("/com/brs/airforce/TestDescription");
    	URL url = new Object().getClass().getResource(path);
		
		File file = new File(url.toURI());
		
		BufferedReader br = new BufferedReader(new FileReader(file));

	   String strCurrentLine;

	   while ((strCurrentLine = br.readLine()) != null) {
	    System.out.println(strCurrentLine);
	   }
    	
		return null; 
    } 

}



/*

//Calling getClass() from static method:
//https://stackoverflow.com/questions/8275499/how-to-call-getclass-from-a-static-method-in-java

//Loading file from classpath:
//https://stackoverflow.com/questions/4359876/how-to-load-reference-a-file-as-a-file-instance-from-the-classpath

*/