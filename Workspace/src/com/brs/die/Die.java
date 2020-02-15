package com.brs.die;

import java.util.Random;
import java.util.function.Supplier;

public interface Die {
	
	//imitates D6 die roll by providing random number from 1-6:
	static final Supplier<Integer>D6=()->new Random().nextInt(6)+1;

}
