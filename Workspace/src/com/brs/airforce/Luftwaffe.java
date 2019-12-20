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


public class Luftwaffe implements AirForce{
	
	private final String description = "Luftwaffe description"; //description of air force
	
	//models of plane available:
	private final List<Model>models = Arrays.asList(
			Model.BF109_E, Model.BF109_F, Model.BF109_G, Model.BF109_K, Model.BF110_C, 
    		Model.BF110_G, Model.FW190_A, Model.FW190_D,  Model.ME_262_A, Model.ME_262_B);
	
	//plane model periods of availability:
	private static final Map<Model, List<Availability>> modelToAvailabilities = new HashMap<Model, List<Availability>>() {{
		put(Model.BF109_E, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_ONE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_ONE), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_ONE), Status.LIMIT),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_TWO), Status.LIMIT)));
	    put(Model.BF109_F, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_ONE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_ONE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_ONE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_TWO), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_TWO), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_TWO), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_THREE), Status.LIMIT),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_THREE), Status.LIMIT)));
	    put(Model.BF109_G, 
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
	    put(Model.BF109_K, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FOUR), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_FOUR), Status.AUTO),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FIVE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FIVE), Status.AUTO)));
	    put(Model.BF110_C, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_ONE), Status.LIMIT),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_ONE), Status.LIMIT)));
	    put(Model.BF110_G, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_ONE), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_ONE), Status.LIMIT),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_TWO), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_TWO), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_TWO), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_THREE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_THREE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_THREE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FOUR), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FOUR), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_FOUR), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FIVE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FIVE), Status.AUTO)));
	    put(Model.FW190_A, 
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
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_FOUR), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FIVE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FIVE), Status.AUTO)));
	    put(Model.FW190_D, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FIVE), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FIVE), Status.AUTO)));
	    put(Model.ME_262_A, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_FOUR), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FIVE), Status.LIMIT),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FIVE), Status.LIMIT)));
	    put(Model.ME_262_B, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FIVE), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FIVE), Status.LIMIT)));
		
	}};

	//Getters: //++++++++++++++++++++CHANGE PRIVACY OF THESE +++++++++
	@Override
	public String getDescription() { return description; } //get description
	
	@Override
	public List<Model> getModels() { return models; } //get models available

	@Override 
	public List<Availability> getAvailabilities(Model model) { return modelToAvailabilities.get(model); } //get model availabilities

	
}
