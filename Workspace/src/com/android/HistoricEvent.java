package com.android;

import java.util.ArrayList;
import java.util.List;

//public enum HistoricEvent {
public class HistoricEvent {
	
	private Name name;
	private Period period;
	
	private List<AirForce>airforces = new ArrayList<AirForce>(); //list of airforces avaliable
	
	enum Name{
		BATTLE_OF_BRITAIN, //determines period and airforces ++++++++
		GUADALCANAL, 
		STALINGRAD,
		ASSAULT_ON_THE_REICH, 
		KURSK, 
		THE_ITALIAN_CAMPAIGN,
		DEFENCE_OF_THE_HOME_ISLANDS;
	}
}


//make a period class