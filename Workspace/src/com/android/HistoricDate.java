package com.android;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HistoricDate {
	
	private int startDate; //should be dates,not ints ++++++++++++++
	private int endDate;
	private String airForce;
	private Name name;
	
	private enum Name{
		BATTLE_OF_BRITAIN, 
		GUADALCANAL, 
		STALINGRAD,
		ASSAULT_ON_THE_REICH, 
		KURSK, 
		THE_ITALIAN_CAMPAIGN,
		DEFENCE_OF_THE_HOME_ISLANDS;
	}
	
	HistoricDate(){ 
		setName(Name.BATTLE_OF_BRITAIN); //change to accept 
	}
	
	private void setName(Name name){
		this.name = name;
	}
	
	public Name getName() {
		return name;
	}
	
	
	//=====================
	private List<Name> names = Arrays.asList(Name.values());
	
	public List<String>getNames(){
		/*
		List<String>stringNames = new ArrayList<String>();
		names.forEach((name) -> {
		    stringNames.add(name.toString());
		});
		return stringNames;
		*/
		
		return null;
	}
	
	
	
	
	
}
