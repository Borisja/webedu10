package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dao.ProjectDAO;
import dao.SprintDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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

/**
 * Deze klasse is bestemd voor invullen entries.
 * @author rezanaser
 *
 */

public class AddEntryViewController {
	
	@FXML ComboBox projectCombo;
	@FXML ComboBox userStorysCombo;
	@FXML ComboBox sprintCombo;
	@FXML Button btnAddEntry;
	
	public void fillSprints(int nr)
	{
		ArrayList<SprintModel> pList = new SprintDAO().sprintsProjects(nr);
		ObservableList<SprintModel> data;
		ObservableList<String> dataNames;
		data = FXCollections.observableArrayList();
		dataNames = FXCollections.observableArrayList();
		pList.forEach(e -> data.add(e));
		pList.forEach(e -> dataNames.add(e.getSprintName()));
		this.sprintCombo.setItems(dataNames);
	}
	
	public void fillProjectsBox()
	{

		
		refresh();
		
		if(projectCombo.getSelectionModel().getSelectedItem().equals(1))
		{
			fillSprints(1);
		}
		
		
		
	}
	public void refresh()
	{
		ArrayList<ProjectModel> pList = new ProjectDAO().project_list();
		ObservableList<ProjectModel> data;
		ObservableList<Integer> dataNames;
		data = FXCollections.observableArrayList();
		dataNames = FXCollections.observableArrayList();
		pList.forEach(e -> data.add(e));
		pList.forEach(e -> dataNames.add(e.getProjectId()));
		this.projectCombo.setItems(dataNames);
	}
}
