package com.brs;

public class Pairing {
	
	private final String arg1;
	private final String arg2;
	
	public Pairing(String arg1, String arg2) {
		super();
		this.arg1 = arg1;
		this.arg2 = arg2;
	}

	@Override
	public String toString() {
		return "[" + arg1 + " " + arg2 + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arg1 == null) ? 0 : arg1.hashCode());
		result = prime * result + ((arg2 == null) ? 0 : arg2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Pairing))
			return false;
		Pairing other = (Pairing) obj;
		if (arg1 == null) {
			if (other.arg1 != null)
				return false;
		} else if (!arg1.equals(other.arg1))
			return false;
		if (arg2 == null) {
			if (other.arg2 != null)
				return false;
		} else if (!arg2.equals(other.arg2))
			return false;
		return true;
	}
	
	
	

	
	
	

}
