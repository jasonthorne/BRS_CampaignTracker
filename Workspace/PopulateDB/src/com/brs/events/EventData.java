package com.brs.events;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import com.brs.periods.Period;

//public interface EventData {
public abstract class EventData {// implements InsertEventAirforces{
	
	//protected abstract Event getName();
	private Event name; // = Event.ASSAULT_ON_THE_REICH;
	
	
	
	protected Event getName() { return this.name; }
	
	
	protected EventData() {
		//this.name = name;
	}
	
	protected abstract void setName();


}
