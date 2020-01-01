package com.brs.airforce;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brs.period.Period;
import com.brs.period.Block;
import com.brs.period.Year;
import com.brs.plane.Plane;
import com.brs.plane.Plane.Availability;
import com.brs.plane.PlaneData.Model;
import com.brs.plane.PlaneData.Status;
import com.brs.airforce.AirForce;


public class UnitedStates extends AirForce{
	
	//name of air force:
	private static final AirForceName NAME = AirForceName.USAAF; 
	
	//description of air force:
	private static final String DESCRIPTION = "***USAAF description here***"; 
	
	//models of plane available: 
	private static final List<Model>MODELS = Arrays.asList(
			Model.F4F_WILDCAT, Model.F4U_CORSAIR, Model.F6F_HELLCAT, Model.P_38E_LIGHTNING, Model.P_38J_LIGHTNING, 
    		Model.P_39_AIRCOBRA, Model.P_40B_WARHAWK, Model.P_40E_TOMAHAWK, Model.P_40N_KITTYHAWK, Model.P_47C_THUNDERBOLT, 
    		Model.P_47D_THUNDERBOLT, Model.P_51B_MUSTANG, Model.P_51D_MUSTANG);

	//constructor:
	public UnitedStates(){
		addAirForceModels(); //add air force and it's models to Map
	}
	
	//creates a HashMap of periods and their statuses for the model of plane passed to it: 
	@Override 
	protected void setPeriodToStatus(Model model) {
		periodToStatus = new HashMap<Period, Status>(); //(re)set HashMap
		
		switch(model) { //populate periods and statuses, according to model:
		  case F4F_WILDCAT:
			  periods = Period.getPeriods(new Period(Block.LATE, Year.FORTY_ONE), new Period(Block.MID, Year.FORTY_FOUR)); 
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO,
			  Status.LIMIT, Status.LIMIT);
			  break;
		  case F4U_CORSAIR:
			  periods = Period.getPeriods(new Period(Block.EARLY, Year.FORTY_FOUR), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case F6F_HELLCAT:
			  periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_THREE), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case P_38E_LIGHTNING:
			  periods = Period.getPeriods(new Period(Block.EARLY, Year.FORTY_TWO), new Period(Block.LATE, Year.FORTY_THREE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.LIMIT);
			  break;
		  case P_38J_LIGHTNING:
			  periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_THREE), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case P_39_AIRCOBRA:
			  periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_ONE), new Period(Block.LATE, Year.FORTY_FOUR));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO,
			  Status.AUTO, Status.AUTO, Status.AUTO, Status.LIMIT);
			  break; 
		  case P_40B_WARHAWK:
			  periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_ONE), new Period(Block.LATE, Year.FORTY_TWO));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.LIMIT, Status.LIMIT);
			  break;
		  case P_40E_TOMAHAWK:
			  periods = Period.getPeriods(new Period(Block.LATE, Year.FORTY_ONE), new Period(Block.MID, Year.FORTY_THREE));
			  statuses = Arrays.asList(Status.LIMIT, Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.LIMIT);
			  break;
		  case P_40N_KITTYHAWK:
			  periods = Period.getPeriods(new Period(Block.EARLY, Year.FORTY_THREE), new Period(Block.LATE, Year.FORTY_FOUR));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.LIMIT);
			  break;
		  case P_47C_THUNDERBOLT:
			  periods = Period.getPeriods(new Period(Block.EARLY, Year.FORTY_THREE), new Period(Block.MID, Year.FORTY_FOUR));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.LIMIT);
			  break;
		  case P_47D_THUNDERBOLT:
			  periods = Period.getPeriods(new Period(Block.EARLY, Year.FORTY_FOUR), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case P_51B_MUSTANG:
			  periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_THREE), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case P_51D_MUSTANG:
			  periods = Period.getPeriods(new Period(Block.EARLY, Year.FORTY_FOUR), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		}
		 //add periods and statuses to HashMap:
		for (int i=0; i<periods.size(); i++) { periodToStatus.put(periods.get(i), statuses.get(i)); }
			
	}
	
	@Override
	public String getName() { return NAME.toString(); } //return name of air force
	@Override
	public String getDescription() { return DESCRIPTION; } //return description of air force
	@Override 
	protected void addAirForceModels() { airForceToModels.put(NAME, MODELS); } //add name and models to Map

}
