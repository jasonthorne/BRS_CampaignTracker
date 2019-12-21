package com.brs.airforce;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brs.period.Period;
import com.brs.period.PeriodData.Block;
import com.brs.period.PeriodData.Year;
import com.brs.plane.Plane;
import com.brs.plane.Plane.Availability;
import com.brs.plane.PlaneData.Model;
import com.brs.plane.PlaneData.Status;
import com.brs.airforce.AirForce;


public class AirForceUSAAF extends AirForce{
	
	//description of air force
	
	/*
	//models of plane available: 
	private final List<Model>models = Arrays.asList(
			Model.F4F_WILDCAT, Model.F4U_CORSAIR, Model.F6F_HELLCAT, Model.P_38E_LIGHTNING, Model.P_38J_LIGHTNING, 
    		Model.P_39_AIRCOBRA, Model.P_40B_WARHAWK, Model.P_40E_TOMAHAWK, Model.P_40N_KITTYHAWK, Model.P_47C_THUNDERBOLT, 
    		Model.P_47D_THUNDERBOLT, Model.P_51B_MUSTANG, Model.P_51D_MUSTANG);
	*/
	
	
	/*
	 * grab a list of periods from start date to last date (like with periods)
	 * declare a list of statuses
	 * loop through the ammount of dates,
	 *  and create new availabilities according to the date pos and it's corrisponding status list  pos value 
	 *  
	 *  List<Status>statuses = Arrays.asList(Status.LIMIT, ... ); //THINK ABOUT USING A QUEUE HERE
	 *  Period.getPeriods(new Period(Block.LATE, Year.FORTY_ONE), new Period(Block.MID, Year.FORTY_FOUR)
	 *  
	 *  spin through periods returned and create a hashmap with the period you're at and the index you're at
	 *  
	 *  return that hashmap (called periodToStatus) to the plane class
	 */
	
	
	
	
	
	//plane model periods of availability:
	private static final Map<Model, List<Availability>> modelToAvailabilities = new HashMap<Model, List<Availability>>() {{
		put(Model.F4F_WILDCAT, 
				
				
				
				
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_ONE), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_TWO), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_TWO), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_TWO), Status.AUTO),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_THREE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_THREE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_THREE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FOUR), Status.LIMIT),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FOUR), Status.LIMIT)));
	    put(Model.F4U_CORSAIR, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FOUR), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FOUR), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_FOUR), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FIVE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FIVE), Status.AUTO)));
	    put(Model.F6F_HELLCAT, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_THREE), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_THREE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FOUR), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FOUR), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_FOUR), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FIVE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FIVE), Status.AUTO)));
	    put(Model.P_38E_LIGHTNING, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_TWO), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_TWO), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_TWO), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_THREE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_THREE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_THREE), Status.LIMIT)));
	    put(Model.P_38J_LIGHTNING, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_THREE), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_THREE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FOUR), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FOUR), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_FOUR), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FIVE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FIVE), Status.AUTO)));
	    put(Model.P_39_AIRCOBRA, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_ONE), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_ONE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_TWO), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_TWO), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_TWO), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_THREE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_THREE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_THREE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FOUR), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FOUR), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_FOUR), Status.LIMIT)));
	    put(Model.P_40B_WARHAWK, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_ONE), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_ONE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_TWO), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_TWO), Status.LIMIT),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_TWO), Status.LIMIT)));
	    put(Model.P_40E_TOMAHAWK, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_ONE), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_TWO), Status.LIMIT),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_TWO), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_TWO), Status.AUTO),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_THREE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_THREE), Status.LIMIT)));
	    put(Model.P_40N_KITTYHAWK, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_THREE), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_THREE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_THREE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FOUR), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FOUR), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_FOUR), Status.LIMIT)));
	    put(Model.P_47C_THUNDERBOLT, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_THREE), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_THREE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_THREE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FOUR), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FOUR), Status.LIMIT)));
	    put(Model.P_47D_THUNDERBOLT, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FOUR), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FOUR), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_FOUR), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FIVE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FIVE), Status.AUTO)));
	    put(Model.P_51B_MUSTANG, 
				Arrays.asList(
				new Plane.Availability(new Period(Block.MID, Year.FORTY_THREE), Status.LIMIT), 
				new Plane.Availability(new Period(Block.LATE, Year.FORTY_THREE), Status.AUTO),
				new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FOUR), Status.AUTO), 
				new Plane.Availability(new Period(Block.MID, Year.FORTY_FOUR), Status.AUTO),
				new Plane.Availability(new Period(Block.LATE, Year.FORTY_FOUR), Status.AUTO),
				new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FIVE), Status.AUTO),
				new Plane.Availability(new Period(Block.MID, Year.FORTY_FIVE), Status.AUTO)));
	    put(Model.P_51D_MUSTANG, 
				Arrays.asList(
				new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FOUR), Status.LIMIT), 
				new Plane.Availability(new Period(Block.MID, Year.FORTY_FOUR), Status.AUTO),
				new Plane.Availability(new Period(Block.LATE, Year.FORTY_FOUR), Status.AUTO), 
				new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FIVE), Status.AUTO),
				new Plane.Availability(new Period(Block.MID, Year.FORTY_FIVE), Status.AUTO)));
	}};

	//Getters: //++++++++++++++++++++CHANGE PRIVACY OF THESE +++++++++
	@Override
	public String getDescription() { 
		return "AirForceUSAAF description"; 
	} 
	
	@Override
	public List<Model> getModels() { //??????????????????? test that this works!!! ++++++++++++++++++
		return Arrays.asList( 
				Model.F4F_WILDCAT, Model.F4U_CORSAIR, Model.F6F_HELLCAT, Model.P_38E_LIGHTNING, Model.P_38J_LIGHTNING, 
	    		Model.P_39_AIRCOBRA, Model.P_40B_WARHAWK, Model.P_40E_TOMAHAWK, Model.P_40N_KITTYHAWK, Model.P_47C_THUNDERBOLT, 
	    		Model.P_47D_THUNDERBOLT, Model.P_51B_MUSTANG, Model.P_51D_MUSTANG);
	} 

	//@Override //MAYBE RETURN A HASHMAP!!! key:period, value status
	//public List<Availability> getAvailabilities(Model model) { return modelToAvailabilities.get(model); } //get model availabilities

	
}
