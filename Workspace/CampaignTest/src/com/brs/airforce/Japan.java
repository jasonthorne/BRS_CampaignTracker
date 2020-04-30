package com.brs.airforce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.brs.period.Block;
import com.brs.period.Period;
import com.brs.period.Periods;
import com.brs.period.Year;
import com.brs.plane.Model;
import com.brs.plane.Status;


public class Japan extends AirForce{
	
	
	
	/*
	 * 
	 * List of PERIOD STATUS's for each plane
	 * 
	 * plane
	 * model
	 * airforce
	 * list of period statuses - add just the ones that are limit or auto. HAVe db set others as none by default. 
	 * 
	 * 
	 */
	
	//Japan name:
	private static final AirForceName NAME = AirForceName.IJAAF; 
	
	//Japan models of plane:
	private static final List<Model>MODELS = Arrays.asList(
			Model.A6_M2_ZERO, Model.A6_M5_ZERO, Model.J2M_RAIDEN, Model.N1K1_J_SHIDEN, Model.N1K2_J_SHIDEN_KAI,
			Model.KI_43_HAYABUSA, Model.KI_44_SHOKI, Model.KI_61_HIEN, Model.KI_84_HAYATE, Model.KI_100_HIEN);

	//creates a HashMap of periods and their statuses for the model of plane passed to it: 
	@Override 
	protected void setPeriodToStatus(Model model) {
		periodToStatus = new HashMap<Period, Status>(); //(re)set HashMap
		
		switch(model) { //populate periods and statuses, according to model:
		  case A6_M2_ZERO:
			  periods = Periods.getPeriods(new Period(Block.LATE, Year.FORTY), new Period(Block.LATE, Year.FORTY_THREE)); 
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO,
			  Status.AUTO, Status.AUTO, Status.LIMIT);
			  break;
		  case A6_M5_ZERO: 
			  periods = Periods.getPeriods(new Period(Block.MID, Year.FORTY_THREE), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case J2M_RAIDEN: 
			  periods = Periods.getPeriods(new Period(Block.EARLY, Year.FORTY_FOUR), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.LIMIT, Status.LIMIT, Status.LIMIT, Status.LIMIT);
			  break;
		  case N1K1_J_SHIDEN: 
			  periods = Periods.getPeriods(new Period(Block.MID, Year.FORTY_FOUR), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.LIMIT, Status.LIMIT, Status.LIMIT);
			  break;
		  case N1K2_J_SHIDEN_KAI:
			  periods = Periods.getPeriods(new Period(Block.EARLY, Year.FORTY_FIVE), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.LIMIT);
			  break;
		  case KI_43_HAYABUSA: 
			  periods = Periods.getPeriods(new Period(Block.MID, Year.FORTY_ONE), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO,
			  Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO);
			  break; 
		  case KI_44_SHOKI: 
			  periods = Periods.getPeriods(new Period(Block.MID, Year.FORTY_TWO), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO,
			  Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case KI_61_HIEN: 
			  periods = Periods.getPeriods(new Period(Block.MID, Year.FORTY_THREE), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.LIMIT, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO, Status.AUTO);
			  break;
		  case KI_84_HAYATE: 
			  periods = Periods.getPeriods(new Period(Block.EARLY, Year.FORTY_FOUR), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.LIMIT, Status.LIMIT, Status.LIMIT, Status.LIMIT);
			  break;
		  case KI_100_HIEN:
			  periods = Periods.getPeriods(new Period(Block.EARLY, Year.FORTY_FIVE), new Period(Block.MID, Year.FORTY_FIVE));
			  statuses = Arrays.asList(Status.LIMIT, Status.LIMIT);
			  break;
		}
		 //add periods and statuses to HashMap:
		for (int i=0, j=periods.size(); i<j; i++) { periodToStatus.put(periods.get(i), statuses.get(i)); }
	}
	
	@Override
	public String getName() {return NAME.toString();} //get name of air force
	@Override
	public List<Model> getAllModels() {return new ArrayList<Model>(MODELS);} //get copy of models of plane available
	
}
