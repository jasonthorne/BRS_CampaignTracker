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


public class RAF {//implements AirForce{
	
	private  final String description = "RAF description"; //description of air force
	
	//models of plane available:
	private final List<Model>models = Arrays.asList(
			Model.HURRICANE_I, Model.HURRICANE_II, Model.MOSQUITO_II, Model.MOSQUITO_VI, Model.SPITFIRE_II,
    		Model.SPITFIRE_V, Model.SPITFIRE_IX, Model.SPITFIRE_XIV,  Model.TEMPEST_V, Model.TYPHOON_IB);
	
	//plane model periods of availability:
	private static final Map<Model, List<Availability>> modelToAvailabilities = new HashMap<Model, List<Availability>>() {{
		put(Model.HURRICANE_I, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY),Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY), Status.AUTO)));
	    put(Model.HURRICANE_II, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY), Status.LIMIT),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_ONE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_ONE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_ONE), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_TWO), Status.LIMIT),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_TWO), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_TWO), Status.LIMIT),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_THREE), Status.LIMIT)));
	    put(Model.MOSQUITO_II, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_TWO), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_TWO), Status.AUTO),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_THREE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_THREE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_THREE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FOUR), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FOUR), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_FOUR), Status.AUTO),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FIVE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FIVE), Status.AUTO)));
	    put(Model.MOSQUITO_VI, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_THREE), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_THREE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_THREE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FOUR), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FOUR), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_FOUR), Status.AUTO),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FIVE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FIVE), Status.AUTO)));
	    put(Model.SPITFIRE_II, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY), Status.AUTO),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_ONE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_ONE), Status.LIMIT),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_ONE), Status.LIMIT)));
	    put(Model.SPITFIRE_V, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_ONE), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_ONE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_ONE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_TWO), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_TWO), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_TWO), Status.AUTO),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_THREE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_THREE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_THREE), Status.LIMIT)));
	    put(Model.SPITFIRE_IX, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_TWO), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_THREE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_THREE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_THREE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FOUR), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FOUR), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_FOUR), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FIVE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FIVE), Status.LIMIT)));
	    put(Model.SPITFIRE_XIV, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FOUR), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_FOUR), Status.AUTO),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FIVE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FIVE), Status.AUTO)));
	    put(Model.TEMPEST_V, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FOUR), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_FOUR), Status.AUTO),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FIVE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FIVE), Status.AUTO)));
	    put(Model.TYPHOON_IB, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FOUR), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_FOUR), Status.AUTO),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FIVE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FIVE), Status.AUTO)));
	}};

	/*
	//Getters: //++++++++++++++++++++CHANGE PRIVACY OF THESE +++++++++
	@Override
	public String getDescription() { return description; } //get description
	
	@Override
	public List<Model> getModels() { return models; } //get models available

	@Override 
	public List<Availability> getAvailabilities(Model model) { return modelToAvailabilities.get(model); } //get model availabilities
	*/
	
}
