package com.brs;

import ideas.FileIdentifier;

public interface FileReadingTEST {
	
	default void getText() {
		String text = "";
		String currLine;
		//path to file constructed from classe's canonical name and file identifier:
		String path = "/" + (this.getClass().getCanonicalName().replace(".", "/")) + "test";
		
		
		System.out.println("TEST PATH IS: " + path);
	}

}
