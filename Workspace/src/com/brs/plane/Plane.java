package com.brs.plane;


public final class Plane {
	
	private final Model model; //model of plane
	private Status status; //status of plane
	
	public Plane(Model model, Status status) {
		this.model = model; //set model
		this.status = status; //set status
	}
	
	//update status of plane:
	public void updateStatus(Status status) { this.status = status; }
	
	@Override
	public String toString() { return "Plane: [model=" + model + ", status=" + status + "]"; }
	
}
