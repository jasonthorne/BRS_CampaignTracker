package com.brs.airforce;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.brs.plane.PlaneData.Model;

public final class AirForceData{
	
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
	
	
	//new plane???
	
	//avalabilities - status period
	
	///this should be a class and dictate the polane avbaliablilty once the airforce is xelected. ++++++++++++++++


	//name

	//desciption

	//planes
	
	/*
	 * MAKE A PLANE DATA CLASS - include all models of planes avaliable
	 * //and plane statuses.
	 * for default val: 
	 * https://stackoverflow.com/questions/4664026/default-or-initial-value-for-a-java-enum-array
	 * Day[] days = new Day[3];
	Arrays.fill(days, Day.MONDAY);
	 * and name and description
	 * 
	 *  In THIS class add the data of which planes are related to what airforce.
	 *  and what dates they're avalaible(somehow!!!)
	 *  and what planes 
	 *  
	 *  eg:
	 *  
	 *  plane models hashmap - airForces_models
	 *  plane avaliability hashmap - airForces_avaliability
	 */
	
	
}


	
	

