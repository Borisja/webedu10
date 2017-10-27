package controller;

import java.net.URL;
import java.util.ResourceBundle;

import dao.ProjectDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
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
	@FXML Button addProject;
	@FXML Button removeProject;
	@FXML Button modifyProject;
	@FXML Button close;
	@FXML Pane pane;
	
	private ObservableList<ProjectModel> allProjects = FXCollections.observableArrayList(); 
	private ProjectDAO projectDAO = new ProjectDAO();
	
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
