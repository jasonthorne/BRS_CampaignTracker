package com.brs.die;

import java.util.Random;
import java.util.function.Predicate;
import java.util.function.Supplier;

public interface Die {
	
	//imitates D6 die roll by providing random number from 1-6:
	static final Supplier<Integer>D6=()->new Random().nextInt(6)+1;
	
	//imitates a manoeuvre test:
	static final Predicate<Integer>MANOEUVRE=(d6s)-> {
		for(int i=0; i<d6s; i++) //for number of D6s rolls
			if(D6.get().equals(6)) { return true; } //return true if any roll a 6 
		return false; //else test has failed
	};
}
