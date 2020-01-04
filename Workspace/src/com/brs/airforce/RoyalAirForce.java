package com.brs.airforce;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brs.period.Period;
import com.brs.FileIdentifier;
import com.brs.FileReading;
import com.brs.period.Block;
import com.brs.period.Year;
import com.brs.plane.Plane;
import com.brs.plane.Model;
import com.brs.plane.Status;


public class RoyalAirForce extends AirForce{
	
	
	//https://stackoverflow.com/questions/8275499/how-to-call-getclass-from-a-static-method-in-java
	static Class currentClass = new Object(){}.getClass().getEnclosingClass(); //pass the second half of this into a method????
	
	
	//RoyalAirForce name:
	private static final AirForceName NAME = AirForceName.RAF; 
	
	//private static final String path = "/".concat(RoyalAirForce.class.getCanonicalName().replace(".", "/").concat("Description")); 
	private final static String FILE_PATH = "/".concat(currentClass.getCanonicalName().replace(".", "/").concat("Description")); 
	
	//RoyalAirForce description (read in from file):
	//private static final String DESCRIPTION = FileReading.getText("/com/brs/airforce/TestDescription");
	 static final String DESCRIPTION = "";	//getText("/com/brs/airforce/TestDescription"); 
	
	//RoyalAirForce models of plane:
	private static final List<Model>MODELS = Arrays.asList(
			Model.HURRICANE_I, Model.HURRICANE_II, Model.MOSQUITO_II, Model.MOSQUITO_VI, Model.SPITFIRE_II,
			Model.SPITFIRE_V, Model.SPITFIRE_IX, Model.SPITFIRE_XIV,  Model.TEMPEST_V, Model.TYPHOON_IB);
	
	
	//constructor calls setters:
	public RoyalAirForce(){ 
		/////////////////////////////System.out.println("GET TEXT2: " + getText2());
		System.out.println("CURRENT CLASS: " + currentClass.getSimpleName());
		System.out.println(FILE_PATH);
		setName(); 
		setDescription(); 
		setModels(); 
		System.out.println("NEW5******: "+ getText2(this.getClass(), FileIdentifier.DESCRIPTION));
		//	"/".concat(currentClass.getCanonicalName().replace(".", "/").concat("Description"));
		////////////System.out.println("NEW4: "+ getText(getPath(this.getClass())));
		///////////////System.out.println("NEW3: "+ getText("/".concat(this.getClass().getCanonicalName().replace(".", "/").concat("Description"))));
		/////////////System.out.println("NEW2: "+ getText("/".concat(currentClass.getCanonicalName().replace(".", "/").concat("Description"))));
		////////////System.out.println("NEW: "+ getText("/com/brs/airforce/TestDescription"));
		System.out.println("CLASS NAME: " + this.getClass().getCanonicalName().replace(".", "/").concat("Description"));
		System.out.println("PACKAGE NAME: " + this.getClass().getPackage());
		System.out.println("SIMPLE NAME: " + this.getClass().getSimpleName());
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
	
	@Override
	protected void setName(){name = NAME;} //set name of air force
	@Override
	protected void setDescription(){description = DESCRIPTION;} //set description of air force 
	@Override
	protected void setModels(){models = MODELS;} //set models of plane available
	
}
