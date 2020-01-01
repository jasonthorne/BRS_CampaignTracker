import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brs.period.Block;
import com.brs.period.Period;
import com.brs.period.Year;
import com.brs.plane.Plane;
import com.brs.plane.Plane.Availability;
import com.brs.plane.PlaneData.Model;
import com.brs.plane.PlaneData.Status;

public class DataDump {
	
	/*
	 * AIRFORCE:
	 * 
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
	
	/*
	protected List<Model>models; //models of plane available
	protected abstract void setModels(); //concrete class sets models
	public List<Model> getModels() { return models; }  //return models
	*/
	
	//===================================================================
	
	//RoyalAirForce:
	/*
	//plane model periods of availability:
	private static final Map<Model, List<Availability>> modelToAvailabilities = new HashMap<Model, List<Availability>>() {{
		put(Model.HURRICANE_I, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY),Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY), Status.AUTO)));
	    put(Model.HURRICANE_II, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY), Status.LIMIT),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_ONE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_ONE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_ONE), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_TWO), Status.LIMIT),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_TWO), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_TWO), Status.LIMIT),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_THREE), Status.LIMIT)));
	    put(Model.MOSQUITO_II, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_TWO), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_TWO), Status.AUTO),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_THREE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_THREE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_THREE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FOUR), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FOUR), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_FOUR), Status.AUTO),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FIVE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FIVE), Status.AUTO)));
	    put(Model.MOSQUITO_VI, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_THREE), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_THREE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_THREE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FOUR), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FOUR), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_FOUR), Status.AUTO),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FIVE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FIVE), Status.AUTO)));
	    put(Model.SPITFIRE_II, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY), Status.AUTO),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_ONE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_ONE), Status.LIMIT),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_ONE), Status.LIMIT)));
	    put(Model.SPITFIRE_V, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_ONE), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_ONE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_ONE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_TWO), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_TWO), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_TWO), Status.AUTO),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_THREE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_THREE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_THREE), Status.LIMIT)));
	    put(Model.SPITFIRE_IX, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_TWO), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_THREE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_THREE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_THREE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FOUR), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FOUR), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_FOUR), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FIVE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FIVE), Status.LIMIT)));
	    put(Model.SPITFIRE_XIV, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FOUR), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_FOUR), Status.AUTO),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FIVE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FIVE), Status.AUTO)));
	    put(Model.TEMPEST_V, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FOUR), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_FOUR), Status.AUTO),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FIVE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FIVE), Status.AUTO)));
	    put(Model.TYPHOON_IB, 
	    		Arrays.asList( //THIS IS WRONG (should have different data): 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FOUR), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_FOUR), Status.AUTO),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FIVE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FIVE), Status.AUTO)));
	}};		
	
*/
	
	
	
	
}
