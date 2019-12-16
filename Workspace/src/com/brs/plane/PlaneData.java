package com.brs.plane;

public class PlaneData {
	
	//Plane models:
	public enum Model{
		
		//+++++++++++++++++++++++++++++58 planes in list  :(
		A6_M2_ZERO("A6-M2 Zero"), A6_M5_Zero("A6-M5 Zero"), BF109_E("Bf109 E"), BF109_F("Bf109 F"), BF109_G("Bf109 G"), 
		BF109_K("Bf109 K"), BF110_C("Bf110 C"), BF110_G("Bf110 G"), F4F_WILDCAT("F4F Wildcat"), F4U_CORSAIR("F4U Corsair"),
		F6F_HELLCAT("F6F Hellcat"), FW190_A("Fw190 A"), FW190_D("Fw190 D"), HURRICANE_I("Hurricane I"), HURRICANE_II("Hurricane II"),
		IL_2M3_STURMOVIK("Il-2m3 Sturmovik"), J2M_RAIDEN("J2M Raiden"), KI_43_HAYABUSA("KI-43 Hayabusa"), KI_44_SHOKI("KI-44 Shoki"), KI_61_HIEN("KI-61 Hien"),
		KI_84_HAYATE ("KI-84 Hayate"),
		KI_100_HIEN ("KI-100 Hien"),
		TEST("test");
		private String model; //name of chosen model
		//constructor:
		private Model(String model) {this.model = model;} //set name of model
		@Override 
		public String toString() {return model;} //return chosen model
	}

}
