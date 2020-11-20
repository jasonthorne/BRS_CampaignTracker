package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import animation.Fadeable;
import animation.Fadeable.FadeOption;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Event;
import model.Period;
import model.Period.Block;
import model.Plane;
import model.TEST;

public class PlanesTableController implements Rootable {
	
	@FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    
    //root fxml element & children:
    @FXML private VBox rootVB;
  

    @FXML
    void initialize() {
    	////////testLbl.setText(startPeriod.toString() + " " + endPeriod.toString());
    }
    
    //++++++++++++++++++++++++++++++++++++++
    
    //BUG - button needs disabled when table is opened! then enabled on close of table
    //+++++++++++++++++++++++++++++++++++++
    
    //=========================================================
    
    
    
    
    
    ///////////private final TableView<String> planesTable = new TableView<>(); //table view for planes
    private final TableView<TEST> planesTable = new TableView<TEST>(); //table view for planes
    private final List<TableColumn<String,String>>yearCols = new ArrayList<>(); //list of year columns
    
    TableColumn<String,String> planesColumn = new TableColumn<>("Planes");
    TableColumn<String,String> year1Column = new TableColumn<>("1941");
    TableColumn<String,String> year2Column = new TableColumn<>("1942");
   
    TableColumn<String,String> earlyColumn = new TableColumn<>("Early");
    TableColumn<String,String> midColumn = new TableColumn<>("Mid");
    TableColumn<String,String> lateColumn = new TableColumn<>("Late");
    
    private final List<Period>periods = new ArrayList<Period>();
    
    /*
    final ObservableList<TEST> testData = FXCollections.observableArrayList(
    		new TEST(),
    		new TEST()
    	);*/
    
    final ObservableList<TEST> testData = FXCollections.observableArrayList();
    
   
    
   
	void test() {
    	
    	/*year1Column.getColumns().setAll(earlyColumn, midColumn, lateColumn);
    	year2Column.getColumns().setAll(earlyColumn, midColumn, lateColumn);*/
    	
		testData.clear();
		
    	testData.addAll(new TEST(), new TEST());
    	
    	/*
    	TableColumn<TEST,String> aCol = new TableColumn<>("colum a name");
        TableColumn<TEST,String> bCol = new TableColumn<>("colum b name");
        
        aCol.setCellValueFactory(
        	    new PropertyValueFactory<TEST,String>("a2")
        	);
    	bCol.setCellValueFactory(
    	    new PropertyValueFactory<TEST,String>("b2")
    	);
    	
    
    	planesTable.setItems(testData);
    	planesTable.getColumns().setAll(aCol,bCol);
    	
    	*/
    	
    	planesTable.setItems(testData);
    	planesTable.getColumns().clear();
    	
    	TableColumn<TEST,String> aCol;
    	
    	aCol = new TableColumn<>("colum a name");
    	aCol.setCellValueFactory(
        	    new PropertyValueFactory<TEST,String>("a2")
        	);
    	planesTable.getColumns().add(aCol);
    	
    	String testB2ColName = "colum b name";
    	String testB2 = "b2";
    	
    	aCol = new TableColumn<>(testB2ColName);
    	aCol.setCellValueFactory(
        	    new PropertyValueFactory<TEST,String>(testB2)
        	);
    	planesTable.getColumns().add(aCol);
    	
		rootVB.getChildren().setAll(planesTable);
    	
    }
    
    
    
    
    
    //=========================================================
    
    private Period startPeriod; //start period
    private Period endPeriod; //end period
    
  	private final Stage stage = new Stage(); //stage
    private final Scene scene; //scene
   
    //constructor:
	/////////////PlanesTableController(Period start, Period end) {
	PlanesTableController() {
	
		///////startPeriod = start; //set start period
		///////endPeriod = end; //set end period
		System.out.println("huh???");
		////////setTableCols(start, end);
		
		//add root to scene:
		scene = new Scene(Rootable.getRoot(this, "/view/planesTable.fxml")); 
    	stage.setScene(scene); //add scene to stage	
    }
	
	//set range of periods for planes:
	void setPlanePeriods(Period start, Period end){
		startPeriod = start; //set start period
		endPeriod = end; //set end period
	}
	
	
	private void setTableCols(Period start, Period end) {
	
		int currYear = start.getYear(); //holds year values
		Block currBlock; //holds block values
    	Iterator<Block>blocksIterator; //blocks iterator
    	boolean canAdd = false; //flag for adding values
    	
    	outerWhile:
    	while(currYear <= end.getYear()) { //loop through years
    		blocksIterator = Arrays.asList(Block.values()).iterator(); //(re)set blocks iterator
    		
    		while(blocksIterator.hasNext()) { //loop through blocks
    			currBlock = blocksIterator.next(); //advance to next block
    			
    			//if found start date, allow adding of columns:
    			if(currBlock.equals(start.getBlock()) && currYear == start.getYear()) {canAdd = true;}
    				
    			if(canAdd) {
    				System.out.println(currBlock.toString() + " " + currYear);
    				
    				if(currBlock.equals(end.getBlock()) && currYear == end.getYear()) {break outerWhile;}
    			}
    		}
    		currYear++; //advance to next year
    	}
	}
	
    void showPlanes(List<Plane>planes){ //airForceName???????????????
    	
    	
    	System.out.println(startPeriod + " " + endPeriod);
    	
    	test(); //+++++++++++++++++
    	
    	//System.out.println(airForceName);
    	
    	//----------------------------
    	/*
    	int currYear = startPeriod.getYear();
    	int endYear = endPeriod.getYear();
    	Iterator<Block>blocksIterator; //blocks iterator
    	Block currBlock;
    	Block endBlock = endPeriod.getBlock();
    	boolean canAdd = false; //flag for adding values
    	
    	outerWhile:
    	while(currYear <= endYear) {
    	
    		//(re)set blocks iterator:
    		blocksIterator = Arrays.asList(Block.values()).iterator();
    		
    		while(blocksIterator.hasNext()) { //iterate through blocks
    			currBlock = blocksIterator.next(); //advance to next block
    			
    			if(currBlock.equals(startPeriod.getBlock()) && currYear == startPeriod.getYear()) {
    				canAdd = true;
    			}
    			
    			if(canAdd) {
    				System.out.println(currBlock.toString() + " " + currYear);
    				
    				if(currBlock.equals(endBlock) && currYear == endYear) { break outerWhile; }
    			}
    			
    		}
    	
    		
    		currYear++; //increment current year
    	}
    	*/

    	/*year1Column.getColumns().setAll(earlyColumn, midColumn, lateColumn);
    	year2Column.getColumns().setAll(earlyColumn, midColumn, lateColumn);
    	
    	planesTable.getColumns().setAll(planesColumn,year1Column, year2Column);
		rootVB.getChildren().setAll(planesTable);*/
		//-----------------------------
    	
    	showStage();
    }
    
    
    
    
  
    
    
	
    //show stage:
    void showStage() { stage.showAndWait(); }
    
        

}
