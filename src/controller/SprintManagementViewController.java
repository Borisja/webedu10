package controller;

import java.net.URL;
import java.util.ResourceBundle;

import dao.CustomerDAO;
import dao.ProjectDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.CustomerModel;
import model.ProjectModel;
import model.SprintModel;

public class SprintManagementViewController implements Initializable
{

	@FXML TableView<SprintModel> sprintTableView;
	@FXML TableColumn<SprintModel, Integer> sprintID;
	@FXML TableColumn<SprintModel, String> sprintName;
	@FXML TableColumn<SprintModel, String> sprintDescription;
	@FXML TableColumn<SprintModel, String> sprintIsDeleted;
	@FXML ComboBox<ProjectModel> projectComboBox;
	@FXML Pane pane;
	@FXML Pane popUp;
	@FXML Pane popUpAdd;
	@FXML Label projectIDLabel;
	@FXML TextField changeSprintNameTextField;
	@FXML TextField sprintDescriptionTextField;
	@FXML TextField newSprintNameTextField;
	@FXML TextField newSprintDescriptionTextField;
	@FXML Button changeSprintButton;
	@FXML Button addSprintButton;
	@FXML Button deleteSprintButton;
	@FXML Label warningLabel;
	@FXML Button button;
	
	private ObservableList<ProjectModel> allProjects = FXCollections.observableArrayList(); 
	private ProjectDAO projectDAO = new ProjectDAO();
	private CustomerDAO customerDAO = new CustomerDAO();
	
	
	@FXML public void addSprint()
	{
		
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
