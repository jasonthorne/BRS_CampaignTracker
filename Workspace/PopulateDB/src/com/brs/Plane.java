package com.brs;

import java.util.ArrayList;
import java.util.List;
import com.brs.Model;

public class Plane {
	
	private final Model model; //model of plane
	private final AirForce airforce; //air force flown for
	private final List<PeriodStatus>periodStatuses;	//periods available, and their corresponding status
	
	public Plane(Model model, AirForce airforce, List<PeriodStatus>periodStatuses) {
		this.model = model; //set model
		this.airforce = airforce;
		this.periodStatuses = new ArrayList<PeriodStatus>(periodStatuses);
	}
	
	//getter:
	public Plane getPlane() {
		return new Plane(this.model, this.airforce, new ArrayList<PeriodStatus>(this.periodStatuses));
	}

	@Override
	public String toString() {
		return "Plane [model=" + model + ", airforce=" + airforce + ", periodStatuses=" + periodStatuses + "]";
	}
	
}
