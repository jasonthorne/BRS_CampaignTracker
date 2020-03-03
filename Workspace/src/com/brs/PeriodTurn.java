package com.brs;

import com.brs.month.Month;
import com.brs.period.Period;

public class PeriodTurn {
	
	private final Period period;
	private final Month month;
	 
	public PeriodTurn(Period period, Month month) {
		this.period = period;
		this.month = month;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		result = prime * result + ((period == null) ? 0 : period.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PeriodTurn))
			return false;
		PeriodTurn other = (PeriodTurn) obj;
		if (month != other.month)
			return false;
		if (period == null) {
			if (other.period != null)
				return false;
		} else if (!period.equals(other.period))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PeriodTurn [period=" + period + ", month=" + month + "]";
	}


}
