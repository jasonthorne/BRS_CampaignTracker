package com.brs.airforce;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brs.period.Period;
import com.brs.plane.PlaneData.Model;
import com.brs.plane.PlaneData.Status;

public abstract class AirForce {
//public interface AirForce {
	
	//air force names:
	public enum AirForceName{
		
		RAF("RAF"), 
		LUFTWAFFE("Luftwaffe"), 
		USAAF("USA"), 
		VVS("Soviet"), 
		IJAAF("Japan"); 
		private String airForce; //name of chosen air force
		//constructor:
		private AirForceName(String airForce) { this.airForce = airForce; }  //assign name of air force
		@Override public String toString() { return airForce; }  //return name of air force
	}
	
	protected String name; //name of AirForce
	protected abstract void setName(); //concrete class sets name
	public String getName() { return name; } //return name
	
	protected String description; //description of AirForce
	protected abstract void setDescription(); //concrete class sets description
	public String getDescription() { return description; } //return description
		//+++++++++++++NONE OF BELOW MIGHT BE NEEDED> (as we can pull this info from the hashmap below)make a getModel that puts with a key into HashMap +++++++++++++
	protected List<Model>models; //models of plane available
	protected abstract void setModels(); //concrete class sets models
	public List<Model> getModels() { return models; }  //return models
	
	protected static Map<AirForceName, List<Model>> airForceToModels = new HashMap<AirForceName, List<Model>>();
	protected abstract void putAirForceToModels(); //concrete class puts name and models to airForceToModels
	
	/*
	//HAVE THIS HASHMAP BE MADE FROM PULLING EACH PLANE CLASSES LIST OF PLANES FROM ITS SUBSIQUENT CLASS:
	//air force models:
	@SuppressWarnings("serial") private static final Map<AirForceName, List<Model>> airForceToModels = new HashMap<AirForceName, List<Model>>() {{
	    put(AirForceName.RAF, Arrays.asList(
	    		Model.HURRICANE_I, Model.HURRICANE_II, Model.MOSQUITO_II, Model.MOSQUITO_VI, Model.SPITFIRE_II,
	    		Model.SPITFIRE_V, Model.SPITFIRE_IX, Model.SPITFIRE_XIV, Model.TEMPEST_V, Model.TYPHOON_IB));
	    put(AirForceName.LUFTWAFFE, Arrays.asList(
	    		Model.BF109_E, Model.BF109_F, Model.BF109_G, Model.BF109_K, Model.BF110_C, 
	    		Model.BF110_G, Model.FW190_A, Model.FW190_D,  Model.ME_262_A, Model.ME_262_B));
	    put(AirForceName.USAAF, Arrays.asList(
	    		Model.F4F_WILDCAT, Model.F4U_CORSAIR, Model.F6F_HELLCAT, Model.P_38E_LIGHTNING, Model.P_38J_LIGHTNING, 
	    		Model.P_39_AIRCOBRA, Model.P_40B_WARHAWK, Model.P_40E_TOMAHAWK, Model.P_40N_KITTYHAWK, Model.P_47C_THUNDERBOLT, 
	    		Model.P_47D_THUNDERBOLT, Model.P_51B_MUSTANG, Model.P_51D_MUSTANG));
	    put(AirForceName.VVS, Arrays.asList(
	    		Model.HURRICANE_II, Model.IL_2M3_STURMOVIK, Model.LAGG_3, Model.LAVOCHKIN_LA_5FN, Model.MIG_3, 
	    		Model.P_39_AIRCOBRA, Model.P_63_KINGCOBRA, Model.POLIKARPOV_I_15, Model.POLIKARPOV_I_16, Model.POLIKARPOV_I_153, 
	    		Model.YAK_1, Model.YAK_3, Model.YAK_7B, Model.YAK_9D, Model.YAK_9U));
	    put(AirForceName.IJAAF, Arrays.asList(
	    		Model.A6_M2_ZERO, Model.A6_M5_ZERO, Model.J2M_RAIDEN, Model.KI_43_HAYABUSA,  Model.KI_44_SHOKI,  
	    		Model.KI_61_HIEN, Model.KI_84_HAYATE, Model.KI_100_HIEN, Model.N1K1_J_SHIDEN, Model.N1K2_J_SHIDEN_KAI));
	}};
	
	//get models available to an air force:
	public static List<Model>getAirForceModels(AirForceName airForceName){ return airForceToModels.get(airForceName); }
	*/
	
	protected static List<Period>periods; //periods
	protected static List<Status>statuses; //statuses of periods
	protected static Map<Period, Status>periodToStatus; //Map of periods and their statuses
	
	protected abstract void setPeriodToStatus(Model model) throws Exception;
	
	public Map<Period, Status>getPeriodToStatus(Model model) throws Exception{ 
		setPeriodToStatus(model);
		return periodToStatus;
	}
	
	
	public List<Model>getAllModels(){ //get all models available to all air forces
		return null; 
		
	}; 
	
	
	
	
	
	
	
	
	
	
	
	
}
