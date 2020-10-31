package controller;

import com.jfoenix.controls.JFXListCell;

import model.Event;

public class EventCellController extends JFXListCell<Event>{
	
	private final SelectEventController selectEventController; //?????????? needed????
	
	//constructor:
	EventCellController(SelectEventController selectEventController){
		this.selectEventController = selectEventController; //assign selectEvent controller
	}

}
