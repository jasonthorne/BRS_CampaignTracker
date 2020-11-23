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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
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
    	
    }
    
    ///public static final String Column1MapKey = "A";
    
    public static final Period PeriodKey = new Period(Block.EARLY, 0);
    
    
    private final ObservableList<Plane> planes = FXCollections.observableArrayList(); //observable planes
    private final TableView<Plane> planesTable = new TableView<Plane>(); //table view for planes
  
 
    /////TableColumn<Plane,String> blockCol = null;
    ////////TableColumn<Plane,String> yearCol = null;
    ///////Block currBlock; //holds block values
    /////////int currYear; //holds year values
    //=========================================================
    
  
    
  	private final Stage stage = new Stage(); //stage
    private final Scene scene; //scene
   
    //constructor:
	PlanesTableController(List<Plane>planes) {
		//add root to scene:
		scene = new Scene(Rootable.getRoot(this, "/view/planesTable.fxml")); 
    	stage.setScene(scene); //add scene to stage	
    	
    	this.planes.addAll(planes);
    	planesTable.setItems(this.planes);
    	
		System.out.println("planes: " + planes);
		
		
    	setTable();
    }
	
	private void setPeriods() {} //??????????????????????????????
		
	
	
	private void setTable() {
		
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
    	
    	TableColumn<Plane,String> planeCol = new TableColumn<>("Plane");
    	planeCol.setCellValueFactory(
        	    new PropertyValueFactory<Plane,String>("name"));
    	planesTable.getColumns().add(planeCol);
    	
    	TableColumn<Plane,String> yearCol = null;
    	TableColumn<Plane,String> blockCol = null;
    	
    	
    	Callback<TableColumn.CellDataFeatures<Plane, String>, ObservableValue<String>> callBack = 
                new Callback<TableColumn.CellDataFeatures<Plane, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Plane, String> param) {
            	 return new SimpleStringProperty(
            			 param.getValue().getPlaneAvailabilities().get(
            					 param.getTableColumn().getUserData()).toString());		
            }
        };
    	
    	
    	
		/*map.containsKey(
        "ass"+Integer.toString((int)param.getTableColumn().getUserData()))
        ? new SimpleStringProperty(String.format("%.1f",100d*param.getValue().map.get(
            "ass"+Integer.toString((int)param.getTableColumn().getUserData()))))
        :new SimpleStringProperty("");*/
    	
    	

    	/*
    	blockCol.setCellValueFactory(Plane -> {
    		
	    	   return Plane.getValue().getAvailability(new Period(currBlock, currYear));
	    	});
    	*/
    	

    	
    	
    	/*
    	lastNameCol.setCellValueFactory(new Callback<CellDataFeatures<Person, String>, ObservableValue<String>>() {
    	     public ObservableValue<String> call(CellDataFeatures<Person, String> p) {
    	         // p.getValue() returns the Person instance for a particular TableView row
    	         return p.getValue().lastNameProperty();
    	     }
    	  });
    	 }*/
    	
    	/*
    	blockCol.setCellValueFactory(new Callback<CellDataFeatures<Plane, String>, ObservableValue<String>>() {
   	     public ObservableValue<String> call(CellDataFeatures<Plane, String> p) {
   	         // p.getValue() returns the Person instance for a particular TableView row
   	         return p.getValue().getAvailability(new Period(currBlock, currYear));
   	     	}
   	  	});
   	 	*/
    	
    	
    	
    	//-----------------------------------
    	/////////System.out.println("testObservPlanes: " + testObservPlanes);
    	

    	/*blockCol.setCellValueFactory(new Callback<CellDataFeatures<Plane, String>, ObservableValue<String>>() {
	   	     public ObservableValue<String> call(CellDataFeatures<Plane, String> p) {
	   	         // p.getValue() returns the Person instance for a particular TableView row
	   	         return p.getValue().getStatus();//.getAvailability(currBlock, currYear);
  	     	}
  	  	});*/
    	
        //++++++++NOTES HERE: 
    	//https://stackoverflow.com/questions/21639108/javafx-tableview-objects-with-maps
    	
    	outerWhile:
    	while(currYear <= end.getYear()) { //loop through years
    		
    		yearCol = new TableColumn<>(String.valueOf(currYear)); //create year column
    		blocksIterator = Arrays.asList(Block.values()).iterator(); //(re)set blocks iterator
    		
    		while(blocksIterator.hasNext()) { //loop through blocks
    			currBlock = blocksIterator.next(); //advance to next block
    			
    			//if found start date, allow adding of columns:
    			if(currBlock.equals(start.getBlock()) && currYear == start.getYear()) {canAdd = true;}
    				
    			if(canAdd) {
        			//////blockCol = new TableColumn<>(String.valueOf(currBlock)); //create block column 
        	
    				/*TableColumn<Plane,String>*/ blockCol = new TableColumn<>(String.valueOf(currBlock)); //create block column 
    				
        			blockCol.setUserData(new Period(currBlock, currYear));
        			blockCol.setCellValueFactory(callBack);
        			
        			
        	
        		
    				for (int i=0; i<planes.size();i++) {
            			System.out.println(planes.get(i).getName() + " " 
            					+ new TreeMap<Period, Status>(planes.get(i).getPlaneAvailabilities()));
    				}
            		
    				System.out.println(" ");
        			
        			
        			
            		yearCol.getColumns().add(blockCol); //add block column to year column
            		
            		//if found end date:
    				if(currBlock.equals(end.getBlock()) && currYear == end.getYear()) {
    					planesTable.getColumns().add(yearCol); //add year column to table 
    					break outerWhile; //break from outer while
    				}
    			}
    		}
    		planesTable.getColumns().add(yearCol); //add year column to table
    		currYear++; //advance to next year
    	}
    	
    	//add table to root:
    	rootVB.getChildren().setAll(planesTable);
	}
	

	
	    //show stage:
    void showStage() { stage.showAndWait(); }
    
}
