package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dao.ProjectDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.EntryModel;
import model.ProjectModel;
/**
 * Deze klasse is gemaakt om projecten te kunnen beheren
 * @author rezanaser
 *
 */
public class ProjectManagementViewController implements Initializable{
	
	@FXML TableView<ProjectModel> tableViewProjects;
	@FXML TableColumn<ProjectModel, Integer> id;
	@FXML TableColumn<ProjectModel, String> projectName;
	@FXML TableColumn<ProjectModel, String> projectDes;
	@FXML TableColumn<ProjectModel, String> projectIsdeleted;
	@FXML Pane pane;
	@FXML Pane popUp;
	@FXML Pane popUpAdd;
	@FXML Label lblProjectId;
	@FXML TextField projectChangeName;
	@FXML TextField projectDescription;
	@FXML TextField projectNewName;
	@FXML TextField projectNewDescription;
	@FXML Button changeProject;
	
	private ObservableList<ProjectModel> allProjects = FXCollections.observableArrayList(); 
	private ProjectDAO projectDAO = new ProjectDAO();
	
	/**
	 * Wanneer deze methode voegt een nieuwe project toe
	 * @author rezanaser
	 * @throws IOException 
	 */
	public void addProject() 
	{
		projectDAO.addProject(projectNewName.getText(), projectNewDescription.getText());
		refreshTable();
	}
	
	/**
	 * Wanneer deze methode aangeroepen wordt kan je het project wijzigen
	 * @author rezanaser
	 * @throws IOException 
	 */
	public void modifyProject() 
	{
		int projectID = Integer.parseInt(lblProjectId.getText());
		projectDAO.modifyProject(projectID, projectChangeName.getText(), projectDescription.getText());
		refreshTable();
	}
	
	
	/**
	 * Wanneer deze methode aangeroepen wordt kan je het project wijzigen
	 * @author rezanaser
	 * @throws IOException 
	 */
	public void showPopUpChange() 
	{
		ProjectModel selectedItem = tableViewProjects.getSelectionModel().getSelectedItem();
		lblProjectId.setText(String.valueOf(selectedItem.getProjectId()));
		projectChangeName.setText(selectedItem.getProjectName());
		projectDescription.setText(selectedItem.getProjectDescription());
		popUp.setVisible(true);
		
	}
	
	/**
	 * Wanneer deze methode aangeroepen wordt kan je het project wijzigen
	 * @author rezanaser
	 * @throws IOException 
	 */
	public void showPopUpAdd() 
	{
		popUpAdd.setVisible(true);
		
	}
	/**
	 * Deze methode verwijdert het geselecteerde project
	 *@author rezanaser
	 */
	
	public void removeSelectedProject()
	{
		ProjectModel selected_item = tableViewProjects.getSelectionModel().getSelectedItem();
		projectDAO.removeProject(selected_item.getProjectId());
		refreshTable();
	}
	/**
	 * Deze methode word uitgevoerd wanneer er op refresh knop wordt gedrukt.
	 * Bijwerken lijst
	 * @author rezanaser
	 */
	
	public void refreshTable()
	{
		tableViewProjects.getItems().clear();
		id.setCellValueFactory(new PropertyValueFactory<ProjectModel, Integer>("projectId"));
		projectName.setCellValueFactory(new PropertyValueFactory<ProjectModel, String>("projectName"));
		projectDes.setCellValueFactory(new PropertyValueFactory<ProjectModel, String>("projectDescription"));
		projectIsdeleted.setCellValueFactory(new PropertyValueFactory<ProjectModel, String>("projectIsDeleted"));
		
		allProjects.addAll(projectDAO.project_list());
		tableViewProjects.setItems(allProjects);
		tableViewProjects.refresh();
	}
	
	/**
	 * Deze opent het projectenbeheren scherm wanneer er aangeroepen wordt
	 * @author rezanaser
	 */
	public void showView()
	{
		this.pane.setVisible(true);
	}
	
	/**
	 * Deze sluit het projectenbeheren scherm wanneer er aangeroepen wordt
	 * @author rezanaser
	 */
	public void closeView()
	{
		this.pane.setVisible(true);
	}
	
	/**
	 * Deze sluit het projectenbeheren scherm wanneer er aangeroepen wordt
	 * @author rezanaser
	 */
	public void closePopup()
	{
		this.popUp.setVisible(false);
	}
	public void closePopupAdd()
	{
		this.popUpAdd.setVisible(false);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		id.setCellValueFactory(new PropertyValueFactory<ProjectModel, Integer>("projectId"));
		projectName.setCellValueFactory(new PropertyValueFactory<ProjectModel, String>("projectName"));
		projectDes.setCellValueFactory(new PropertyValueFactory<ProjectModel, String>("projectDescription"));
		projectIsdeleted.setCellValueFactory(new PropertyValueFactory<ProjectModel, String>("projectIsDeleted"));
		
		allProjects.addAll(projectDAO.project_list());
		tableViewProjects.setItems(allProjects);
		
	}
	


}
