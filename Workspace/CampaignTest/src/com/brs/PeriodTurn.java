package com.brs;

import com.brs.month.Month;
import com.brs.period.Period;

public class PeriodTurn {
	
	private final Period period;
	private final Integer turn;
	 
	public PeriodTurn(Period period, Integer turn) {
		this.period = period;
		this.turn = turn;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((period == null) ? 0 : period.hashCode());
		result = prime * result + ((turn == null) ? 0 : turn.hashCode());
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
		if (period == null) {
			if (other.period != null)
				return false;
		} else if (!period.equals(other.period))
			return false;
		if (turn == null) {
			if (other.turn != null)
				return false;
		} else if (!turn.equals(other.turn))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "PeriodTurn [period=" + period + ", turn=" + turn + "]";
	}


}
