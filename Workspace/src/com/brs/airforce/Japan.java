package com.brs.airforce;

public class Japan {
	

	/*
	//plane model periods of availability:
	private static final Map<Model, List<Availability>> modelToAvailabilities = new HashMap<Model, List<Availability>>() {{
		put(Model.F4F_WILDCAT, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_ONE), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_TWO), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_TWO), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_TWO), Status.AUTO),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_THREE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_THREE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_THREE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FOUR), Status.LIMIT),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FOUR), Status.LIMIT)));
	    put(Model.F4U_CORSAIR, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FOUR), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FOUR), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_FOUR), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FIVE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FIVE), Status.AUTO)));
	    put(Model.F6F_HELLCAT, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_THREE), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_THREE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FOUR), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FOUR), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_FOUR), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FIVE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FIVE), Status.AUTO)));
	    put(Model.P_38E_LIGHTNING, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_TWO), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_TWO), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_TWO), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_THREE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_THREE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_THREE), Status.LIMIT)));
	    put(Model.P_38J_LIGHTNING, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_THREE), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_THREE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FOUR), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FOUR), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_FOUR), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FIVE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FIVE), Status.AUTO)));
	    put(Model.P_39_AIRCOBRA, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_ONE), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_ONE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_TWO), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_TWO), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_TWO), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_THREE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_THREE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_THREE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FOUR), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FOUR), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_FOUR), Status.LIMIT)));
	    put(Model.P_40B_WARHAWK, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_ONE), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_ONE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_TWO), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_TWO), Status.LIMIT),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_TWO), Status.LIMIT)));
	    put(Model.P_40E_TOMAHAWK, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_ONE), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_TWO), Status.LIMIT),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_TWO), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_TWO), Status.AUTO),
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_THREE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_THREE), Status.LIMIT)));
	    put(Model.P_40N_KITTYHAWK, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_THREE), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_THREE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_THREE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FOUR), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FOUR), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_FOUR), Status.LIMIT)));
	    put(Model.P_47C_THUNDERBOLT, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_THREE), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_THREE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_THREE), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FOUR), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FOUR), Status.LIMIT)));
	    put(Model.P_47D_THUNDERBOLT, 
	    		Arrays.asList(
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FOUR), Status.LIMIT), 
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FOUR), Status.AUTO),
	    		new Plane.Availability(new Period(Block.LATE, Year.FORTY_FOUR), Status.AUTO), 
	    		new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FIVE), Status.AUTO),
	    		new Plane.Availability(new Period(Block.MID, Year.FORTY_FIVE), Status.AUTO)));
	    put(Model.P_51B_MUSTANG, 
				Arrays.asList(
				new Plane.Availability(new Period(Block.MID, Year.FORTY_THREE), Status.LIMIT), 
				new Plane.Availability(new Period(Block.LATE, Year.FORTY_THREE), Status.AUTO),
				new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FOUR), Status.AUTO), 
				new Plane.Availability(new Period(Block.MID, Year.FORTY_FOUR), Status.AUTO),
				new Plane.Availability(new Period(Block.LATE, Year.FORTY_FOUR), Status.AUTO),
				new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FIVE), Status.AUTO),
				new Plane.Availability(new Period(Block.MID, Year.FORTY_FIVE), Status.AUTO)));
	    put(Model.P_51D_MUSTANG, 
				Arrays.asList(
				new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FOUR), Status.LIMIT), 
				new Plane.Availability(new Period(Block.MID, Year.FORTY_FOUR), Status.AUTO),
				new Plane.Availability(new Period(Block.LATE, Year.FORTY_FOUR), Status.AUTO), 
				new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FIVE), Status.AUTO),
				new Plane.Availability(new Period(Block.MID, Year.FORTY_FIVE), Status.AUTO)));
	}};
*/

}
