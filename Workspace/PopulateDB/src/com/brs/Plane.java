package com.brs;

import java.util.ArrayList;
import java.util.List;
import com.brs.Model;
import com.brs.airforces.AirForce;

public class Plane {
	
	private final Model model; //model of plane
	private final AirForce airForce; //air force flown for
	private final List<PeriodStatus>periodStatuses;	//periods plane is available, and their corresponding statuses
	
	public Plane(Model model, AirForce airForce, List<PeriodStatus>periodStatus) {
		this.model = model; //set model
		this.airForce = airForce;
		this.periodStatuses = new ArrayList<PeriodStatus>(periodStatus);
	}
	
	//getter:
	public Plane getPlane() {
		return new Plane(this.model, this.airForce, new ArrayList<PeriodStatus>(this.periodStatuses));
	}
	
	//getters needed here! - to extract each value from plane, for insertion to DB

	@Override
	public String toString() {
		return "Plane [model=" + model + ", airForce=" + airForce + ", periodStatuses=" + periodStatuses + "]";
	}
	
}
