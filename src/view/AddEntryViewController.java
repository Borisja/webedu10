package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.util.StringConverter;
import model.EntryModel;
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
	@FXML ComboBox userStorysCombo;
	@FXML ComboBox<SprintModel> sprintCombo;
	@FXML Button btnAddEntry;
	
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
		});
	}

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.fillProjectsBox();
	}

}
