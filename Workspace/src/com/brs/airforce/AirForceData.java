package com.brs.airforce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.brs.Campaign;
import com.brs.airforce.AirForceData.AirForce;
import com.brs.event.EventData.EventName;
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
		public String toString() { return airForce; }  //return name of air force
	}
	
	//air forces and the models of planes available to them:
	private static final HashMap<AirForce, List<Model>> airForceToModels = new HashMap<AirForce, List<Model>>() {{
	    put(AirForce.RAF, Arrays.asList(
	    		Model.HURRICANE_I, Model.HURRICANE_II, Model.MOSQUITO_II, Model.MOSQUITO_VI, Model.SPITFIRE_II,
	    		Model.SPITFIRE_V, Model.SPITFIRE_IX, Model.SPITFIRE_XIV,  Model.TEMPEST_V, Model.TYPHOON_IB));
	    put(AirForce.LUFTWAFFE, Arrays.asList(
	    		Model.BF109_E, Model.BF109_F, Model.BF109_G, Model.BF109_K, Model.BF110_C, 
	    		Model.BF110_G, Model.FW190_A, Model.FW190_D,  Model.ME_262_A, Model.ME_262_B));
	    put(AirForce.USAAF, Arrays.asList(
	    		Model.F4F_WILDCAT, Model.F4U_CORSAIR, Model.F6F_HELLCAT, Model.P_38E_LIGHTNING, Model.P_38J_LIGHTNING, 
	    		Model.P_39_AIRCOBRA, Model.P_40B_WARHAWK, Model.P_40E_TOMAHAWK, Model.P_40N_KITTYHAWK, Model.P_47C_THUNDERBOLT, 
	    		Model.P_47D_THUNDERBOLT, Model.P_51B_MUSTANG, Model.P_51D_MUSTANG));
	    put(AirForce.VVS, Arrays.asList(
	    		Model.HURRICANE_II, Model.IL_2M3_STURMOVIK, Model.LAGG_3, Model.LAVOCHKIN_LA_5FN, Model.MIG_3, 
	    		Model.P_39_AIRCOBRA, Model.P_63_KINGCOBRA, Model.POLIKARPOV_I_15, Model.POLIKARPOV_I_16, Model.POLIKARPOV_I_153, 
	    		Model.YAK_1, Model.YAK_3, Model.YAK_7B, Model.YAK_9D, Model.YAK_9U));
	    put(AirForce.IJAAF, Arrays.asList(
	    		Model.A6_M2_ZERO, Model.A6_M5_ZERO, Model.J2M_RAIDEN, Model.KI_43_HAYABUSA,  Model.KI_44_SHOKI,  
	    		Model.KI_61_HIEN, Model.KI_84_HAYATE, Model.KI_100_HIEN, Model.N1K1_J_SHIDEN, Model.N1K2_J_SHIDEN_KAI));
	}};
	
	
	//==================================================================================================================
	//map inside map: https://stackoverflow.com/questions/5056708/storing-hashmap-in-a-hashmap
	
	/*
	]
		<K: <k: airforce, v: model>, V: availability>
		<K: <k: airforce, v: model>, V: availability>
		<K: <k: airforce, v: model>, V: availability>
	[
	*/		
	
	//----------------https://stackoverflow.com/questions/14677993/how-to-create-a-hashmap-with-two-keys-key-pair-value
	
	private static final HashMap<List<Enum<?>>, List<?>> test = new HashMap<List<Enum<?>>, List<?>>() {{
	    put(Arrays.asList(AirForce.RAF, Model.HURRICANE_I), Arrays.asList(Arrays.asList(Block.EARLY, Year.FORTY_ONE), Status.LIMIT));
	    put(Arrays.asList(AirForce.LUFTWAFFE, Model.A6_M5_ZERO), Arrays.asList(Arrays.asList(Block.LATE, Year.FORTY_FIVE), Status.NONE));
	   
	}};

	
	
	
	
	
	
	
	//------------------------+++ADD PROTECTION TO ALL THIS STATIC STUFF
	static HashMap<AirForce, Model> airForceToModel = new HashMap<AirForce, Model>(); //hashmap for airForce and model (key hashmap)
	static HashMap<HashMap<AirForce,Model>,Availability>airForceModelToAvailabilities = new HashMap<HashMap<AirForce,Model>,Availability>();
	static List<HashMap<HashMap<AirForce,Model>,Availability>>TEST2 = new ArrayList<HashMap<HashMap<AirForce, Model>, Availability>>(); //NOT SAFE
	
	public static void testLoops(){ 
		
		//LOOP OVER ORIGINAL LIST AND CEATE A LIST (OR HASHMAP) OF ALL PLANE AND ARIFORCE OPTIONS
		
		//NEW HASHMAPS
		/////HashMap<AirForce, Model> airForceToModel2 = new HashMap<AirForce, Model>(); //hashmap for airForce and model (key hashmap)
		//////HashMap<HashMap<AirForce, Model>, Availability> airForceModelToAvailabilities2 = new HashMap<HashMap<AirForce,Model>,Availability>();
		
		//add values to hashMaps:
		airForceToModel.put(AirForce.RAF, Model.SPITFIRE_II); 
		airForceModelToAvailabilities.put(airForceToModel, new Plane.Availability(new Period(Block.EARLY, Year.FORTY_ONE), Status.LIMIT)); 
		
		TEST2.add(airForceModelToAvailabilities); //add values to list
		System.out.println("TEST2 is: " + TEST2);
		
		//clear hashmaps:
		airForceToModel.clear();
		airForceModelToAvailabilities.clear();
		
		System.out.println("airForceToModel is: " + airForceToModel);
		System.out.println("airForceModelToAvailabilities is: " + airForceModelToAvailabilities);
		System.out.println("TEST2 is: " + TEST2);
		
		//rinse and repeat... 
		
		List<Object> s= new ArrayList<>();
		s.add(AirForce.RAF);
		s.add(EventName.ASSAULT_ON_THE_REICH);
		System.out.println(s);
		
		System.out.println(test);
		List<Enum<?>> t= Arrays.asList(AirForce.RAF, Model.HURRICANE_I);
		System.out.println(test.get(t));
	}
	
	
	//-------------------------https://stackoverflow.com/questions/28288546/how-to-copy-hashmap-not-shallow-copy-in-java
	
	static HashMap<AirForce, Model> K_airForce_V_model1 = new HashMap<AirForce, Model>(){{ 
		put(AirForce.RAF, Model.SPITFIRE_II);
	}};
		
	
	static HashMap<AirForce, Model> K_airForce_V_model2 = new HashMap<AirForce, Model>(){{
		put(AirForce.LUFTWAFFE, Model.BF109_E); 
		
	}};
	
	static HashMap<HashMap<AirForce,Model>,Availability>K_k_airForce_v_Model_V_availabilities1 = new HashMap<HashMap<AirForce,Model>,Availability>(){{
		put(K_airForce_V_model1, new Plane.Availability(new Period(Block.EARLY, Year.FORTY_ONE), Status.LIMIT));
		
	}};
	
	static HashMap<HashMap<AirForce,Model>,Availability>K_k_airForce_v_Model_V_availabilities2 = new HashMap<HashMap<AirForce,Model>,Availability>(){{
		put(K_airForce_V_model2, new Plane.Availability(new Period(Block.LATE, Year.FORTY_FOUR), Status.AUTO));
		
	}};
	
	
	//K_k_airForce_v_Model_V_availabilities1.put(K_airForce_V_model1, new Plane.Availability(new Period(Block.EARLY, Year.FORTY_ONE), Status.LIMIT));
	
	//K_airForce_V_model1.put(AirForce.LUFTWAFFE, Model.BF109_E);
	
	static List<HashMap<HashMap<AirForce,Model>,Availability>>TEST = Arrays.asList(
			K_k_airForce_v_Model_V_availabilities1,
			K_k_airForce_v_Model_V_availabilities2
	);
	
	//=========================================================================================================
	
	
	//====================
	public static void getTest(){ //+++ADD PROTECTION TO ALL THIS STATIC STUFF
		
		
		
		System.out.println("TEST: " + TEST);
	}
	
	//==================
	
	
	
	
	
	
	
	
	//outerMap.put("OuterKey", innerMap);
	
	//list of availability objects. each with a period and a status.
	


	//availabilities - status periods
	//name

	//AIRFORCE DESCRIPTION HASHMAP NEEDED ++++++++++++++++++++++++++:
	
	

	//planes
	
	
	
	
}


	
	

