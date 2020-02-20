package com.brs.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

public interface Date {
	
	//return a string of current date:
	static final Supplier<String>DATE=()->DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now());
}

