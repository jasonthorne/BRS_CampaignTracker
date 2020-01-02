package com.brs.airforce;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brs.period.Period;
import com.brs.period.Block;
import com.brs.period.Year;
import com.brs.plane.Plane;
import com.brs.plane.PlaneData.Model;
import com.brs.plane.PlaneData.Status;


public class RoyalAirForce extends AirForce{
	
	//RoyalAirForce name:
	private static final AirForceName NAME = AirForceName.RAF; 
	
	//RoyalAirForce description:
	private static final String DESCRIPTION = "***RAF description here***"; 
	
	//RoyalAirForce models of plane:
	private static final List<Model>MODELS = Arrays.asList(
			Model.HURRICANE_I, Model.HURRICANE_II, Model.MOSQUITO_II, Model.MOSQUITO_VI, Model.SPITFIRE_II,
    		Model.SPITFIRE_V, Model.SPITFIRE_IX, Model.SPITFIRE_XIV,  Model.TEMPEST_V, Model.TYPHOON_IB);
	
	//constructor:
	public RoyalAirForce(){
		setName(NAME); //set name of air force
		setDescription(DESCRIPTION); //set description of air force 
		setModels(MODELS); //set models of plane available
	}
	
	//creates a HashMap of periods and their statuses for the model of plane passed to it: 
	@Override 
	protected void setPeriodToStatus(Model model) {
		periodToStatus = new HashMap<Period, Status>(); //(re)set HashMap
		
		switch(model) { //populate periods and statuses, according to model:
		  case HURRICANE_I:
			  periods = Period.getPeriods(new Period(Block.EARLY, Year.FORTY), new Period(Block.LATE, Year.FORTY)); 
			  statuses = Arrays.asList(Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case HURRICANE_II:
			  periods = Period.getPeriods(new Period(Block.MID, Year.FORTY), new Period(Block.EARLY, Year.FORTY_THREE));
			  statuses = Arrays.asList(Status.LIMIT, Status.LIMIT, Status.AUTO, Status.AUTO, Status.LIMIT, Status.LIMIT, 
			  Status.LIMIT, Status.LIMIT, Status.LIMIT);
			  break;
		  case MOSQUITO_II:
			  periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_TWO), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO,
			  Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case MOSQUITO_VI:
			  periods = Period.getPeriods(new Period(Block.EARLY, Year.FORTY_THREE), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO,
			  Status.AUTO);
			  break;
		  case SPITFIRE_II:
			  periods = Period.getPeriods(new Period(Block.MID, Year.FORTY), new Period(Block.LATE, Year.FORTY_ONE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.LIMIT, Status.LIMIT);
			  break;
		  case SPITFIRE_V:
			  periods = Period.getPeriods(new Period(Block.EARLY, Year.FORTY_ONE), new Period(Block.LATE, Year.FORTY_THREE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO,
			  Status.AUTO, Status.LIMIT);
			  break; 
		  case SPITFIRE_IX:
			  periods = Period.getPeriods(new Period(Block.LATE, Year.FORTY_TWO), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO,
			  Status.AUTO, Status.LIMIT);
			  break;
		  case SPITFIRE_XIV:
			  periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_FOUR), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case TEMPEST_V:
			  periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_FOUR), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case TYPHOON_IB:
			  periods = Period.getPeriods(new Period(Block.EARLY, Year.FORTY_TWO), new Period(Block.EARLY, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO,
			  Status.LIMIT, Status.LIMIT, Status.LIMIT);
			  break;
		}
		 //add periods and statuses to HashMap:
		for (int i=0; i<periods.size(); i++) { periodToStatus.put(periods.get(i), statuses.get(i)); }
			
	}
	
}
