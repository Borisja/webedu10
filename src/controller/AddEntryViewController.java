package controller;

import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
	@FXML TextField entryStartTime;
	@FXML TextField entryEndTime;
	@FXML TextField entryDescription;
	@FXML DatePicker entryDate;
	private AdministratorDAO adminDao = new AdministratorDAO();
	EmployeeModel currentEmployee;
	
	public void showAddEntryView()
	{
		this.pane.setVisible(true);
	}
	
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
			try{
				this.fillUserStoriesBox(sprintCombo.getSelectionModel().getSelectedItem().getSprintId());
			}catch(NullPointerException er )
			{
				System.out.println(er.getMessage());
			}
			

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
			userStorysCombo.setItems(null);

		});
	}
	
	public void addEntryToDatabase() throws ParseException
	{
		try {
			Date date1 = Date.valueOf(entryDate.getValue());
			String startTime = entryStartTime.getText().trim();
			String endTime = entryEndTime.getText().trim();
			SimpleDateFormat formatStartTime = new SimpleDateFormat("hh:mm");
			SimpleDateFormat formatEndTime = new SimpleDateFormat("hh:mm");
			java.util.Date d1 = (java.util.Date) formatStartTime.parse(startTime);
			java.util.Date d2 = (java.util.Date) formatEndTime.parse(endTime);
			java.sql.Time convertedStartTime = new java.sql.Time(d1.getTime());
			java.sql.Time convertedEndTime = new java.sql.Time(d2.getTime());

			int projectId = 0;
			int sprintId = 0;
			int userId = 0;
			//De volgende try-catchs checken of er wel iets geselectreed is. Als niets geselecteerd dan wordt de id 0.
			//Dit wordt nog een een gecheckt in AdministratorDAO
			try {

				projectId = projectCombo.getSelectionModel().getSelectedItem().getProjectId();

			} catch (NullPointerException e) {
				projectId = 0;
			}

			try {

				sprintId = sprintCombo.getSelectionModel().getSelectedItem().getSprintId();

			} catch (NullPointerException e) {
				sprintId = 0;
			}

			try {
				userId = userStorysCombo.getSelectionModel().getSelectedItem().getUserStoryId();
			} catch (NullPointerException e) {
				userId = 0;
			}

			adminDao.addEntry(
					currentEmployee.getEmployeeId(),
					projectId,
					sprintId,
					date1,
					entryDescription.getText(),
					convertedStartTime,
					convertedEndTime,
					userId);
			Alert showMessage = new Alert(AlertType.INFORMATION);
			showMessage.setContentText("Nieuwe entry is toegevoegd aan de database");
			showMessage.showAndWait();
		} catch (ParseException ex){
			Alert parseErrorMessage = new Alert(AlertType.INFORMATION);
			parseErrorMessage.setContentText("Vul de begin en eind tijd op de volgende manier in: UU:MM ");
			parseErrorMessage.showAndWait();
		}
	}

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		this.fillProjectsBox();
	}
	/**
	 * Deze methode opent de view van AddEntryView
	 * @author rezanaser
	 */
	public void showView() {
		this.fillProjectsBox();
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
