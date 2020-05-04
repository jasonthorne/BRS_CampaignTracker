package com.brs;

import com.brs.periods.Period;
import com.brs.plane_statuses.PlaneStatus;

public class PeriodStatus {
	
	private final Period period; //eg: 'Early 1940' 
	private final PlaneStatus planeStatus; //eg: 'Limit' 
	
	//constructor:
	public PeriodStatus(Period period, PlaneStatus planeStatus){ 
		this.period = period;
		this.planeStatus = planeStatus;
	}
	
	//getter:
	public PeriodStatus getPeriodStatus() {
		return new PeriodStatus(this.period, this.planeStatus); 
	}

	@Override
	public String toString() {
		return "PeriodStatus [period=" + period + ", planeStatus=" + planeStatus + "]";
	}

	

	
	
	
	
}
