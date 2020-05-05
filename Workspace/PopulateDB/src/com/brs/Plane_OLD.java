package com.brs;

import java.util.ArrayList;
import java.util.List;

import com.brs.airforces.AirForce;
import com.brs.planes.Plane;

public class Plane_OLD {
	
	private final Plane plane; //plane of plane
	private final AirForce airForce; //air force flown for
	private final List<PeriodStatus>periodStatuses;	//periods plane is available, and their corresponding statuses
	
	public Plane_OLD(Plane plane, AirForce airForce, List<PeriodStatus>periodStatus) {
		this.plane = plane; //set plane
		this.airForce = airForce;
		this.periodStatuses = new ArrayList<PeriodStatus>(periodStatus);
	}
	
	//getter:
	public Plane_OLD getPlane() {
		return new Plane_OLD(this.plane, this.airForce, new ArrayList<PeriodStatus>(this.periodStatuses));
	}
	
	//getters needed here! - to extract each value from plane, for insertion to DB

	@Override
	public String toString() {
		return "Plane_OLD [plane=" + plane + ", airForce=" + airForce + ", periodStatuses=" + periodStatuses + "]";
	}
	
}
