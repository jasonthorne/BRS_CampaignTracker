package controller;

import java.util.Stack;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;

public abstract class Traverse {
	
	//buttons for traversing :
	@FXML private JFXButton backBtn;
	@FXML private JFXButton fwrdBtn;
	
	//stacks holding moves taken:
	private Stack<Traversable>fwrdMoves = new Stack<Traversable>(); 
	private Stack<Traversable>bkwrdMoves = new Stack<Traversable>(); 
	
	//current position
	private Traversable currPos; 
	
	//set traversal buttons:
	void setTraverseBtns(JFXButton backBtn, JFXButton fwrdBtn) {
		this.backBtn = backBtn; 
		this.fwrdBtn = fwrdBtn;
		
		//set actions:
		backBtn.setOnAction(event -> moveBkwrd());
		fwrdBtn.setOnAction(event -> moveFwrd());
		
		//initially disable:
		///////backBtn.setDisable(true);
		///////fwrdBtn.setDisable(true);
	}
	
	void moveFwrd(Traversable targetPos) {
		
	}
	
	private void moveFwrd() {
		System.out.println("moveFwrd");
	}
	
	private void moveBkwrd() {
		System.out.println("moveBkwrd");
		
		
	}

}
