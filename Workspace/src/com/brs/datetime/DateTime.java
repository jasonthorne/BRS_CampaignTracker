package com.brs.datetime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface DateTime {
	
	//returns a string of formatted LocalDateTime:
	static String getDateTime(DateTimeFormat dateTimeFormat) { 
		
		LocalDateTime localDateTime = LocalDateTime.now(); //date and time
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormat.toString()); //formatter
		
		return dateTimeFormatter.format(localDateTime); //return formatted date and time
	}
}
