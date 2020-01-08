package com.brs;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface DateTime {
	
	static String getDateTime() { //returns a string of current date and time
		
		LocalDateTime localDateTime = LocalDateTime.now(); //date and time
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); //formatter
		return dateTimeFormatter.format(localDateTime); //return formatted date and time
	}
}
