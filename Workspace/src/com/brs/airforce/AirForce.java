package com.brs.airforce;

import java.util.List;

import com.brs.plane.Plane.Availability;
import com.brs.plane.PlaneData.Model;

public interface AirForce {
	
	List<Model>getModels(); //get models available to air force
	
	List<Availability>getAvailabilities(Model model); //get availabilities of a model


}
