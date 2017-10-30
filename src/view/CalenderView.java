package view;

import java.net.URL;
import java.util.ResourceBundle;

import dao.AdministratorDAO;
import dao.EmployeeDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.EmployeeModel;
import model.EntryModel;

public class CalenderView implements Initializable{
	/**
	 * Load a table with the entries that have been submitted.
	 * Allow user to submit new entries.
	 */
	private ObservableList<EntryModel> data;
    @FXML private Button btn_edit, btn_submit;
	@FXML TableView<EntryModel> allEntries;
	@FXML TableColumn<EntryModel, String> EntryIdCol;
	@FXML TableColumn<EntryModel, String> EntryProjectCol;
	@FXML TableColumn<EntryModel, String> EntrySprintCol;
	@FXML TableColumn<EntryModel, String> EntryUserStoryCol;
	@FXML TableColumn<EntryModel, String> EntryNameCol;
	@FXML TableColumn<EntryModel, String> EntryStartCol;
	@FXML TableColumn<EntryModel, String> EntryStopCol;
	@FXML TableColumn<EntryModel, String> EntryStatusCol;
	@FXML Pane pane;
	
    /**
	 * Show table containing entries from user that is currently logged in.
	 * @param em - employee model to use throughout the flow.
	 */
	@SuppressWarnings("unchecked")
	public void fillCalender(EmployeeModel em){
		data = FXCollections.observableArrayList();
		//add columns to table
        //table.getColumns().addAll(EntryIdCol, EntryProjectCol, EntryUserStoryCol, EntrySprintCol,EntryNameCol, EntryStartCol, EntryStopCol, EntryStatusCol);
        
        //For each entry in entry_list add to data
        AdministratorDAO edao = new AdministratorDAO();
        edao.entry_queued_list(em.getEmployeeId()).forEach(entry-> data.add(entry));
		EntryIdCol.setCellValueFactory(
	    		new PropertyValueFactory<EntryModel, String>("entryId"));EntryProjectCol.setCellValueFactory(
	    		new PropertyValueFactory<EntryModel, String>("entryProjectDescription"));
        EntrySprintCol.setCellValueFactory(
	    		new PropertyValueFactory<EntryModel, String>("entrySprintDescription"));
		EntryUserStoryCol.setCellValueFactory(
	    		new PropertyValueFactory<EntryModel, String>("entryUserStoryDescription"));
        EntryNameCol.setCellValueFactory(
	    		new PropertyValueFactory<EntryModel, String>("entryDescription"));
        EntryStartCol.setCellValueFactory(
	    		new PropertyValueFactory<EntryModel, String>("entryStartTime"));
		EntryStopCol.setCellValueFactory(
	    		new PropertyValueFactory<EntryModel, String>("entryEndTime"));
		EntryStatusCol.setCellValueFactory(
	    		new PropertyValueFactory<EntryModel, String>("entryStatus"));
        //Set data into the table items.
		allEntries.setItems(data);

        
		
	}
	
	public void closeView()
	{
		this.pane.setVisible(false);
	}
	public void showCalenderView()
	{
		this.pane.setVisible(true);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		
	}
}
