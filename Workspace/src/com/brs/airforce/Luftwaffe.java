package com.brs.airforce;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brs.period.Period;
import com.brs.FileIdentifier;
import com.brs.period.Block;
import com.brs.period.Year;
import com.brs.plane.Plane;
import com.brs.plane.Model;
import com.brs.plane.Status;


public class Luftwaffe extends AirForce{
	
	//Luftwaffe name:
	private static final AirForceName NAME = AirForceName.LUFTWAFFE; 
	
	//Luftwaffe description:
	private static final String DESCRIPTION = "***Luftwaffe description here***"; 
	
	//Luftwaffe models of plane:
	private static final List<Model>MODELS = Arrays.asList(
			Model.BF109_E, Model.BF109_F, Model.BF109_G, Model.BF109_K, Model.BF110_C, 
			Model.BF110_G, Model.FW190_A, Model.FW190_D,  Model.ME_262_A, Model.ME_262_B);
	
	//constructor calls setters:
	public Luftwaffe(){ 
		//System.out.println("NEW6+++++++++++++: "+ getText2(this.getClass(), FileIdentifier.DESCRIPTION));
		System.out.println("NEW6+++++++++++++: "+ getText(FileIdentifier.DESCRIPTION));
		setName(); 
		setDescription(); 
		setModels(); 
	}
	
	//creates a HashMap of periods and their statuses for the model of plane passed to it: 
	@Override 
	protected void setPeriodToStatus(Model model) {
		periodToStatus = new HashMap<Period, Status>(); //(re)set HashMap
		
		switch(model) { //populate periods and statuses, according to model:
		  case BF109_E:
			  periods = Period.getPeriods(new Period(Block.EARLY, Year.FORTY), new Period(Block.EARLY, Year.FORTY_TWO)); 
			  statuses = Arrays.asList(Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.LIMIT, Status.LIMIT, Status.LIMIT);
			  break;
		  case BF109_F:
			  periods = Period.getPeriods(new Period(Block.LATE, Year.FORTY), new Period(Block.MID, Year.FORTY_THREE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, 
			  Status.AUTO, Status.LIMIT, Status.LIMIT);
			  break;
		  case BF109_G:
			  periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_TWO), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO,
			  Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case BF109_K:
			  periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_FOUR), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case BF110_C:
			  periods = Period.getPeriods(new Period(Block.EARLY, Year.FORTY), new Period(Block.MID, Year.FORTY_ONE));
			  statuses = Arrays.asList(Status.AUTO, Status.AUTO, Status.AUTO, Status.LIMIT, Status.LIMIT);
			  break;
		  case BF110_G:
			  periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_ONE), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO,
			  Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO);
			  break; 
		  case FW190_A: 
			  periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_ONE), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO,
			  Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case FW190_D:
			  periods = Period.getPeriods(new Period(Block.EARLY, Year.FORTY_FIVE), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO);
			  break;
		  case ME_262_A:
			  periods = Period.getPeriods(new Period(Block.LATE, Year.FORTY_FOUR), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.LIMIT, Status.LIMIT);
			  break;
		  case ME_262_B:
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
