package com.brs.statuses;

import com.brs.Insert;

public interface InsertStatuses {
	
	static final Insert INSERT_STATUSES = ()->{
		System.out.println("hi there!"); //overriding the public void yo
	};
}
