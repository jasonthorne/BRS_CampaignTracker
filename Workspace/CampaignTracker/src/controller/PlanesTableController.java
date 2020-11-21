package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

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
import model.Plane.Status;
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
    
    
    private final ObservableList<TEST> testData = FXCollections.observableArrayList();
    
    private final TableView<Plane> planesTable2 = new TableView<Plane>(); //table view for planes
    //observable list of planes:
    private final ObservableList<Plane> testObservPlanes = FXCollections.observableArrayList();
    //private final ObservableList<Plane> planes = FXCollections.observableArrayList();
    
   
	void test() {
    	
		testData.clear();
    	testData.addAll(new TEST(), new TEST());
    	planesTable.setItems(testData);
    	planesTable.getColumns().clear();
    	//-------------------
    	testObservPlanes.clear();
    	testObservPlanes.addAll(testPlanes);
    	planesTable2.setItems(testObservPlanes);
    	//--------------------
    	
    	TableColumn<TEST,String> aCol;
    	
    	aCol = new TableColumn<>("colum a name");
    	aCol.setCellValueFactory(
        	    new PropertyValueFactory<TEST,String>("a2")
        	);
    	planesTable.getColumns().add(aCol);
    	
    	//-------------------------
    	TableColumn<Plane,String> planeName;
    	planeName = new TableColumn<>("Plane");
    	planeName.setCellValueFactory(
        	    new PropertyValueFactory<Plane,String>("SSname"));
    	planesTable2.getColumns().add(planeName);
    	rootVB.getChildren().setAll(planesTable2);
    	//------------------------
    	
    	String testB2ColName = "colum b name";
    	String testB2 = "b2";
    	
    	aCol = new TableColumn<>(testB2ColName);
    	aCol.setCellValueFactory(
        	    new PropertyValueFactory<TEST,String>(testB2)
        	);
    	planesTable.getColumns().add(aCol);
    	
		////////rootVB.getChildren().setAll(planesTable);
		
		
		//------------------------------------------
    	/*planesTable2.getColumns().clear();
    	TableColumn<Plane,String> planeName;
    	planeName = new TableColumn<>("Plane");
    	planeName.setCellValueFactory(
        	    new PropertyValueFactory<Plane,String>("SSname"));
    	planesTable2.getColumns().add(planeName);
    	rootVB.getChildren().setAll(planesTable2);*/
    	//-----------------------------------
		
    	
    }
    
    
    
    
    
    //=========================================================
    
    ///////Period start; Period end;
	List<Plane>testPlanes;
    
  	private final Stage stage = new Stage(); //stage
    private final Scene scene; //scene
   
    //constructor:
	/////////////PlanesTableController(Period start, Period end) {
	PlanesTableController(List<Plane>planes) {

		//add root to scene:
		scene = new Scene(Rootable.getRoot(this, "/view/planesTable.fxml")); 
    	stage.setScene(scene); //add scene to stage	
    	
    	
    	//make observable list of planes:
		//this.planes = FXCollections.observableArrayList(planes);
    	/////testObservPlanes.addAll(planes);
		////////planesTable2.setItems(testObservPlanes);
		///////////System.out.println(this.planes);
		
		
		testPlanes = new ArrayList<Plane>(planes);
		test();
    			
    	//setTableCols2();
    }
	
	private void setPeriods() {} //??????????????????????????????
		
	
	/*
	private void setTableCols2() {
		
		//get list of periods covered, from a plane's availabilities keySet:
		List<Period>periods =  new ArrayList<Period>(
				new TreeMap<Period, Status>( //TreeMap orders keySet by period's compareTo
						planes.get(0).getPlaneAvailabilities()).keySet());
		
		Period start =  periods.get(0); //get start period
		Period end =  periods.get(periods.size()-1); //get end period
		
		int currYear = start.getYear(); //holds year values
		Block currBlock; //holds block values
    	Iterator<Block>blocksIterator; //blocks iterator
    	boolean canAdd = false; //flag for adding values
    	
    	//------------------------------------------
    	planesTable2.getColumns().clear();
    	TableColumn<Plane,String> planeName;
    	planeName = new TableColumn<>("Plane");
    	planeName.setCellValueFactory(
        	    new PropertyValueFactory<Plane,String>("SSname"));
    	planesTable2.getColumns().add(planeName);
    	rootVB.getChildren().setAll(planesTable2);
    	//-----------------------------------
		
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
    	}*/
    	
    	
    	/*
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
    	
    	
    	
		
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	    //show stage:
    void showStage() { stage.showAndWait(); }
    
}
