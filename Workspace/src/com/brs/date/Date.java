package com.brs.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public interface Date {
	
	static String getDate() { //returns a string of current date
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //formatter
		return dateTimeFormatter.format(LocalDate.now()); //return current formatted date
	}
}
