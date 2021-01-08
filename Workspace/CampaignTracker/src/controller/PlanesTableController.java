package controller;

import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import model.Period;
import model.Period.Block;
import model.Plane;
import model.Plane.Status;

public class PlanesTableController implements Rootable {
	
	@FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    
    //root fxml element:
    @FXML private VBox rootVB;
    
    @FXML
    void initialize() { }
    	
    private final ObservableList<Plane> observPlanes = FXCollections.observableArrayList(); //observable planes
    private final TableView<Plane> planesTable = new TableView<Plane>(); //table view for planes
    
  	private final Stage stage = new Stage(); //stage
    private final Scene scene = new Scene(Rootable.getRoot(this, "/view/planesTable.fxml")); //rooted scene
    
    //constructor:
	PlanesTableController(List<Plane>planes, String airForceName) {
		setStage(airForceName); //set stage
		setPlanesTable(planes); //set planes table 
    	buildTable(); //build table
    }
	
	
	//set stage:
	private void setStage(String airForceName) {
		
		stage.setTitle(airForceName + " Planes"); //set title
		stage.setScene(scene); //add scene to stage	
		
		//set close event:
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			/** https://stackoverflow.com/questions/22576261/how-do-i-get-the-close-event-of-a-stage-in-javafx */
            @Override
            public void handle(WindowEvent event) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                    	System.out.println("Stage is closing"); 
                    }
                });
            }
        });
		
	}
	
	
	private void setPlanesTable(List<Plane>planes) {
		
		observPlanes.addAll(planes); //add planes to observable list
		planesTable.setItems(observPlanes); //add observable list to table
    	
    	//-------------------
    	//https://stackoverflow.com/questions/27945817/javafx-adapt-tableview-height-to-number-of-rows
    	planesTable.setFixedCellSize(25);
    	planesTable.prefHeightProperty().bind(planesTable.fixedCellSizeProperty().multiply(Bindings.size(planesTable.getItems()).add(2.01)));
    	// - not needed (for now!)- planesTable.minHeightProperty().bind(planesTable.prefHeightProperty());
    	//- not needed (for now!)- planesTable.maxHeightProperty().bind(planesTable.prefHeightProperty());
    	
    	/* https://stackoverflow.com/questions/28428280/how-to-set-column-width-in-tableview-in-javafx */
    	planesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); //+++++++++NOT QUITE FIXED. SEE barbarossa soviet planes! 
    	
    	//https://stackoverflow.com/questions/14650787/javafx-column-in-tableview-auto-fit-size/38686402
    	/*planesTable.setColumnResizePolicy(new Callback<TableView.ResizeFeatures, Boolean>() {
    		  @Override
    		  public Boolean call(ResizeFeatures p) {
    		     return true;
    		  }
    	
    	});*/
    	//----------------------
    	//planesTable.setStyle("-fx-table-cell-border-color: transparent;");
		
	}
	
	private void buildTable() {
		
		//TreeMap of a plane's availabilities, sorted by period compareTo:
		TreeMap<Period,Status> sortedAvails = new TreeMap<Period,Status>( 
				observPlanes.get(0).getPlaneAvailabilities());
		
		Period start = sortedAvails.firstKey(); //get start period
		Period end = sortedAvails.lastKey(); //get end period
		
    	//create plane column:
    	TableColumn<Plane,String> planeCol = new TableColumn<>("Plane");
    	
    	//set cell factory:
    	planeCol.setCellValueFactory(
    			new PropertyValueFactory<Plane,String>("name"));
        	    
    	//add plane column to table:
    	planesTable.getColumns().add(planeCol); 
    	
    	//year and block columns:
    	TableColumn<Plane,String> yearCol;
    	TableColumn<Plane,String> blockCol;
    	
    	//call back for populating block column cells with plane period availabilities:
    	Callback<TableColumn.CellDataFeatures<Plane, String>, ObservableValue<String>> callBack = 
                new Callback<TableColumn.CellDataFeatures<Plane, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Plane, String> param) {
            	 return new SimpleStringProperty(
            			 param.getValue().getPlaneAvailabilities().get(
            					 param.getTableColumn().getUserData()).toString());		
            }
        };/** https://stackoverflow.com/questions/21639108/javafx-tableview-objects-with-maps */
        
        
        int currYear = start.getYear(); //holds year values
		Block currBlock; //holds block values
    	Iterator<Block>blocksIterator; //blocks iterator
    	boolean canAdd = false; //flag for adding values
    	
    	outerWhile:
    	while(currYear <= end.getYear()) { //loop through years
    		
    		yearCol = new TableColumn<>(String.valueOf(currYear)); //create year column
    		blocksIterator = Arrays.asList(Block.values()).iterator(); //(re)set blocks iterator
    		
    		while(blocksIterator.hasNext()) { //loop through blocks
    			currBlock = blocksIterator.next(); //advance to next block
    			
    			//if found start date, allow adding of values:
    			if(currBlock.equals(start.getBlock()) && currYear == start.getYear()) {canAdd = true;}
    				
    			if(canAdd) {
    				blockCol = new TableColumn<>(String.valueOf(currBlock)); //create block column 
    				blockCol.setStyle( "-fx-alignment: CENTER;"); ///+++++++++HAVE THIS IN A CSS FILE FOR TABLE VIEWS ////https://stackoverflow.com/questions/13455326/javafx-tableview-text-alignment
        			blockCol.setUserData(new Period(currBlock, currYear)); //add period to block column
        			blockCol.setCellValueFactory(callBack); //set block column cell factory
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
    void showStage() { 
    	/**++++++++++++++++++++++++++++++++++++++OBV REMOVE ALL OF THIS FROM THIS PAGE :P */
    	/*
    	stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.out.println("Stage is closing");
            }
        }); */
    	//https://stackoverflow.com/questions/22576261/how-do-i-get-the-close-event-of-a-stage-in-javafx
    	
    	/*stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                    	System.out.println("Stage is closing"); 
                    }
                });
            }
        });*/
    	/**++++++++++++++++++++++++++++++++++++++++++++++*/
    	stage.showAndWait(); 
    }
    
}
