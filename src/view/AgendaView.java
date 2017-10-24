package view;

import dao.EmployeeDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.EmployeeModel;
import model.EntryModel;

public class AgendaView {
	/**
	 * Load a table with the entries that have been submitted.
	 * Allow user to submit new entries.
	 */
	private ObservableList<EntryModel> data;
    private TableView<EntryModel> table = new TableView<EntryModel>();
    private Button btn_edit, btn_submit;
    private Stage agenda_stage;
    private GridPane grid;
    private VBox btn_box;
	
    /**
	 * Show table containing entries from user that is currently logged in.
	 * @param em - employee model to use throughout the flow.
	 */
	@SuppressWarnings("unchecked")
	public void agendaShow(EmployeeModel em){
		agenda_stage = new Stage();
		grid = new GridPane();
		btn_box = new VBox();
		
		data = FXCollections.observableArrayList();
		
		//Columns
		TableColumn<EntryModel, String> EntryIdCol = new TableColumn<EntryModel, String>("Entry ID");
		EntryIdCol.setCellValueFactory(
	    		new PropertyValueFactory<EntryModel, String>("entryId"));
		EntryIdCol.setMinWidth(50);
		
		TableColumn<EntryModel, String> EntryProjectCol = new TableColumn<EntryModel, String>("Entry Project");
		EntryProjectCol.setCellValueFactory(
	    		new PropertyValueFactory<EntryModel, String>("entryProjectDescription"));
		EntryProjectCol.setMinWidth(150);
        
		TableColumn<EntryModel, String> EntrySprintCol = new TableColumn<EntryModel, String>("Entry Sprint");
		EntrySprintCol.setCellValueFactory(
	    		new PropertyValueFactory<EntryModel, String>("entrySprintDescription"));
		EntrySprintCol.setMinWidth(150);
        
		TableColumn<EntryModel, String> EntryNameCol = new TableColumn<EntryModel, String>("Entry Description");
		EntryNameCol.setCellValueFactory(
	    		new PropertyValueFactory<EntryModel, String>("entryDescription"));
		EntryNameCol.setMinWidth(150);
        
		TableColumn<EntryModel, String> EntryStartCol = new TableColumn<EntryModel, String>("Entry Start Time");
		EntryStartCol.setCellValueFactory(
	    		new PropertyValueFactory<EntryModel, String>("entryStartTime"));
		EntryStartCol.setMinWidth(100);
		
		TableColumn<EntryModel, String> EntryStopCol = new TableColumn<EntryModel, String>("Entry Stop Time");
		EntryStopCol.setCellValueFactory(
	    		new PropertyValueFactory<EntryModel, String>("entryEndTime"));
		EntryStopCol.setMinWidth(100);
		
		TableColumn<EntryModel, String> EntryStatusCol = new TableColumn<EntryModel, String>("Entry Current Status");
		EntryStatusCol.setCellValueFactory(
	    		new PropertyValueFactory<EntryModel, String>("entryStatus"));
		EntryStatusCol.setMinWidth(150);
        
//		table.setPrefSize(600, 600);
		EntryIdCol.prefWidthProperty().bind(table.widthProperty().divide(12));
		EntryProjectCol.prefWidthProperty().bind(table.widthProperty().divide(8));
		EntrySprintCol.prefWidthProperty().bind(table.widthProperty().divide(8));
		EntryNameCol.prefWidthProperty().bind(table.widthProperty().divide(7));
		EntryStartCol.prefWidthProperty().bind(table.widthProperty().divide(10));
		EntryStopCol.prefWidthProperty().bind(table.widthProperty().divide(10));
		EntryStatusCol.prefWidthProperty().bind(table.widthProperty().divide(7));
		
		//add columns to table
        table.getColumns().addAll(EntryIdCol, EntryProjectCol, EntrySprintCol,EntryNameCol, EntryStartCol, EntryStopCol, EntryStatusCol);
        
        //For each entry in entry_list add to data
        EmployeeDAO edao = new EmployeeDAO();
        edao.entry_list(em.getEmployeeId()).forEach(entry-> data.add(entry));
        
        //Set data into the table items.
        table.setItems(data);
        
        btn_edit = new Button("Edit Hours");
        btn_submit = new Button("Submit Hours");
        
        btn_box.getChildren().addAll(btn_edit, btn_submit);
        
        grid.add(table, 0, 0);
        grid.add(btn_box, 1, 0);

        btn_edit.setOnAction(e->{
        	EntryModel selected_entry = table.getSelectionModel().getSelectedItem();
        	new EntryHoursOverview().EntryOverviewShow(selected_entry);
        });
        
		Scene agenda_scene = new Scene(grid, 800, 300);
		agenda_stage.setScene(agenda_scene);
		agenda_stage.setMaximized(true);
		agenda_stage.show();
		
	}
}
