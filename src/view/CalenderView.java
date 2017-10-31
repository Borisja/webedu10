package view;

import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import dao.AdministratorDAO;
import dao.EmployeeDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.EmployeeModel;
import model.EntryModel;
import model.ProjectModel;

public class CalenderView implements Initializable{
	/**
	 * Load a table with the entries that have been submitted.
	 * Allow user to submit new entries.
	 */
	private ObservableList<EntryModel> data;
	private @FXML TableView<EntryModel> allEntries;
	private @FXML TableColumn<EntryModel, String> EntryIdCol;
	private @FXML TableColumn<EntryModel, Integer> EntryProjectCol;
	private @FXML TableColumn<EntryModel, Integer> EntrySprintCol;
	private @FXML TableColumn<EntryModel, Integer> EntryUserStoryCol;
	private @FXML TableColumn<EntryModel, String> EntryNameCol;
	private @FXML TableColumn<EntryModel, String> EntryStartCol;
	private @FXML TableColumn<EntryModel, String> EntryStopCol;
	private @FXML TableColumn<EntryModel, String> EntryStatusCol;
	private @FXML TableColumn<EntryModel, String> EntryDateCol;
	private @FXML Pane pane;
	private @FXML Pane entryChangePane;
	private @FXML TextField txtEntryStartTime;
	private @FXML TextField txtEntryEndTime;
	private @FXML Label lblId;
	private AdministratorDAO adminDao = new AdministratorDAO();
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
	    		new PropertyValueFactory<EntryModel, String>("entryId"));
		EntryProjectCol.setCellValueFactory(
	    		new PropertyValueFactory<EntryModel, Integer>("entryProjectFk"));
        EntrySprintCol.setCellValueFactory(
	    		new PropertyValueFactory<EntryModel, Integer>("entrySprintFk"));
		EntryUserStoryCol.setCellValueFactory(
	    		new PropertyValueFactory<EntryModel, Integer>("entryUserstoryFk"));
        EntryNameCol.setCellValueFactory(
	    		new PropertyValueFactory<EntryModel, String>("entryDescription"));
        EntryStartCol.setCellValueFactory(
	    		new PropertyValueFactory<EntryModel, String>("entryStartTime"));
		EntryStopCol.setCellValueFactory(
	    		new PropertyValueFactory<EntryModel, String>("entryEndTime"));
		EntryStatusCol.setCellValueFactory(
	    		new PropertyValueFactory<EntryModel, String>("entryIsLocked"));
		EntryDateCol.setCellValueFactory(
	    		new PropertyValueFactory<EntryModel, String>("entryDate"));
        //Set data into the table items.
		allEntries.setItems(data);

        
		
	}
	public void showModifyEntry()
	{
		EntryModel selectedItem = allEntries.getSelectionModel().getSelectedItem();
		lblId.setText(String.valueOf(selectedItem.getEntryId()));
		txtEntryStartTime.setText(selectedItem.getEntryStartTime());
		txtEntryEndTime.setText(selectedItem.getEntryEndTime());
		this.entryChangePane.setVisible(true);
	}
	
	public void modifyEntry() throws ParseException
	{
		EntryModel selectedItem = allEntries.getSelectionModel().getSelectedItem();
		Date date1 = Date.valueOf(selectedItem.getEntryDate());
		String startTime = txtEntryStartTime.getText();
		String endTime = txtEntryEndTime.getText();
		SimpleDateFormat formatStartTime = new SimpleDateFormat("hh:mm:ss");
		SimpleDateFormat formatEndTime = new SimpleDateFormat("hh:mm:ss");
	    java.util.Date d1 =(java.util.Date)formatStartTime.parse(startTime);
	    java.util.Date d2 =(java.util.Date)formatEndTime.parse(endTime);
	    java.sql.Time convertedStartTime = new java.sql.Time(d1.getTime());
	    java.sql.Time convertedEndTime = new java.sql.Time(d2.getTime());
	    
	    int projectId = 0;
	    int sprintId = 0;
	    int userId = 0;
	    //De volgende try-catchs checken of er wel iets geselectreed is. Als niets geselecteerd dan wordt de id 0.
	    //Dit wordt nog een een gecheckt in AdministratorDAO
	    try{
	    	
	    	projectId = selectedItem.getEntryProjectFk();
	    	
	    }catch(NullPointerException e)
	    {
	    	projectId = 0;
	    }
	    
	    try{
	    	
	    	sprintId = selectedItem.getEntrySprintFk();
	    	
	    }catch(NullPointerException e)
	    {
	    	sprintId = 0;
	    }
	    
	    try{
	    	userId = selectedItem.getEntryUserstoryFk();
	    }catch(NullPointerException e)
	    {
	    	userId = 0;
	    }
		adminDao.modifyEntry(selectedItem.getEntryId(), projectId, 
				sprintId, date1, 
				selectedItem.getEntryDescription(), convertedStartTime, convertedEndTime, userId);
	}
	public void hideModifyEntry()
	{
		this.entryChangePane.setVisible(false);
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
