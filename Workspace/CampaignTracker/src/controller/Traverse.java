package controller;

import java.util.Stack;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public abstract class Traverse {
	
	//buttons for traversal:
	@FXML private JFXButton backBtn;
	@FXML private JFXButton fwrdBtn;
	
	//label to show current position:
	@FXML private Label currPosLbl;
	
	//current position:
	private Traversable currPos; 
	
	//stacks holding moves taken:
	private Stack<Traversable>fwrdMoves = new Stack<Traversable>(); 
	private Stack<Traversable>bkwrdMoves = new Stack<Traversable>(); 

	//set buttons:
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
	
	/** UNSAFE SETTER???? */
	void setCurrPos(Traversable currPos) { this.currPos = currPos; }
		
	
	
	void setFrwdMoves(Traversable t){
		
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
