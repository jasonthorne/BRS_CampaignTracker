package com.brs.airforce;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.brs.airforce.AirForceData.AirForce;
import com.brs.period.Period;
import com.brs.period.PeriodData.Block;
import com.brs.period.PeriodData.Year;
import com.brs.plane.Plane;
import com.brs.plane.Plane.Availability;
import com.brs.plane.PlaneData.Model;
import com.brs.plane.PlaneData.Status;;

public class AirForceData{
	
	//AirForce values:
	public enum AirForce{
		
		RAF("RAF"), 
		LUFTWAFFE("Luftwaffe"), 
		USAAF("USA"), 
		VVS("Soviet"), 
		IJAAF("Japan"); 
		
		private String airForce; //name of chosen air force
		//constructor:
		private AirForce(String airForce) { this.airForce = airForce; }  //assign name of air force
		@Override 
		public String toString() { return airForce; }  //return name of air forc
	}
	
	//MAKE hashmap OF "avaliablePlanes" INSTEAD. by creating a plane object with a hashmap of avaliable dates and their statuses
	//ONE issue here though is these objects are ALL created. 
	//maybe here we add a list of hashmapsa as the value instead, with the plane type being the key, and maybe a third hashmap holding  the dates and values.

	//WHY ISNT THIS IN A SWITCH STEMENT!! - passing in the name of the chosen airfoce, and then creating the revelent plane objects
	//and giving them a hashmap of avaliable dates and their statuses.
	
	//air forces and the models of planes available to them:
	private static final HashMap<AirForce, List<Model>> airForces_models = new HashMap<AirForce, List<Model>>() {{
	    put(AirForce.RAF, Arrays.asList(
	    		Model.SPITFIRE_II, Model.SPITFIRE_V, Model.SPITFIRE_IX, Model.SPITFIRE_XIV, Model.HURRICANE_I,
	    		Model.HURRICANE_II, Model.TYPHOON_IB, Model.TEMPEST_V, Model.MOSQUITO_II, Model.MOSQUITO_VI));
	    put(AirForce.LUFTWAFFE, Arrays.asList(
	    		Model.BF109_E, Model.BF109_F, Model.BF109_G, Model.BF109_K, Model.FW190_A,
	    		Model.FW190_D, Model.BF110_C, Model.BF110_G, Model.ME_262_A, Model.ME_262_B));
	    put(AirForce.USAAF, Arrays.asList(
	    		Model.P_38E_LIGHTNING, Model.P_38J_LIGHTNING, Model.P_39_AIRCOBRA, Model.P_40B_WARHAWK, Model.P_40E_TOMAHAWK,
	    		Model.P_40N_KITTYHAWK, Model.P_47C_THUNDERBOLT, Model.P_47D_THUNDERBOLT, Model.P_51B_MUSTANG, Model.P_51D_MUSTANG,
	    		Model.F4F_WILDCAT, Model.F6F_HELLCAT, Model.F4U_CORSAIR));
	    put(AirForce.VVS, Arrays.asList(
	    		Model.POLIKARPOV_I_15, Model.POLIKARPOV_I_153, Model.POLIKARPOV_I_16, Model.MIG_3, Model.YAK_1,
	    		Model.YAK_3, Model.YAK_7B, Model.YAK_9D, Model.YAK_9U, Model.LAGG_3,
	    		Model.LAVOCHKIN_LA_5FN, Model.P_39_AIRCOBRA, Model.P_63_KINGCOBRA, Model.HURRICANE_II, Model.IL_2M3_STURMOVIK));
	    put(AirForce.IJAAF, Arrays.asList(
	    		Model.A6_M2_ZERO, Model.A6_M5_ZERO, Model.J2M_RAIDEN, Model.N1K1_J_SHIDEN, Model.N1K2_J_SHIDEN_KAI,
	    		Model.KI_43_HAYABUSA, Model.KI_44_SHOKI, Model.KI_61_HIEN, Model.KI_100_HIEN, Model.KI_84_HAYATE));
	}};
	
	//++++++++++++++++++++map inside map: https://stackoverflow.com/questions/5056708/storing-hashmap-in-a-hashmap
	
	//POPULATE THE INNER MAP BY ITERATING OVER THE ABOVE MAP 
	//inner map: airForces_model
	private static final HashMap<AirForce, Model> airForces_model = new HashMap<AirForce, Model>(){{
		put(AirForce.RAF, Model.SPITFIRE_II); //++++++TEST KEYS
		
	}};
	
	//Map<String, Map<String, Value>> outerMap = new HashMap<String, HashMap<String, Value>>();
	//Map<Map<String, Value>,String> outerMap = new HashMap<HashMap<String, Value>, String>();
	// outer map: airForcesModels_Availabilities
	private static final HashMap<HashMap<AirForce, Model>, Availability> airForcesModels_Availabilities = new HashMap<HashMap<AirForce, Model>, Availability>(){{
		put(airForces_model, new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FOUR), Status.AUTO));
		
	}};
		
	//====================
	public static void getTest(){
		System.out.println(airForcesModels_Availabilities);
	}
	
	//==================
	//outerMap.put("OuterKey", innerMap);
	
	//list of availability objects. each with a period and a status.
	


	//availabilities - status periods
	//name

	//AIRFORCE DESCRIPTION HASHMAP NEEDED ++++++++++++++++++++++++++:
	
	

	//planes

	
	
}


	
	

