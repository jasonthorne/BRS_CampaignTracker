package com.brs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;

public interface FileReading {
	
    default String getText(String path) {
    	
    	String text = "";
		String currentLine;
		
		try { //try find & read from file
			
	    	URL url = this.getClass().getResource(path);
	    	////////final URL url = new Object().getClass().getResource(path);
	    	File file = new File(url.toURI());
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

			while ((currentLine = bufferedReader.readLine()) != null) {
				text = text.concat(currentLine.concat("\n")); //add current line (with line break) to text
			}
			bufferedReader.close(); //close reader
			
		}catch (Exception e) {
			e.printStackTrace();
			return ""; //return blank string in event of error
		}
		return text; //return text
    } 
    
    
    //---------------------
    
    default String getPath(Class<?> currClass) {
		return "/" + (currClass.getCanonicalName().replace(".", "/")) + "Description";
    }
    
    
    //===========================
    
    
	 default String getText2(Class<?> callingClass, FileIdentifier fileIdentifier) {
	    	
	    	String text = "";
			String currentLine;
			//build path to file using calling classe's canonical name and file identifier
			String path = "/" + (callingClass.getCanonicalName().replace(".", "/")) + fileIdentifier.toString();
			
			try { //try find & read from file
				
		    	URL url = this.getClass().getResource(path);
		    	File file = new File(url.toURI());
				BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
	
				while ((currentLine = bufferedReader.readLine()) != null) {
					text = text.concat(currentLine.concat("\n")); //add current line (with line break) to text
				}
				bufferedReader.close(); //close reader
				
			}catch (Exception e) {
				e.printStackTrace();
				return ""; //return blank string in event of error
			}
			return text; //return text
	    } 
	    
	    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

//+++++++++++++++++++++++++++++++++++++NOTES:++++++++++++++++++++++++++++
//https://funnelgarden.com/java_read_file/#2a_BufferedReader_Default_Encoding
////URL url = new Object().getClass().getResource("/com/brs/airforce/TestDescription");

/*

Loading file from path:
https://stackoverflow.com/questions/4359876/how-to-load-reference-a-file-as-a-file-instance-from-the-classpath

Calling getClass() from static method:
https://stackoverflow.com/questions/8275499/how-to-call-getclass-from-a-static-method-in-java

 */