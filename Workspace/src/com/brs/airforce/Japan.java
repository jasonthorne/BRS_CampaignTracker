package com.brs.airforce;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.Files;

public class Japan {
	
	
	public void test() throws Exception {
		
		
		/*
		//https://stackoverflow.com/questions/4359876/how-to-load-reference-a-file-as-a-file-instance-from-the-classpath
		
		URL url = this.getClass().getResource("/com/brs/airforce/TestDescription");
				
		File file = new File(url.toURI());
		///com/brs/airforce/description
		
		//https://funnelgarden.com/java_read_file/#Files-readAllBytes
		
		//String fileName = "C:\\Users\\Jay\\Desktop\\BRS_CampaignTracker\\Workspace\\src\\com\\brs\\description\\test";    
	    //File file = new File(fileName);

	    byte [] fileBytes = Files.readAllBytes(file.toPath());
	    char singleChar;
	    for(byte b : fileBytes) {
	      singleChar = (char) b;
	      System.out.print(singleChar);
	    }
	*/
	
		//final String FILENAME = "/com/brs/airforce/TestDescription";
		
		URL url = this.getClass().getResource("/com/brs/airforce/TestDescription");
		
		File file = new File(url.toURI());
		
		BufferedReader br = new BufferedReader(new FileReader(file));

	   String strCurrentLine;

	   while ((strCurrentLine = br.readLine()) != null) {
	    System.out.println(strCurrentLine);
	   }
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
    
	}
	
	
	



}
