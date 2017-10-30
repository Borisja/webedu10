package view;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import controller.AddHoursController;
import dao.AdministratorDAO;
import dao.ProjectDAO;
import dao.SprintDAO;
import dao.UserStoryDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import model.EmployeeModel;
import model.ProjectModel;
import model.SprintModel;
import model.UserStoryModel;

/**
 * Deze klasse is bestemd voor invullen entries.
 * @author rezanaser
 *
 */

public class AddEntryViewController implements Initializable {
	
	@FXML ComboBox<ProjectModel> projectCombo;
	@FXML ComboBox<UserStoryModel> userStorysCombo;
	@FXML ComboBox<SprintModel> sprintCombo;
	@FXML Button btnAddEntry;
	@FXML Pane pane;
	@FXML Button addEntryButton;
	private EntryController entryController;
	
	public void showAddEntryView()
	{
		this.pane.setVisible(true);
	}
	
	
	/**
	 * Deze methode krijgt huidige AddHoursController van de GebruikerViewController mee 
	 * @param ac -> is van type AddHoursController
	 * @author rezanaser
	 */
	public void setController(EntryController ac)
	{
		this.entryController = ac;
	}
	
	/**
	 * Deze methode toont het volgende scherm(AddHours) nadat er geklikt wordt op toevoegen knop
	 * @author rezanaser
	 */
	public void showAddHoursView()
	{
		this.pane.setVisible(false);
		this.entryController.showView();
	}
	@FXML TextField entryStartTime;
	@FXML TextField entryEndTime;
	@FXML TextField entryDescription;
	@FXML DatePicker entryDate;
	
	private AdministratorDAO adminDao = new AdministratorDAO();
	EmployeeModel currentEmployee;
	
	/**
	 * Fill the sprint box when project box onaction is called
	 * @param p_id - project id from the selected model
	 */
	public void fillSprintsBox(int p_id)
	{
		ArrayList<SprintModel> sprint_list = new SprintDAO().sprintsProjects(p_id);
		
		ObservableList<SprintModel> data = FXCollections.observableArrayList();
		
		sprint_list.forEach(e -> data.add(e));

		/**
		 * Change what you see in the combobox, to the omschrijving rather then the object address.
		 * Must study this to understand. works for now.
		 */
		Callback<ListView<SprintModel>, ListCell<SprintModel>> factory = lv -> new ListCell<SprintModel>() {
		    @Override
		    protected void updateItem(SprintModel item, boolean empty) {
		        super.updateItem(item, empty);
		        setText(empty ? "" :item.getSprintName());
		    }

		};
		
		this.sprintCombo.setItems(data);
		this.sprintCombo.setCellFactory(factory);
		this.sprintCombo.setButtonCell(factory.call(null));
		
		this.sprintCombo.setOnAction(e-> {
		});
	}
	
	/**
	 * Fill the user story box when project box onaction is called
	 * @param p_id - project id from the selected model
	 */	
	public void fillUserStoriesBox(int p_id)
	{	
		ArrayList<UserStoryModel> pList = new UserStoryDAO().userstoriesProjects(p_id);
		
		ObservableList<UserStoryModel> data;
		
		data = FXCollections.observableArrayList();
		
		pList.forEach(e -> data.add(e));

		/**
		 * Change what you see in the combobox, to the omschrijving rather then the object address.
		 * Must study this to understand. works for now.
		 */
		Callback<ListView<UserStoryModel>, ListCell<UserStoryModel>> factory = lv -> new ListCell<UserStoryModel>() {
		    @Override
		    protected void updateItem(UserStoryModel item, boolean empty) {
		        super.updateItem(item, empty);
		        setText(empty ? "" :item.getUserStoryName());
		    }

		};
		
		this.userStorysCombo.setItems(data);
		this.userStorysCombo.setCellFactory(factory);
		this.userStorysCombo.setButtonCell(factory.call(null));
	
		
	}
	
	/**
	 * Fill the project box, when item is selected load the rest.
	 */
	public void fillProjectsBox()
	{	
		ArrayList<ProjectModel> pList = new ProjectDAO().project_list();
		
		ObservableList<ProjectModel> data;
		
		data = FXCollections.observableArrayList();
		
		pList.forEach(e -> data.add(e));

		/**
		 * Change what you see in the combobox, to the omschrijving rather then the object address.
		 * Must study this to understand. works for now.
		 */
		Callback<ListView<ProjectModel>, ListCell<ProjectModel>> factory = lv -> new ListCell<ProjectModel>() {
		    @Override
		    protected void updateItem(ProjectModel item, boolean empty) {
		        super.updateItem(item, empty);
		        setText(empty ? "" :item.getProjectName());
		    }

		};
		
		this.projectCombo.setItems(data);
		this.projectCombo.setCellFactory(factory);
		this.projectCombo.setButtonCell(factory.call(null));
		
		this.projectCombo.setOnAction(e-> {
			this.fillSprintsBox(projectCombo.getSelectionModel().getSelectedItem().getProjectId());
			this.fillUserStoriesBox(projectCombo.getSelectionModel().getSelectedItem().getProjectId());

		});
	}
	/**
	 * Deze methode converteert de tijd van string naar time en localdate naar date en maakt een nieuwe entry
	 * in de tabel entry_version
	 * @throws ParseException
	 * @author rezanaser
	 */
	
	public void addEntryToDatabase() throws ParseException
	{
		Date date1 = Date.valueOf(entryDate.getValue());
		String startTime = entryStartTime.getText();
		String endTime = entryEndTime.getText();
		SimpleDateFormat formatStartTime = new SimpleDateFormat("hh:mm:ss");
		SimpleDateFormat formatEndTime = new SimpleDateFormat("hh:mm:ss");
	    java.util.Date d1 =(java.util.Date)formatStartTime.parse(startTime);
	    java.util.Date d2 =(java.util.Date)formatEndTime.parse(endTime);
	    java.sql.Time convertedStartTime = new java.sql.Time(d1.getTime());
	    java.sql.Time convertedEndTime = new java.sql.Time(d2.getTime());

		adminDao.addEntry(currentEmployee.getEmployeeId(), projectCombo.getSelectionModel().getSelectedItem().getProjectId(), 
				 sprintCombo.getSelectionModel().getSelectedItem().getSprintId()
				,date1, entryDescription.getText(), convertedStartTime, convertedEndTime,
				userStorysCombo.getSelectionModel().getSelectedItem().getUserStoryId());
		Alert showMessage = new Alert(AlertType.INFORMATION);
		showMessage.setContentText("Nieuwe entry is toegevoegd aan de database");
		showMessage.showAndWait();
	}

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.fillProjectsBox();
	}
	/**
	 * Deze methode opent de view van AddEntryView
	 * @author rezanaser
	 */
	public void showView() {
		this.pane.setVisible(true);

	}
	/**
	 * Deze methode sluit de view van AddEntryView als er op sluit button geklikt wordt
	 * @author rezanaser
	 */
	public void closeView()
	{
		this.pane.setVisible(false);
	}
	/**
	 * Deze methode krijgt het huidige employee model  van de UserViewController
	 * @param em - > het model van ingelogde employee
	 */
	public void setCurrentUser(EmployeeModel em) {
		this.currentEmployee =em;
		
	}

}
