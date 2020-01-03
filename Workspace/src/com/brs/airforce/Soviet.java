package com.brs.airforce;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.brs.period.Block;
import com.brs.period.Period;
import com.brs.period.Year;
import com.brs.plane.Model;
import com.brs.plane.Status;

public class Soviet extends AirForce{
	
	//Soviet name:
	private static final AirForceName NAME = AirForceName.VVS; 
	
	//Soviet description:
	private static final String DESCRIPTION = "***Soviet description here***"; 
	
	//Soviet models of plane:
	private static final List<Model>MODELS = Arrays.asList(
			Model.HURRICANE_II, Model.IL_2M3_STURMOVIK, Model.LAGG_3, Model.LAVOCHKIN_LA_5FN, Model.MIG_3,
			Model.P_39_AIRCOBRA, Model.P_63_KINGCOBRA, Model.POLIKARPOV_I_15, Model.POLIKARPOV_I_16, Model.POLIKARPOV_I_153,
			Model.YAK_1, Model.YAK_3, Model.YAK_7B, Model.YAK_9D, Model.YAK_9U);
	
	//constructor calls setters:
	public Soviet(){ 
		setName(); 
		setDescription(); 
		setModels(); 
	}
	
	//creates a HashMap of periods and their statuses for the model of plane passed to it: 
	@Override 
	protected void setPeriodToStatus(Model model) {
		periodToStatus = new HashMap<Period, Status>(); //(re)set HashMap
		
		switch(model) { //populate periods and statuses according to model:
		  case HURRICANE_II:
			  periods = Period.getPeriods(new Period(Block.EARLY, Year.FORTY_TWO), new Period(Block.MID, Year.FORTY_FIVE)); 
			  statuses = Arrays.asList(Status.LIMIT, Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, 
			  Status.AUTO, Status.AUTO, Status.LIMIT, Status.LIMIT);
			  break;
		  case IL_2M3_STURMOVIK:
			  periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_ONE), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, 
			  Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case LAGG_3:
			  periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_ONE), new Period(Block.LATE, Year.FORTY_FOUR));
			  statuses = Arrays.asList(Status.LIMIT, Status.LIMIT, Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO,
			  Status.AUTO, Status.AUTO, Status.LIMIT, Status.LIMIT);
			  break;
		  case LAVOCHKIN_LA_5FN:
			  periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_TWO), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO,
			  Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case MIG_3:
			  periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_ONE), new Period(Block.EARLY, Year.FORTY_TWO));
			  statuses = Arrays.asList(Status.LIMIT, Status.LIMIT, Status.LIMIT);
			  break;
		  case P_39_AIRCOBRA:
			  periods = Period.getPeriods(new Period(Block.LATE, Year.FORTY_TWO), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO,
			  Status.AUTO, Status.LIMIT);
			  break; 
		  case P_63_KINGCOBRA:
			  periods = Period.getPeriods(new Period(Block.EARLY, Year.FORTY_FOUR), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.LIMIT, Status.LIMIT, Status.LIMIT, Status.LIMIT);
			  break;
		  case POLIKARPOV_I_15:
			  periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_ONE), new Period(Block.LATE, Year.FORTY_TWO));
			  statuses = Arrays.asList(Status.LIMIT, Status.LIMIT, Status.LIMIT, Status.LIMIT, Status.LIMIT);
			  break;
		  case POLIKARPOV_I_16:
			  periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_ONE), new Period(Block.MID, Year.FORTY_THREE));
			  statuses = Arrays.asList(Status.AUTO, Status.LIMIT, Status.AUTO, Status.AUTO, Status.LIMIT, Status.LIMIT, Status.LIMIT);
			  break;
		  case POLIKARPOV_I_153: 
			  periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_ONE), new Period(Block.LATE, Year.FORTY_TWO));
			  statuses = Arrays.asList(Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.LIMIT);
			  break;
		  case YAK_1: 
			  periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_ONE), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.LIMIT, Status.AUTO, Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO,
			  Status.LIMIT, Status.AUTO, Status.AUTO, Status.LIMIT, Status.LIMIT, Status.LIMIT);
			  break;
		  case YAK_3: 
			  periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_ONE), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO,
			  Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case YAK_7B: 
			  periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_TWO), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO,
			  Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case YAK_9D:
			  periods = Period.getPeriods(new Period(Block.LATE, Year.FORTY_TWO), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO,
			  Status.AUTO, Status.AUTO);
			  break;
		  case YAK_9U:
			  periods = Period.getPeriods(new Period(Block.EARLY, Year.FORTY_FIVE), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.LIMIT);
			  break;
		}
		 //add periods and statuses to HashMap:
		for (int i=0; i<periods.size(); i++) { periodToStatus.put(periods.get(i), statuses.get(i)); }
	}
	
	@Override
	protected void setName(){name = NAME;} //set name of air force
	@Override
	protected void setDescription(){description = DESCRIPTION;} //set description of air force 
	@Override
	protected void setModels(){models = MODELS;} //set models of plane available
		
}
