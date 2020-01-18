package com.brs.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//returns a string of current date:
public interface Date {
	
	static String getDate() { 
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //formatter
		return dateTimeFormatter.format(LocalDate.now()); //return current formatted date
	}
}
