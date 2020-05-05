package com.brs;

import com.brs.periods.Period;
import com.brs.statuses.Status;

public class PeriodStatus {
	
	private final Period period; //eg: 'Early 1940' 
	private final Status status; //eg: 'Limit' 
	
	//constructor:
	public PeriodStatus(Period period, Status status){ 
		this.period = period;
		this.status = status;
	}
	
	//getter:
	public PeriodStatus getPeriodStatus() {
		return new PeriodStatus(this.period, this.status); 
	}

	@Override
	public String toString() {
		return "PeriodStatus [period=" + period + ", status=" + status + "]";
	}

	

	
	
	
	
}
