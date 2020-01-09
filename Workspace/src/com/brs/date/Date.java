package com.brs.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public interface Date {
	
	static String getDate() { //returns a string of current date
		
		LocalDate localDate = LocalDate.now(); //date
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //formatter
		
		return dateTimeFormatter.format(localDate); //return formatted date
	}
}
