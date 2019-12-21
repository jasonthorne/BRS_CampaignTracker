package com.brs.airforce;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.brs.period.Period;
import com.brs.period.PeriodData.Block;
import com.brs.period.PeriodData.Year;
import com.brs.plane.Plane;
import com.brs.plane.Plane.Availability;
import com.brs.plane.PlaneData.Model;
import com.brs.plane.PlaneData.Status;
import com.brs.airforce.AirForce;


public class AirForceUSAAF implements AirForce{
	
	//models of plane available: 
	private static final List<Model>models = Arrays.asList(
			Model.F4F_WILDCAT, Model.F4U_CORSAIR, Model.F6F_HELLCAT, Model.P_38E_LIGHTNING, Model.P_38J_LIGHTNING, 
    		Model.P_39_AIRCOBRA, Model.P_40B_WARHAWK, Model.P_40E_TOMAHAWK, Model.P_40N_KITTYHAWK, Model.P_47C_THUNDERBOLT, 
    		Model.P_47D_THUNDERBOLT, Model.P_51B_MUSTANG, Model.P_51D_MUSTANG);
	
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
	 *  
	 *  planeclass can call this to get its status, by passing in current period
	 */
	
	private static List<Period>periods; //holds periods
	private static List<Status>statuses; //holds statuses of periods
	private static Map<Period, Status>periodToStatus; //periods and their statuses
	
	public static Map<Period, Status> getPeriodToStatus(Model model) throws Exception {
		periodToStatus = new HashMap<Period, Status>(); //(re)set HashMap
		
		switch(model) { //populate periods and statuses, according to model:
		  case F4F_WILDCAT:
			  periods = Period.getPeriods(new Period(Block.LATE, Year.FORTY_ONE), new Period(Block.MID, Year.FORTY_FOUR));
			  statuses = Arrays.asList(Status.LIMIT,Status.AUTO,Status.AUTO,Status.AUTO,Status.AUTO,Status.AUTO,Status.AUTO,
			  Status.LIMIT,Status.LIMIT);
			  break;
		  case F4U_CORSAIR:
			  periods = Period.getPeriods(new Period(Block.EARLY, Year.FORTY_FOUR), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT,Status.AUTO,Status.AUTO,Status.AUTO,Status.AUTO);
			  break;
		  case F6F_HELLCAT:
			  periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_THREE), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT,Status.AUTO,Status.AUTO,Status.AUTO,Status.AUTO,Status.AUTO,Status.AUTO);
			  break;
		  case P_38E_LIGHTNING:
			  periods = Period.getPeriods(new Period(Block.EARLY, Year.FORTY_TWO), new Period(Block.LATE, Year.FORTY_THREE));
			  statuses = Arrays.asList(Status.LIMIT,Status.AUTO,Status.AUTO,Status.AUTO,Status.AUTO,Status.LIMIT);
			  break;
		  case P_38J_LIGHTNING:
			  periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_THREE), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT,Status.AUTO,Status.AUTO,Status.AUTO,Status.AUTO,Status.AUTO,Status.AUTO);
			  break;
		  case P_39_AIRCOBRA:
			  periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_ONE), new Period(Block.LATE, Year.FORTY_FOUR));
			  statuses = Arrays.asList(Status.LIMIT,Status.AUTO,Status.AUTO,Status.AUTO,Status.AUTO,Status.AUTO,Status.AUTO,
			  Status.AUTO,Status.AUTO, Status.AUTO,Status.LIMIT);
			  break; 
		  case P_40B_WARHAWK:
			  periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_ONE), new Period(Block.LATE, Year.FORTY_TWO));
			  statuses = Arrays.asList(Status.LIMIT,Status.AUTO,Status.AUTO,Status.LIMIT,Status.LIMIT);
			  break;
		  case P_40E_TOMAHAWK:
			  periods = Period.getPeriods(new Period(Block.LATE, Year.FORTY_ONE), new Period(Block.MID, Year.FORTY_THREE));
			  statuses = Arrays.asList(Status.LIMIT,Status.LIMIT,Status.AUTO,Status.AUTO,Status.AUTO,Status.LIMIT);
			  break;
		  case P_40N_KITTYHAWK:
			  periods = Period.getPeriods(new Period(Block.EARLY, Year.FORTY_THREE), new Period(Block.LATE, Year.FORTY_FOUR));
			  statuses = Arrays.asList(Status.LIMIT,Status.AUTO,Status.AUTO,Status.AUTO,Status.AUTO,Status.LIMIT);
			  break;
		  case P_47C_THUNDERBOLT:
			  periods = Period.getPeriods(new Period(Block.EARLY, Year.FORTY_THREE), new Period(Block.MID, Year.FORTY_FOUR));
			  statuses = Arrays.asList(Status.LIMIT,Status.AUTO,Status.AUTO,Status.AUTO,Status.LIMIT);
			  break;
		  case P_47D_THUNDERBOLT:
			  periods = Period.getPeriods(new Period(Block.EARLY, Year.FORTY_FOUR), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT,Status.AUTO,Status.AUTO,Status.AUTO,Status.AUTO);
			  break;
		  case P_51B_MUSTANG:
			  periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_THREE), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT,Status.AUTO,Status.AUTO,Status.AUTO,Status.AUTO,Status.AUTO,Status.AUTO);
			  break;
		  case P_51D_MUSTANG:
			  periods = Period.getPeriods(new Period(Block.EARLY, Year.FORTY_FOUR), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT,Status.AUTO,Status.AUTO,Status.AUTO,Status.AUTO);
			  break;
		  default: throw new Exception("Error: model not found.");
		}
		 //add periods and statuses to HashMap:
		for (int i=0; i<periods.size(); i++) { periodToStatus.put(periods.get(i), statuses.get(i)); }
	    return periodToStatus; //return HashMap
	}
	
	
	
	
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
		return "AirForceUSAAF description..."; 	//description of air force
	} 
	
	@Override
	public List<Model> getModels() { return models; } 

	//@Override //MAYBE RETURN A HASHMAP!!! key:period, value status
	//public List<Availability> getAvailabilities(Model model) { return modelToAvailabilities.get(model); } //get model availabilities

	
}
