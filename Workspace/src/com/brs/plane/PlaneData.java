package com.brs.plane;

public abstract class PlaneData {
	
	//Plane models:
	public enum Model{
		//56 values in rows of 4:
		A6_M2_ZERO("A6-M2 Zero"), A6_M5_ZERO("A6-M5 Zero"), BF109_E("Bf109 E"), BF109_F("Bf109 F"), 
		BF109_G("Bf109 G"), BF109_K("Bf109 K"), BF110_C("Bf110 C"), BF110_G("Bf110 G"), 
		F4F_WILDCAT("F4F Wildcat"), F4U_CORSAIR("F4U Corsair"), F6F_HELLCAT("F6F Hellcat"), FW190_A("Fw190 A"), 
		FW190_D("Fw190 D"), HURRICANE_I("Hurricane I"), HURRICANE_II("Hurricane II"), IL_2M3_STURMOVIK("Il-2m3 Sturmovik"), 
		J2M_RAIDEN("J2M Raiden"), KI_43_HAYABUSA("KI-43 Hayabusa"), KI_44_SHOKI("KI-44 Shoki"), KI_61_HIEN("KI-61 Hien"),
		KI_84_HAYATE("KI-84 Hayate"), KI_100_HIEN("KI-100 Hien"), LAGG_3("LaGG-3"), LAVOCHKIN_LA_5FN("Lavochkin La-5FN"), 
		ME_262_A("Me 262 A"), ME_262_B("Me 262 B"), MIG_3("MiG-3"), MOSQUITO_II("Mosquito II"), 
		MOSQUITO_VI("Mosquito VI"), N1K1_J_SHIDEN("N1K1-J Shiden"), N1K2_J_SHIDEN_KAI("N1K2-J Shiden-Kai"), P_38E_LIGHTNING("P-38E Lightning"), 
		P_38J_LIGHTNING("P-38J Lightning"), P_39_AIRCOBRA("P-39 Aircobra"), P_40B_WARHAWK("P-40B Warhawk"), P_40E_TOMAHAWK("P-40E Tomahawk"), 
		P_40N_KITTYHAWK("P-40N Kittyhawk"), P_47C_THUNDERBOLT("P-47C Thunderbolt"), P_47D_THUNDERBOLT("P-47D Thunderbolt"), P_51B_MUSTANG("P-51B Mustang"),
		P_51D_MUSTANG("P-51D Mustang"), P_63_KINGCOBRA("P-63 Kingcobra"), POLIKARPOV_I_15("Polikarpov I-15"), POLIKARPOV_I_16("Polikarpov I-16"), 
		POLIKARPOV_I_153("Polikarpov I-153"), SPITFIRE_II("Spitfire II"), SPITFIRE_V("Spitfire V"), SPITFIRE_IX("Spitfire IX"), 
		SPITFIRE_XIV("Spitfire XIV"), TEMPEST_V("Tempest V"), TYPHOON_IB("Typhoon Ib"), YAK_1("Yak-1"), 
		YAK_3("Yak-3"), YAK_7B("Yak-7B"), YAK_9D("Yak-9D"), YAK_9U("Yak-9U");
		
		private String model; //name of chosen model
		//constructor:
		private Model(String model) {this.model = model;} //set name of model
		@Override 
		public String toString() {return model;} //return chosen model
	}
	
	//Status values:
	public enum Status{ 
		NONE("None"), 
		LIMIT("Limit"), 
		AUTO("Auto");
		
		private String status; //name of chosen model
		//constructor:
		private Status(String status) {this.status = status;} //set name of model
		@Override 
		public String toString() {return status;} //return chosen model
	}
		

}

