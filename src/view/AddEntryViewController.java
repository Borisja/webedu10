package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.ProjectDAO;
import dao.SprintDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
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
	@FXML ComboBox userStoryCombo;
	@FXML ComboBox<SprintModel> sprintCombo;
	@FXML Button addEntryButton;
	@FXML Pane pane;
	private EntryController entryController;
	
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
	
	/**
	 * Fill the sprint box when project box onaction is called
	 * @param p_id - project id from the selected model
	 */
	public void fillSprintsBox(int p_id)
	{
		ArrayList<SprintModel> sprint_list = new SprintDAO().sprintsProjects(p_id);
		
		ObservableList<SprintModel> data;
		
		data = FXCollections.observableArrayList();
		
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
			
			//Met de onderstaande regel stuurt de combobox het geselecteerde sprintmodel mee naar AddHoursController

			entryController.setChosenSprint(sprintCombo.getSelectionModel().getSelectedItem());
		});
	}
	
	/**
	 * Fill the user story box when project box onaction is called
	 * @param p_id - project id from the selected model
	 */	
	public void fillUserStoriesBox(int p_id)
	{	
//		ArrayList<UserStoryModel> pList = new ProjectDAO().project_list();
		
		ObservableList<UserStoryModel> data;
		
		data = FXCollections.observableArrayList();
		
//		pList.forEach(e -> data.add(e));

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
		
		this.userStoryCombo.setItems(data);
		this.userStoryCombo.setCellFactory(factory);
		this.userStoryCombo.setButtonCell(factory.call(null));
		
		this.userStoryCombo.setOnAction(e-> {
			//Met de onderstaande regel stuurt de combobox het geselecteerde userstorymodel mee naar AddHoursController
			
			entryController.setChosenUserStory((UserStoryModel) userStoryCombo.getSelectionModel().getSelectedItem());
		});
		
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

			//Met de onderstaande regel stuurt de combobox het geselecteerde projectmodel mee naar AddHoursController
			entryController.setChosenProject(projectCombo.getSelectionModel().getSelectedItem());
		});
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

}
