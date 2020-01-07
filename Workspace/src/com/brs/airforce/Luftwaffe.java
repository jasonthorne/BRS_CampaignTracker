package com.brs.airforce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.brs.period.Period;
import com.brs.period.Periods;
import com.brs.period.Block;
import com.brs.period.Year;
import com.brs.plane.Model;
import com.brs.plane.Status;



public class Luftwaffe extends AirForce{
	
	//Luftwaffe name:
	private static final AirForceName NAME = AirForceName.LUFTWAFFE; 
	
	//Luftwaffe models of plane:
	private static final List<Model>MODELS = Arrays.asList(
			Model.BF109_E, Model.BF109_F, Model.BF109_G, Model.BF109_K, Model.BF110_C, 
			Model.BF110_G, Model.FW190_A, Model.FW190_D, Model.ME_262_A, Model.ME_262_B);
	
	//creates a HashMap of periods and their statuses for the model of plane passed to it: 
	@Override 
	protected void setPeriodToStatus(Model model) {
		periodToStatus = new HashMap<Period, Status>(); //(re)set HashMap
		
		switch(model) { //populate periods and statuses, according to model:
		  case BF109_E:
			  periods = Periods.getPeriods(new Period(Block.EARLY, Year.FORTY), new Period(Block.EARLY, Year.FORTY_TWO)); 
			  statuses = Arrays.asList(Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.LIMIT, Status.LIMIT, Status.LIMIT);
			  break;
		  case BF109_F:
			  periods = Periods.getPeriods(new Period(Block.LATE, Year.FORTY), new Period(Block.MID, Year.FORTY_THREE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, 
			  Status.AUTO, Status.LIMIT, Status.LIMIT);
			  break;
		  case BF109_G:
			  periods = Periods.getPeriods(new Period(Block.MID, Year.FORTY_TWO), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO,
			  Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case BF109_K:
			  periods = Periods.getPeriods(new Period(Block.MID, Year.FORTY_FOUR), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case BF110_C:
			  periods = Periods.getPeriods(new Period(Block.EARLY, Year.FORTY), new Period(Block.MID, Year.FORTY_ONE));
			  statuses = Arrays.asList(Status.AUTO, Status.AUTO, Status.AUTO, Status.LIMIT, Status.LIMIT);
			  break;
		  case BF110_G:
			  periods = Periods.getPeriods(new Period(Block.MID, Year.FORTY_ONE), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO,
			  Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO);
			  break; 
		  case FW190_A: 
			  periods = Periods.getPeriods(new Period(Block.MID, Year.FORTY_ONE), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO,
			  Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case FW190_D:
			  periods = Periods.getPeriods(new Period(Block.EARLY, Year.FORTY_FIVE), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO);
			  break;
		  case ME_262_A:
			  periods = Periods.getPeriods(new Period(Block.LATE, Year.FORTY_FOUR), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.LIMIT, Status.LIMIT);
			  break;
		  case ME_262_B:
			  periods = Periods.getPeriods(new Period(Block.EARLY, Year.FORTY_FIVE), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.LIMIT);
			  break;
		}
		 //add periods and statuses to HashMap:
		for (int i=0; i<periods.size(); i++) { periodToStatus.put(periods.get(i), statuses.get(i)); }
	}
	
	@Override
	public String getName() {return NAME.toString();} //get name of air force
	@Override
	public List<Model> getAllModels() {return new ArrayList<Model>(MODELS);} //get copy of models of plane available
	
}
